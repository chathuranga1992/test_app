package com.example.sample_log_app.ui.register.domain;


import com.example.sample_log_app.GenericEntity;

public class Validation extends GenericEntity {
    final boolean isValid;
    final String masssage;

    public Validation(boolean isValid, String masssage) {
        this.isValid = isValid;
        this.masssage = masssage;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMasssage() {
        return masssage;
    }
}
