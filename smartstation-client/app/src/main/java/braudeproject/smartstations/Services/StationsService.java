package braudeproject.smartstations.Services;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;

import braudeproject.smartstations.Models.Station;

public class StationsService {

    public static void getStations(final RequestCallback<Station[]> callback) {
        String url = WebServices.baseUrl + "/stations/getStations";

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Station[] stations = WebServices.gson.fromJson(response.toString(), Station[].class);
                callback.onSuccess(stations);
            }
        }, null);

        WebServices.addRequest(req);
    }

    public static void getStationStatus(String stationId, final RequestCallback<Station> callback) {
        String url = WebServices.baseUrl + "/stations/getStationStatus?stationId=" + stationId;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Station station = WebServices.gson.fromJson(response.toString(), Station.class);
                callback.onSuccess(station);
            }
        }, null);

        WebServices.addRequest(req);
    }



    public static void getStationsHashed(final RequestCallback<HashMap<String, Station>> callback){
        String url = WebServices.baseUrl + "/stations/getStationsHashed";

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Type type = new TypeToken<HashMap<String, Station>>(){}.getType();

                HashMap<String, Station> stations = WebServices.gson.fromJson(response.toString(), type);
                callback.onSuccess(stations);
            }
        }, null);

        WebServices.addRequest(req);

    }

}
