package com.raido.rental.dao.factory;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.factory.impl.MySqlDaoFactory;

import java.util.ResourceBundle;

public abstract class DaoFactory {

    public static DaoFactory getInstance() {  //type can be stored in properties
        ResourceBundle bundle = ResourceBundle.getBundle("dao_type");
        String daoType = bundle.getString("current.dao.type");

        switch(daoType) {
        case "mysql.dao":
            return MySqlDaoFactory.getInstance();
        default:
            return MySqlDaoFactory.getInstance();
        }
    }

    public abstract UserDao getUserDao();

    public abstract CarDao getCarDao();



}
