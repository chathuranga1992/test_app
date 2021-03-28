package com.example.sample_log_app.ui.register.domain;


import com.example.sample_log_app.GenericEntity;

public class UserInputData extends GenericEntity {
    private String userName;
    private String userEmail;
    private String socialCode;
    private String userPhone;
    private String userPassword;
    private String userConPassword;
    private String userDob;
    private String userGender;
    private String zipCode;
    private String ageGroup;
    private boolean isFB;




    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserConPassword() {
        return userConPassword;
    }

    public void setUserConPassword(String userConPassword) {
        this.userConPassword = userConPassword;
    }

    public String getSocialCode() {
        return socialCode;
    }

    public void setSocialCode(String socialCode) {
        this.socialCode = socialCode;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserDob() {
        return userDob;
    }

    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public boolean isFB() {
        return isFB;
    }

    public void setFB(boolean FB) {
        isFB = FB;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }
}
