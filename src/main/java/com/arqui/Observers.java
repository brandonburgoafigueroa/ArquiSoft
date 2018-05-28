package com.arqui;

import com.arqui.DisplayState.IDisplay;

import java.util.ArrayList;
import java.util.List;

public class Observers implements IObservers {

    private List<View> observables;

    public Observers() {
        observables=new ArrayList<View>();
    }

    public void addObservable(View observable)
    {
        observables.add(observable);

    }

    public void showText(String message)
    {
        for (View observer:observables) {
            observer.showText(message);
        }
    }

    @Override
    public void showOptions(ArrayList<String> options) {
        for (View observer:observables) {
            observer.showOptions();
        }
    }

    @Override
    public void sendDisplay(IDisplay display) {
        for (View observer:observables) {
            observer.setDisplay(display);
        }
    }

    @Override
    public void showError(String errorName) {
        for (View observer:observables) {
            observer.showError(errorName);
        }
    }

    @Override
    public void show(String text) {
        for (View observer:observables) {
            observer.show(text);
        }
    }
}
