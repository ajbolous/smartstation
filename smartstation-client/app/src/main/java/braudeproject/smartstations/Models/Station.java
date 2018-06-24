package braudeproject.smartstations.Models;

import com.google.gson.annotations.SerializedName;

import braudeproject.smartstations.Services.Utils;

public class Station {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("lat")
    public double lat;

    @SerializedName("lng")
    public double lng;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public double getDistance(double lat, double lng){
        return Utils.haversine(lat, this.lat, lng, this.lng, 1, 1);
    }

}
