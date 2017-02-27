/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 81 "../../../../../TileOPersistence.ump"
// line 82 "../../../../../TileO.ump"
public class TeleportActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TeleportActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------
  
  public void play(Tile t) {
	  t.land();
  }

  public Game.Mode getActionCardMode(){

    return Game.Mode.GAME_TELEPORTACTIONCARD;
  }

  public void delete()
  {
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 84 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -7103632970539771717L ;

  
}