package com.arqui;

public interface IState {
    boolean dial(String command);
    boolean hangup();
}
