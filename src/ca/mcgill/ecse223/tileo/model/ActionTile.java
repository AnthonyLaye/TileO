/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 40 "../../../../../model.ump"
public class ActionTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionTile Attributes
  private int normalForNTurns;
  private int normalTurnsLeft;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionTile(int aX, int aY, boolean aIsActivated, Layout aLayout, int aNormalForNTurns, int aNormalTurnsLeft)
  {
    super(aX, aY, aIsActivated, aLayout);
    normalForNTurns = aNormalForNTurns;
    normalTurnsLeft = aNormalTurnsLeft;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNormalForNTurns(int aNormalForNTurns)
  {
    boolean wasSet = false;
    normalForNTurns = aNormalForNTurns;
    wasSet = true;
    return wasSet;
  }

  public boolean setNormalTurnsLeft(int aNormalTurnsLeft)
  {
    boolean wasSet = false;
    normalTurnsLeft = aNormalTurnsLeft;
    wasSet = true;
    return wasSet;
  }

  public int getNormalForNTurns()
  {
    return normalForNTurns;
  }

  public int getNormalTurnsLeft()
  {
    return normalTurnsLeft;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "normalForNTurns" + ":" + getNormalForNTurns()+ "," +
            "normalTurnsLeft" + ":" + getNormalTurnsLeft()+ "]"
     + outputString;
  }
}