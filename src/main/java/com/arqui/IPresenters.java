package com.arqui;

import com.arqui.DisplayState.IDisplay;
import com.arqui.Presenters.IPresenter;

public interface IPresenters {
    void addObservable(IView observable);
    void showText(String message);
    void showOptions();
    void sendDisplay(IDisplay display);
    void showError(String errorName);
    void setText(String text);

    //presentadores
    void addPresenter(IPresenter presenter);
    void setDisplay(IDisplay display);
    void showTextP(String textName);
    void showOptionsP();
    void showErrorP(String errorName);
    void showP(String text);
    void show();
}
