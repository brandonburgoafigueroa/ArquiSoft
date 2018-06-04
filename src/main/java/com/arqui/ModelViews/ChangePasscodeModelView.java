package com.arqui.ModelViews;

import com.arqui.Interfaces.IModelView;

import java.util.ArrayList;

public class ChangePasscodeModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    public ChangePasscodeModelView()
    {
        Options=new ArrayList<String>();
        Information ="Enter new passcode followed by the # key";
    }
    public ArrayList<String> getOptions() {
        return Options;
    }

    public String getInformation() {
        return Information;
    }

}
