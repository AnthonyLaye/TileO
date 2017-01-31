/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.awt.Color;

// line 8 "../../../../../model.ump"
public class PlayerPiece
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayerPiece Attributes
  private int startingX;
  private int startingY;
  private Color color;
  private boolean isBlocked;

  //PlayerPiece Associations
  private Player player;
  private Tile tile;
  private BoardGame boardGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayerPiece(int aStartingX, int aStartingY, Color aColor, boolean aIsBlocked, Player aPlayer, Tile aTile, BoardGame aBoardGame)
  {
    startingX = aStartingX;
    startingY = aStartingY;
    color = aColor;
    isBlocked = aIsBlocked;
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create playerPiece due to player");
    }
    boolean didAddTile = setTile(aTile);
    if (!didAddTile)
    {
      throw new RuntimeException("Unable to create playerPiece due to tile");
    }
    boolean didAddBoardGame = setBoardGame(aBoardGame);
    if (!didAddBoardGame)
    {
      throw new RuntimeException("Unable to create piece due to boardGame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartingX(int aStartingX)
  {
    boolean wasSet = false;
    startingX = aStartingX;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartingY(int aStartingY)
  {
    boolean wasSet = false;
    startingY = aStartingY;
    wasSet = true;
    return wasSet;
  }

  public boolean setColor(Color aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsBlocked(boolean aIsBlocked)
  {
    boolean wasSet = false;
    isBlocked = aIsBlocked;
    wasSet = true;
    return wasSet;
  }

  public int getStartingX()
  {
    return startingX;
  }

  public int getStartingY()
  {
    return startingY;
  }

  public Color getColor()
  {
    return color;
  }

  public boolean getIsBlocked()
  {
    return isBlocked;
  }

  public boolean isIsBlocked()
  {
    return isBlocked;
  }

  public Player getPlayer()
  {
    return player;
  }

  public Tile getTile()
  {
    return tile;
  }

  public BoardGame getBoardGame()
  {
    return boardGame;
  }

  public boolean setPlayer(Player aNewPlayer)
  {
    boolean wasSet = false;
    if (aNewPlayer == null)
    {
      //Unable to setPlayer to null, as playerPiece must always be associated to a player
      return wasSet;
    }
    
    PlayerPiece existingPlayerPiece = aNewPlayer.getPlayerPiece();
    if (existingPlayerPiece != null && !equals(existingPlayerPiece))
    {
      //Unable to setPlayer, the current player already has a playerPiece, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Player anOldPlayer = player;
    player = aNewPlayer;
    player.setPlayerPiece(this);

    if (anOldPlayer != null)
    {
      anOldPlayer.setPlayerPiece(null);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setTile(Tile aNewTile)
  {
    boolean wasSet = false;
    if (aNewTile == null)
    {
      //Unable to setTile to null, as playerPiece must always be associated to a tile
      return wasSet;
    }
    
    PlayerPiece existingPlayerPiece = aNewTile.getPlayerPiece();
    if (existingPlayerPiece != null && !equals(existingPlayerPiece))
    {
      //Unable to setTile, the current tile already has a playerPiece, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Tile anOldTile = tile;
    tile = aNewTile;
    tile.setPlayerPiece(this);

    if (anOldTile != null)
    {
      anOldTile.setPlayerPiece(null);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setBoardGame(BoardGame aBoardGame)
  {
    boolean wasSet = false;
    //Must provide boardGame to piece
    if (aBoardGame == null)
    {
      return wasSet;
    }

    //boardGame already at maximum (4)
    if (aBoardGame.numberOfPieces() >= BoardGame.maximumNumberOfPieces())
    {
      return wasSet;
    }
    
    BoardGame existingBoardGame = boardGame;
    boardGame = aBoardGame;
    if (existingBoardGame != null && !existingBoardGame.equals(aBoardGame))
    {
      boolean didRemove = existingBoardGame.removePiece(this);
      if (!didRemove)
      {
        boardGame = existingBoardGame;
        return wasSet;
      }
    }
    boardGame.addPiece(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Player existingPlayer = player;
    player = null;
    if (existingPlayer != null)
    {
      existingPlayer.setPlayerPiece(null);
    }
    Tile existingTile = tile;
    tile = null;
    if (existingTile != null)
    {
      existingTile.setPlayerPiece(null);
    }
    BoardGame placeholderBoardGame = boardGame;
    this.boardGame = null;
    placeholderBoardGame.removePiece(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "startingX" + ":" + getStartingX()+ "," +
            "startingY" + ":" + getStartingY()+ "," +
            "isBlocked" + ":" + getIsBlocked()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "color" + "=" + (getColor() != null ? !getColor().equals(this)  ? getColor().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "tile = "+(getTile()!=null?Integer.toHexString(System.identityHashCode(getTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "boardGame = "+(getBoardGame()!=null?Integer.toHexString(System.identityHashCode(getBoardGame())):"null")
     + outputString;
  }
}