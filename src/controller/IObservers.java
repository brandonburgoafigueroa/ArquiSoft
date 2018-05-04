package controller;

import view.View;

public interface IObservers {
    void addObservable(View observable);
    void updateObservables(String message);
}
