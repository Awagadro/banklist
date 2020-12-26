package devinc.banklist;

import devinc.banklist.model.User;
import devinc.banklist.model.UserAccount;
import devinc.banklist.servise.UserAccountService;
import devinc.banklist.servise.UserAccountServiceImpl;
import devinc.banklist.servise.UserService;
import devinc.banklist.servise.UserServiceImpl;

import java.util.List;

public class Starter {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        UserAccountService accountService = new UserAccountServiceImpl();

        accountService.deleteAll();
        userService.deleteAll();

        User u1 = new User();
        UserAccount a1 = new UserAccount();

        u1.setName("Николай");
        u1.setSurName("Первый");
        User user = userService.add(u1);
        a1.setAccount(100);
        a1.setUser(user);
        accountService.add(a1);

        u1.setName("Екатерина");
        u1.setSurName("Вторая");
        user = userService.add(u1);
        a1.setAccount(150);
        a1.setUser(user);
        accountService.add(a1);

        u1.setName("Петр");
        u1.setSurName("Третий");
        user = userService.add(u1);
        a1.setAccount(100);
        a1.setUser(user);
        accountService.add(a1);
        a1.setAccount(-100);
        a1.setUser(user);
        accountService.add(a1);

        List<User> users = userService.allUsers();
        System.out.println(users);

       List<UserAccount> accounts = accountService.allAccounts();

        user = accountService.findRichestUser();
        System.out.println("richest user");
        System.out.println(user);

        System.out.println("sum of all accounts");
        System.out.println(accountService.allAccountsSum());
    }
}
