package reflection.constructor;

import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;

public class WebSever {
    public void startSever() throws Exception {
        HttpServer httpServer =  HttpServer.create(ServerConfiguration.getInstance().getServerAddress(), 0);
        httpServer.createContext("/greeting").setHandler(
                exchange -> {
                    String greetingMessage = ServerConfiguration.getInstance().getGreetingMessage();

                    exchange.sendResponseHeaders(200, greetingMessage.length());

                    OutputStream responseBody = exchange.getResponseBody();
                    responseBody.write(greetingMessage.getBytes());
                    responseBody.flush();
                    responseBody.close();
                }
        );

        System.out.println(
                String.format("Starting server on address %s: %d",
                        ServerConfiguration.getInstance().getServerAddress().getHostName(),
                        ServerConfiguration.getInstance().getServerAddress().getPort()
                        )
        );

        httpServer.start();
    }
}
