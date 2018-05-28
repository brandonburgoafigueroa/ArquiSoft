package com.arqui;

import java.util.ArrayList;

public interface IObservers {
    void addObservable(View observable);
    void showText(String message);
    void showOptions(ArrayList<String> options);
}
