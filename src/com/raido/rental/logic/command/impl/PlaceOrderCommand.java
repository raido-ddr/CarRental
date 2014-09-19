package com.raido.rental.logic.command.impl;

import com.raido.rental.entity.Order;
import com.raido.rental.logic.command.OrderCommand;
import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlaceOrderCommand extends OrderCommand {

    private static volatile PlaceOrderCommand instance;

    private static Lock lock = new ReentrantLock();

    private PlaceOrderCommand() {}

    public static PlaceOrderCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new PlaceOrderCommand();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        if(METHOD_GET.equals(request.getMethod())) {
            //set id and cost attr???

            return PAGE_NAME_BUNDLE.getString("place.order.page");
        }

        Order order = createOrderFromData(request);
        if(order != null) {

        }

        //success message
        return PAGE_NAME_BUNDLE.getString("user.main.page");
    }


}
