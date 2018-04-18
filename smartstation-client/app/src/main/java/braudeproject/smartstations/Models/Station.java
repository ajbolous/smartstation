package braudeproject.smartstations.Models;

public class Station {
    public String id;
    public String name;
    public String description;

    public double lat;
    public double lng;

    public static Station fromJson(String json){
        return new Station();
    }
}
