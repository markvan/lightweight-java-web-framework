package TrelloApp;

import org.trello4j.model.Card;
import org.trello4j.model.List;
import org.trello4j.*;

// useful URL once logged into Trello: http://www.hwartig.com/trelloapiexplorer

public class App {
    public static void main(String[] args) throws Exception {
        // create a new Trello API
        Trello trello = new TrelloImpl(Secrets.API_KEY, Secrets.API_TOKEN);

        // Mark's COMP61511 demo board
        String boardId = "4e760bd470326f7b7ea7bbcb";

        // find the lists on the board
        java.util.List<List> lists = trello.getListByBoard(boardId);

        System.out.println("61511 board lists are");
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i).getName());
        }

        // get the ID of the first card on the middle list
        java.util.List<Card> cardsInList = trello.getCardsByList(lists.get(1).getId());
        String firstCardID = cardsInList.get(0).getId();

        // change the card's name
        TrelloWriter.changeCardName(firstCardID, "Marks funky card");
    }
}


//Card card = trello.getCard( Secrets.CARD_ID );
