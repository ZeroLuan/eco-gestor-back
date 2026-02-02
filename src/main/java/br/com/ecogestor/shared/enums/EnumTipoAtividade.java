package br.com.ecogestor.shared.enums;

import lombok.Getter;

@Getter
public enum EnumTipoAtividade {
    
    RESIDUO_CRIADO("RESIDUO_CRIADO", "Resíduo Criado", "coleta"),
    RESIDUO_EDITADO("RESIDUO_EDITADO", "Resíduo Editado", "coleta"),
    RESIDUO_REMOVIDO("RESIDUO_REMOVIDO", "Resíduo Removido", "coleta"),
    
    PONTO_CRIADO("PONTO_CRIADO", "Ponto de Coleta Criado", "ponto"),
    PONTO_EDITADO("PONTO_EDITADO", "Ponto de Coleta Editado", "ponto"),
    PONTO_REMOVIDO("PONTO_REMOVIDO", "Ponto de Coleta Removido", "ponto"),
    
    LICENCA_CRIADA("LICENCA_CRIADA", "Licença Ambiental Criada", "licenca"),
    LICENCA_EDITADA("LICENCA_EDITADA", "Licença Ambiental Editada", "licenca"),
    LICENCA_REMOVIDA("LICENCA_REMOVIDA", "Licença Ambiental Removida", "licenca"),
    
    COOPERATIVA_CRIADA("COOPERATIVA_CRIADA", "Cooperativa Criada", "cooperativa"),
    COOPERATIVA_EDITADA("COOPERATIVA_EDITADA", "Cooperativa Editada", "cooperativa"),
    COOPERATIVA_REMOVIDA("COOPERATIVA_REMOVIDA", "Cooperativa Removida", "cooperativa");
    
    private final String value;
    private final String label;
    private final String categoria;
    
    EnumTipoAtividade(String value, String label, String categoria) {
        this.value = value;
        this.label = label;
        this.categoria = categoria;
    }
}
