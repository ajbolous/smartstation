package braudeproject.smartstations.Services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import braudeproject.smartstations.Models.Station;

public class MapsService {

    private String baseUrl = "http://10.0.2.2:5000";
    private RequestQueue requestQueue;
    private Gson gson;
    private List<Station> stations;

    public MapsService(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
        this.requestQueue.start();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
    }


    public void getStations(final RequestCallback<List<Station>> callback) {
        String url = baseUrl + "/getStations";
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                stations = Arrays.asList(gson.fromJson(response.toString(), Station[].class));
                callback.onSuccess(stations);
            }
        }, null);

        requestQueue.add(req);
    }

}
