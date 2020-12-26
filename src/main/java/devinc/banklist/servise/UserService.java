package devinc.banklist.servise;

import devinc.banklist.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    User add(User user);

    void delete(int id);

    void deleteAll();

    void edit(User user);

    User getById(int id);
}
