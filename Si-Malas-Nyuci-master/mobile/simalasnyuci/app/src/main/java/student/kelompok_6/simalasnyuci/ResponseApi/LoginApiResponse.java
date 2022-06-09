package student.kelompok_6.simalasnyuci.ResponseApi;


import androidx.annotation.Nullable;

import student.kelompok_6.simalasnyuci.model.User;

public class LoginApiResponse {
    private String status;
    @Nullable
    private User user;
    private String message;

    public LoginApiResponse(String status, @Nullable User user, String message) {
        this.status = status;
        this.user = user;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    @Nullable
    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
