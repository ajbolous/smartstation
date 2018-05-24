package braudeproject.smartstations.Services;
import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    @SerializedName("success")
    public boolean success;

    @SerializedName("message")
    public String message;
}

