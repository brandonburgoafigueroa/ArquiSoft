package com.arqui.ModelViews;

import com.arqui.Interfaces.IModelView;

import java.util.ArrayList;

public class ConnectModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    public ConnectModelView ()
    {
        Options=new ArrayList<String>();
        Information ="Enter mailbox number followed by #";
    }

    public ArrayList<String> getOptions() {
        return Options;
    }
    public String getInformation() {
        return Information;
    }



}
