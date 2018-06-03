package com.arqui.ModelViews;

import com.arqui.Interfaces.IDisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayLogin implements IDisplay {
    private ArrayList<String> Options;
    private Map<String, String> Errors;
    private Map<String, String> Text;
    public DisplayLogin()
    {
        Options=new ArrayList<>();
        Errors=new HashMap<>();
        Errors.put("Invalid_Passcode", "Incorrect passcode. Try again!");
        Text=new HashMap<>();
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
