package com.arqui.Presenters;

import com.arqui.IView;

import java.util.ArrayList;
import java.util.List;

public class ConsolePresenter implements IPresenter {
private List<IView> Views;
public ConsolePresenter()
{
    Views=new ArrayList<>();
}
    @Override
    public void addView(IView view) {
        Views.add(view);
    }

    @Override
    public void showText(String key) {

    }

    @Override
    public void showError(String key) {

    }

    @Override
    public void show(String text) {

    }
}
