/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 87 "../../../../../TileOPersistence.ump"
// line 634 "../../../../../TileO.ump"
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

  public void delete()
  {
    super.delete();
  }

  // line 638 "../../../../../TileO.ump"
   public Game.Mode getActionCardMode(){
    return Game.Mode.GAME_LOSETURNACTIONCARD;
  }

  // line 642 "../../../../../TileO.ump"
   public void play(){
    Player currentPlayer = getDeck().getGame().getCurrentPlayer();
  	currentPlayer.loseTurn();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 90 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -4548100986646590925L ;

  
}