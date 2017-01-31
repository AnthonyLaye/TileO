/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 54 "../../../../../model.ump"
public class Deck
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Deck Associations
  private List<Card> cards;
  private BoardGame boardGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Deck(BoardGame aBoardGame)
  {
    cards = new ArrayList<Card>();
    if (aBoardGame == null || aBoardGame.getDeck() != null)
    {
      throw new RuntimeException("Unable to create Deck due to aBoardGame");
    }
    boardGame = aBoardGame;
  }

  public Deck(Layout aGridForBoardGame)
  {
    cards = new ArrayList<Card>();
    boardGame = new BoardGame(this, aGridForBoardGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Card getCard(int index)
  {
    Card aCard = cards.get(index);
    return aCard;
  }

  public List<Card> getCards()
  {
    List<Card> newCards = Collections.unmodifiableList(cards);
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

  public int indexOfCard(Card aCard)
  {
    int index = cards.indexOf(aCard);
    return index;
  }

  public BoardGame getBoardGame()
  {
    return boardGame;
  }

  public boolean isNumberOfCardsValid()
  {
    boolean isValid = numberOfCards() >= minimumNumberOfCards() && numberOfCards() <= maximumNumberOfCards();
    return isValid;
  }

  public static int requiredNumberOfCards()
  {
    return 32;
  }

  public static int minimumNumberOfCards()
  {
    return 32;
  }

  public static int maximumNumberOfCards()
  {
    return 32;
  }

  public boolean addCard(Card aCard)
  {
    boolean wasAdded = false;
    if (cards.contains(aCard)) { return false; }
    if (numberOfCards() >= maximumNumberOfCards())
    {
      return wasAdded;
    }

    Deck existingDeck = aCard.getDeck();
    boolean isNewDeck = existingDeck != null && !this.equals(existingDeck);

    if (isNewDeck && existingDeck.numberOfCards() <= minimumNumberOfCards())
    {
      return wasAdded;
    }

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

  public boolean removeCard(Card aCard)
  {
    boolean wasRemoved = false;
    //Unable to remove aCard, as it must always have a deck
    if (this.equals(aCard.getDeck()))
    {
      return wasRemoved;
    }

    //deck already at minimum (32)
    if (numberOfCards() <= minimumNumberOfCards())
    {
      return wasRemoved;
    }
    cards.remove(aCard);
    wasRemoved = true;
    return wasRemoved;
  }

  public void delete()
  {
    while (cards.size() > 0)
    {
      Card aCard = cards.get(cards.size() - 1);
      aCard.delete();
      cards.remove(aCard);
    }
    
    BoardGame existingBoardGame = boardGame;
    boardGame = null;
    if (existingBoardGame != null)
    {
      existingBoardGame.delete();
    }
  }

}