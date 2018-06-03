package com.arqui.Interfaces;

import java.util.ArrayList;

public interface IModelView {
    ArrayList<String> getOptions();
    String getInformation();
    void setInformation(String Information);
}
