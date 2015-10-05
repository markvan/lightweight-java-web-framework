package SpecialStack;

import org.trello4j.CardService;
import org.trello4j.model.Card;

import org.trello4j.*;


import java.util.HashMap;
import java.util.Map;


/**
 * useful URL once logged into Trello: http://www.hwartig.com/trelloapiexplorer
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Get a card from Trello:" );

        Secrets secrets = new Secrets();
        Trello trello = new TrelloImpl(secrets.API_KEY, secrets.API_TOKEN);

        Card card = trello.getCard( secrets.CARD_ID );

        System.out.println( card );



    }
}
