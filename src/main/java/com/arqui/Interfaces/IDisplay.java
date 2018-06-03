package com.arqui.Interfaces;

import java.util.ArrayList;

public interface IDisplay {
    ArrayList<String> getOptions();
    String getError(String errorName);
    String getText(String textName);
}
