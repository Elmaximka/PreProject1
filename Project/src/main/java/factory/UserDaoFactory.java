package factory;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

public class UserDaoFactory {
    public static UserDAO getUserDAO() {

        Properties properties = new Properties();

        try {

            properties.load(UserDaoFactory.class.getClassLoader().getResourceAsStream("/confir.properties"));
            String daotype = properties.getProperty("daotype");
            if (daotype.equalsIgnoreCase("UserHibernateDAO")) {
                return new UserHibernateDAO();
            } else {
                return new UserJdbcDAO();
            }

        } catch (IOException e) {
            System.out.print("No such file excepted");
            throw new NoSuchElementException();
        }

    }

}