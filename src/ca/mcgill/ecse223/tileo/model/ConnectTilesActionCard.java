/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 69 "../../../../../TileOPersistence.ump"
// line 587 "../../../../../TileO.ump"
public class ConnectTilesActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ConnectTilesActionCard(String aInstructions, Deck aDeck)
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

  // line 591 "../../../../../TileO.ump"
   public boolean play(Tile t1, Tile t2){
    return getDeck().getGame().connectTiles(t1, t2);
  }

  // line 594 "../../../../../TileO.ump"
   public Game.Mode getActionCardMode(){
    return Game.Mode.GAME_CONNECTTILESACTIONCARD;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 72 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 4457855681344552983L ;

  
}