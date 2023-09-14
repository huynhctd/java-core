package dynamic_config_loader.data;

public class DataSource {
    private final String host;

    private String username;
    private String password;

    public DataSource() {
        this.host = "host";
    }

    @Override
    public String toString() {
        return "DataSource{" +
                "host='" + host + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
