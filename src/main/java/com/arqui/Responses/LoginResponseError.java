package com.arqui.ResponseRequest;

import com.arqui.Interfaces.IResponse;

public class LoginResponseError implements IResponse {
    public String Error;
    public LoginResponseError(){
        Error="Incorrect passcode. Try again!";
    }

    @Override
    public String getContentResponse() {
        return Error;
    }
}
