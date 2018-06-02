package com.arqui.Presenters;

import com.arqui.DisplayState.IDisplay;
import com.arqui.IView;

import java.util.List;

public interface IPresenter {
   void addView(IView view);
   void setDisplay(IDisplay display);
   void setText(String textName);
   void setError(String errorName);
   void setTextPlain(String text);
   void setupOptions();
   void show();

}
