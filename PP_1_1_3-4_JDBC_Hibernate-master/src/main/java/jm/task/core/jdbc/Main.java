package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("oleg", "komissarov", (byte)29);
        us.removeUserById(1);
        us.getAllUsers();
        us.removeUserById(20);
        us.cleanUsersTable();
    }
}
