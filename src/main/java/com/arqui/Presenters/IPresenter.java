package com.arqui.Presenters;

import com.arqui.IView;

import java.util.List;

public interface IPresenter {
   void addView(IView view);
   void showText(String key);
   void showError(String key);
   void show(String text);

}
