package devinc.banklist;

import devinc.banklist.model.User;
import devinc.banklist.servise.UserService;
import devinc.banklist.servise.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Starter2 {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        List<User> users = userService.allUsers();
        System.out.println(users);

    }
}
