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
        String currentMessage = connection.getCurrentMailbox().getCurrentMessage().getText();
        get("/currentMessage", (req, res) -> currentMessage);
    }
    public void executeCommand(){
        get("/executeCommand", (req, res) ->{
            String id = req.params(":id");
            Boolean state = connection.executeCommand(id);
            connection.executeCommand("#");
            return state;
        });
    }
    public void currentGreeting(){
        String greeting = connection.getCurrentMailbox().getGreeting();
        get("/currentGreeting", (req, res) -> greeting);
    }

    

}
