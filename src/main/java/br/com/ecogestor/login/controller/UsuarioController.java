package br.com.ecogestor.login.controller;

import br.com.ecogestor.login.dto.request.CriarUsuarioRequest;
import br.com.ecogestor.login.dto.response.UsuarioResponse;
import br.com.ecogestor.login.entity.Role;
import br.com.ecogestor.login.entity.Usuario;
import br.com.ecogestor.login.repository.RoleRepository;
import br.com.ecogestor.login.repository.UsuarioRepository;
import br.com.ecogestor.login.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api/usuario")
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(path = "/criar")
    @Transactional
    public ResponseEntity<Void> criarUsuario(@RequestBody CriarUsuarioRequest request) {

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());

        if (basicRole == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Role BASIC não encontrada no sistema!");
        }

        var usuarioNoBanco = usuarioRepository.procurarUsuarioPorEmail(request.email());
        if(usuarioNoBanco.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário com esse email já existe!");
        }

        if(!Objects.equals(request.senha(), request.confirmarSenha())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Senhas não conferem!");
        }

        var usuario = new Usuario();
        usuario.setNomeCompleto(request.nomeCompleto());
        usuario.setEmail(request.email());
        usuario.setSenha(bCryptPasswordEncoder.encode(request.senha()));
        usuario.setRole(Set.of(basicRole));

        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "buscar/usuario/logado")
    public ResponseEntity<UsuarioResponse> buscarUsuarioLogado(){
        log.info("Buscando usuário logado ->");
        return ResponseEntity.ok(usuarioService.buscarUsuarioLogado());
    }


}
