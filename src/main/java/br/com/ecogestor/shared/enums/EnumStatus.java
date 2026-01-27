package br.com.ecogestor.shared.enums;

import lombok.Getter;

@Getter
public enum EnumStatus {

    ATIVA("ATIVA", "Ativa"),
    VENCIDA("VENCIDA", "Vencida"),
    PENDENTE("PENDENTE", "Pendente"),
    INATIVA("INATIVA", "Inativa");

    private final String value;
    private final String label;

    EnumStatus(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
