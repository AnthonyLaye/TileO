/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import ca.mcgill.ecse223.tileo.view.BoardVisualizer;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import java.util.List;

// line 859 "../../../../../TileO.ump"
public class WinTileHintActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WinTileHintActionCard(String aInstructions, Deck aDeck)
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

  // line 866 "../../../../../TileO.ump"
   public Game.Mode getActionCardMode(){
    return Game.Mode.GAME_WINTILEHINTACTIONCARD;
  }

  // line 870 "../../../../../TileO.ump"
   public boolean play(Tile tile){
    List<Tile> neighbours = tile.getNeighbours(TileOApplication.getTileOPage().board.getBoardSize());
	  if(tile instanceof WinTile){
		  return true;
	  }
	  if(neighbours == null || neighbours.size() == 0){
		  return false;
	  }
	  for(Tile aTile : neighbours){
		if(aTile instanceof WinTile){
			return true;
		}
	  }
	  return false;
  }

}