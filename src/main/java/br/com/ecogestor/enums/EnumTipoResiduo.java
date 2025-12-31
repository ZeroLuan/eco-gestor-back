package br.com.ecogestor.enums;

import lombok.Getter;

@Getter
public enum EnumTipoResiduo {

    PLASTICO("PLASTICO", "Plástico"),
    PAPEL("PAPEL", "Papel"),
    VIDRO("VIDRO", "Vidro"),
    METAL("METAL", "Metal"),
    ORGANICO("ORGANICO", "Orgânico"),
    ELETRONICO("ELETRONICO", "Eletrônico"),
    MISTO("MISTO", "Misto");

    private final String value;
    private final String label;

    EnumTipoResiduo(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
