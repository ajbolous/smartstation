package braudeproject.smartstations.Services;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import braudeproject.smartstations.Models.Route;
import braudeproject.smartstations.Models.ShortestRoutes;

public class RoutesService {

    public static void getAvailableRoutes(String driverId, final RequestCallback<Route[]> callback){
        String url = WebServices.baseUrl + "/routes/getAvailableRoutes?driverId=" + driverId;

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Route[] routes = WebServices.gson.fromJson(response.toString(), Route[].class);
                callback.onSuccess(routes);
            }
        }, null);

        WebServices.addRequest(req);
    }

    public static void getRoute(int routeId, final RequestCallback<Route>callback){
        String url = WebServices.baseUrl + "/routes/getRoute?routeId=" + routeId;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Route route = WebServices.gson.fromJson(response.toString(), Route.class);
                callback.onSuccess(route);
            }
        }, null);

        WebServices.addRequest(req);
    }

    public static void getShortestRoutes(String destinationId, final RequestCallback<ShortestRoutes>callback){
        String url = WebServices.baseUrl + "/routes/getShortestRoute?destinationId=" + destinationId;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ShortestRoutes route = WebServices.gson.fromJson(response.toString(), ShortestRoutes.class);
                callback.onSuccess(route);
            }
        }, null);

        WebServices.addRequest(req);
    }
}
