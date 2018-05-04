package controller;

public interface IState {
    void start(String command);
    void hangup();
}
