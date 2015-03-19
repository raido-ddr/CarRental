package com.raido.rental.listener;

import com.raido.rental.logic.util.resourcemanager.MessageBundle;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

@WebListener
public class CarRentalSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();

        session.setAttribute("locale",
                Locale.forLanguageTag(MessageBundle
                        .getString("config","default.locale")));

        session.setAttribute("role", "guest");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
