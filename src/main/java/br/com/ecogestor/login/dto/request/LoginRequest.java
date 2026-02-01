package br.com.ecogestor.login.dto.request;

/**
 * Request do endpoint de login
 * Contém as credenciais do usuário
 */
public record LoginRequest(String email, String senha) {

}
