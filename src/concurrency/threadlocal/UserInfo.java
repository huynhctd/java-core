package concurrency.threadlocal;

public class UserInfo {

    public UserInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    private String userId;
    private String userName;

    public String getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    @Override
    public String toString() {
        return "{" + "userId : "+ this.userId + ", " + "userName : " + this.userName + "}";
    }
}
