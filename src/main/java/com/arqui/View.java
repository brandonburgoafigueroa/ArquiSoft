package com.arqui;

import com.arqui.DisplayState.IDisplay;

public interface View {
    void showText(String message);
    void showError(String errorName);
    void showOptions();
    void setDisplay(IDisplay display);

    void show(String text);
}
