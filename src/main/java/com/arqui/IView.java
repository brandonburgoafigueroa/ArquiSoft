package com.arqui;

import com.arqui.DisplayState.IDisplay;

public interface IView {
    void showText(String message);
    void showError(String errorName);
    void showOptions();
    void setDisplay(IDisplay display);
    void show(String text);
//nuevos metodos para los presentadores
    void setInformation(String information);
    void setOption(String option);
    void showView();


}
