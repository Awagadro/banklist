package devinc.banklist.servise;

import devinc.banklist.model.User;
import devinc.banklist.model.UserAccount;

import java.util.List;

public interface UserAccountService {
    List<UserAccount> allAccounts();

    UserAccount add(UserAccount account);

    void delete(int id);

    void deleteAll();

    void edit(UserAccount account);

    UserAccount getById(int id);

    List<UserAccount> findAccountsForUser(Integer userId);

    User findRichestUser();

    int allAccountsSum();

}
