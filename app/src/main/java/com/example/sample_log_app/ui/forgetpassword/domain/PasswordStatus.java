package com.example.sample_log_app.ui.forgetpassword.domain;

public class PasswordStatus {
    private boolean isDone;
    private String msg;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
