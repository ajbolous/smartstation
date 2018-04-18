package braudeproject.smartstations.Services;

import android.app.Application;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import braudeproject.smartstations.Models.Station;

public class MapsService {

    private String baseUrl = "http://10.0.2.2:5000";

    public ArrayList<Station> stations  = new ArrayList<>();

    public ArrayList<Station> getStations() throws InterruptedException{
        RequestQueue mRequestQueue;

// Instantiate the cache
        Cache cache = new Cache() {
            @Override
            public Entry get(String key) {
                return null;
            }

            @Override
            public void put(String key, Entry entry) {

            }

            @Override
            public void initialize() {

            }

            @Override
            public void invalidate(String key, boolean fullExpire) {

            }

            @Override
            public void remove(String key) {

            }

            @Override
            public void clear() {

            }
        }; // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();


        JsonArrayRequest req = _getStations();

        mRequestQueue.add(req);

        return stations;
    }

    private JsonArrayRequest _getStations() {

        String url = baseUrl + "/getStations";

        return new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Station station = new Station();
                        station.id = obj.getString("id");
                        station.name = obj.getString("name");
                        station.description = obj.getString("description");
                        station.lat = obj.getDouble("lat");
                        station.lng = obj.getDouble("lng");
                        stations.add(station);
                    } catch (Exception e) {

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );
        }
}
