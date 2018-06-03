package com.arqui.Api;

import com.arqui.Interfaces.IApi;
import com.arqui.Interfaces.IConnection;
import com.arqui.Interfaces.IRequest;
import com.arqui.Requests.ExecuteCommandRequest;

import static spark.Spark.get;
import static spark.SparkBase.port;

public class VoiceMailService implements IApi {
    private IConnection connection;
    private IRequest request;
    public VoiceMailService(IConnection connection){
        this.connection = connection;
        request=new ExecuteCommandRequest();
        port(getHerokuAssignedPort());
        startAPIService();
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
            request.setContent(id);
            connection.executeCommand(request);
            request.setContent("#");
            Boolean state =connection.executeCommand(request);
            return state;
        });
    }
    public void executeOption(){
        get("/executeOption/:id", (req, res) ->{
            String id = req.params(":id");
            request.setContent(id);
            Boolean state = connection.executeCommand(request);
            return state;
        });
    }
    public void saveMessage(){
        get("/saveMessage/:message", (req, res) ->{
            String message = req.params(":message");
            request.setContent(message);
            connection.executeCommand(request);
            request.setContent("#");
            Boolean state=connection.executeCommand(request);
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
