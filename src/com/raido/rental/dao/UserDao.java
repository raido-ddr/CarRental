package com.raido.rental.dao;

import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.entity.User;


public interface UserDao {

    void createUser(User user) throws DaoException;

    User findUserById(int id) throws DaoException;

    User authorizeUser(String login, String hashedPassword) throws DaoException;

    boolean isLoginUnique(String login) throws DaoException;

}
