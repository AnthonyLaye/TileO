/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 87 "../../../../../TileOPersistence.ump"
// line 86 "../../../../../TileO.ump"
public class LoseTurnActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LoseTurnActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Game.Mode getActionCardMode(){

    return Game.Mode.GAME_LOSETURNACTIONCARD;
  }

  public void delete()
  {
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 90 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -4548100986646590925L ;

  
}