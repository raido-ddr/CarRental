package com.raido.rental.entity.dbenum;

/**
 * Created by Raido_DDR on 9/13/2014.
 */
public enum CarStatus {

    AVAILABLE,
    DAMAGED,
    DELETED;

    public String getValue() {
        return this.toString().toLowerCase();
    }
}
