package com.arqui.ModelViews;

import com.arqui.Interfaces.IModelView;

import java.util.ArrayList;

public class RecordingModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    @Override
    public ArrayList<String> getOptions() {
        return Options;
    }

    @Override
    public String getInformation() {
        return Information;
    }

    public RecordingModelView(String Information) {
     this.Information=Information;
        Options=new ArrayList<>();
    }
}
