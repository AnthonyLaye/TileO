/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 35 "../../../../../model.ump"
public class HiddenTile extends Tile
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final boolean ISWIN = true;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HiddenTile(int aX, int aY, boolean aIsActivated, Layout aLayout)
  {
    super(aX, aY, aIsActivated, aLayout);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+ "]"
     + outputString;
  }
}