package devinc.banklist.dao;

import devinc.banklist.model.UserAccount;

import java.util.List;

public interface UserAccountDAO {
    List<UserAccount> allAccounts();

    UserAccount add(UserAccount userAccount);

    void delete(int id);

    void deleteAll();

    void edit(UserAccount userAccount);

    UserAccount getById(int id);

    List<UserAccount> findAccountsForUser(Integer userId);
}
