package braudeproject.smartstations.Models;

import com.google.gson.annotations.SerializedName;

public class Route {

    public int getRouteNumber() {
        return routeNumber;
    }

    public String getOperator() {
        return routeOperator;
    }

    public Stop[] getStops() {
        return stops;
    }


    @SerializedName("id")
    String routeId;

    @SerializedName("number")
    int routeNumber;

    @SerializedName("operator")
    String routeOperator;


    @SerializedName("stops")
    Stop[] stops;


}
