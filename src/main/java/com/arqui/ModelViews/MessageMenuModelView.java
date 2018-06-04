package com.arqui.ModelViews;

import com.arqui.Interfaces.IModelView;

import java.util.ArrayList;

public class MessageMenuModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    public MessageMenuModelView ()
    {
        Options=new ArrayList<String>();
        setOptions();
        Information ="";
    }

    private void setOptions() {
        Options.add("listen to the current message");
        Options.add("save the current message");
        Options.add("delete the current message");
        Options.add("return to the main menu");
    }

    @Override
    public ArrayList<String> getOptions() {
        return Options;
    }

    @Override
    public String getInformation() {
        return Information;
    }
    public MessageMenuModelView(String Information) {
        this.Information=Information;
        Options=new ArrayList<String>();
        setOptions();
    }
}
