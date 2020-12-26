package devinc.banklist.dto;

public class UserAccountDTO {
    private int id;
    private int account;
    private String userName;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserAccountDTO{" +
                "id=" + id +
                ", account=" + account +
                ", userName='" + userName + '\'' +
                '}';
    }
}
