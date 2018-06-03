package com.arqui.Presenters;

import com.arqui.Interfaces.IDisplay;
import com.arqui.Interfaces.IPresenter;
import com.arqui.Interfaces.IView;

import java.util.ArrayList;
import java.util.List;

public class ConsolePresenter implements IPresenter {
private List<IView> Views;
private IDisplay display;
public ConsolePresenter()
{
    Views=new ArrayList<>();
}
    @Override
    public void addView(IView view) {
        Views.add(view);
    }

    @Override
    public void setDisplay(IDisplay display) {
        this.display=display;
    }

    @Override
    public void setText(String textName) {
        String text=display.getText(textName);
    for (IView view:Views) {
            view.setInformation(text);
        }
    }

    @Override
    public void setError(String errorName) {
        String error=display.getError(errorName);
        for (IView view:Views) {
            view.setInformation(error);
        }
    }

    @Override
    public void setTextPlain(String text) {
        for (IView view:Views) {
            view.setInformation(text);
        }
    }

    @Override
    public void setupOptions() {
        for (IView view:Views) {
            setOptions(view);
        }
    }

    @Override
    public void show() {
        for (IView view:Views) {
            view.showView();
        }
    }

    private void setOptions(IView view) {
        ArrayList<String> options=display.getOptions();
        for (String option:options) {
            view.setOption(option);
        }
    }
}
