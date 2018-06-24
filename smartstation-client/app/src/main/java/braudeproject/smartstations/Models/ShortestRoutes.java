package braudeproject.smartstations.Models;

import com.google.gson.annotations.SerializedName;

public class ShortestRoutes {

    @SerializedName("stations")
    public Station[] stations;

    @SerializedName("totalDistance")
    public double totalDistance;
}
