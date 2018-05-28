package com.arqui;

import static spark.Spark.get;
import static spark.SparkBase.port;

public class VoiceMailService {
    private Connection connection;
    public VoiceMailService(Connection connection){
        this.connection = connection;
    }
    public VoiceMailService(){
    }
    public void hello(){
        port(getHerokuAssignedPort());
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



}
