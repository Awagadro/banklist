package devinc.banklist.model;

public class UserAccount {
    private int id;
    private int account;
    private User user;

    public UserAccount() {
    }

    public int getId() {
        return id;
    }

    public int getAccount() {
        return account;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", account=" + account +
                ", userId=" + user.getId() +
                '}';
    }
}
