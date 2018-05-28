package com.arqui;

import com.arqui.DisplayState.IDisplay;

import java.util.ArrayList;

public interface IObservers {
    void addObservable(View observable);
    void showText(String message);
    void showOptions(ArrayList<String> options);
    void sendDisplay(IDisplay display);
    void showError(String errorName);

    void show(String text);
}
