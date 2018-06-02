package com.arqui.DisplayState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayChangeGreeting implements IDisplay{
    private ArrayList<String> Options;
    private Map<String, String> Errors;
    private Map<String, String> Text;
    public DisplayChangeGreeting()
    {
        Options=new ArrayList<>();
        Errors=new HashMap<>();
        Text=new HashMap<>();
        Text.put("ChangeGreeting", "Record your greeting, then press the # key");
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
