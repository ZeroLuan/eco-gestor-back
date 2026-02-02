package br.com.ecogestor.atividade.service;

import br.com.ecogestor.login.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AtividadeHelper {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String getUsuarioEmailLogado() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && 
                !authentication.getPrincipal().equals("anonymousUser")) {
                
                String idUsuario = authentication.getName();
                var usuario = usuarioRepository.findById(UUID.fromString(idUsuario));
                if (usuario.isPresent()) {
                    return usuario.get().getEmail();
                }
            }
        } catch (Exception e) {
            // Ignora erros
        }
        return "sistema@ecogestor.com";
    }
}
