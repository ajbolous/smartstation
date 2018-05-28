package braudeproject.smartstations.Services;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class UsersServices {

    public static void login(String userid, String password, final RequestCallback<ServerResponse> callback){

        JSONObject credentials = new JSONObject();
        try {
            credentials.put("userid", userid);
            credentials.put("password", password);
        }catch (JSONException e) {

        }

        String url = WebServices.baseUrl + "/login";

        JsonRequest req = new JsonObjectRequest(Request.Method.POST, url, credentials, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(WebServices.gson.fromJson(response.toString(), ServerResponse.class));
            }
        }, null);

        WebServices.addRequest(req);
    }
}
