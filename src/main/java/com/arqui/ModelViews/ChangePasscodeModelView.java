package com.arqui.ModelViews;

import com.arqui.Interfaces.IModelView;

import java.util.ArrayList;

public class ChangePasscodeModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    public ChangePasscodeModelView()
    {
        Options=new ArrayList<>();
        Information ="Enter new passcode followed by the # key";
    }
    @Override
    public ArrayList<String> getOptions() {
        return Options;
    }

    @Override
    public String getInformation() {
        return Information;
    }

    public ChangePasscodeModelView(String Information) {
        this.Information=Information;
        Options=new ArrayList<>();
    }
}
