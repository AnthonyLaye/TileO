/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 33 "../../../../../TileOPersistence.ump"
// line 405 "../../../../../TileO.ump"
public class NormalTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NormalTile(int aX, int aY, Game aGame)
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

  // line 410 "../../../../../TileO.ump"
   public void land(){
    Game currentGame = this.getGame();
    Player currentPlayer = currentGame.getCurrentPlayer();
    currentPlayer.setCurrentTile(this);
	
	currentGame.setNextPlayer();
    currentGame.setMode(Game.Mode.GAME);
    setHasBeenVisited(true);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 36 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -2291031409983159043L ;

  
}