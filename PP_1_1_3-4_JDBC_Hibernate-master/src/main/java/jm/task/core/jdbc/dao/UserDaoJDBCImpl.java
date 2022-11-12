package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;
    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE `alegakom_basetest`.`users` (`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,`FirstName` VARCHAR(45) NULL,`LastName` VARCHAR(45) NULL,`Age` INT NULL, PRIMARY KEY ( `id` ));"); // создаю таблицу со значениями
            System.out.println("Список в базе данных создан");
        }
        catch (SQLSyntaxErrorException e) {
            System.out.println("Возможно таблица уже существует");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL ошибка");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement())  {
            statement.execute("DROP TABLE Users"); //удаляю таблицу по имени полностью из базы
            System.out.println("Список из удалён из базы данных");
        }
        catch (SQLSyntaxErrorException e) {
            System.out.println("Возможно таблица не существует");
        }catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users(FirstName, LastName, Age) values(?, ?, ?);";
        try(PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        }
        catch (SQLSyntaxErrorException e) {
            System.out.println("Возможна таблица для вставки отсутствует");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL ошибка");
        }
    }

    public void removeUserById(long id) {
        try(Statement statement = connection.createStatement()) {
            statement.execute("DELETE from users WHERE id = " + id + ";");
            System.out.println("Пользователь с id = " + id + " был удалён из базы");
        }
        catch (SQLSyntaxErrorException e) {
            System.out.println("Возможно такого id не существует");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL ошибка");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            String sql = "select * from users;";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                User user = new User(rs.getString(2), rs.getNString(3), rs.getByte(4) );
                list.add(user); // добавляю в лист юзеров построчно
                user.setId(rs.getLong(1)); // задаю id без конструктора, для совместного вывода в toString
            }
        }
        catch (SQLSyntaxErrorException e) {
            System.out.println("Возможно таблицы не существует");
        }
        catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
        System.out.println(list);
        return list;
    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE Users;"); // очищаю таблицу
            System.out.println("Список очищен");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL ошибка");
        }
    }
}
