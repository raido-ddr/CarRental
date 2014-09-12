package com.raido.rental.dao.pool;


import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseResourceManager {

    private static volatile DatabaseResourceManager instance;

    private static Lock lock = new ReentrantLock();

    private ResourceBundle bundle =
            ResourceBundle.getBundle("database");



    public static DatabaseResourceManager getInstance() {

        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new DatabaseResourceManager();
            }
            lock.unlock();

        }

        return instance;
    }

    private DatabaseResourceManager() {}


    public String getValue(String key){
        return bundle.getString(key);
    }
}