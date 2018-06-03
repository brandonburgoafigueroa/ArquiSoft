package com.arqui.ModelViews;

import com.arqui.Interfaces.IModelView;

import java.util.ArrayList;

public class MessageMenuModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    public MessageMenuModelView ()
    {
        Options=new ArrayList<>();
        Options.add("listen to the current message");
        Options.add("save the current message");
        Options.add("delete the current message");
        Options.add("return to the main menu");
        Information ="";
    }
    @Override
    public ArrayList<String> getOptions() {
        return Options;
    }

    @Override
    public String getInformation() {
        return Information;
    }

    @Override
    public void setInformation(String Information) {
        this.Information=Information;
    }
}
