package com.arqui.ModelViews;

import com.arqui.Interfaces.IModelView;

import java.util.ArrayList;

public class ConnectModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    public ConnectModelView ()
    {
        Options=new ArrayList<>();
        Information ="Enter mailbox number followed by #";
    }

    @Override
    public ArrayList<String> getOptions() {
        return Options;
    }

    @Override
    public String getInformation() {
        return Information;
    }


    public ConnectModelView(String Information) {
        this.Information=Information;
        Options=new ArrayList<>();
    }

}
