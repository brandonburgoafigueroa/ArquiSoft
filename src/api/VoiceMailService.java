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
    public void currentMessage(){
        String currentMessage = connection.getCurrentMailbox().getCurrentMessage().getText();
        get("/currentMessage", (req, res) -> currentMessage);
    }

}
