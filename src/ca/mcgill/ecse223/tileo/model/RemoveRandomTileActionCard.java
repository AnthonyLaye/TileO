/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

// line 93 "../../../../../TileOPersistence.ump"
// line 777 "../../../../../TileO.ump"
public class RemoveRandomTileActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RemoveRandomTileActionCard(String aInstructions, Deck aDeck)
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

  // line 781 "../../../../../TileO.ump"
   public Game.Mode getActionCardMode(){
    return Game.Mode.GAME_REMOVERANDOMTILEACTIONCARD;
  }

  // line 785 "../../../../../TileO.ump"
   public void play(){
    getDeck().getGame().removeRandomTile();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 94 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 2104727098193344390L ;

  
}