/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 820 "../../../../../TileO.ump"
public class SendBackToStartActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SendBackToStartActionCard(String aInstructions, Deck aDeck)
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

  // line 825 "../../../../../TileO.ump"
   public Game.Mode getActionCardMode(){
    return Game.Mode.GAME_SENDBACKTOSTARTACTIONCARD;
  }

  // line 829 "../../../../../TileO.ump"
   public boolean play(Tile t, ArrayList<Player> otherPlayers){
    for(Player p: otherPlayers){
            if(t==p.getCurrentTile()){
                p.setCurrentTile(p.getStartingTile());
                return true;
            }
        }
        return false;
  }

}