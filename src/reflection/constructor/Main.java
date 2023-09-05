package reflection.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws Exception {
        // int config sever
        initServerConfiguration();

        // start web
        WebSever webSever =  new WebSever();
        webSever.startSever();
    }

    static void initServerConfiguration() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor constructor = ServerConfiguration.class.getDeclaredConstructor(int.class, String.class);
        constructor.setAccessible(true);
        ServerConfiguration serverConfiguration = (ServerConfiguration) constructor.newInstance(8080, "welcome");
        System.out.println(serverConfiguration);
    }
}
