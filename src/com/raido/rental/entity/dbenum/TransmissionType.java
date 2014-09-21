package com.raido.rental.entity.dbenum;

public enum TransmissionType {

    AUTOMATIC,
    MANUAL,
    DSG;

    private String value = this.toString().toLowerCase();

    public String getValue() {
        return value;
    }
}
