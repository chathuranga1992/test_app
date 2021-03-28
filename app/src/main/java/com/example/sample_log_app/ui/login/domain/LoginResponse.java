package com.example.sample_log_app.ui.login.domain;



import com.example.sample_log_app.GenericEntity;
import com.example.sample_log_app.data.db.entity.User;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class LoginResponse extends GenericEntity {

    @SerializedName("access_token")
    private String mAccessToken;
    @SerializedName("expires_in")
    private Long mExpiresIn;
    @SerializedName("not-before-policy")
    private Long mNotBeforePolicy;
    @SerializedName("refresh_expires_in")
    private Long mRefreshExpiresIn;
    @SerializedName("refresh_token")
    private String mRefreshToken;
    @SerializedName("scope")
    private String mScope;
    @SerializedName("session_state")
    private String mSessionState;
    @SerializedName("token_type")
    private String mTokenType;
    @SerializedName("user")
    private User user;

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public Long getExpiresIn() {
        return mExpiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        mExpiresIn = expiresIn;
    }

    public Long getNotBeforePolicy() {
        return mNotBeforePolicy;
    }

    public void setNotBeforePolicy(Long notBeforePolicy) {
        mNotBeforePolicy = notBeforePolicy;
    }

    public Long getRefreshExpiresIn() {
        return mRefreshExpiresIn;
    }

    public void setRefreshExpiresIn(Long refreshExpiresIn) {
        mRefreshExpiresIn = refreshExpiresIn;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }

    public String getScope() {
        return mScope;
    }

    public void setScope(String scope) {
        mScope = scope;
    }

    public String getSessionState() {
        return mSessionState;
    }

    public void setSessionState(String sessionState) {
        mSessionState = sessionState;
    }

    public String getTokenType() {
        return mTokenType;
    }

    public void setTokenType(String tokenType) {
        mTokenType = tokenType;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
