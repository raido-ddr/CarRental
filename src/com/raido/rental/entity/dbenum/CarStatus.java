package com.raido.rental.entity.dbenum;

/**
 * Created by Raido_DDR on 9/13/2014.
 */
public enum CarStatus {

    AVAILABLE,
    DAMAGED,
    DELETED;

    private String value = this.toString().toLowerCase();

    public String getValue() {
        return value;
    }
}
