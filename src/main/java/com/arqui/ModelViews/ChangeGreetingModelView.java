package com.arqui.ModelViews;

import com.arqui.Interfaces.IModelView;

import javax.swing.text.html.Option;
import java.util.ArrayList;

public class ChangeGreetingModelView implements IModelView {
    private ArrayList<String> Options;
    private String Information;
    public ChangeGreetingModelView ()
    {
        Options=new ArrayList<String>();
        Information ="Record your greeting, then press the # key";
    }

    public ArrayList<String> getOptions() {
        return Options;
    }


    public String getInformation() {
        return Information;
    }

    
}
