package com.arqui;

import com.arqui.DisplayState.IDisplay;
import com.arqui.Presenters.IPresenter;

public interface IPresenters {

    //presentadores
    void addPresenter(IPresenter presenter);
    void setDisplay(IDisplay display);
    void setInformation(String textName);
    void setOptions();
    void setError(String errorName);
    void setTextPlain(String text);
    void showView();
}
