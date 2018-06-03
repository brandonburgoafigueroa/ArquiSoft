package com.arqui.DisplayState;

import com.arqui.Interfaces.IDisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class DisplayConnect implements IDisplay {
    private ArrayList<String> Options;
    private Map<String, String> Errors;
    private Map<String, String> Text;
    public DisplayConnect()
    {
        Options=new ArrayList<>();
        Errors=new HashMap<>();
        Errors.put("Invalid", "Incorrect mailbox number. Try again!");
        Text=new HashMap<>();
        Text.put("InitialPrompt","Enter mailbox number followed by #");
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
