/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;
import java.awt.Color;

// line 46 "../../../../../model.ump"
public class BoardGame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BoardGame Attributes
  private int nSpareConnections;

  //BoardGame Associations
  private List<PlayerPiece> pieces;
  private Deck deck;
  private Layout grid;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BoardGame(Deck aDeck, Layout aGrid)
  {
    nSpareConnections = 32;
    pieces = new ArrayList<PlayerPiece>();
    if (aDeck == null || aDeck.getBoardGame() != null)
    {
      throw new RuntimeException("Unable to create BoardGame due to aDeck");
    }
    deck = aDeck;
    if (aGrid == null || aGrid.getBoardGame() != null)
    {
      throw new RuntimeException("Unable to create BoardGame due to aGrid");
    }
    grid = aGrid;
  }

  public BoardGame(int aNColumnsForGrid, int aNRowsForGrid)
  {
    nSpareConnections = 32;
    pieces = new ArrayList<PlayerPiece>();
    deck = new Deck(this);
    grid = new Layout(aNColumnsForGrid, aNRowsForGrid, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNSpareConnections(int aNSpareConnections)
  {
    boolean wasSet = false;
    nSpareConnections = aNSpareConnections;
    wasSet = true;
    return wasSet;
  }

  public int getNSpareConnections()
  {
    return nSpareConnections;
  }

  public PlayerPiece getPiece(int index)
  {
    PlayerPiece aPiece = pieces.get(index);
    return aPiece;
  }

  public List<PlayerPiece> getPieces()
  {
    List<PlayerPiece> newPieces = Collections.unmodifiableList(pieces);
    return newPieces;
  }

  public int numberOfPieces()
  {
    int number = pieces.size();
    return number;
  }

  public boolean hasPieces()
  {
    boolean has = pieces.size() > 0;
    return has;
  }

  public int indexOfPiece(PlayerPiece aPiece)
  {
    int index = pieces.indexOf(aPiece);
    return index;
  }

  public Deck getDeck()
  {
    return deck;
  }

  public Layout getGrid()
  {
    return grid;
  }

  public boolean isNumberOfPiecesValid()
  {
    boolean isValid = numberOfPieces() >= minimumNumberOfPieces() && numberOfPieces() <= maximumNumberOfPieces();
    return isValid;
  }

  public static int minimumNumberOfPieces()
  {
    return 2;
  }

  public static int maximumNumberOfPieces()
  {
    return 4;
  }

  public PlayerPiece addPiece(int aStartingX, int aStartingY, Color aColor, boolean aIsBlocked, Player aPlayer, Tile aTile)
  {
    if (numberOfPieces() >= maximumNumberOfPieces())
    {
      return null;
    }
    else
    {
      return new PlayerPiece(aStartingX, aStartingY, aColor, aIsBlocked, aPlayer, aTile, this);
    }
  }

  public boolean addPiece(PlayerPiece aPiece)
  {
    boolean wasAdded = false;
    if (pieces.contains(aPiece)) { return false; }
    if (numberOfPieces() >= maximumNumberOfPieces())
    {
      return wasAdded;
    }

    BoardGame existingBoardGame = aPiece.getBoardGame();
    boolean isNewBoardGame = existingBoardGame != null && !this.equals(existingBoardGame);

    if (isNewBoardGame && existingBoardGame.numberOfPieces() <= minimumNumberOfPieces())
    {
      return wasAdded;
    }

    if (isNewBoardGame)
    {
      aPiece.setBoardGame(this);
    }
    else
    {
      pieces.add(aPiece);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePiece(PlayerPiece aPiece)
  {
    boolean wasRemoved = false;
    //Unable to remove aPiece, as it must always have a boardGame
    if (this.equals(aPiece.getBoardGame()))
    {
      return wasRemoved;
    }

    //boardGame already at minimum (2)
    if (numberOfPieces() <= minimumNumberOfPieces())
    {
      return wasRemoved;
    }
    pieces.remove(aPiece);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addPieceAt(PlayerPiece aPiece, int index)
  {  
    boolean wasAdded = false;
    if(addPiece(aPiece))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPieces()) { index = numberOfPieces() - 1; }
      pieces.remove(aPiece);
      pieces.add(index, aPiece);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePieceAt(PlayerPiece aPiece, int index)
  {
    boolean wasAdded = false;
    if(pieces.contains(aPiece))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPieces()) { index = numberOfPieces() - 1; }
      pieces.remove(aPiece);
      pieces.add(index, aPiece);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPieceAt(aPiece, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=pieces.size(); i > 0; i--)
    {
      PlayerPiece aPiece = pieces.get(i - 1);
      aPiece.delete();
    }
    Deck existingDeck = deck;
    deck = null;
    if (existingDeck != null)
    {
      existingDeck.delete();
    }
    Layout existingGrid = grid;
    grid = null;
    if (existingGrid != null)
    {
      existingGrid.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "nSpareConnections" + ":" + getNSpareConnections()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "deck = "+(getDeck()!=null?Integer.toHexString(System.identityHashCode(getDeck())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "grid = "+(getGrid()!=null?Integer.toHexString(System.identityHashCode(getGrid())):"null")
     + outputString;
  }
}