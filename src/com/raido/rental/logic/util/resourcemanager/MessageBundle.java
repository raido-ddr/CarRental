package com.raido.rental.logic.util.resourcemanager;


import java.util.Locale;
import java.util.ResourceBundle;

public final class MessageBundle {

    private MessageBundle() {
    }

    public static String getString(String bundleName,
            String propertyName) {
        return ResourceBundle.getBundle(bundleName).getString(propertyName);
    }

    public static String getString(String bundleName,
            String propertyName, Locale locale) {

        return ResourceBundle.getBundle(bundleName, locale)
                .getString(propertyName);
    }
}
