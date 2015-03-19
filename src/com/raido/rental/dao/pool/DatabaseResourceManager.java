package com.raido.rental.dao.pool;


import com.raido.rental.logic.util.resourcemanager.MessageBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseResourceManager {

    private static  DatabaseResourceManager instance;

    private static Lock lock = new ReentrantLock();

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
        return MessageBundle.getString("config", key);
    }
}