/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-980fc67 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 4 "../../../../../../../../ump/tmp527783/model.ump"
// line 91 "../../../../../../../../ump/tmp527783/model.ump"
public class TileO
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TileO Associations
  private List<Game> games;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TileO()
  {
    games = new ArrayList<Game>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  public List<Game> getGames()
  {
    List<Game> newGames = Collections.unmodifiableList(games);
    return newGames;
  }

  public int numberOfGames()
  {
    int number = games.size();
    return number;
  }

  public boolean hasGames()
  {
    boolean has = games.size() > 0;
    return has;
  }

  public int indexOfGame(Game aGame)
  {
    int index = games.indexOf(aGame);
    return index;
  }

  public static int minimumNumberOfGames()
  {
    return 0;
  }

  public Game addGame(int aCurrentConnectionPieces, Deck aDeck, Die aDie)
  {
    return new Game(aCurrentConnectionPieces, aDeck, aDie, this);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    TileO existingTileO = aGame.getTileO();
    boolean isNewTileO = existingTileO != null && !this.equals(existingTileO);
    if (isNewTileO)
    {
      aGame.setTileO(this);
    }
    else
    {
      games.add(aGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aGame, as it must always have a tileO
    if (!this.equals(aGame.getTileO()))
    {
      games.remove(aGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addGameAt(Game aGame, int index)
  {  
    boolean wasAdded = false;
    if(addGame(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameAt(Game aGame, int index)
  {
    boolean wasAdded = false;
    if(games.contains(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameAt(aGame, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (games.size() > 0)
    {
      Game aGame = games.get(games.size() - 1);
      aGame.delete();
      games.remove(aGame);
    }
    
  }

}