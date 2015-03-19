package com.raido.rental.dao.factory;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.OrderDao;
import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.factory.impl.MySqlDaoFactory;
import com.raido.rental.logic.util.resourcemanager.MessageBundle;

public abstract class DaoFactory {

    public static DaoFactory getInstance() {
        String daoType =
                MessageBundle.getString("config", "current.dao.type");

        switch(daoType) {
        case "mysql.dao":
            return MySqlDaoFactory.getInstance();
        default:
            return MySqlDaoFactory.getInstance();
        }
    }

    public abstract UserDao getUserDao();

    public abstract CarDao getCarDao();

    public abstract OrderDao getOrderDao();

}
