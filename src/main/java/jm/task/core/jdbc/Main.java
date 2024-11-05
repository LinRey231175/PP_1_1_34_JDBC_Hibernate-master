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


    public static void main(String[] args) {
        UserDaoHibernateImpl UserService = new UserDaoHibernateImpl();
        UserService.createUsersTable();
        UserService.saveUser ("Name1", "LastName1", (byte) 20);
        UserService.saveUser("Name2", "LastName2", (byte) 25);
        UserService.saveUser("Name3", "LastName3", (byte) 31);
        UserService.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println(UserService.getAllUsers());
        UserService.cleanUsersTable();
        UserService.dropUsersTable();
    }



}