package com.example.sample_log_app.ui.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample_log_app.ui.register.domain.RegisterStatus;
import com.example.sample_log_app.ui.register.domain.UserInputData;
import com.example.sample_log_app.util.L;
import com.example.sample_log_app.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public  class RegistrationViewModel extends ViewModel {

    static final int USER_NAME = 1;
    static final int USER_EMAIL = 2;
    static final int USER_PASSWORD = 3;
    static final int USER_CON_PASSWORD = 4;
    static final int ALL_VALID = 5;

    private RegistrationRepositories mRepositories;

    void init() {
        mRepositories = new RegistrationRepositories();
    }

    Map<Integer, Boolean> validateUserInputs(UserInputData userInputData) {
        Map<Integer, Boolean> validationMap = new HashMap<>();
        int innValidaCount = 0;
        boolean isValid = false;

        if (StringUtils.isEmpty(userInputData.getUserName())) {
            isValid = false;
            innValidaCount++;
        } else {
            isValid = true;
        }
        validationMap.put(USER_NAME, isValid);

        if(StringUtils.isEmpty(userInputData.getUserEmail())){
            isValid = false;
            innValidaCount++;
        }else{
            isValid = true;
        }
        validationMap.put(USER_EMAIL, isValid);

        if (!StringUtils.isValidEmail(userInputData.getUserEmail())) {
            isValid = false;
            innValidaCount++;
        } else {
            isValid = true;
        }
        validationMap.put(USER_EMAIL, isValid);

        if (userInputData.getUserPassword().length() <  6) {
            isValid = false;
            innValidaCount++;
        } else {
            isValid = true;
        }
        validationMap.put(USER_PASSWORD, isValid);

        if (!StringUtils.equals(userInputData.getUserPassword(), userInputData.getUserConPassword())) {
            isValid = false;
            innValidaCount++;
        } else {
            isValid = true;
        }
        validationMap.put(USER_CON_PASSWORD, isValid);

        isValid = false;
        if (innValidaCount == 0) {
            isValid = true;
        }

        validationMap.put(ALL_VALID,isValid);

        return validationMap;
    }



    boolean setupValidations(){
        boolean valid = false;


        return valid;
    }

    MutableLiveData<RegisterStatus> doRegisterUser(UserInputData userInputData) {

        String name = userInputData.getUserName();
        String email = userInputData.getUserEmail();
        String pw = userInputData.getUserPassword();
        String gender = userInputData.getUserGender();
        String type = "User";
        String age = userInputData.getAgeGroup();
        L.e("zip","----->>>"+userInputData.getZipCode());
        int zip = Integer.valueOf(userInputData.getZipCode());
        L.e("zip","----->>>"+zip);
        return mRepositories.doRegisterUser(name,email,gender,pw,type,age,zip);
    }


}
