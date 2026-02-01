package br.com.ecogestor.login.controller;

import br.com.ecogestor.login.dto.request.LoginRequest;
import br.com.ecogestor.login.dto.response.LoginResponse;
import br.com.ecogestor.login.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    private final UsuarioRepository usuarioRepository;

    private final JwtEncoder jwtEncoder;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(UsuarioRepository usuarioRepository,
                           JwtEncoder jwtEncoder,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtEncoder = jwtEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var usuario = usuarioRepository.procurarUsuarioPorEmail(loginRequest.email());

        // Verifica se o Usuario existe no banco e se a senha é valida.
        if(usuario.isEmpty() || !usuario.get().loginCorreto(loginRequest, bCryptPasswordEncoder)){
            throw new BadCredentialsException("Usuário ou Senha Inválidos!");
        }
        //Agora que temos a certeza que o login está correto, vamos gerar o token JWT.

        // seta o tempo valido do token para 300L = 5 Min
        var agora = Instant.now();
        var expiraEm = 1000L;

        // Gera o token JWT com as roles do usuário
        var scopes = usuario.get().getRole()
                .stream()
                .map(role -> role.getName())
                .toList();

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(usuario.get().getIdUsuario().toString())
                .claim("scope", scopes)
                .issuedAt(agora)
                .expiresAt(agora.plusSeconds(expiraEm))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return  ResponseEntity.ok(new LoginResponse(jwtValue, expiraEm));
    }

}
