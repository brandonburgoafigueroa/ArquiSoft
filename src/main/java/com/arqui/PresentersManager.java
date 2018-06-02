package com.arqui;

import com.arqui.DisplayState.IDisplay;
import com.arqui.Presenters.IPresenter;

import java.util.ArrayList;
import java.util.List;

public class PresentersManager implements IPresenters {

    private List<IView> observables;
    private List<IPresenter> presenters;

    public PresentersManager() {
        observables=new ArrayList<IView>();
        presenters=new ArrayList<>();
    }

    public void addObservable(IView observable)
    {
        observables.add(observable);

    }

    public void showText(String message)
    {
        for (IView observer:observables) {
            observer.showText(message);
        }
    }

    @Override
    public void showOptions() {
        for (IView observer:observables) {
            observer.showOptions();
        }
    }

    @Override
    public void sendDisplay(IDisplay display) {
        for (IView observer:observables) {
            observer.setDisplay(display);
        }
    }

    @Override
    public void showError(String errorName) {
        for (IView observer:observables) {
            observer.showError(errorName);
        }
    }

    @Override
    public void setText(String text) {
        for (IView observer:observables) {
            observer.show(text);
        }
    }
//presenters
    @Override
    public void addPresenter(IPresenter presenter) {
        presenters.add(presenter);
    }

    @Override
    public void setDisplay(IDisplay display) {
        for (IPresenter presenter:presenters) {
            presenter.setDisplay(display);
        }
    }

    @Override
    public void showTextP(String textName) {
        for (IPresenter presenter:presenters) {
            presenter.setText(textName);
        }
    }

    @Override
    public void showOptionsP() {
        for (IPresenter presenter:presenters) {
            presenter.setupOptions();
        }
    }

    @Override
    public void showErrorP(String errorName) {
        for (IPresenter presenter:presenters) {
            presenter.setError(errorName);
        }
    }

    @Override
    public void showP(String text) {
        for (IPresenter presenter:presenters) {
            presenter.setTextPlain(text);
        }
    }

    @Override
    public void show() {
        for (IPresenter presenter:presenters) {
            presenter.show();
        }
    }
    //presenter
}
