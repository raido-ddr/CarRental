package com.raido.rental.logic.util.requestparam;

import com.raido.rental.entity.dbenum.OrderStatus;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Raido_DDR on 19.09.2014.
 */
public class RequestParameterHelper {

    private static volatile RequestParameterHelper instance;

    private static Lock lock = new ReentrantLock();

    private RequestParameterHelper() {}

    public static RequestParameterHelper getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new RequestParameterHelper();
            }
            lock.unlock();

        }
        return instance;
    }

    public String getString(HttpServletRequest request,
            String name) {
        return request.getParameter(name).trim();
    }

    public String getUpperCaseString(HttpServletRequest request,
            String name) {
        return request.getParameter(name).trim().toUpperCase();
    }

    public int getInt(HttpServletRequest request,
            String name) {
        String paramString = request.getParameter(name).trim();
        return Integer.parseInt(paramString);
     }

    public float getFloat(HttpServletRequest request,
            String name) {
        String paramString = request.getParameter(name).trim();
        return Float.parseFloat(paramString);
    }

    public Date getDate(HttpServletRequest request,
            String name) {
        String paramString = request.getParameter(name).trim();
        return Date.valueOf(paramString);
    }

    public OrderStatus getOrderStatus(HttpServletRequest request,
            String name) {
        String statusString = request.getParameter(name);
        return OrderStatus.valueOf(statusString.toUpperCase());
    }


}
