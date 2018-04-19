package braudeproject.smartstations.Services;

public interface RequestCallback<T> {
    void onSuccess(T result);
}
