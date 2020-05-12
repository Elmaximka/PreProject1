package DAO;

import model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO {
    private final Connection connection;

    public UserJdbcDAO() {
        connection = getConnection();
    }

    public boolean deleteUser(String name) {
        if (getUserByName(name) != null) {
            try (PreparedStatement st = connection.prepareStatement("delete from users where name='" + name + "'")) {
                st.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public User getUserByName(String name) {
        try (PreparedStatement st = connection.prepareStatement("select * from users where name='" + name + "'")) {
            ResultSet res = st.executeQuery();
            res.next();
            return new User(res.getString("name"),
                    res.getString("password"), res.getString("gender"), res.getString("role"));
        } catch (SQLException e) {
            return null;
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from users")) {
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                list.add(new User(res.getLong("id"), res.getString("name"),
                        res.getString("password"), res.getString("gender"),
                        res.getString("role")));
            }
            res.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addUser(User user) throws Throwable {
        String name = user.getName();
        String password = user.getPassword();
        String gender = user.getGender();
        String role = user.getRole();
        try (PreparedStatement sq = connection.prepareStatement("select name from users where name='" + name + "'")) {
            ResultSet res = sq.executeQuery();
            res.next();
            if (name.compareToIgnoreCase(res.getString(1)) == 0) {
                throw new Throwable();
            }
        } catch (SQLException eq) {
            eq.printStackTrace();
        }
        try (PreparedStatement st = connection.prepareStatement("insert into users(name, password, gender, role)" +
                " values ('" + name + "','" + password + "','" + gender + "', '" + role + "')")) {
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try (PreparedStatement stmt = connection.prepareStatement("create table if not exists users (id bigint auto_increment, name varchar(256)," +
                " password varchar(256), gender varchar(256), role varchar(256), primary key (id))")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try (PreparedStatement stmt = connection.prepareStatement("DROP TABLE IF EXISTS users")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("crud?").                //db name
                    append("useUnicode=true&").     //unicode
                    append("serverTimezone=Europe/Moscow");  // setTimeZone

            return DriverManager.getConnection(url.toString(), "root", "root");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}