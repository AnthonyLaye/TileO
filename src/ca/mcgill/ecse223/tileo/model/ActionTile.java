/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-980fc67 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 38 "../../../../../../../../ump/tmp527783/model.ump"
// line 121 "../../../../../../../../ump/tmp527783/model.ump"
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


  public String toString()
  {
    return super.toString() + "["+
            "inactivityPeriod" + ":" + getInactivityPeriod()+ "," +
            "turnsUntilActive" + ":" + getTurnsUntilActive()+ "]";
  }
}