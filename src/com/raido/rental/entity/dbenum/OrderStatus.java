package com.raido.rental.entity.dbenum;


public enum OrderStatus {

    NEW,
    CONFIRMED,
    ACTIVE,
    REJECTED,
    DAMAGED,
    ARCHIVED,
    DELETED;

    private String value = this.toString().toLowerCase();

    public String getValue() {
        return value;
    }


}
