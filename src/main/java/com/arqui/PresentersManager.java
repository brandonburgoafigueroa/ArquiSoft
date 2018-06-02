package com.arqui;

import com.arqui.DisplayState.IDisplay;

import java.util.ArrayList;
import java.util.List;

public class Observers implements IObservers {

    private List<IView> observables;

    public Observers() {
        observables=new ArrayList<IView>();
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
    public void show(String text) {
        for (IView observer:observables) {
            observer.show(text);
        }
    }
}
