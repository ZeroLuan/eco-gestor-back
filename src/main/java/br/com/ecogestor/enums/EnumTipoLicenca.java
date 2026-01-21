package br.com.ecogestor.enums;

import lombok.Getter;

@Getter
public enum EnumTipoLicenca {

    LP("LP", "Licença Prévia"),
    LI("LI", "Licença de Instalação"),
    L0("L0", "Licença de Operação");

    private final String value;
    private final String label;

    EnumTipoLicenca(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
