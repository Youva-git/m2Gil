package com.fullstack.backend.exception;

public enum ErrorCodes {
    BAD_CREDENTIALS(0001),
    BOUTIQUE_NOT_FOUND(1000),
    BOUTIQUE_NOT_VALIDE(1001),
    CATEGORIE_NOT_FOND(2000),
    PRODUIT_NOT_FOUND(3000),
    USER_NOT_FOUND(4000);
    private int code;
    ErrorCodes(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }
}
