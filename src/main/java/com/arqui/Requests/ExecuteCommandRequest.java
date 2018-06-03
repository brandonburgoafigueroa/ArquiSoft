package com.arqui.Requests;

import com.arqui.Interfaces.IRequest;

public class ExecuteCommandRequest implements IRequest {
    private String Content;
    @Override
    public void setContent(String Content) {
        this.Content=Content;
    }

    @Override
    public String getContent() {
        return Content;
    }
}
