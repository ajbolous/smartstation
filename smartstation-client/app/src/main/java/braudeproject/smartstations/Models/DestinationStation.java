package braudeproject.smartstations.Models;

import com.google.gson.annotations.SerializedName;

public class DestinationStation {

    @SerializedName("station")
    public Station station;

    @SerializedName("distance")
    public double distance;

    @SerializedName("routes")
    public String[] routes;

}
