package reflection.constructor;

import java.net.InetSocketAddress;

public class ServerConfiguration {
    private static ServerConfiguration INSTANCE;

    private final InetSocketAddress serverAddress;
    private final String greetingMessage;

    public InetSocketAddress getServerAddress() {
        return serverAddress;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }

    public static ServerConfiguration getInstance() {
        return INSTANCE;
    }

    public ServerConfiguration(int port, String greetingMessage) {
        this.serverAddress = new InetSocketAddress("localhost", port);
        this.greetingMessage = greetingMessage;

        if (INSTANCE == null) INSTANCE = this;
    }

    @Override
    public String toString() {
        return "ServerConfiguration{" +
                "serverAddress=" + serverAddress +
                ", greetingMessage='" + greetingMessage + '\'' +
                '}';
    }
}
