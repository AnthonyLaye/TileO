/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 27 "../../../../../TileOPersistence.ump"
// line 3 "../../../../../ActionTileState.ump"
// line 392 "../../../../../TileO.ump"
public class ActionTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionTile Attributes
  private int inactivityPeriod;
  private int turnsUntilActive;

  //ActionTile State Machines
  public enum InactivityStatus { Active, Inactive }
  private InactivityStatus inactivityStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionTile(int aX, int aY, Game aGame, int aInactivityPeriod)
  {
    super(aX, aY, aGame);
    inactivityPeriod = aInactivityPeriod;
    turnsUntilActive = 0;
    setInactivityStatus(InactivityStatus.Active);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTurnsUntilActive(int aTurnsUntilActive)
  {
    boolean wasSet = false;
    turnsUntilActive = aTurnsUntilActive;
    wasSet = true;
    return wasSet;
  }

  public int getInactivityPeriod()
  {
    return inactivityPeriod;
  }

  public int getTurnsUntilActive()
  {
    return turnsUntilActive;
  }

  public String getInactivityStatusFullName()
  {
    String answer = inactivityStatus.toString();
    return answer;
  }

  public InactivityStatus getInactivityStatus()
  {
    return inactivityStatus;
  }

  public void land()
  {
    boolean wasEventProcessed = false;
    
    InactivityStatus aInactivityStatus = inactivityStatus;
    switch (aInactivityStatus)
    {
      case Active:
        // line 6 "../../../../../ActionTileState.ump"
        doLand();
  			setTurnsUntilActive(inactivityPeriod+1);
        setInactivityStatus(InactivityStatus.Inactive);
        wasEventProcessed = true;
        break;
      case Inactive:
        // line 18 "../../../../../ActionTileState.ump"
        doLandAsNormal();
        setInactivityStatus(InactivityStatus.Inactive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }
  }

  public boolean deactivate()
  {
    boolean wasEventProcessed = false;
    
    InactivityStatus aInactivityStatus = inactivityStatus;
    switch (aInactivityStatus)
    {
      case Active:
        // line 10 "../../../../../ActionTileState.ump"
        setTurnsUntilActive(inactivityPeriod+1);
  			getGame().addInactiveActionTile(this);
        setInactivityStatus(InactivityStatus.Inactive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean inactivityPeriodCompleted()
  {
    boolean wasEventProcessed = false;
    
    InactivityStatus aInactivityStatus = inactivityStatus;
    switch (aInactivityStatus)
    {
      case Inactive:
        setInactivityStatus(InactivityStatus.Active);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setInactivityStatus(InactivityStatus aInactivityStatus)
  {
    inactivityStatus = aInactivityStatus;
  }

  public void delete()
  {
    super.delete();
  }

  // line 399 "../../../../../TileO.ump"
   public void doLand(){
    Game currentGame = getGame();
    currentGame.addInactiveActionTile(this);
    Player currentPlayer = currentGame.getCurrentPlayer();
    currentPlayer.setCurrentTile(this);
    setHasBeenVisited(true);

    Deck deck = currentGame.getDeck();
    ActionCard currentCard = deck.getCurrentCard();
    Game.Mode mode = currentCard.getActionCardMode();
    currentGame.setMode(mode);
  }

  // line 412 "../../../../../TileO.ump"
   public void doLandAsNormal(){
    Game currentGame = this.getGame();
    Player currentPlayer = currentGame.getCurrentPlayer();
    currentPlayer.setCurrentTile(this);
    
	currentGame.setNextPlayer();
   	currentGame.setMode(Game.Mode.GAME);
   	setHasBeenVisited(true);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "inactivityPeriod" + ":" + getInactivityPeriod()+ "," +
            "turnsUntilActive" + ":" + getTurnsUntilActive()+ "]"
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 30 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 8565684981117074687L ;

  
}