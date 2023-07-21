package concurrency.threadlocal.inheritable;

import concurrency.threadlocal.UserInfo;


public class InheritableRequestHandler implements Runnable{
    private String request;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            processRequest(request);
        }
    };

    public InheritableRequestHandler(String request) {
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("====Thread Task======: " + Thread.currentThread().getName() );
        // get userInfo from request
        UserInfo userInfo = extractUserInFoFromRequest(request);

        InheritableUserContext.setUserIdAndUserName(userInfo);

        //processRequest
        processRequest(request);

        // get User Context from child thread

        Thread childThread = new Thread(runnable);
        childThread.setName(Thread.currentThread().getName() + "-child");

        childThread.start();


        //clear thead local
        InheritableUserContext.clear();


    }

    private UserInfo extractUserInFoFromRequest(String request) {
        String[] strings = request.split(",");
        return new UserInfo(strings[0], strings[1]);
    }

    private void processRequest(String request) {
        //get info userContext
        System.out.println(Thread.currentThread().getName() + " " + "UserInFo : " + InheritableUserContext.getUserInfo() );
    }
}
