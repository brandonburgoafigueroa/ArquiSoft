package controller;

public interface IState {
    void start(String key, Mailbox currentMailbox, State state, Observers observers);
}
