/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 51 "../../../../../TileOPersistence.ump"
// line 497 "../../../../../TileO.ump"
public class Deck implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Deck Associations
  private List<ActionCard> cards;
  private ActionCard currentCard;
  private List<ConnectTilesActionCard> connectTilesActionCards;
  private List<LoseTurnActionCard> loseTurnActionCards;
  private List<RemoveConnectionActionCard> removeConnectionActionCards;
  private List<RemoveRandomTileActionCard> removeRandomTileActionCards;
  private List<RollDieActionCard> rollDieActionCards;
  private List<TeleportActionCard> teleportActionCards;
  private List<TurnInactiveActionCard> turnInactiveActionCards;
  private List<ChooseAdditionalMoveActionCard> chooseAdditionalMoveActionCards;
  private List<RevealTileActionCard> revealTileActionCards;
  private List<WinTileHintActionCard> winTileHintActionCards;
  private List<SendBackToStartActionCard> sendBackToStartActionCards;
  private List<SwapPositionActionCard> swapPositionActionCards;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Deck(Game aGame)
  {
    cards = new ArrayList<ActionCard>();
    connectTilesActionCards = new ArrayList<ConnectTilesActionCard>();
    loseTurnActionCards = new ArrayList<LoseTurnActionCard>();
    removeConnectionActionCards = new ArrayList<RemoveConnectionActionCard>();
    removeRandomTileActionCards = new ArrayList<RemoveRandomTileActionCard>();
    rollDieActionCards = new ArrayList<RollDieActionCard>();
    teleportActionCards = new ArrayList<TeleportActionCard>();
    turnInactiveActionCards = new ArrayList<TurnInactiveActionCard>();
    chooseAdditionalMoveActionCards = new ArrayList<ChooseAdditionalMoveActionCard>();
    revealTileActionCards = new ArrayList<RevealTileActionCard>();
    winTileHintActionCards = new ArrayList<WinTileHintActionCard>();
    sendBackToStartActionCards = new ArrayList<SendBackToStartActionCard>();
    swapPositionActionCards = new ArrayList<SwapPositionActionCard>();
    if (aGame == null || aGame.getDeck() != null)
    {
      throw new RuntimeException("Unable to create Deck due to aGame");
    }
    game = aGame;
  }

  public Deck(int aCurrentConnectionPiecesForGame, Die aDieForGame)
  {
    cards = new ArrayList<ActionCard>();
    connectTilesActionCards = new ArrayList<ConnectTilesActionCard>();
    loseTurnActionCards = new ArrayList<LoseTurnActionCard>();
    removeConnectionActionCards = new ArrayList<RemoveConnectionActionCard>();
    removeRandomTileActionCards = new ArrayList<RemoveRandomTileActionCard>();
    rollDieActionCards = new ArrayList<RollDieActionCard>();
    teleportActionCards = new ArrayList<TeleportActionCard>();
    turnInactiveActionCards = new ArrayList<TurnInactiveActionCard>();
    chooseAdditionalMoveActionCards = new ArrayList<ChooseAdditionalMoveActionCard>();
    revealTileActionCards = new ArrayList<RevealTileActionCard>();
    winTileHintActionCards = new ArrayList<WinTileHintActionCard>();
    sendBackToStartActionCards = new ArrayList<SendBackToStartActionCard>();
    swapPositionActionCards = new ArrayList<SwapPositionActionCard>();
    game = new Game(aCurrentConnectionPiecesForGame, this, aDieForGame);
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

  public ConnectTilesActionCard getConnectTilesActionCard(int index)
  {
    ConnectTilesActionCard aConnectTilesActionCard = connectTilesActionCards.get(index);
    return aConnectTilesActionCard;
  }

  public List<ConnectTilesActionCard> getConnectTilesActionCards()
  {
    List<ConnectTilesActionCard> newConnectTilesActionCards = Collections.unmodifiableList(connectTilesActionCards);
    return newConnectTilesActionCards;
  }

  public int numberOfConnectTilesActionCards()
  {
    int number = connectTilesActionCards.size();
    return number;
  }

  public boolean hasConnectTilesActionCards()
  {
    boolean has = connectTilesActionCards.size() > 0;
    return has;
  }

  public int indexOfConnectTilesActionCard(ConnectTilesActionCard aConnectTilesActionCard)
  {
    int index = connectTilesActionCards.indexOf(aConnectTilesActionCard);
    return index;
  }

  public LoseTurnActionCard getLoseTurnActionCard(int index)
  {
    LoseTurnActionCard aLoseTurnActionCard = loseTurnActionCards.get(index);
    return aLoseTurnActionCard;
  }

  public List<LoseTurnActionCard> getLoseTurnActionCards()
  {
    List<LoseTurnActionCard> newLoseTurnActionCards = Collections.unmodifiableList(loseTurnActionCards);
    return newLoseTurnActionCards;
  }

  public int numberOfLoseTurnActionCards()
  {
    int number = loseTurnActionCards.size();
    return number;
  }

  public boolean hasLoseTurnActionCards()
  {
    boolean has = loseTurnActionCards.size() > 0;
    return has;
  }

  public int indexOfLoseTurnActionCard(LoseTurnActionCard aLoseTurnActionCard)
  {
    int index = loseTurnActionCards.indexOf(aLoseTurnActionCard);
    return index;
  }

  public RemoveConnectionActionCard getRemoveConnectionActionCard(int index)
  {
    RemoveConnectionActionCard aRemoveConnectionActionCard = removeConnectionActionCards.get(index);
    return aRemoveConnectionActionCard;
  }

  public List<RemoveConnectionActionCard> getRemoveConnectionActionCards()
  {
    List<RemoveConnectionActionCard> newRemoveConnectionActionCards = Collections.unmodifiableList(removeConnectionActionCards);
    return newRemoveConnectionActionCards;
  }

  public int numberOfRemoveConnectionActionCards()
  {
    int number = removeConnectionActionCards.size();
    return number;
  }

  public boolean hasRemoveConnectionActionCards()
  {
    boolean has = removeConnectionActionCards.size() > 0;
    return has;
  }

  public int indexOfRemoveConnectionActionCard(RemoveConnectionActionCard aRemoveConnectionActionCard)
  {
    int index = removeConnectionActionCards.indexOf(aRemoveConnectionActionCard);
    return index;
  }

  public RemoveRandomTileActionCard getRemoveRandomTileActionCard(int index)
  {
    RemoveRandomTileActionCard aRemoveRandomTileActionCard = removeRandomTileActionCards.get(index);
    return aRemoveRandomTileActionCard;
  }

  public List<RemoveRandomTileActionCard> getRemoveRandomTileActionCards()
  {
    List<RemoveRandomTileActionCard> newRemoveRandomTileActionCards = Collections.unmodifiableList(removeRandomTileActionCards);
    return newRemoveRandomTileActionCards;
  }

  public int numberOfRemoveRandomTileActionCards()
  {
    int number = removeRandomTileActionCards.size();
    return number;
  }

  public boolean hasRemoveRandomTileActionCards()
  {
    boolean has = removeRandomTileActionCards.size() > 0;
    return has;
  }

  public int indexOfRemoveRandomTileActionCard(RemoveRandomTileActionCard aRemoveRandomTileActionCard)
  {
    int index = removeRandomTileActionCards.indexOf(aRemoveRandomTileActionCard);
    return index;
  }

  public RollDieActionCard getRollDieActionCard(int index)
  {
    RollDieActionCard aRollDieActionCard = rollDieActionCards.get(index);
    return aRollDieActionCard;
  }

  public List<RollDieActionCard> getRollDieActionCards()
  {
    List<RollDieActionCard> newRollDieActionCards = Collections.unmodifiableList(rollDieActionCards);
    return newRollDieActionCards;
  }

  public int numberOfRollDieActionCards()
  {
    int number = rollDieActionCards.size();
    return number;
  }

  public boolean hasRollDieActionCards()
  {
    boolean has = rollDieActionCards.size() > 0;
    return has;
  }

  public int indexOfRollDieActionCard(RollDieActionCard aRollDieActionCard)
  {
    int index = rollDieActionCards.indexOf(aRollDieActionCard);
    return index;
  }

  public TeleportActionCard getTeleportActionCard(int index)
  {
    TeleportActionCard aTeleportActionCard = teleportActionCards.get(index);
    return aTeleportActionCard;
  }

  public List<TeleportActionCard> getTeleportActionCards()
  {
    List<TeleportActionCard> newTeleportActionCards = Collections.unmodifiableList(teleportActionCards);
    return newTeleportActionCards;
  }

  public int numberOfTeleportActionCards()
  {
    int number = teleportActionCards.size();
    return number;
  }

  public boolean hasTeleportActionCards()
  {
    boolean has = teleportActionCards.size() > 0;
    return has;
  }

  public int indexOfTeleportActionCard(TeleportActionCard aTeleportActionCard)
  {
    int index = teleportActionCards.indexOf(aTeleportActionCard);
    return index;
  }

  public TurnInactiveActionCard getTurnInactiveActionCard(int index)
  {
    TurnInactiveActionCard aTurnInactiveActionCard = turnInactiveActionCards.get(index);
    return aTurnInactiveActionCard;
  }

  public List<TurnInactiveActionCard> getTurnInactiveActionCards()
  {
    List<TurnInactiveActionCard> newTurnInactiveActionCards = Collections.unmodifiableList(turnInactiveActionCards);
    return newTurnInactiveActionCards;
  }

  public int numberOfTurnInactiveActionCards()
  {
    int number = turnInactiveActionCards.size();
    return number;
  }

  public boolean hasTurnInactiveActionCards()
  {
    boolean has = turnInactiveActionCards.size() > 0;
    return has;
  }

  public int indexOfTurnInactiveActionCard(TurnInactiveActionCard aTurnInactiveActionCard)
  {
    int index = turnInactiveActionCards.indexOf(aTurnInactiveActionCard);
    return index;
  }

  public ChooseAdditionalMoveActionCard getChooseAdditionalMoveActionCard(int index)
  {
    ChooseAdditionalMoveActionCard aChooseAdditionalMoveActionCard = chooseAdditionalMoveActionCards.get(index);
    return aChooseAdditionalMoveActionCard;
  }

  public List<ChooseAdditionalMoveActionCard> getChooseAdditionalMoveActionCards()
  {
    List<ChooseAdditionalMoveActionCard> newChooseAdditionalMoveActionCards = Collections.unmodifiableList(chooseAdditionalMoveActionCards);
    return newChooseAdditionalMoveActionCards;
  }

  public int numberOfChooseAdditionalMoveActionCards()
  {
    int number = chooseAdditionalMoveActionCards.size();
    return number;
  }

  public boolean hasChooseAdditionalMoveActionCards()
  {
    boolean has = chooseAdditionalMoveActionCards.size() > 0;
    return has;
  }

  public int indexOfChooseAdditionalMoveActionCard(ChooseAdditionalMoveActionCard aChooseAdditionalMoveActionCard)
  {
    int index = chooseAdditionalMoveActionCards.indexOf(aChooseAdditionalMoveActionCard);
    return index;
  }

  public RevealTileActionCard getRevealTileActionCard(int index)
  {
    RevealTileActionCard aRevealTileActionCard = revealTileActionCards.get(index);
    return aRevealTileActionCard;
  }

  public List<RevealTileActionCard> getRevealTileActionCards()
  {
    List<RevealTileActionCard> newRevealTileActionCards = Collections.unmodifiableList(revealTileActionCards);
    return newRevealTileActionCards;
  }

  public int numberOfRevealTileActionCards()
  {
    int number = revealTileActionCards.size();
    return number;
  }

  public boolean hasRevealTileActionCards()
  {
    boolean has = revealTileActionCards.size() > 0;
    return has;
  }

  public int indexOfRevealTileActionCard(RevealTileActionCard aRevealTileActionCard)
  {
    int index = revealTileActionCards.indexOf(aRevealTileActionCard);
    return index;
  }

  public WinTileHintActionCard getWinTileHintActionCard(int index)
  {
    WinTileHintActionCard aWinTileHintActionCard = winTileHintActionCards.get(index);
    return aWinTileHintActionCard;
  }

  public List<WinTileHintActionCard> getWinTileHintActionCards()
  {
    List<WinTileHintActionCard> newWinTileHintActionCards = Collections.unmodifiableList(winTileHintActionCards);
    return newWinTileHintActionCards;
  }

  public int numberOfWinTileHintActionCards()
  {
    int number = winTileHintActionCards.size();
    return number;
  }

  public boolean hasWinTileHintActionCards()
  {
    boolean has = winTileHintActionCards.size() > 0;
    return has;
  }

  public int indexOfWinTileHintActionCard(WinTileHintActionCard aWinTileHintActionCard)
  {
    int index = winTileHintActionCards.indexOf(aWinTileHintActionCard);
    return index;
  }

  public SendBackToStartActionCard getSendBackToStartActionCard(int index)
  {
    SendBackToStartActionCard aSendBackToStartActionCard = sendBackToStartActionCards.get(index);
    return aSendBackToStartActionCard;
  }

  public List<SendBackToStartActionCard> getSendBackToStartActionCards()
  {
    List<SendBackToStartActionCard> newSendBackToStartActionCards = Collections.unmodifiableList(sendBackToStartActionCards);
    return newSendBackToStartActionCards;
  }

  public int numberOfSendBackToStartActionCards()
  {
    int number = sendBackToStartActionCards.size();
    return number;
  }

  public boolean hasSendBackToStartActionCards()
  {
    boolean has = sendBackToStartActionCards.size() > 0;
    return has;
  }

  public int indexOfSendBackToStartActionCard(SendBackToStartActionCard aSendBackToStartActionCard)
  {
    int index = sendBackToStartActionCards.indexOf(aSendBackToStartActionCard);
    return index;
  }

  public SwapPositionActionCard getSwapPositionActionCard(int index)
  {
    SwapPositionActionCard aSwapPositionActionCard = swapPositionActionCards.get(index);
    return aSwapPositionActionCard;
  }

  public List<SwapPositionActionCard> getSwapPositionActionCards()
  {
    List<SwapPositionActionCard> newSwapPositionActionCards = Collections.unmodifiableList(swapPositionActionCards);
    return newSwapPositionActionCards;
  }

  public int numberOfSwapPositionActionCards()
  {
    int number = swapPositionActionCards.size();
    return number;
  }

  public boolean hasSwapPositionActionCards()
  {
    boolean has = swapPositionActionCards.size() > 0;
    return has;
  }

  public int indexOfSwapPositionActionCard(SwapPositionActionCard aSwapPositionActionCard)
  {
    int index = swapPositionActionCards.indexOf(aSwapPositionActionCard);
    return index;
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

  public static int minimumNumberOfConnectTilesActionCards()
  {
    return 0;
  }

  public boolean addConnectTilesActionCard(ConnectTilesActionCard aConnectTilesActionCard)
  {
    boolean wasAdded = false;
    if (connectTilesActionCards.contains(aConnectTilesActionCard)) { return false; }
    connectTilesActionCards.add(aConnectTilesActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeConnectTilesActionCard(ConnectTilesActionCard aConnectTilesActionCard)
  {
    boolean wasRemoved = false;
    if (connectTilesActionCards.contains(aConnectTilesActionCard))
    {
      connectTilesActionCards.remove(aConnectTilesActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addConnectTilesActionCardAt(ConnectTilesActionCard aConnectTilesActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addConnectTilesActionCard(aConnectTilesActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnectTilesActionCards()) { index = numberOfConnectTilesActionCards() - 1; }
      connectTilesActionCards.remove(aConnectTilesActionCard);
      connectTilesActionCards.add(index, aConnectTilesActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveConnectTilesActionCardAt(ConnectTilesActionCard aConnectTilesActionCard, int index)
  {
    boolean wasAdded = false;
    if(connectTilesActionCards.contains(aConnectTilesActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnectTilesActionCards()) { index = numberOfConnectTilesActionCards() - 1; }
      connectTilesActionCards.remove(aConnectTilesActionCard);
      connectTilesActionCards.add(index, aConnectTilesActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addConnectTilesActionCardAt(aConnectTilesActionCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfLoseTurnActionCards()
  {
    return 0;
  }

  public boolean addLoseTurnActionCard(LoseTurnActionCard aLoseTurnActionCard)
  {
    boolean wasAdded = false;
    if (loseTurnActionCards.contains(aLoseTurnActionCard)) { return false; }
    loseTurnActionCards.add(aLoseTurnActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLoseTurnActionCard(LoseTurnActionCard aLoseTurnActionCard)
  {
    boolean wasRemoved = false;
    if (loseTurnActionCards.contains(aLoseTurnActionCard))
    {
      loseTurnActionCards.remove(aLoseTurnActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addLoseTurnActionCardAt(LoseTurnActionCard aLoseTurnActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addLoseTurnActionCard(aLoseTurnActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLoseTurnActionCards()) { index = numberOfLoseTurnActionCards() - 1; }
      loseTurnActionCards.remove(aLoseTurnActionCard);
      loseTurnActionCards.add(index, aLoseTurnActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLoseTurnActionCardAt(LoseTurnActionCard aLoseTurnActionCard, int index)
  {
    boolean wasAdded = false;
    if(loseTurnActionCards.contains(aLoseTurnActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLoseTurnActionCards()) { index = numberOfLoseTurnActionCards() - 1; }
      loseTurnActionCards.remove(aLoseTurnActionCard);
      loseTurnActionCards.add(index, aLoseTurnActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLoseTurnActionCardAt(aLoseTurnActionCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfRemoveConnectionActionCards()
  {
    return 0;
  }

  public boolean addRemoveConnectionActionCard(RemoveConnectionActionCard aRemoveConnectionActionCard)
  {
    boolean wasAdded = false;
    if (removeConnectionActionCards.contains(aRemoveConnectionActionCard)) { return false; }
    removeConnectionActionCards.add(aRemoveConnectionActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRemoveConnectionActionCard(RemoveConnectionActionCard aRemoveConnectionActionCard)
  {
    boolean wasRemoved = false;
    if (removeConnectionActionCards.contains(aRemoveConnectionActionCard))
    {
      removeConnectionActionCards.remove(aRemoveConnectionActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addRemoveConnectionActionCardAt(RemoveConnectionActionCard aRemoveConnectionActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addRemoveConnectionActionCard(aRemoveConnectionActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRemoveConnectionActionCards()) { index = numberOfRemoveConnectionActionCards() - 1; }
      removeConnectionActionCards.remove(aRemoveConnectionActionCard);
      removeConnectionActionCards.add(index, aRemoveConnectionActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRemoveConnectionActionCardAt(RemoveConnectionActionCard aRemoveConnectionActionCard, int index)
  {
    boolean wasAdded = false;
    if(removeConnectionActionCards.contains(aRemoveConnectionActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRemoveConnectionActionCards()) { index = numberOfRemoveConnectionActionCards() - 1; }
      removeConnectionActionCards.remove(aRemoveConnectionActionCard);
      removeConnectionActionCards.add(index, aRemoveConnectionActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRemoveConnectionActionCardAt(aRemoveConnectionActionCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfRemoveRandomTileActionCards()
  {
    return 0;
  }

  public boolean addRemoveRandomTileActionCard(RemoveRandomTileActionCard aRemoveRandomTileActionCard)
  {
    boolean wasAdded = false;
    if (removeRandomTileActionCards.contains(aRemoveRandomTileActionCard)) { return false; }
    removeRandomTileActionCards.add(aRemoveRandomTileActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRemoveRandomTileActionCard(RemoveRandomTileActionCard aRemoveRandomTileActionCard)
  {
    boolean wasRemoved = false;
    if (removeRandomTileActionCards.contains(aRemoveRandomTileActionCard))
    {
      removeRandomTileActionCards.remove(aRemoveRandomTileActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addRemoveRandomTileActionCardAt(RemoveRandomTileActionCard aRemoveRandomTileActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addRemoveRandomTileActionCard(aRemoveRandomTileActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRemoveRandomTileActionCards()) { index = numberOfRemoveRandomTileActionCards() - 1; }
      removeRandomTileActionCards.remove(aRemoveRandomTileActionCard);
      removeRandomTileActionCards.add(index, aRemoveRandomTileActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRemoveRandomTileActionCardAt(RemoveRandomTileActionCard aRemoveRandomTileActionCard, int index)
  {
    boolean wasAdded = false;
    if(removeRandomTileActionCards.contains(aRemoveRandomTileActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRemoveRandomTileActionCards()) { index = numberOfRemoveRandomTileActionCards() - 1; }
      removeRandomTileActionCards.remove(aRemoveRandomTileActionCard);
      removeRandomTileActionCards.add(index, aRemoveRandomTileActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRemoveRandomTileActionCardAt(aRemoveRandomTileActionCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfRollDieActionCards()
  {
    return 0;
  }

  public boolean addRollDieActionCard(RollDieActionCard aRollDieActionCard)
  {
    boolean wasAdded = false;
    if (rollDieActionCards.contains(aRollDieActionCard)) { return false; }
    rollDieActionCards.add(aRollDieActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRollDieActionCard(RollDieActionCard aRollDieActionCard)
  {
    boolean wasRemoved = false;
    if (rollDieActionCards.contains(aRollDieActionCard))
    {
      rollDieActionCards.remove(aRollDieActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addRollDieActionCardAt(RollDieActionCard aRollDieActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addRollDieActionCard(aRollDieActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRollDieActionCards()) { index = numberOfRollDieActionCards() - 1; }
      rollDieActionCards.remove(aRollDieActionCard);
      rollDieActionCards.add(index, aRollDieActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRollDieActionCardAt(RollDieActionCard aRollDieActionCard, int index)
  {
    boolean wasAdded = false;
    if(rollDieActionCards.contains(aRollDieActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRollDieActionCards()) { index = numberOfRollDieActionCards() - 1; }
      rollDieActionCards.remove(aRollDieActionCard);
      rollDieActionCards.add(index, aRollDieActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRollDieActionCardAt(aRollDieActionCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTeleportActionCards()
  {
    return 0;
  }

  public boolean addTeleportActionCard(TeleportActionCard aTeleportActionCard)
  {
    boolean wasAdded = false;
    if (teleportActionCards.contains(aTeleportActionCard)) { return false; }
    teleportActionCards.add(aTeleportActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTeleportActionCard(TeleportActionCard aTeleportActionCard)
  {
    boolean wasRemoved = false;
    if (teleportActionCards.contains(aTeleportActionCard))
    {
      teleportActionCards.remove(aTeleportActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTeleportActionCardAt(TeleportActionCard aTeleportActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addTeleportActionCard(aTeleportActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTeleportActionCards()) { index = numberOfTeleportActionCards() - 1; }
      teleportActionCards.remove(aTeleportActionCard);
      teleportActionCards.add(index, aTeleportActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTeleportActionCardAt(TeleportActionCard aTeleportActionCard, int index)
  {
    boolean wasAdded = false;
    if(teleportActionCards.contains(aTeleportActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTeleportActionCards()) { index = numberOfTeleportActionCards() - 1; }
      teleportActionCards.remove(aTeleportActionCard);
      teleportActionCards.add(index, aTeleportActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTeleportActionCardAt(aTeleportActionCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTurnInactiveActionCards()
  {
    return 0;
  }

  public boolean addTurnInactiveActionCard(TurnInactiveActionCard aTurnInactiveActionCard)
  {
    boolean wasAdded = false;
    if (turnInactiveActionCards.contains(aTurnInactiveActionCard)) { return false; }
    turnInactiveActionCards.add(aTurnInactiveActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTurnInactiveActionCard(TurnInactiveActionCard aTurnInactiveActionCard)
  {
    boolean wasRemoved = false;
    if (turnInactiveActionCards.contains(aTurnInactiveActionCard))
    {
      turnInactiveActionCards.remove(aTurnInactiveActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTurnInactiveActionCardAt(TurnInactiveActionCard aTurnInactiveActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addTurnInactiveActionCard(aTurnInactiveActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTurnInactiveActionCards()) { index = numberOfTurnInactiveActionCards() - 1; }
      turnInactiveActionCards.remove(aTurnInactiveActionCard);
      turnInactiveActionCards.add(index, aTurnInactiveActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTurnInactiveActionCardAt(TurnInactiveActionCard aTurnInactiveActionCard, int index)
  {
    boolean wasAdded = false;
    if(turnInactiveActionCards.contains(aTurnInactiveActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTurnInactiveActionCards()) { index = numberOfTurnInactiveActionCards() - 1; }
      turnInactiveActionCards.remove(aTurnInactiveActionCard);
      turnInactiveActionCards.add(index, aTurnInactiveActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTurnInactiveActionCardAt(aTurnInactiveActionCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfChooseAdditionalMoveActionCards()
  {
    return 0;
  }

  public boolean addChooseAdditionalMoveActionCard(ChooseAdditionalMoveActionCard aChooseAdditionalMoveActionCard)
  {
    boolean wasAdded = false;
    if (chooseAdditionalMoveActionCards.contains(aChooseAdditionalMoveActionCard)) { return false; }
    chooseAdditionalMoveActionCards.add(aChooseAdditionalMoveActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeChooseAdditionalMoveActionCard(ChooseAdditionalMoveActionCard aChooseAdditionalMoveActionCard)
  {
    boolean wasRemoved = false;
    if (chooseAdditionalMoveActionCards.contains(aChooseAdditionalMoveActionCard))
    {
      chooseAdditionalMoveActionCards.remove(aChooseAdditionalMoveActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addChooseAdditionalMoveActionCardAt(ChooseAdditionalMoveActionCard aChooseAdditionalMoveActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addChooseAdditionalMoveActionCard(aChooseAdditionalMoveActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChooseAdditionalMoveActionCards()) { index = numberOfChooseAdditionalMoveActionCards() - 1; }
      chooseAdditionalMoveActionCards.remove(aChooseAdditionalMoveActionCard);
      chooseAdditionalMoveActionCards.add(index, aChooseAdditionalMoveActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveChooseAdditionalMoveActionCardAt(ChooseAdditionalMoveActionCard aChooseAdditionalMoveActionCard, int index)
  {
    boolean wasAdded = false;
    if(chooseAdditionalMoveActionCards.contains(aChooseAdditionalMoveActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChooseAdditionalMoveActionCards()) { index = numberOfChooseAdditionalMoveActionCards() - 1; }
      chooseAdditionalMoveActionCards.remove(aChooseAdditionalMoveActionCard);
      chooseAdditionalMoveActionCards.add(index, aChooseAdditionalMoveActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addChooseAdditionalMoveActionCardAt(aChooseAdditionalMoveActionCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfRevealTileActionCards()
  {
    return 0;
  }

  public boolean addRevealTileActionCard(RevealTileActionCard aRevealTileActionCard)
  {
    boolean wasAdded = false;
    if (revealTileActionCards.contains(aRevealTileActionCard)) { return false; }
    revealTileActionCards.add(aRevealTileActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRevealTileActionCard(RevealTileActionCard aRevealTileActionCard)
  {
    boolean wasRemoved = false;
    if (revealTileActionCards.contains(aRevealTileActionCard))
    {
      revealTileActionCards.remove(aRevealTileActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addRevealTileActionCardAt(RevealTileActionCard aRevealTileActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addRevealTileActionCard(aRevealTileActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRevealTileActionCards()) { index = numberOfRevealTileActionCards() - 1; }
      revealTileActionCards.remove(aRevealTileActionCard);
      revealTileActionCards.add(index, aRevealTileActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRevealTileActionCardAt(RevealTileActionCard aRevealTileActionCard, int index)
  {
    boolean wasAdded = false;
    if(revealTileActionCards.contains(aRevealTileActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRevealTileActionCards()) { index = numberOfRevealTileActionCards() - 1; }
      revealTileActionCards.remove(aRevealTileActionCard);
      revealTileActionCards.add(index, aRevealTileActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRevealTileActionCardAt(aRevealTileActionCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfWinTileHintActionCards()
  {
    return 0;
  }

  public boolean addWinTileHintActionCard(WinTileHintActionCard aWinTileHintActionCard)
  {
    boolean wasAdded = false;
    if (winTileHintActionCards.contains(aWinTileHintActionCard)) { return false; }
    winTileHintActionCards.add(aWinTileHintActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWinTileHintActionCard(WinTileHintActionCard aWinTileHintActionCard)
  {
    boolean wasRemoved = false;
    if (winTileHintActionCards.contains(aWinTileHintActionCard))
    {
      winTileHintActionCards.remove(aWinTileHintActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addWinTileHintActionCardAt(WinTileHintActionCard aWinTileHintActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addWinTileHintActionCard(aWinTileHintActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWinTileHintActionCards()) { index = numberOfWinTileHintActionCards() - 1; }
      winTileHintActionCards.remove(aWinTileHintActionCard);
      winTileHintActionCards.add(index, aWinTileHintActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWinTileHintActionCardAt(WinTileHintActionCard aWinTileHintActionCard, int index)
  {
    boolean wasAdded = false;
    if(winTileHintActionCards.contains(aWinTileHintActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWinTileHintActionCards()) { index = numberOfWinTileHintActionCards() - 1; }
      winTileHintActionCards.remove(aWinTileHintActionCard);
      winTileHintActionCards.add(index, aWinTileHintActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWinTileHintActionCardAt(aWinTileHintActionCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSendBackToStartActionCards()
  {
    return 0;
  }

  public boolean addSendBackToStartActionCard(SendBackToStartActionCard aSendBackToStartActionCard)
  {
    boolean wasAdded = false;
    if (sendBackToStartActionCards.contains(aSendBackToStartActionCard)) { return false; }
    sendBackToStartActionCards.add(aSendBackToStartActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSendBackToStartActionCard(SendBackToStartActionCard aSendBackToStartActionCard)
  {
    boolean wasRemoved = false;
    if (sendBackToStartActionCards.contains(aSendBackToStartActionCard))
    {
      sendBackToStartActionCards.remove(aSendBackToStartActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSendBackToStartActionCardAt(SendBackToStartActionCard aSendBackToStartActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addSendBackToStartActionCard(aSendBackToStartActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSendBackToStartActionCards()) { index = numberOfSendBackToStartActionCards() - 1; }
      sendBackToStartActionCards.remove(aSendBackToStartActionCard);
      sendBackToStartActionCards.add(index, aSendBackToStartActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSendBackToStartActionCardAt(SendBackToStartActionCard aSendBackToStartActionCard, int index)
  {
    boolean wasAdded = false;
    if(sendBackToStartActionCards.contains(aSendBackToStartActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSendBackToStartActionCards()) { index = numberOfSendBackToStartActionCards() - 1; }
      sendBackToStartActionCards.remove(aSendBackToStartActionCard);
      sendBackToStartActionCards.add(index, aSendBackToStartActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSendBackToStartActionCardAt(aSendBackToStartActionCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSwapPositionActionCards()
  {
    return 0;
  }

  public boolean addSwapPositionActionCard(SwapPositionActionCard aSwapPositionActionCard)
  {
    boolean wasAdded = false;
    if (swapPositionActionCards.contains(aSwapPositionActionCard)) { return false; }
    swapPositionActionCards.add(aSwapPositionActionCard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSwapPositionActionCard(SwapPositionActionCard aSwapPositionActionCard)
  {
    boolean wasRemoved = false;
    if (swapPositionActionCards.contains(aSwapPositionActionCard))
    {
      swapPositionActionCards.remove(aSwapPositionActionCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSwapPositionActionCardAt(SwapPositionActionCard aSwapPositionActionCard, int index)
  {  
    boolean wasAdded = false;
    if(addSwapPositionActionCard(aSwapPositionActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSwapPositionActionCards()) { index = numberOfSwapPositionActionCards() - 1; }
      swapPositionActionCards.remove(aSwapPositionActionCard);
      swapPositionActionCards.add(index, aSwapPositionActionCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSwapPositionActionCardAt(SwapPositionActionCard aSwapPositionActionCard, int index)
  {
    boolean wasAdded = false;
    if(swapPositionActionCards.contains(aSwapPositionActionCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSwapPositionActionCards()) { index = numberOfSwapPositionActionCards() - 1; }
      swapPositionActionCards.remove(aSwapPositionActionCard);
      swapPositionActionCards.add(index, aSwapPositionActionCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSwapPositionActionCardAt(aSwapPositionActionCard, index);
    }
    return wasAdded;
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
    connectTilesActionCards.clear();
    loseTurnActionCards.clear();
    removeConnectionActionCards.clear();
    removeRandomTileActionCards.clear();
    rollDieActionCards.clear();
    teleportActionCards.clear();
    turnInactiveActionCards.clear();
    chooseAdditionalMoveActionCards.clear();
    revealTileActionCards.clear();
    winTileHintActionCards.clear();
    sendBackToStartActionCards.clear();
    swapPositionActionCards.clear();
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }

  // line 515 "../../../../../TileO.ump"
   public void shuffle(){
    Random rand = new Random();
    for (int i=0; i<100; ++i){
        ActionCard card = getCard(0);
        addOrMoveCardAt(card, rand.nextInt(Game.NumberOfActionCards));
    }
    setCurrentCard(getCard(0));
  }

  // line 524 "../../../../../TileO.ump"
   public void print(){
    System.out.println("~~~ DECK ~~~~");
    System.out.println("RollDieActionCard: "+numberOfCardsForType(0));
    System.out.println("ConnectTilesActionCard: "+numberOfCardsForType(1));
    System.out.println("RemoveConnectionActionCard: "+numberOfCardsForType(2));
    System.out.println("TeleportActionCard: "+numberOfCardsForType(3));
    System.out.println("LoseTurnActionCard: "+numberOfCardsForType(4));
    System.out.println("RemoveRandomTileActionCard: "+numberOfCardsForType(5));
    System.out.println("TurnInactiveActionCard: "+numberOfCardsForType(6));
    System.out.println("ChooseAdditionalMoveActionCard: "+numberOfCardsForType(7));
    System.out.println("RevealTileActionCard: "+numberOfCardsForType(8));
    System.out.println("WinTileHintActionCard: "+numberOfCardsForType(10));
    System.out.println("SendToStartActionCard: "+numberOfCardsForType(9));
    System.out.println("SwapPoisitionActionCard: "+numberOfCardsForType(11));
    System.out.println("Total: "+numberOfCards());
  }

  // line 541 "../../../../../TileO.ump"
   public int numberOfCardsForType(int type){
    switch (type) {
	   		case 0:
	   			return numberOfRollDieActionCards();
	   		case 1:
	   			return numberOfConnectTilesActionCards();
	   		case 2:
	   			return numberOfRemoveConnectionActionCards();
	   		case 3:
	   			return numberOfTeleportActionCards();
	   		case 4:
	   			return numberOfLoseTurnActionCards();
	   		case 5:
	   			return numberOfRemoveRandomTileActionCards();
	   		case 6:
	   			return numberOfTurnInactiveActionCards();
	   		case 7:
	   			return numberOfChooseAdditionalMoveActionCards();
	   		case 8:
	   			return numberOfRevealTileActionCards();
	   		case 10:
	   			return numberOfWinTileHintActionCards();
	   		case 9:
                return numberOfSendBackToStartActionCards();
	   		case 11:
	   			return numberOfSwapPositionActionCards();
	   		default:
	   			throw new RuntimeException("Card type not supported");
     }
  }

  // line 572 "../../../../../TileO.ump"
   public void addCards(int n, int cardType){
    if (n > maximumNumberOfCards())
    	n = n%maximumNumberOfCards();
	if (numberOfCards() + n > maximumNumberOfCards()) 
		n = maximumNumberOfCards() - n;
    switch (cardType) {
	        case 0:
	            // ROLL
	            for (int i=0; i<n; ++i)
	                addRollDieActionCard(new RollDieActionCard("Roll the die for an extra turn", this));
	            break;
	        case 1:
	            // CONN
	    		for (int i=0;i<n;++i)
	                addConnectTilesActionCard(new ConnectTilesActionCard("Connect two tiles", this));
	            break;
	        case 2:
	            // RMCONN
	    		for (int i=0;i<n;++i)
	    		    addRemoveConnectionActionCard(new RemoveConnectionActionCard("Remove a connection", this));
	            break;
	        case 3:
	            // TELE
	    		for (int i=0;i<n;++i)
	    		    addTeleportActionCard(new TeleportActionCard("Move your piece to a new tile", this));
	            break;
	        case 4:
	            // LOSE
	    		for (int i=0;i<n;++i)
	    		    addLoseTurnActionCard(new LoseTurnActionCard("Lose your next turn", this));
	            break;
	        case 5:
	            // RMRANDOM
	    		for (int i=0;i<n;++i)
	    		    addRemoveRandomTileActionCard(new RemoveRandomTileActionCard("Remove a random tile", this));
	            break;
	        case 6:
	            // TURNINACTIVE
	    		for (int i=0;i<n;++i)
	    		    addTurnInactiveActionCard(new TurnInactiveActionCard("Turns all action tile inactive", this));
	            break;
	        case 7:
	            // CHOOSEADDMOVE
	    		for (int i=0;i<n;++i)
	    		    addChooseAdditionalMoveActionCard(new ChooseAdditionalMoveActionCard("Choose additional move", this));
	            break;
	        case 8:
	        	// REVEAL
	        	for (int i=0;i<n;++i) 
	        		addRevealTileActionCard(new RevealTileActionCard("Reveal a tile", this));
	        	break;
	        case 10:
	        	// WINHINT
	        	for (int i=0;i<n;++i) 
	        		addWinTileHintActionCard(new WinTileHintActionCard("Win tile hint", this));
	        	break;
	        case 9:
	        	// START
                for (int i=0;i<n;++i) 
                    addSendBackToStartActionCard(new SendBackToStartActionCard("Send other player to start", this));
                break;
	        case 11:
	        	// SWAP
	        	for (int i=0;i<n;++i) 
	        		addSwapPositionActionCard(new SwapPositionActionCard("Swap position", this));
	        	break;
	        default:
	        	throw new RuntimeException("Card type not implemented");
	    }
  }

  // line 641 "../../../../../TileO.ump"
   public void rmCards(int toRm, int cardType){
    ActionCard c;
	   for (int i=0; i<toRm; ++i) {	
		   switch (cardType) {
		   		case 0:
		   			c = getRollDieActionCard(0);
		   			removeRollDieActionCard((RollDieActionCard)c);
		   			c.delete();
		   			break;
		   		case 1:
		   			c = getConnectTilesActionCard(0);
		   			removeConnectTilesActionCard((ConnectTilesActionCard)c);
		   			c.delete();
		   			break;
		   		case 2:
		   			c = getRemoveConnectionActionCard(0);
		   			removeRemoveConnectionActionCard((RemoveConnectionActionCard)c);
		   			c.delete();
		   			break;
		   		case 3:
		   			c = getTeleportActionCard(0);
		   			removeTeleportActionCard((TeleportActionCard)c);
		   			c.delete();
		   			break;
		   		case 4:
		   			c = getLoseTurnActionCard(0);
		   			removeLoseTurnActionCard((LoseTurnActionCard)c);
		   			c.delete();
		   			break;
		   		case 5:
		   			c = getRemoveRandomTileActionCard(0);
		   			removeRemoveRandomTileActionCard((RemoveRandomTileActionCard)c);
		   			c.delete();
		   			break;
		   		case 6:
		   			c = getTurnInactiveActionCard(0);
		   			removeTurnInactiveActionCard((TurnInactiveActionCard)c);
		   			c.delete();
		   			break;
		   		case 7:
		   			c = getChooseAdditionalMoveActionCard(0);
		   			removeChooseAdditionalMoveActionCard((ChooseAdditionalMoveActionCard)c);
		   			c.delete();
		   			break;
		   		case 8:
		   			c = getRevealTileActionCard(0);
		   			removeRevealTileActionCard((RevealTileActionCard)c);
		   			c.delete();
		   			break;
		   		case 10:
		   			c = getWinTileHintActionCard(0);
		   			removeWinTileHintActionCard((WinTileHintActionCard)c);
		   			c.delete();
		   			break;
		   		case 9:
		   			c = getSendBackToStartActionCard(0);
		   			removeSendBackToStartActionCard((SendBackToStartActionCard)c);
		   			c.delete();
		   			break;
		   		case 11:
		   			c = getSwapPositionActionCard(0);
		   			removeSwapPositionActionCard((SwapPositionActionCard)c);
		   			c.delete();
		   			break;
		   		default:
		   			throw new RuntimeException("Card type not supported");
		   	}
	   }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 54 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 1693774402081599849L ;

  
}