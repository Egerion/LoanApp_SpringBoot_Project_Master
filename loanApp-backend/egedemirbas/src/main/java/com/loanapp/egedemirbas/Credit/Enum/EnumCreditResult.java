package com.loanapp.egedemirbas.Credit.Enum;

public enum EnumCreditResult {

    CONFIRMED("CREDIT_CONFIRMED"),
    REJECTED("CREDIT_REJECTED"),
    LAST("LAST");

    private String type;

    EnumCreditResult(String type) {
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
