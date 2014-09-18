package com.raido.rental.entity.dbenum;

public enum TransmissionType {

    AUTOMATIC,
    MANUAL;

    public String getStatusValue() {
        return this.toString().toLowerCase();
    }
}
