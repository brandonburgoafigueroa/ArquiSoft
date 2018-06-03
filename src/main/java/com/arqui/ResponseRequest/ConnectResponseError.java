package com.arqui.ResponseRequest;

import com.arqui.Interfaces.IResponseError;

public class ConnectResponseError implements IResponseError {
    public String Error;
    public ConnectResponseError(){
        Error="Incorrect mailbox number. Try again!";
    }

    @Override
    public String getError() {
        return Error;
    }
}
