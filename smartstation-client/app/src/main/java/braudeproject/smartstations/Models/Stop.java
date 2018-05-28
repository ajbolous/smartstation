package braudeproject.smartstations.Models;

import com.google.gson.annotations.SerializedName;

public class Stop {

    @SerializedName("id")
    public String id;

    @SerializedName("stationId")
    public String stationId;

    @SerializedName("distanceFromOrigin")
    public double distanceFromOrigin;

    @SerializedName("timeFromOrigin")
    public double timeFromOrigin;

}
