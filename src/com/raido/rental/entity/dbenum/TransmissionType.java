package com.raido.rental.entity.dbenum;

public enum TransmissionType {

    AUTOMATIC,
    MANUAL;

    public String getValue() {
        return this.toString().toLowerCase();
    }
}
