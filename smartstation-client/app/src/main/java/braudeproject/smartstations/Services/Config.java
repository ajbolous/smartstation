package braudeproject.smartstations.Services;



public class Config {
    public enum AppMode {
        Driver,
        Station,
        User
    }

    public AppMode Mode = AppMode.Station;

    private static final Config ourInstance = new Config();

    public static Config getInstance() {
        return ourInstance;
    }

    public static String stationId = "50840";

    private Config() {

    }
}
