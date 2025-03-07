package com.isika.projet3.SmartImplant.dto;

public class AuthResponseDTO {

    private String accestoken;
    private String tokenType = "Bearer";

    public AuthResponseDTO(String accestoken) {
        this.accestoken = accestoken;
    }

    public AuthResponseDTO() {
    }

    public String getAccestoken() {
        return accestoken;
    }

    public void setAccestoken(String accestoken) {
        this.accestoken = accestoken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

}
