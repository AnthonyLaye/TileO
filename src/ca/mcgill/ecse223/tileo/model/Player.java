/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.util.Node;
import java.util.*;

// line 15 "../../../../../TileOPersistence.ump"
// line 3 "../../../../../PlayerState.ump"
// line 231 "../../../../../TileO.ump"
public class Player implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int number;
  private int turnsUntilActive;

  //Player State Machines
  public enum PlayerState { Play, SkipTurn }
  private PlayerState playerState;
  public enum Color { RED, BLUE, GREEN, YELLOW }
  private Color color;

  //Player Associations
  private Tile startingTile;
  private Tile currentTile;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(int aNumber, Game aGame)
  {
    number = aNumber;
    turnsUntilActive = 0;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create player due to game");
    }
    setPlayerState(PlayerState.Play);
    setColor(Color.RED);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
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

  public int getTurnsUntilActive()
  {
    return turnsUntilActive;
  }

  public String getPlayerStateFullName()
  {
    String answer = playerState.toString();
    return answer;
  }

  public String getColorFullName()
  {
    String answer = color.toString();
    return answer;
  }

  public PlayerState getPlayerState()
  {
    return playerState;
  }

  public Color getColor()
  {
    return color;
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

  private void setPlayerState(PlayerState aPlayerState)
  {
    playerState = aPlayerState;
  }

  public boolean setColor(Color aColor)
  {
    color = aColor;
    return true;
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
    startingTile = null;
    currentTile = null;
    Game placeholderGame = game;
    this.game = null;
    placeholderGame.removePlayer(this);
  }

  // line 242 "../../../../../TileO.ump"
   public void setColorByNumber(){
    switch (number) {
  		case 0:
  			setColor(Color.RED);
  			break;
  		case 1:
  			setColor(Color.BLUE);
  			break;
  		case 2:
  			setColor(Color.GREEN);
  			break;
  		case 3:
  			setColor(Color.YELLOW);
  			break;
  	}
  }

  // line 259 "../../../../../TileO.ump"
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

  // line 292 "../../../../../TileO.ump"
   public void forceDelete(){
    startingTile = null;
    currentTile = null;
    Game placeholderGame = game;
    this.game = null;
    placeholderGame.forceRemovePlayer(this);
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