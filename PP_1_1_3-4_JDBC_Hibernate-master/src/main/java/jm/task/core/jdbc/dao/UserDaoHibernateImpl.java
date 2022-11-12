package jm.task.core.jdbc.dao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sf;


    public UserDaoHibernateImpl() {
        sf = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sf.openSession();
        try {
            // start a transaction
            session.beginTransaction();
            // save the user object
            session.save(user);
            // commit transaction
            session.getTransaction().commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Ошибка запроса");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("Пользователь с id = " + id + " был удалён из базы");
        } catch (IllegalArgumentException e) {
            session.getTransaction().rollback();
            System.out.println("Возможно строки с таким id не существует");
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Ошибка запроса");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            list = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Ошибка запроса");
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(list);
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица очищена");
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Ошибка запроса");
        } finally {
            session.close();
        }
    }
}
