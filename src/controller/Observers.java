package controller;

import view.View;

import java.util.*;

public class Observers {
    private List<View> observables;
    public void updateObservables(String message)
    {
        for (View observer:observables) {
            observer.update(message);
        }
    }
    public void addObserver(View observable)
    {
        observables.add(observable);
    }
    public Observers(){
        observables=new ArrayList<>();
    }
}
