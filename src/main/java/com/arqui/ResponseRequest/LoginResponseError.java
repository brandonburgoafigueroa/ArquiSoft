package com.arqui.ResponseRequest;

import com.arqui.Interfaces.IResponseError;

public class LoginResponseError implements IResponseError {
    public String Error;
    public LoginResponseError(){
        Error="Incorrect passcode. Try again!";
    }

    @Override
    public String getError() {
        return Error;
    }
}
