package br.com.ecogestor.login.dto.request;

public record CriarUsuarioRequest(String nomeCompleto, String email, String senha, String confirmarSenha) {

}
