package com.arqui.Presenters;

import com.arqui.DisplayState.IDisplay;
import com.arqui.IView;

import java.util.ArrayList;
import java.util.List;

public class UserInterfacePresenter implements IPresenter {
    private List<IView> Views;
    public UserInterfacePresenter()
    {
        Views=new ArrayList<>();
    }
    @Override
    public void addView(IView view) {
        Views.add(view);
    }

    @Override
    public void setDisplay(IDisplay display) {

    }

    @Override
    public void setText(String key) {

    }

    @Override
    public void setError(String key) {

    }

    @Override
    public void setTextPlain(String text) {

    }

    @Override
    public void setupOptions() {

    }

    @Override
    public void show() {

    }
}
