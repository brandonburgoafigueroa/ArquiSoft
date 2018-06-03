package com.arqui.ResponseRequest;

import com.arqui.Interfaces.IResponse;

public class ConnectResponseError implements IResponse {
    public String Error;
    public ConnectResponseError(){
        Error="Incorrect mailbox number. Try again!";
    }

    @Override
    public String getContentResponse() {
        return Error;
    }
}
