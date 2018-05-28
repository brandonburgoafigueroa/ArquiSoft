package com.arqui;

import static spark.Spark.get;
import static spark.SparkBase.port;

public class VoiceMailService {
    private Connection connection;
    public VoiceMailService(Connection connection){
        this.connection = connection;
        port(getHerokuAssignedPort());
    }
    public VoiceMailService(){
        port(getHerokuAssignedPort());
    }
    public void hello(){
        String currentMessage = "hello";
        get("/hello", (req, res) -> currentMessage);
    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public void currentMessage(){
        get("/currentMessage", (req, res) ->
                getCurrentMessage()
        );
    }

    private String getCurrentMessage() {
        return connection.getCurrentMailbox().getCurrentMessage()==null?"No messages":connection.getCurrentMailbox().getCurrentMessage().getText();
    }

    public void executeCommandWithNumeral(){
        get("/executeCommand/:id", (req, res) ->{
            String id = req.params(":id");
            Boolean state = connection.executeCommand(id);
            connection.executeCommand("#");
            return state;
        });
    }
    public void executeOption(){
        get("/executeOption/:id", (req, res) ->{
            String id = req.params(":id");
            Boolean state = connection.executeCommand(id);
            return state;
        });
    }
    public void saveMessage(){
        get("/saveMessage/:message", (req, res) ->{
            String message = req.params(":message");
            Boolean state = connection.executeCommand(message);
            state=connection.executeCommand("h");
            return state;
        });
    }
    public void currentGreeting(){
        get("/currentGreeting", (req, res) -> connection.getCurrentMailbox().getGreeting());
    }
    public void ping(){
        get("/ping", (req, res) -> true);
    }

    public void startAPIService(){
        currentMessage();
        executeCommandWithNumeral();
        executeOption();
        currentGreeting();
        saveMessage();
        ping();
    }

}
