package br.com.ecogestor.login.service;

import br.com.ecogestor.login.dto.response.UsuarioResponse;
import br.com.ecogestor.login.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse buscarUsuarioLogado() {
        // Pega a autenticação do contexto de segurança
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado");
        }
        
        // O subject do JWT contém o ID do usuário
        String idUsuario = authentication.getName();
        
        // Busca o usuário no banco pelo ID
        var usuario = usuarioRepository.findById(UUID.fromString(idUsuario))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        
        return new UsuarioResponse(usuario.getNomeCompleto(), usuario.getEmail());
    }
}
