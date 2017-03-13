/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 39 "../../../../../TileOPersistence.ump"
// line 289 "../../../../../TileO.ump"
public class WinTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WinTile(int aX, int aY, Game aGame)
  {
    super(aX, aY, aGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  // line 293 "../../../../../TileO.ump"
   public void land(){
    /* Called when a player lands on the Win Tile */

    Game currentGame = getGame();
    this.setHasBeenVisited(true);
    currentGame.getCurrentPlayer().setCurrentTile(this);
    currentGame.setMode(Game.Mode.GAME_WON);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 42 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 9004047970838151833L ;

  
}