package braudeproject.smartstations.Services;



public class Config {
    public enum AppMode {
        Driver,
        Station,
        User
    }

    public AppMode Mode = AppMode.Driver;

    private static final Config ourInstance = new Config();

    public static Config getInstance() {
        return ourInstance;
    }

    public static String stationId = "53950";

    private Config() {

    }
}
