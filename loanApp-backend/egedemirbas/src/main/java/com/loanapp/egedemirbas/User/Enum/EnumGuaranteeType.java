package com.loanapp.egedemirbas.User.Enum;

public enum EnumGuaranteeType {

    NONE("NO_GUARANTEE"),
    GUARANTEED("GUARANTEED"),
    LAST("LAST");

    private String type;

    EnumGuaranteeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
