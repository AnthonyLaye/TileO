/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.util.Node;
import java.util.*;

// line 15 "../../../../../TileOPersistence.ump"
// line 138 "../../../../../TileO.ump"
public class Player implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Player> playersByNumber = new HashMap<Integer, Player>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int number;
  private int turnsUntilActive;

  //Player State Machines
  public enum Color { RED, BLUE, GREEN, YELLOW }
  private Color color;
  public enum PlayerState { Play, SkipTurn }
  private PlayerState playerState;

  //Player Associations
  private Tile startingTile;
  private Tile currentTile;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(int aNumber, Game aGame)
  {
    turnsUntilActive = 0;
    if (!setNumber(aNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate number");
    }
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create player due to game");
    }
    setColor(Color.RED);
    setPlayerState(PlayerState.Play);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    Integer anOldNumber = getNumber();
    if (hasWithNumber(aNumber)) {
      return wasSet;
    }
    number = aNumber;
    wasSet = true;
    if (anOldNumber != null) {
      playersByNumber.remove(anOldNumber);
    }
    playersByNumber.put(aNumber, this);
    return wasSet;
  }

  public boolean setTurnsUntilActive(int aTurnsUntilActive)
  {
    boolean wasSet = false;
    turnsUntilActive = aTurnsUntilActive;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public static Player getWithNumber(int aNumber)
  {
    return playersByNumber.get(aNumber);
  }

  public static boolean hasWithNumber(int aNumber)
  {
    return getWithNumber(aNumber) != null;
  }

  public int getTurnsUntilActive()
  {
    return turnsUntilActive;
  }

  public String getColorFullName()
  {
    String answer = color.toString();
    return answer;
  }

  public String getPlayerStateFullName()
  {
    String answer = playerState.toString();
    return answer;
  }

  public Color getColor()
  {
    return color;
  }

  public PlayerState getPlayerState()
  {
    return playerState;
  }

  public boolean loseTurn()
  {
    boolean wasEventProcessed = false;
    
    PlayerState aPlayerState = playerState;
    switch (aPlayerState)
    {
      case Play:
        setPlayerState(PlayerState.SkipTurn);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean turnSkipped()
  {
    boolean wasEventProcessed = false;
    
    PlayerState aPlayerState = playerState;
    switch (aPlayerState)
    {
      case SkipTurn:
        setPlayerState(PlayerState.Play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean setColor(Color aColor)
  {
    color = aColor;
    return true;
  }

  private void setPlayerState(PlayerState aPlayerState)
  {
    playerState = aPlayerState;
  }

  public Tile getStartingTile()
  {
    return startingTile;
  }

  public boolean hasStartingTile()
  {
    boolean has = startingTile != null;
    return has;
  }

  public Tile getCurrentTile()
  {
    return currentTile;
  }

  public boolean hasCurrentTile()
  {
    boolean has = currentTile != null;
    return has;
  }

  public Game getGame()
  {
    return game;
  }

  public boolean setStartingTile(Tile aNewStartingTile)
  {
    boolean wasSet = false;
    startingTile = aNewStartingTile;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentTile(Tile aNewCurrentTile)
  {
    boolean wasSet = false;
    currentTile = aNewCurrentTile;
    wasSet = true;
    return wasSet;
  }

  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to player
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (4)
    if (aGame.numberOfPlayers() >= Game.maximumNumberOfPlayers())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removePlayer(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addPlayer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    playersByNumber.remove(getNumber());
    startingTile = null;
    currentTile = null;
    Game placeholderGame = game;
    this.game = null;
    placeholderGame.removePlayer(this);
  }

  // line 158 "../../../../../TileO.ump"
   public ArrayList<Tile> getPossibleMoves(int depth){
    // Depth first search with limited depth, Iterate over the possible children
      // Cannot go back but loops are allowed
      Stack<Node> fringe = new Stack<Node>();
      List<Connection> connections;
      List<Tile> connectedTiles;
      HashSet<Tile> possibleTiles = new HashSet<Tile>();
      int tIdx;
      Tile t;
      
      Node current = new Node(currentTile, null, 0);
      fringe.push(current);

      while (!fringe.isEmpty()) {
          current = fringe.pop();
    	  t = current.getTile();
    	  
          if (current.getDepth() == depth){
        	  possibleTiles.add(t);
              continue;
          }
          
          connections = t.getConnections();
          for (Connection aConnection : connections){
        	  connectedTiles = aConnection.getTiles();
        	  tIdx = connectedTiles.get(0)==t ? 1:0; // select the other tile
              if (current.getParent()==null  || connectedTiles.get(tIdx) != current.getParent().getTile())
            	  fringe.push(new Node(connectedTiles.get(tIdx), current, current.getDepth()+1));
          }
      }
      return new ArrayList<Tile>(possibleTiles);
  }

  // line 192 "../../../../../TileO.ump"
   public static  void resetMap(){
    //I had problems with tests (Vincent)
    playersByNumber = new HashMap<Integer, Player>();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "turnsUntilActive" + ":" + getTurnsUntilActive()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startingTile = "+(getStartingTile()!=null?Integer.toHexString(System.identityHashCode(getStartingTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentTile = "+(getCurrentTile()!=null?Integer.toHexString(System.identityHashCode(getCurrentTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 18 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 3679478658515911317L ;

  
}
