package com.arqui.Interfaces;

public interface IView {
//nuevos metodos para los presentadores
    void setInformation(String information);
    void setOption(String option);
    void showView();
    void setDisplay(IDisplay display);
    void setPersistenceText(String persistenceType);
}
