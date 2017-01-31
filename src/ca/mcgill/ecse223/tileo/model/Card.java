/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

// line 58 "../../../../../model.ump"
public class Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Card Attributes
  private CardAction action;

  //Card State Machines
  public enum CardAction { RollDie, AddConnection, RemoveConnection, MovePiece, LoseTurn }
  private CardAction cardAction;

  //Card Associations
  private Deck deck;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Card(CardAction aAction, Deck aDeck)
  {
    action = aAction;
    boolean didAddDeck = setDeck(aDeck);
    if (!didAddDeck)
    {
      throw new RuntimeException("Unable to create card due to deck");
    }
    setCardAction(CardAction.RollDie);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAction(CardAction aAction)
  {
    boolean wasSet = false;
    action = aAction;
    wasSet = true;
    return wasSet;
  }

  public CardAction getAction()
  {
    return action;
  }

  public String getCardActionFullName()
  {
    String answer = cardAction.toString();
    return answer;
  }

  public CardAction getCardAction()
  {
    return cardAction;
  }

  public boolean setCardAction(CardAction aCardAction)
  {
    cardAction = aCardAction;
    return true;
  }

  public Deck getDeck()
  {
    return deck;
  }

  public boolean setDeck(Deck aDeck)
  {
    boolean wasSet = false;
    //Must provide deck to card
    if (aDeck == null)
    {
      return wasSet;
    }

    //deck already at maximum (32)
    if (aDeck.numberOfCards() >= Deck.maximumNumberOfCards())
    {
      return wasSet;
    }
    
    Deck existingDeck = deck;
    deck = aDeck;
    if (existingDeck != null && !existingDeck.equals(aDeck))
    {
      boolean didRemove = existingDeck.removeCard(this);
      if (!didRemove)
      {
        deck = existingDeck;
        return wasSet;
      }
    }
    deck.addCard(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Deck placeholderDeck = deck;
    this.deck = null;
    placeholderDeck.removeCard(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "action" + "=" + (getAction() != null ? !getAction().equals(this)  ? getAction().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "deck = "+(getDeck()!=null?Integer.toHexString(System.identityHashCode(getDeck())):"null")
     + outputString;
  }
}