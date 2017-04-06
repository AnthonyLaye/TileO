/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.ArrayList;

// line 840 "../../../../../TileO.ump"
public class SwapPositionActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SwapPositionActionCard(String aInstructions, Deck aDeck)
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

  // line 845 "../../../../../TileO.ump"
   public Game.Mode getActionCardMode(){
    return Game.Mode.GAME_SWAPPOSITIONACTIONCARD;
  }

  // line 849 "../../../../../TileO.ump"
   public boolean play(Tile t, ArrayList<Player> otherPlayers, Player currentPlayer){
    for (Player p : otherPlayers) {
        	if (t == p.getCurrentTile()) {
         		Tile firstTile = currentPlayer.getCurrentTile();
            	p.setCurrentTile(firstTile);
            	currentPlayer.setCurrentTile(t);
            	return true;
            }
         }
        return false;
  }

}