package devinc.banklist.dao;

import devinc.banklist.dao.utils.ConnectorDB;
import devinc.banklist.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SQL_DELETE_ALL_USERS = "DELETE FROM user";
    public static final String SQL_SELECT_USER_BY_ID = "SELECT sur_name, name FROM user WHERE id =?";
    public static final String SQL_DELETE_USER_BY_ID = "delete from user where id=?";
    public static final String SQL_CREATE_NEW_USER = "INSERT INTO user (name, sur_name) VALUES(?,?)";
    public static final String SQL_UPDATE_USER = "UPDATE user SET name=?, sur_name=? WHERE id=?";


    @Override
    public List<User> allUsers() {
        List<User> users = new ArrayList<User>();
        Connection con = null;
        Statement st = null;
        //System.out.println("findAll() started");

        try {
            con = ConnectorDB.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_USERS);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurName(rs.getString("sur_name"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);

        } finally {
            ConnectorDB.close(st);
        }
        return users;
    }

    @Override
    public User add(User user) {
        Connection con = null;
        PreparedStatement st = null;
        //System.out.println("create(entity) started");
        try {
            con = ConnectorDB.getConnection();
            st = con.prepareStatement(SQL_CREATE_NEW_USER, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getName());
            st.setString(2, user.getSurName());
            st.executeUpdate();
            final ResultSet rs = st.getGeneratedKeys();
            rs.next();
            final int id = rs.getInt(1);
            user.setId(id);
        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);
        } finally {
            ConnectorDB.close(st);
        }
        return  user;
    }

    @Override
    public void delete(int id) {
        Connection con = null;
        PreparedStatement st = null;
        //System.out.println("delete by id started");
        try {
            con = ConnectorDB.getConnection();
            st = con.prepareStatement(SQL_DELETE_USER_BY_ID);
            st.setObject(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);


        } finally {
            ConnectorDB.close(st);
        }
    }

    @Override
    public void deleteAll() {
        Connection con = null;
        Statement st = null;
        // System.out.println("deleteAll() started");
        try {
            con = ConnectorDB.getConnection();
            st = con.createStatement();
            st.executeUpdate(SQL_DELETE_ALL_USERS);
        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);

        } finally {
            ConnectorDB.close(st);
        }
    }

    @Override
    public void edit(User user) {
        Connection con = null;
        PreparedStatement st = null;
        //System.out.println("update(entity) started");
        try {
            con = ConnectorDB.getConnection();
            st = con.prepareStatement(SQL_UPDATE_USER);
            st.setString(1, user.getName());
            st.setString(2, user.getSurName());
            st.setObject(3, user.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);

        } finally {
            ConnectorDB.close(st);
        }
    }

    @Override
    public User getById(int id) {
        User user = new User();
        Connection con = null;
        PreparedStatement st = null;
      //  System.out.println("find by id started");
        try {
            con = ConnectorDB.getConnection();
            st = con.prepareStatement(SQL_SELECT_USER_BY_ID);
            st.setObject(1, id);
            st.execute();

            final ResultSet rs = st.getResultSet();
            rs.next();
            user.setId(id);
            user.setName(rs.getString("name"));
            user.setSurName(rs.getString("sur_name"));

        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);

        } finally {
            ConnectorDB.close(st);
        }

        return user;
    }
}
