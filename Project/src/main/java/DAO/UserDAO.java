package DAO;

import model.User;

import java.util.List;

public interface UserDAO {
    boolean deleteUser(String name);

    User getUserByName(String name);

    List<User> getAllUsers();

    void addUser(User user) throws Throwable;

    void createTable();

    void dropTable();

}