package com.raido.rental.dao.factory.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.dao.impl.MySqlCarDao;
import com.raido.rental.dao.impl.MySqlUserDao;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Raido_DDR on 9/5/2014.
 */
public class MySqlDaoFactory extends DaoFactory {

    private static volatile MySqlDaoFactory instance;

    private static Lock lock = new ReentrantLock();

    private MySqlDaoFactory() {}

    public static MySqlDaoFactory getInstance() {

        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new MySqlDaoFactory();
            }
            lock.unlock();

        }

        return instance;
    }

    @Override
    public UserDao getUserDao() {
        return MySqlUserDao.getInstance();
    }

    @Override
    public CarDao getCarDao() {
        return MySqlCarDao.getInstance();
    }


}
