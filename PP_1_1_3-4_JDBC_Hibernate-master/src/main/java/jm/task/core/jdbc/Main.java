package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import net.bytebuddy.asm.MemberSubstitution;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
//        dao.createUsersTable(); // создать
//        dao.cleanUsersTable(); // очистить
//        dao.dropUsersTable(); //удалить
//        dao.saveUser("oleg", "Komissarov", (byte)29); // внести юзера
//        dao.getAllUsers(); // получить всю таблицу
//        dao.removeUserById(2); // удалить по id

       ///////////////////////////Hibernate ///////////////////////////////////

//        UserDaoHibernateImpl dao1 = new UserDaoHibernateImpl();
//        dao1.createUsersTable();
//        dao1.saveUser("oleg", "komissarov", (byte)29);
//        dao1.removeUserById(1);
//        dao1.getAllUsers();
//        dao1.removeUserById(20);
//        dao1.cleanUsersTable();
    }
}
