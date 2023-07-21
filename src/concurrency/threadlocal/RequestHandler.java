package concurrency.threadlocal;

public class RequestHandler implements Runnable{
    private String request;

    public RequestHandler(String request) {
        this.request = request;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            processRequest(request);
        }
    };

    @Override
    public void run() {
        // get userInfo from request
        UserInfo userInfo = extractUserInFoFromRequest(request);

        UserContext.setUserIdAndUserName(userInfo);

        // process
        processRequest(request);

        // get User Context from child thread
        Thread childThread = new Thread(runnable);
        childThread.setName(Thread.currentThread().getName() + "-child");
        childThread.start();

        //clear thead local
        UserContext.clear();

    }

    private UserInfo extractUserInFoFromRequest(String request) {
        String[] strings = request.split(",");
        return new UserInfo(strings[0], strings[1]);
    }

    private void processRequest(String request) {
        //get info userContext
        System.out.println(Thread.currentThread().getName() + " " + "UserInFo : "  + UserContext.getUserInfo());
    }
}
