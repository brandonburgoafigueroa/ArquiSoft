package com.arqui.DisplayState;

import com.arqui.Interfaces.IDisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayPersistenceType implements IDisplay {
    private ArrayList<String> Options;
    private Map<String, String> Errors;
    private Map<String, String> Text;
    public DisplayPersistenceType()
    {
        Options=new ArrayList<>();
        Errors=new HashMap<>();
        Text=new HashMap<>();
        Text.put("OnMemory", "En Memoria");
        Text.put("OnDataBase", "En Base de datos");
    }

    @Override
    public ArrayList<String> getOptions() {
        return Options;
    }

    @Override
    public String getError(String errorName) {
        return Errors.get(errorName);
    }

    @Override
    public String getText(String textName) {
        return Text.get(textName);
    }
}
