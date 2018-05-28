package com.arqui;

import com.arqui.DisplayState.IDisplay;

public interface View {
    void showText(String message);
    void showOptions(IDisplay display);
}
