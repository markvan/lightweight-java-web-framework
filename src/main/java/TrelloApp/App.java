package TrelloApp;

import org.trello4j.model.List;
import org.trello4j.*;

import java.util.ArrayList;

// useful URL once logged into Trello: http://www.hwartig.com/trelloapiexplorer

public class App 
{
    public static void main( String[] args )
    {
        String boardId = "4e760bd470326f7b7ea7bbcb";

        Trello trello = new TrelloImpl(Secrets.API_KEY, Secrets.API_TOKEN);

        ArrayList<List> lists;
        lists = (ArrayList<List>) trello.getListByBoard(boardId);

        System.out.println( "Lists from trello:" );
        System.out.println( lists );

        System.out.println( "And their names:" );
        for (int i=0; i<lists.size(); i++) {
            System.out.println( lists.get(i).getName() );
        }
    }
}
//Card card = trello.getCard( Secrets.CARD_ID );
