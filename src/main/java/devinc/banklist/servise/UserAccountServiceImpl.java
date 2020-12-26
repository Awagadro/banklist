package devinc.banklist.servise;

import devinc.banklist.dao.UserAccountDAO;
import devinc.banklist.dao.UserAccountDAOImpl;
import devinc.banklist.model.User;
import devinc.banklist.model.UserAccount;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAccountServiceImpl implements UserAccountService {
    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public List<UserAccount> allAccounts() {
        return userAccountDAO.allAccounts();
    }

    @Override
    public UserAccount add(UserAccount account) {
        return userAccountDAO.add(account);
    }

    @Override
    public void delete(int id) {
        userAccountDAO.delete(id);
    }

    @Override
    public void deleteAll() {
        userAccountDAO.deleteAll();
    }

    @Override
    public void edit(UserAccount account) {
        userAccountDAO.edit(account);
    }

    @Override
    public UserAccount getById(int id) {
        return userAccountDAO.getById(id);
    }

    @Override
    public List<UserAccount> findAccountsForUser(Integer userId) {
        return userAccountDAO.findAccountsForUser(userId);
    }

    @Override
    public User findRichestUser() {

        List<UserAccount> accounts = userAccountDAO.allAccounts();
        List<User> users = userService.allUsers();
        Map<Integer, Integer> balanceMap = new HashMap<>();
        for (User user : users) {
            balanceMap.put(user.getId(), 0);
            for (UserAccount account : accounts) {
                if (user.getId() == account.getUser().getId()) {
                    int temp = balanceMap.get(user.getId());
                    balanceMap.put(user.getId(), temp + account.getAccount());
                }
            }
        }
        Map.Entry<Integer, Integer> maxEntry = Collections.max(balanceMap.entrySet(), (Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) -> e1.getValue()
                .compareTo(e2.getValue()));

        return userService.getById(maxEntry.getKey());

    }

    @Override
    public int allAccountsSum() {
        List<UserAccount> accounts = userAccountDAO.allAccounts();
        Integer sum = accounts.stream().
                map(a->a.getAccount())
                .reduce(0, Integer::sum);
        return sum;
    }


}
