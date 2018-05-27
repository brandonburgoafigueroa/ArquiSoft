package api;
import controller.Connection;

import static spark.Spark.get;
public class VoiceMailService {
    private Connection connection;
    public VoiceMailService(Connection connection){
        this.connection = connection;
    }
    public VoiceMailService(){
    }
    public void hello(){
        String currentMessage = "hello";
        get("/currentMessage", (req, res) -> currentMessage);
    }
    public void currentMessage(){
        String currentMessage = connection.getCurrentMailbox().getCurrentMessage().getText();
        get("/currentMessage", (req, res) -> currentMessage);
    }
    public void executeCommand(){
        get("/currentMessage", (req, res) ->{
            String id = req.params(":id");
            return connection.executeCommand(id);
        });
    }
    public void currentGreeting(){
        String greeting = connection.getCurrentMailbox().getGreeting();
        get("/currentMessage", (req, res) -> greeting);
    }



}
