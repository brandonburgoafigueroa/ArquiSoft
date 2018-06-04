package com.arqui.Responses;

import com.arqui.Interfaces.IPersistence;
import com.arqui.Interfaces.IResponse;
import com.arqui.Repository.Database;
import com.arqui.Repository.Memory;

public class PersistenceResponse implements IResponse {
    String Type="";
    public PersistenceResponse(IPersistence persistence){
        setupTypeText(persistence);

    }

    private void setupTypeText(IPersistence persistence) {
        if (persistence instanceof Database)
        {
            Type="En Base de datos";
        }
        if (persistence instanceof Memory)
        {
            Type="En Memoria";
        }
    }

    @Override
    public String getContentResponse() {
        return Type;
    }
}
