package devinc.banklist.servise;

import devinc.banklist.dao.UserDAO;
import devinc.banklist.dao.UserDAOImpl;
import devinc.banklist.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Override
    public User add(User user) {
        return userDAO.add(user);
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public void deleteAll() {
        userDAO.deleteAll();
    }

    @Override
    public void edit(User user) {
        userDAO.edit(user);
    }

    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }
}
