package com.arqui.Presenters;

import com.arqui.DisplayState.IDisplay;
import com.arqui.IView;

import java.util.List;

public interface IPresenter {
   void addView(IView view);
   void setDisplay(IDisplay display);
   void showText(String key);
   void showError(String key);
   void show(String text);
   void showOptions();

}
