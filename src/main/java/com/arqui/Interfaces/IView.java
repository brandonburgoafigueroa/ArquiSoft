package com.arqui;

import com.arqui.DisplayState.IDisplay;

public interface IView {
//nuevos metodos para los presentadores
    void setInformation(String information);
    void setOption(String option);
    void showView();
    void setDisplay(IDisplay display);

}
