package controller;

public interface IState {
    void start(String key, Connection connection);
    void hangup();
}
