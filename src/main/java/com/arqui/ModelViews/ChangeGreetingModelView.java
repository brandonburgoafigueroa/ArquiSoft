package com.arqui.ModelViews;

import com.arqui.Interfaces.IModelView;

import javax.swing.text.html.Option;
import java.util.ArrayList;

public class ChangeGreetingModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    public ChangeGreetingModelView ()
    {
        Options=new ArrayList<>();
        Information ="Record your greeting, then press the # key";
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
