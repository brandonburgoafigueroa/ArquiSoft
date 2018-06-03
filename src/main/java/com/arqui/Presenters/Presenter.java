package com.arqui.Presenters;

import com.arqui.Interfaces.IDisplay;
import com.arqui.Interfaces.IModelView;
import com.arqui.Interfaces.IPresenter;
import com.arqui.Interfaces.IView;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements IPresenter {
private List<IView> Views;
private IDisplay display;
public Presenter()
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

    @Override
    public void setTextPersistenceType(String persistenceType) {
        String typeOfPersistence=display.getText(persistenceType);
        for (IView views:Views) {
            views.setPersistenceText(typeOfPersistence);
        }
    }

    @Override
    public void setModelView(IModelView modelView) {
        ArrayList<String> options=modelView.getOptions();
        String information=modelView.getInformation();
        for (IView view:Views) {
            setupView(options, information, view);
        }
    }

    private void setupView(ArrayList<String> options, String information, IView view) {
        for (String option:options) {
            view.setOption(option);
        }
        view.setInformation(information);
    }

    private void setOptions(IView view) {
        ArrayList<String> options=display.getOptions();
        for (String option:options) {
            view.setOption(option);
        }
    }
}
