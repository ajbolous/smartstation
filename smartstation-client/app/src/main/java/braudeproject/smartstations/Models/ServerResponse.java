package braudeproject.smartstations.Models;
import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    @SerializedName("sucess")
    public boolean success;

    @SerializedName("message")
    public String message;
}

