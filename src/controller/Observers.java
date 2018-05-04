package controller;

import view.View;

import java.util.List;

public class Observers {

    private List<View> observables;

    public Observers(List<View> observables) {
        this.observables = observables;
    }

    public Observers() {
        
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
