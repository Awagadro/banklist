package devinc.banklist.dao;


import devinc.banklist.dao.utils.ConnectorDB;
import devinc.banklist.model.User;
import devinc.banklist.model.UserAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDAOImpl implements UserAccountDAO {
    public static final String SQL_SELECT_ALL_ACCOUNTS = "SELECT * FROM user_account";
    public static final String SQL_DELETE_ALL_ACCOUNTS = "DELETE FROM user_account";
    public static final String SQL_SELECT_ACCOUNTS_OF_ONE_USER = "SELECT id, account FROM user_account WHERE user_id =?"; // specific
    // method
    // (optional)
    public static final String SQL_SELECT_ACCOUNT_BY_ID = "SELECT account, user_id FROM user_account WHERE id =?";
    public static final String SQL_DELETE_ACCOUNT_BY_ID = "delete from user_account where id=?";
    public static final String SQL_CREATE_NEW_ACCOUNT = "INSERT INTO user_account (account, user_id) VALUES(?,?)";
    public static final String SQL_UPDATE_ACCOUNT = "UPDATE user_account SET account=?, user_id=? WHERE id=?";

    @Override
    public List<UserAccount> allAccounts() {
        List<UserAccount> uAccounts = new ArrayList<UserAccount>();
        Connection con = null;
        Statement st = null;
        //  System.out.println("findAll() started");
        try {
            con = ConnectorDB.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_ACCOUNTS);
            while (rs.next()) {
                UserAccount uAccount = new UserAccount();
                uAccount.setId(rs.getInt("id"));
                uAccount.setAccount(rs.getInt("account"));
                uAccount.setUser(new User(rs.getInt("user_id")));
                uAccounts.add(uAccount);
            }
        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);

        } finally {
            ConnectorDB.close(st);
        }
        return uAccounts;
    }

    @Override
    public List<UserAccount> findAccountsForUser(Integer userId) {
        List<UserAccount> uAccounts = new ArrayList<UserAccount>();
        Connection con = null;
        PreparedStatement st = null;
        //    System.out.println("findAccountsForUser() started");
        try {
            con = ConnectorDB.getConnection();
            st = con.prepareStatement(SQL_SELECT_ACCOUNTS_OF_ONE_USER);
            st.setObject(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserAccount uAccount = new UserAccount();
                uAccount.setId(rs.getInt("id"));
                uAccount.setAccount(rs.getInt("account"));
                uAccount.setUser(new User(userId));
                uAccounts.add(uAccount);
            }
        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);
        } finally {
            ConnectorDB.close(st);
        }
        return uAccounts;
    }

    @Override
    public UserAccount add(UserAccount userAccount) {
        Connection con = null;
        PreparedStatement st = null;
        // System.out.println("create(entity) started");
        try {
            con = ConnectorDB.getConnection();
            st = con.prepareStatement(SQL_CREATE_NEW_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, userAccount.getAccount());
            st.setObject(2, userAccount.getUser().getId());
            st.executeUpdate();
            final ResultSet rs = st.getGeneratedKeys();
            rs.next();
            final int id = rs.getInt(1);
            userAccount.setId(id);
        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);
        } finally {
            ConnectorDB.close(st);
        }
        return userAccount;
    }

    @Override
    public void delete(int id) {
        Connection con = null;
        PreparedStatement st = null;
        //  System.out.println("delete by id started");
        try {
            con = ConnectorDB.getConnection();
            st = con.prepareStatement(SQL_DELETE_ACCOUNT_BY_ID);
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
            st.executeUpdate(SQL_DELETE_ALL_ACCOUNTS);
        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);
        } finally {
            ConnectorDB.close(st);
        }
    }

    @Override
    public void edit(UserAccount userAccount) {
        Connection con = null;
        PreparedStatement st = null;
        //  System.out.println("update(entity) started");
        try {
            con = ConnectorDB.getConnection();
            st = con.prepareStatement(SQL_UPDATE_ACCOUNT);
            st.setInt(1, userAccount.getAccount());
            st.setInt(2, userAccount.getUser().getId());
            st.setObject(3, userAccount.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);
        } finally {
            ConnectorDB.close(st);
        }
    }

    @Override
    public UserAccount getById(int id) {
        UserAccount uAccount = new UserAccount();
        Connection con = null;
        PreparedStatement st = null;
     //   System.out.println("find by id started");
        try {
            con = ConnectorDB.getConnection();
            st = con.prepareStatement(SQL_SELECT_ACCOUNT_BY_ID);
            st.setObject(1, id);
            st.execute();

            final ResultSet rs = st.getResultSet();
            rs.next();
            uAccount.setId(id);
            uAccount.setAccount(rs.getInt("account"));
            uAccount.setUser(new User(rs.getInt("user_id")));
        } catch (SQLException e) {
            System.err.println("SQLException (request failed): " + e);
        } finally {
            ConnectorDB.close(st);
        }
        return uAccount;
    }
}
