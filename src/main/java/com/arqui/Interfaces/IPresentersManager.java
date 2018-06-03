package com.arqui.Interfaces;

public interface IPresenters {

    void addPresenter(IPresenter presenter);
    void setModelView(IModelView modelView);
    void setPersistenceType(IResponse response);
    void setError(IResponse error);
}
