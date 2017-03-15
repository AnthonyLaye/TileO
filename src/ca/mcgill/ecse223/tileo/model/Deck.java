/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.exception.InvalidInputException;

// line 51 "../../../../../TileOPersistence.ump"
// line 306 "../../../../../TileO.ump"
public class Deck implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Deck Associations
  private List<ActionCard> cards;
  private ActionCard currentCard;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Deck(Game aGame)
  {
    cards = new ArrayList<ActionCard>();
    if (aGame == null || aGame.getDeck() != null)
    {
      throw new RuntimeException("Unable to create Deck due to aGame");
    }
    game = aGame;
  }

  public Deck(int aCurrentConnectionPiecesForGame, Die aDieForGame, TileO aTileOForGame)
  {
    cards = new ArrayList<ActionCard>();
    game = new Game(aCurrentConnectionPiecesForGame, this, aDieForGame, aTileOForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public ActionCard getCard(int index)
  {
    ActionCard aCard = cards.get(index);
    return aCard;
  }

  public List<ActionCard> getCards()
  {
    List<ActionCard> newCards = Collections.unmodifiableList(cards);
    return newCards;
  }

  public int numberOfCards()
  {
    int number = cards.size();
    return number;
  }

  public boolean hasCards()
  {
    boolean has = cards.size() > 0;
    return has;
  }

  public int indexOfCard(ActionCard aCard)
  {
    int index = cards.indexOf(aCard);
    return index;
  }

  public ActionCard getCurrentCard()
  {
    return currentCard;
  }

  public boolean hasCurrentCard()
  {
    boolean has = currentCard != null;
    return has;
  }

  public Game getGame()
  {
    return game;
  }

  public static int minimumNumberOfCards()
  {
    return 0;
  }

  public static int maximumNumberOfCards()
  {
    return 32;
  }

  /*public ActionCard addCard(String aInstructions)
  {
    if (numberOfCards() >= maximumNumberOfCards())
    {
      return null;
    }
    else
    {
      return new ActionCard(aInstructions, this);
    }
  }*/

  public boolean addCard(ActionCard aCard)
  {
    boolean wasAdded = false;
    if (cards.contains(aCard)) { return false; }
    if (numberOfCards() >= maximumNumberOfCards())
    {
      return wasAdded;
    }

    Deck existingDeck = aCard.getDeck();
    boolean isNewDeck = existingDeck != null && !this.equals(existingDeck);
    if (isNewDeck)
    {
      aCard.setDeck(this);
    }
    else
    {
      cards.add(aCard);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCard(ActionCard aCard)
  {
    boolean wasRemoved = false;
    //Unable to remove aCard, as it must always have a deck
    if (!this.equals(aCard.getDeck()))
    {
      cards.remove(aCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCardAt(ActionCard aCard, int index)
  {  
    boolean wasAdded = false;
    if(addCard(aCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCards()) { index = numberOfCards() - 1; }
      cards.remove(aCard);
      cards.add(index, aCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCardAt(ActionCard aCard, int index)
  {
    boolean wasAdded = false;
    if(cards.contains(aCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCards()) { index = numberOfCards() - 1; }
      cards.remove(aCard);
      cards.add(index, aCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCardAt(aCard, index);
    }
    return wasAdded;
  }

  public boolean setCurrentCard(ActionCard aNewCurrentCard)
  {
    boolean wasSet = false;
    currentCard = aNewCurrentCard;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (cards.size() > 0)
    {
      ActionCard aCard = cards.get(cards.size() - 1);
      aCard.delete();
      cards.remove(aCard);
    }
    
    currentCard = null;
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }

  // line 311 "../../../../../TileO.ump"
   public void shuffle(){
    Random rand = new Random();
    for (int i=0; i<100; ++i){
        ActionCard card = getCard(0);
        addOrMoveCardAt(card, rand.nextInt(Game.NumberOfActionCards));
    }
    setCurrentCard(getCard(0));
  }

  public void print() {
    System.out.println("~~~ DECK ~~~~");
    System.out.println("RollDieActionCard: "+numberOfCardsForType(0));
    System.out.println("ConnectTilesActionCard: "+numberOfCardsForType(1));
    System.out.println("RemoveConnectionActionCard: "+numberOfCardsForType(2));
    System.out.println("TeleportActionCard: "+numberOfCardsForType(3));
    System.out.println("LoseTurnActionCard: "+numberOfCardsForType(4));
    System.out.println("");
  }

   
   public int numberOfCardsForType(int type) {
	    int n = 0;
	    for (ActionCard aCard: getCards()) {
	        switch (type) {
	            case 0:
	                if (aCard instanceof RollDieActionCard) n++;
	                break;
	            case 1:
	                if (aCard instanceof ConnectTilesActionCard) n++;
	                break;
	            case 2:
	                if (aCard instanceof RemoveConnectionActionCard) n++;
	                break;
	            case 3:
	                if (aCard instanceof TeleportActionCard) n++;
	                break;
	            case 4:
	                if (aCard instanceof LoseTurnActionCard) n++;
	        }
	    }
	    return n;
	  }
   
   public void addCards(int n, int cardType) {
	    switch (cardType) {
	        case 0:
	            // ROLL
	            for (int i=0; i<n; ++i)
	                new RollDieActionCard("Roll the die for an extra turn", this);
	            break;
	        case 1:
	            // CONN
	    		for (int i=0;i<n;++i)
	                new ConnectTilesActionCard("Connect two tiles", this);
	            break;
	        case 2:
	            // RMCONN
	    		for (int i=0;i<n;++i)
	    		    new RemoveConnectionActionCard("Remove a connection", this);
	            break;
	        case 3:
	            // TELE
	    		for (int i=0;i<n;++i)
	    		    new TeleportActionCard("Move your piece to a new tile", this);
	            break;
	        case 4:
	            // LOSE
	    		for (int i=0;i<n;++i)
	    		    new LoseTurnActionCard("Lose your next turn", this);
	            break;
	    }
	  }

	  public void rmCards(int toRm, int cardType) {
	    for (int i=0; i<numberOfCards(); ++i) {
	        ActionCard card = getCard(i);
	        boolean wasDeleted = false;
	        switch (cardType) {
	            case 0:
	                // ROLL
	                if (card instanceof RollDieActionCard){
	                    card.delete();
	                    wasDeleted = true;
	                }
	                break;
	            case 1:
	                // CONN
	                if (card instanceof ConnectTilesActionCard){
	                    card.delete();
	                    wasDeleted = true;
	                }
	                break;
	            case 2:
	                // RMCONN
	                if (card instanceof RemoveConnectionActionCard){
	                    card.delete();
	                    wasDeleted = true;
	                }
	                break;
	            case 3:
	                // TELE
	                if (card instanceof TeleportActionCard){
	                    card.delete();
	                    wasDeleted = true;
	                }
	                break;
	            case 4:
	                // LOSE
	                if (card instanceof LoseTurnActionCard){
	                    card.delete();
	                    wasDeleted = true;
	                }
	                break;
	        }
	        if (wasDeleted) {
	            toRm--;
	            i--;
	        }
	        if (toRm == 0) break; 
	    }
	  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 54 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 1693774402081599849L ;

  
}
