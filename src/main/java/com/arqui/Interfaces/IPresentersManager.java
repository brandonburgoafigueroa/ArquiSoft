package com.arqui.Interfaces;

public interface IPresentersManager {

    void addPresenter(IPresenter presenter);
    void setModelView(IModelView modelView);
    void setPersistenceType(IResponse response);
    void setError(IResponse error);
}
