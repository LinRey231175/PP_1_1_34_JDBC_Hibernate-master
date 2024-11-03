package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    private static SessionFactory sessionFactory;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mysqld";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Andrey231175!";

    public static void main(String[] args) {
        UserDaoHibernateImpl daoHibernate = new UserDaoHibernateImpl();
        daoHibernate.createUsersTable();
        daoHibernate.saveUser ("Name1", "LastName1", (byte) 20);
        daoHibernate.saveUser("Name2", "LastName2", (byte) 25);
        daoHibernate.saveUser("Name3", "LastName3", (byte) 31);
        daoHibernate.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println(daoHibernate.getAllUsers());
        daoHibernate.cleanUsersTable();
        daoHibernate.dropUsersTable();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, LOGIN);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");
                configuration.setProperties(settings)
                        .addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("Exception" + e);
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection creation error...");
        }
        return connection;
    }

}