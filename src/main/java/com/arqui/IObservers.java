package com.arqui;

import com.arqui.DisplayState.IDisplay;

public interface IObservers {
    void addObservable(IView observable);
    void showText(String message);
    void showOptions();
    void sendDisplay(IDisplay display);
    void showError(String errorName);

    void show(String text);
}
