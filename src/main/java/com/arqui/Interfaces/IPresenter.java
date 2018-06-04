package com.arqui.Interfaces;


public interface IPresenter {
   void addView(IView view);
   void setModelView(IModelView modelView);

    void setError(IResponse error);

    void setPersistenceType(IResponse response);

    void show();
}
