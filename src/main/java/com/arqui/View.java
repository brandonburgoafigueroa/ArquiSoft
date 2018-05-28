package com.arqui;

import com.arqui.DisplayState.IDisplay;

public interface View {
    void update(String message);
    void showOptions(IDisplay display);
}
