package service;

import DAO.UserDAO;
import factory.UserDaoFactory;
import model.User;

import java.util.List;

public class UserService {
    private static UserService instance;
    private static UserDAO userDao;

    private UserService() {
        userDao = UserDaoFactory.getUserDAO();
    }

    public static UserService instance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public boolean deleteUser(String name) {
        return userDao.deleteUser(name);
    }

    public boolean addUser(User user) {
        try {
            if (user.getName() != null && user.getPassword() != null && user.getGender() != null) {
                createTable();
                userDao.addUser(user);
                return true;
            }
        } catch (Throwable e) {
            return false;
        }
        return false;
    }

    public void cleanUp() {
        userDao.dropTable();
    }

    public void createTable() {
        userDao.createTable();
    }


}