package controller;

import view.View;

import java.util.ArrayList;
import java.util.List;

public class Observers {

    private List<View> observables;

    public Observers() {
        observables=new ArrayList<>();
    }

    public void addObservable(View observable)
    {
        observables.add(observable);

    }

    public void updateObservables(String message)
    {
        for (View observer:observables) {
            observer.update(message);
        }
    }
}
