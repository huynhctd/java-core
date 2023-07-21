package concurrency.threadlocal.inheritable;

import concurrency.threadlocal.UserInfo;

public class InheritableUserContext {
    private static InheritableThreadLocal<UserInfo> userInfoThreadLocal = new InheritableThreadLocal<>();

    public static void setUserIdAndUserName(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    public static String getUserId() {
        return userInfoThreadLocal.get().getUserId();
    }

    public static String getUserName() {
        return userInfoThreadLocal.get().getUserName();
    }

    public static  UserInfo getUserInfo() {
        return userInfoThreadLocal.get();
    }

    public static void clear() {
        userInfoThreadLocal.remove();
    }
}
