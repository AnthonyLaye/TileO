/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 27 "../../../../../TileOPersistence.ump"
// line 176 "../../../../../TileO.ump"
public class ActionTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionTile Attributes
  private int inactivityPeriod;
  private int turnsUntilActive;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionTile(int aX, int aY, Game aGame, int aInactivityPeriod)
  {
    super(aX, aY, aGame);
    inactivityPeriod = aInactivityPeriod;
    turnsUntilActive = 0;
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

  public void delete()
  {
    super.delete();
  }

  // line 184 "../../../../../TileO.ump"
   public void land(){
    Game currentGame = getGame();
    Player currentPlayer = currentGame.getCurrentPlayer();
    currentPlayer.setCurrentTile(this);
    setHasBeenVisited(true);

    Deck deck = currentGame.getDeck();
    ActionCard currentCard = deck.getCurrentCard();
    Game.Mode mode = currentCard.getActionCardMode();
    currentGame.setMode(mode);
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