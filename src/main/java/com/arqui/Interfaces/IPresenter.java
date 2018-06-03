package com.arqui.Interfaces;


public interface IPresenter {
   void addView(IView view);
   void setDisplay(IDisplay display);
   void setText(String textName);
   void setError(String errorName);
   void setTextPlain(String text);
   void setupOptions();
   void show();
   void setTextPersistenceType(String persistenceType);

   //model view
   void setModelView(IModelView modelView);

}
