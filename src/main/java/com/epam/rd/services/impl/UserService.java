package com.epam.rd.services.impl;

import com.epam.rd.dao.impl.SimpleDaoFactory;
import com.epam.rd.models.User;
import com.epam.rd.services.Service;

import java.util.List;

public class UserService implements Service<User> {

    @Override
    public boolean create(User entity) {
        return SimpleDaoFactory.getDaoFactory().getUserDao().create(entity);
    }

    @Override
    public User read(int id) {
        return SimpleDaoFactory.getDaoFactory().getUserDao().read(id);
    }

    @Override
    public boolean update(User entity) {
        return SimpleDaoFactory.getDaoFactory().getUserDao().update(entity);
    }

    @Override
    public boolean delete(int id) {
        return SimpleDaoFactory.getDaoFactory().getUserDao().delete(id);
    }

    @Override
    public List<User> getAll() {
        return SimpleDaoFactory.getDaoFactory().getUserDao().getAll();
    }

    @Override
    public int getCount() {
        return SimpleDaoFactory.getDaoFactory().getUserDao().getCount();
    }
    public void deleteAllUsers() {
        SimpleDaoFactory.getDaoFactory().getUserDao().deleteAllUsers();
    }

    public User getByLogin(String login) {
        return SimpleDaoFactory.getDaoFactory().getUserDao().getByLogin(login);
    }

    public List<User> getRangeOfUsers(int startIndex, int count) {
        return SimpleDaoFactory.getDaoFactory().getUserDao().getRangeOfUsers(startIndex, count);
    }
}
