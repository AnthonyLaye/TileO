/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.ArrayList;

// line 63 "../../../../../TileOPersistence.ump"
// line 575 "../../../../../TileO.ump"
public class RollDieActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RollDieActionCard(String aInstructions, Deck aDeck)
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

  // line 580 "../../../../../TileO.ump"
   public ArrayList<Tile> play(){
    return getDeck().getGame().rollDie();
  }

  // line 583 "../../../../../TileO.ump"
   public Game.Mode getActionCardMode(){
    return Game.Mode.GAME_ROLLDIEACTIONCARD;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 66 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 7274690872707643783L ;

  
}