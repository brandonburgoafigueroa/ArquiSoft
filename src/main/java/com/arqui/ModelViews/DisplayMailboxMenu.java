package com.arqui.ModelViews;

import com.arqui.Interfaces.IDisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayMailboxMenu implements IDisplay {
    private ArrayList<String> Options;
    private Map<String, String> Errors;
    private Map<String, String> Text;
    public DisplayMailboxMenu()
    {
        Options=new ArrayList<>();
        Options.add("listen to your messages");
        Options.add("change your passcode");
        Options.add("change your greeting");
        Errors=new HashMap<>();
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
