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

    public static String baseUrl = "http://10.0.2.2:5000";
    private static RequestQueue requestQueue;
    public static Gson gson;

    private WebServices() { }

    public static void initialize(Context context){
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.start();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
    }

    public static void addRequest(JsonRequest request){
        requestQueue.add(request);
    }



}
