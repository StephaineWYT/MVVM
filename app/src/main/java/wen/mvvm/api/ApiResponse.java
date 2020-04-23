package wen.mvvm.api;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class ApiResponse {

    private final int code;
    public final String errorMessage;

    public ApiResponse(Response response) {
        code = response.code();
        if (response.isSuccessful()) {
            errorMessage = null;
        } else {
            String message = null;
            ResponseBody errorBody = response.errorBody();
            if (errorBody != null) {
                try {
                    message = errorBody.string();
                } catch (IOException ignored) {
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            errorMessage = message;
        }
    }
    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }
}
