package braudeproject.smartstations.Services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import braudeproject.smartstations.Models.Station;
import braudeproject.smartstations.Models.Route;

public class WebServices {

    private static String baseUrl = "http://10.0.2.2:5000";
    private static RequestQueue requestQueue;
    private static Gson gson;

    private WebServices() { }

    public static void initialize(Context context){
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.start();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
    }

    public static void getRoutes(final RequestCallback<List<Route>> callback){
        String url = baseUrl + "/routes/getRoutes";


    }

    public static void getStations(final RequestCallback<List<Station>> callback) {
        String url = baseUrl + "/stations/getStations";

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Station> stations = Arrays.asList(gson.fromJson(response.toString(), Station[].class));
                callback.onSuccess(stations);
            }
        }, null);

        requestQueue.add(req);
    }

    public static void login(String userid, String password, final RequestCallback<ServerResponse> callback){

        JSONObject credentials = new JSONObject();
        try {
            credentials.put("userid", userid);
            credentials.put("password", password);
        }catch (JSONException e) {

        }

        String url = baseUrl + "/login";

        JsonRequest req = new JsonObjectRequest(Request.Method.POST, url, credentials, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(gson.fromJson(response.toString(), ServerResponse.class));
            }
        }, null);

        requestQueue.add(req);
    }
}
