package com.raido.rental.dao;

import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.entity.User;


public abstract class UserDao extends Dao {

    public abstract int createUser(User user) throws DaoException;

    public abstract User findUserById(int id) throws DaoException;

    public abstract User authorizeUser(String login, String hashedPassword) throws DaoException;

    public abstract boolean isLoginUnique(String login) throws DaoException;

}
