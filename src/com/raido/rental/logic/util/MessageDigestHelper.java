package com.raido.rental.logic.util;

import com.raido.rental.dao.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Raido_DDR on 9/5/2014.
 */
public class MessageDigestHelper {

    private static final Logger LOGGER =
            Logger.getLogger(ConnectionPool.class);

    private static MessageDigestHelper instance;

    private MessageDigestHelper() {}

    public static MessageDigestHelper getInstance() {
        if(instance == null) {
            instance = new MessageDigestHelper();
        }

        return instance;
    }

    public String getMd5Hash(String original) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e);
            return null;
        }

        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }
}
