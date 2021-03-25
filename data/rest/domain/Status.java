package com.example.sample_log_app.data.rest.domain;


import com.example.sample_log_app.GenericEntity;
import com.google.gson.annotations.SerializedName;


public class Status extends GenericEntity {

    public enum Type {
        RUNNING,
        SUCCESS_LOADED, // New
        SUCCESS_EMPTY, // New
        FAILED,
        FAILED_AUTH
    }

    @SerializedName("error")
    private String mError;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("statusCode")
    private Long mStatusCode;
    private boolean isDone = true;

    private Type mType;

    public Status() {
        mType = Type.RUNNING;
    }

    public String getError() {
        return mError;
    }

    public void setError(String error) {
        mError = error;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Long getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(Long statusCode) {
        mStatusCode = statusCode;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type mType) {
        this.mType = mType;
    }
}
