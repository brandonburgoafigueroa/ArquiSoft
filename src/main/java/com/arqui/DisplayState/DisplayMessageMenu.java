package com.arqui.DisplayState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayMessageMenu implements IDisplay {
    private ArrayList<String> Options;
    private Map<String, String> Errors;
    private Map<String, String> Text;
    public DisplayMessageMenu()
    {
        Options=new ArrayList<>();
        Options.add("listen to the current message");
        Options.add("save the current message");
        Options.add("delete the current message");
        Options.add("return to the main menu");
        Errors=new HashMap<>();
        Text=new HashMap<>();
        Text.put("Empty", "No messages.");
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
