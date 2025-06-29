package com.rentalfast.app.domain.models;

public enum Payment {

    CASH(1), CARD(2);

    private final int value;

    private Payment(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
