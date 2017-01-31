/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 16 "../../../../../model.ump"
public class Layout
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Layout Attributes
  private int nColumns;
  private int nRows;

  //Layout Associations
  private List<Tile> tiles;
  private BoardGame boardGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Layout(int aNColumns, int aNRows, BoardGame aBoardGame)
  {
    nColumns = aNColumns;
    nRows = aNRows;
    tiles = new ArrayList<Tile>();
    if (aBoardGame == null || aBoardGame.getGrid() != null)
    {
      throw new RuntimeException("Unable to create Layout due to aBoardGame");
    }
    boardGame = aBoardGame;
  }

  public Layout(int aNColumns, int aNRows, Deck aDeckForBoardGame)
  {
    nColumns = aNColumns;
    nRows = aNRows;
    tiles = new ArrayList<Tile>();
    boardGame = new BoardGame(aDeckForBoardGame, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNColumns(int aNColumns)
  {
    boolean wasSet = false;
    nColumns = aNColumns;
    wasSet = true;
    return wasSet;
  }

  public boolean setNRows(int aNRows)
  {
    boolean wasSet = false;
    nRows = aNRows;
    wasSet = true;
    return wasSet;
  }

  public int getNColumns()
  {
    return nColumns;
  }

  public int getNRows()
  {
    return nRows;
  }

  public Tile getTile(int index)
  {
    Tile aTile = tiles.get(index);
    return aTile;
  }

  public List<Tile> getTiles()
  {
    List<Tile> newTiles = Collections.unmodifiableList(tiles);
    return newTiles;
  }

  public int numberOfTiles()
  {
    int number = tiles.size();
    return number;
  }

  public boolean hasTiles()
  {
    boolean has = tiles.size() > 0;
    return has;
  }

  public int indexOfTile(Tile aTile)
  {
    int index = tiles.indexOf(aTile);
    return index;
  }

  public BoardGame getBoardGame()
  {
    return boardGame;
  }

  public static int minimumNumberOfTiles()
  {
    return 0;
  }

  public Tile addTile(int aX, int aY, boolean aIsActivated)
  {
    return new Tile(aX, aY, aIsActivated, this);
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    Layout existingLayout = aTile.getLayout();
    boolean isNewLayout = existingLayout != null && !this.equals(existingLayout);
    if (isNewLayout)
    {
      aTile.setLayout(this);
    }
    else
    {
      tiles.add(aTile);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    //Unable to remove aTile, as it must always have a layout
    if (!this.equals(aTile.getLayout()))
    {
      tiles.remove(aTile);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTileAt(Tile aTile, int index)
  {  
    boolean wasAdded = false;
    if(addTile(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTileAt(Tile aTile, int index)
  {
    boolean wasAdded = false;
    if(tiles.contains(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTileAt(aTile, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (tiles.size() > 0)
    {
      Tile aTile = tiles.get(tiles.size() - 1);
      aTile.delete();
      tiles.remove(aTile);
    }
    
    BoardGame existingBoardGame = boardGame;
    boardGame = null;
    if (existingBoardGame != null)
    {
      existingBoardGame.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "nColumns" + ":" + getNColumns()+ "," +
            "nRows" + ":" + getNRows()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "boardGame = "+(getBoardGame()!=null?Integer.toHexString(System.identityHashCode(getBoardGame())):"null")
     + outputString;
  }
}