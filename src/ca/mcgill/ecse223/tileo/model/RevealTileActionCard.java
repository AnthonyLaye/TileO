/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

// line 807 "../../../../../TileO.ump"
public class RevealTileActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RevealTileActionCard(String aInstructions, Deck aDeck)
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

  // line 811 "../../../../../TileO.ump"
   public Game.Mode getActionCardMode(){
    return Game.Mode.GAME_REVEALTILEACTIONCARD;
  }

  // line 815 "../../../../../TileO.ump"
   public String play(Tile t){
    if (t instanceof NormalTile) return "Normal";
		else if (t instanceof ActionTile) return "Action";
		else return "Win";
  }

}