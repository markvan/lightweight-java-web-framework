package TrelloApp;

import java.net.*;
import java.io.*;


public class TrelloWriter {

    public static void main(String[] args) throws Exception {
        String cardId = "552ada14a7374ee7200443f6";
        String newName = "Some title xxxxx";
        System.err.print( changeCardName(cardId,newName ) );
    }

    public static int changeCardName(String cardId, String newName) throws Exception {



        URL url = new URL("https://api.trello.com/1/cards/" + cardId + "/"
                            + "?fields=name"
                            + "&key="   + Secrets.API_KEY
                            + "&token=" + Secrets.API_TOKEN
        );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        osw.write("{\"name\": \"" + newName + "\"}");
        osw.flush();
        osw.close();
        return connection.getResponseCode();
    }
}



