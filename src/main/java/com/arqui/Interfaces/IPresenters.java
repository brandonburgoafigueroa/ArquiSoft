package com.arqui.Interfaces;

public interface IPresenters {

    void addPresenter(IPresenter presenter);
    void setDisplay(IDisplay display);
    void setInformation(String textName);
    void setOptions();
    void setError(String errorName);
    void setTextPlain(String text);
    void showView();
    void setPersistenceType(String persistenceType);

    //model views
    void setModelView(IModelView modelView);
}
