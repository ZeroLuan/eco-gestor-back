package br.com.ecogestor.login.dto.response;

/**
 * Response do endpoint de login
 * Retorna o token JWT e o tempo de expiração em segundos
 */
public record LoginResponse(String accessToken, Long expiresIn) {
}
