package com.arqui.ModelViews;

import com.arqui.Interfaces.IModelView;

import java.util.ArrayList;

public class RecordingModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    public RecordingModelView()
    {
        Options=new ArrayList<>();
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
