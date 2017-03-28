/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.computer.StupidPlayer;
import ca.mcgill.ecse223.tileo.computer.GodPlayer;
import ca.mcgill.ecse223.tileo.computer.HackerPlayer;
import java.util.*;

// line 9 "../../../../../TileOPersistence.ump"
// line 32 "../../../../../TileO.ump"
public class Game implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int SpareConnectionPieces = 32;
  public static final int NumberOfActionCards = 32;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private int currentConnectionPieces;
  private String filename;

  //Game State Machines
  public enum Mode { DESIGN, GAME, GAME_WON, GAME_ROLLDIEACTIONCARD, GAME_CONNECTTILESACTIONCARD, GAME_REMOVECONNECTIONACTIONCARD, GAME_TELEPORTACTIONCARD, GAME_LOSETURNACTIONCARD, GAME_REMOVERANDOMTILEACTIONCARD }
  private Mode mode;

  //Game Associations
  private List<Player> players;
  private List<Tile> tiles;
  private List<Connection> connections;
  private Deck deck;
  private Die die;
  private Player currentPlayer;
  private WinTile winTile;
  private List<ActionTile> inactiveActionTiles;
  private TileO tileO;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(int aCurrentConnectionPieces, Deck aDeck, Die aDie, TileO aTileO)
  {
    currentConnectionPieces = aCurrentConnectionPieces;
    filename = null;
    players = new ArrayList<Player>();
    tiles = new ArrayList<Tile>();
    connections = new ArrayList<Connection>();
    if (aDeck == null || aDeck.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aDeck");
    }
    deck = aDeck;
    if (aDie == null || aDie.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aDie");
    }
    die = aDie;
    inactiveActionTiles = new ArrayList<ActionTile>();
    boolean didAddTileO = setTileO(aTileO);
    if (!didAddTileO)
    {
      throw new RuntimeException("Unable to create game due to tileO");
    }
    setMode(Mode.DESIGN);
  }

  public Game(int aCurrentConnectionPieces, TileO aTileO)
  {
    currentConnectionPieces = aCurrentConnectionPieces;
    filename = null;
    players = new ArrayList<Player>();
    tiles = new ArrayList<Tile>();
    connections = new ArrayList<Connection>();
    deck = new Deck(this);
    die = new Die(this);
    inactiveActionTiles = new ArrayList<ActionTile>();
    boolean didAddTileO = setTileO(aTileO);
    if (!didAddTileO)
    {
      throw new RuntimeException("Unable to create game due to tileO");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentConnectionPieces(int aCurrentConnectionPieces)
  {
    boolean wasSet = false;
    currentConnectionPieces = aCurrentConnectionPieces;
    wasSet = true;
    return wasSet;
  }

  public boolean setFilename(String aFilename)
  {
    boolean wasSet = false;
    filename = aFilename;
    wasSet = true;
    return wasSet;
  }

  public int getCurrentConnectionPieces()
  {
    return currentConnectionPieces;
  }

  public String getFilename()
  {
    return filename;
  }

  public String getModeFullName()
  {
    String answer = mode.toString();
    return answer;
  }

  public Mode getMode()
  {
    return mode;
  }

  public boolean setMode(Mode aMode)
  {
    mode = aMode;
    return true;
  }

  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
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

  public Connection getConnection(int index)
  {
    Connection aConnection = connections.get(index);
    return aConnection;
  }

  public List<Connection> getConnections()
  {
    List<Connection> newConnections = Collections.unmodifiableList(connections);
    return newConnections;
  }

  public int numberOfConnections()
  {
    int number = connections.size();
    return number;
  }

  public boolean hasConnections()
  {
    boolean has = connections.size() > 0;
    return has;
  }

  public int indexOfConnection(Connection aConnection)
  {
    int index = connections.indexOf(aConnection);
    return index;
  }

  public Deck getDeck()
  {
    return deck;
  }

  public Die getDie()
  {
    return die;
  }

  public Player getCurrentPlayer()
  {
    return currentPlayer;
  }

  public boolean hasCurrentPlayer()
  {
    boolean has = currentPlayer != null;
    return has;
  }

  public WinTile getWinTile()
  {
    return winTile;
  }

  public boolean hasWinTile()
  {
    boolean has = winTile != null;
    return has;
  }

  public ActionTile getInactiveActionTile(int index)
  {
    ActionTile aInactiveActionTile = inactiveActionTiles.get(index);
    return aInactiveActionTile;
  }

  public List<ActionTile> getInactiveActionTiles()
  {
    List<ActionTile> newInactiveActionTiles = Collections.unmodifiableList(inactiveActionTiles);
    return newInactiveActionTiles;
  }

  public int numberOfInactiveActionTiles()
  {
    int number = inactiveActionTiles.size();
    return number;
  }

  public boolean hasInactiveActionTiles()
  {
    boolean has = inactiveActionTiles.size() > 0;
    return has;
  }

  public int indexOfInactiveActionTile(ActionTile aInactiveActionTile)
  {
    int index = inactiveActionTiles.indexOf(aInactiveActionTile);
    return index;
  }

  public TileO getTileO()
  {
    return tileO;
  }

  public boolean isNumberOfPlayersValid()
  {
    boolean isValid = numberOfPlayers() >= minimumNumberOfPlayers() && numberOfPlayers() <= maximumNumberOfPlayers();
    return isValid;
  }

  public static int minimumNumberOfPlayers()
  {
    return 2;
  }

  public static int maximumNumberOfPlayers()
  {
    return 4;
  }

  public Player addPlayer(int aNumber)
  {
    if (numberOfPlayers() >= maximumNumberOfPlayers())
    {
      return null;
    }
    else
    {
      return new Player(aNumber, this);
    }
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    if (numberOfPlayers() >= maximumNumberOfPlayers())
    {
      return wasAdded;
    }

    Game existingGame = aPlayer.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfPlayers() <= minimumNumberOfPlayers())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aPlayer.setGame(this);
    }
    else
    {
      players.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a game
    if (this.equals(aPlayer.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (2)
    if (numberOfPlayers() <= minimumNumberOfPlayers())
    {
      return wasRemoved;
    }
    players.remove(aPlayer);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTiles()
  {
    return 0;
  }

  /*public Tile addTile(int aX, int aY)
  {
    return new Tile(aX, aY, this);
  }*/

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    Game existingGame = aTile.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aTile.setGame(this);
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
    //Unable to remove aTile, as it must always have a game
    if (!this.equals(aTile.getGame()))
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

  public static int minimumNumberOfConnections()
  {
    return 0;
  }

  public Connection addConnection()
  {
    return new Connection(this);
  }

  public boolean addConnection(Connection aConnection)
  {
    boolean wasAdded = false;
    if (connections.contains(aConnection)) { return false; }
    Game existingGame = aConnection.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aConnection.setGame(this);
    }
    else
    {
      connections.add(aConnection);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeConnection(Connection aConnection)
  {
    boolean wasRemoved = false;
    //Unable to remove aConnection, as it must always have a game
    if (!this.equals(aConnection.getGame()))
    {
      connections.remove(aConnection);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addConnectionAt(Connection aConnection, int index)
  {  
    boolean wasAdded = false;
    if(addConnection(aConnection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnections()) { index = numberOfConnections() - 1; }
      connections.remove(aConnection);
      connections.add(index, aConnection);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveConnectionAt(Connection aConnection, int index)
  {
    boolean wasAdded = false;
    if(connections.contains(aConnection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnections()) { index = numberOfConnections() - 1; }
      connections.remove(aConnection);
      connections.add(index, aConnection);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addConnectionAt(aConnection, index);
    }
    return wasAdded;
  }

  public boolean setCurrentPlayer(Player aNewCurrentPlayer)
  {
    boolean wasSet = false;
    currentPlayer = aNewCurrentPlayer;
    wasSet = true;
    return wasSet;
  }

  public boolean setWinTile(WinTile aNewWinTile)
  {
    boolean wasSet = false;
    winTile = aNewWinTile;
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfInactiveActionTiles()
  {
    return 0;
  }

  public boolean addInactiveActionTile(ActionTile aInactiveActionTile)
  {
    boolean wasAdded = false;
    if (inactiveActionTiles.contains(aInactiveActionTile)) { return false; }
    inactiveActionTiles.add(aInactiveActionTile);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeInactiveActionTile(ActionTile aInactiveActionTile)
  {
    boolean wasRemoved = false;
    if (inactiveActionTiles.contains(aInactiveActionTile))
    {
      inactiveActionTiles.remove(aInactiveActionTile);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addInactiveActionTileAt(ActionTile aInactiveActionTile, int index)
  {  
    boolean wasAdded = false;
    if(addInactiveActionTile(aInactiveActionTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInactiveActionTiles()) { index = numberOfInactiveActionTiles() - 1; }
      inactiveActionTiles.remove(aInactiveActionTile);
      inactiveActionTiles.add(index, aInactiveActionTile);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveInactiveActionTileAt(ActionTile aInactiveActionTile, int index)
  {
    boolean wasAdded = false;
    if(inactiveActionTiles.contains(aInactiveActionTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInactiveActionTiles()) { index = numberOfInactiveActionTiles() - 1; }
      inactiveActionTiles.remove(aInactiveActionTile);
      inactiveActionTiles.add(index, aInactiveActionTile);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addInactiveActionTileAt(aInactiveActionTile, index);
    }
    return wasAdded;
  }

  public boolean setTileO(TileO aTileO)
  {
    boolean wasSet = false;
    if (aTileO == null)
    {
      return wasSet;
    }

    TileO existingTileO = tileO;
    tileO = aTileO;
    if (existingTileO != null && !existingTileO.equals(aTileO))
    {
      existingTileO.removeGame(this);
    }
    tileO.addGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (players.size() > 0)
    {
      Player aPlayer = players.get(players.size() - 1);
      aPlayer.delete();
      players.remove(aPlayer);
    }
    
    while (tiles.size() > 0)
    {
      Tile aTile = tiles.get(tiles.size() - 1);
      aTile.delete();
      tiles.remove(aTile);
    }
    
    while (connections.size() > 0)
    {
      Connection aConnection = connections.get(connections.size() - 1);
      aConnection.delete();
      connections.remove(aConnection);
    }
    
    Deck existingDeck = deck;
    deck = null;
    if (existingDeck != null)
    {
      existingDeck.delete();
    }
    Die existingDie = die;
    die = null;
    if (existingDie != null)
    {
      existingDie.delete();
    }
    currentPlayer = null;
    winTile = null;
    inactiveActionTiles.clear();
    TileO placeholderTileO = tileO;
    this.tileO = null;
    placeholderTileO.removeGame(this);
  }

  // line 53 "../../../../../TileO.ump"
   public void changeDie(){
    die = null;
    die = new Die(this);
  }

  // line 58 "../../../../../TileO.ump"
   public int getMaxSize(){
    int max=0;
	  for (Tile aTile: tiles){
		  if (aTile.getX()>max)
			  max = aTile.getX();
		  if (aTile.getY()>max)
			  max = aTile.getY();
	  }
	  return max+1; // index starts at 0
  }

  // line 69 "../../../../../TileO.ump"
   public boolean connectTiles(Tile t1, Tile t2){
    boolean wasAdded = false;
	int dx = t1.getX() - t2.getX();
  	int dy = t1.getY() - t2.getY();
  	
  	if (((dx==0&&(dy==1||dy==-1))||(dy==0&&(dx==1||dx==-1))) && t1!=t2 && t1!=null && t2!=null) {
  		Connection conn = new Connection(this);
  		conn.addTile(t1);
  		conn.addTile(t2);
  		wasAdded = true;
  	}
  	return wasAdded;
  }

  // line 83 "../../../../../TileO.ump"
   public boolean disconnectTiles(Tile t1, Tile t2){
    Connection conn = null;
	boolean wasDeleted = false;
  	int dx = t1.getX() - t2.getX();
  	int dy = t1.getY() - t2.getY();
  	
  	if (((dx==0&&(dy==1||dy==-1))||(dy==0&&(dx==1||dx==-1))) && t1!=t2 && t1!=null && t2!=null) {
  		for (Connection c: t1.getConnections()){
  			if (t2 == c.getTile(0) || t2 == c.getTile(1)){
  				conn = c;
  				break;
  			}
  		}
  	}   	
  	if (conn!=null){
  		conn.delete();
  		wasDeleted = true;
  	}
  	return wasDeleted;
  }

  // line 104 "../../../../../TileO.ump"
   public ArrayList<Tile> rollDie(){
    int n = getDie().roll();
	dieNumber = Integer.toString(n);
  	ArrayList<Tile> possibleTiles = getCurrentPlayer().getPossibleMoves(n);
  	return possibleTiles;
  }

  // line 111 "../../../../../TileO.ump"
   public void setNextPlayer(){
    while (true) {
  	  setCurrentPlayer(getPlayer((indexOfPlayer(getCurrentPlayer()) + 1)%numberOfPlayers()));
  	  if (getCurrentPlayer().getPlayerState()==Player.PlayerState.SkipTurn){
  	  	getCurrentPlayer().turnSkipped();
  	  }
  	  else break;
  	}
  	for (int i=0; i<inactiveActionTiles.size(); ++i) {
  		ActionTile t = inactiveActionTiles.get(i);
  		t.setTurnsUntilActive(t.getTurnsUntilActive()-1);
  		if (t.getTurnsUntilActive()==0) {
  			removeInactiveActionTile(t);
  			t.inactivityPeriodCompleted();
  			i--;
  		}
  	}
  }

  // line 130 "../../../../../TileO.ump"
   public void setNextCard(){
    Deck currentDeck = getDeck();
  	ActionCard currentCard = currentDeck.getCurrentCard();
  	if (currentDeck.indexOfCard(currentCard)==currentDeck.numberOfCards()-1){
        currentDeck.shuffle();
        currentDeck.setCurrentCard(currentDeck.getCard(0));
    }
    else{
    	currentDeck.setCurrentCard(currentDeck.getCard(currentDeck.indexOfCard(currentCard)+1));
    }
  }

  // line 142 "../../../../../TileO.ump"
   public void swapPlayerForComputer(int playerNum, String type){
    Player p = getPlayer(playerNum);
    Tile t = p.getStartingTile();
    p.forceDelete();
    // Add your type here, make sure to check your spelling

    if (type.equals("Stupid")) {
        StupidPlayer cp = new StupidPlayer(playerNum, this);
        System.out.println("Making player "+playerNum+" -> "+type);
        cp.setColor();
        cp.setStartingTile(t);
        addOrMovePlayerAt(cp, cp.getNumber());
    }
    else if (type.equals("God")) {
    	GodPlayer cp = new GodPlayer(playerNum, this);
    	System.out.println("Making player "+playerNum+" -> "+type);
    	cp.setColor();
    	cp.setStartingTile(t);
    	addOrMovePlayerAt(cp, cp.getNumber());
    }
    else if (type.equals("Hacker")) {
        HackerPlayer cp = new HackerPlayer(playerNum, this);
        System.out.println("Making player "+playerNum+" -> "+type);
        cp.setColor();
        cp.setStartingTile(t);
        addOrMovePlayerAt(cp, cp.getNumber());
    }
    else 
        throw new RuntimeException("Type not implemented");
  }

  // line 167 "../../../../../TileO.ump"
   public void swapComputerForPlayer(int compNum){
    Player cp = getPlayer(compNum);
    Tile t = cp.getStartingTile();
    cp.forceDelete();
    Player p = new Player(compNum, this);
    p.setColor();
    p.setStartingTile(t);
    addOrMovePlayerAt(p, p.getNumber());
  }

  // line 177 "../../../../../TileO.ump"
   public void forceRemovePlayer(Player aPlayer){
    players.remove(aPlayer);
  }

  // line 181 "../../../../../TileO.ump"
   public Tile getTileAtXY(int x, int y){
    for (Tile t: getTiles()) {
        if (t.getX()==x && t.getY()==y)
            return t;
    }
    return null;
  }

  // line 189 "../../../../../TileO.ump"
   public void removeRandomTile(){
    Tile t;
    Random rand = new Random();
    ArrayList<Tile> tilesChecked = new ArrayList<Tile>();
    
    while (true) {
      boolean tileIsLegal = true;
      t = getTile(rand.nextInt(numberOfTiles()));
      
      if (tilesChecked.contains(t)) continue;
      else tilesChecked.add(t);
      
      // checks
      if (t instanceof WinTile) tileIsLegal = false;
      else {
    	  for (Player p: getPlayers()) {
            if (p.getStartingTile() == t || p.getCurrentTile() == t) {
              tileIsLegal = false;
              break;
            }
    	  }
      }
      if (tileIsLegal) break;
      if (tilesChecked.size() == numberOfTiles()) return; // there's no tile to remove
    }
    t.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "currentConnectionPieces" + ":" + getCurrentConnectionPieces()+ "," +
            "filename" + ":" + getFilename()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "deck = "+(getDeck()!=null?Integer.toHexString(System.identityHashCode(getDeck())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "die = "+(getDie()!=null?Integer.toHexString(System.identityHashCode(getDie())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentPlayer = "+(getCurrentPlayer()!=null?Integer.toHexString(System.identityHashCode(getCurrentPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "winTile = "+(getWinTile()!=null?Integer.toHexString(System.identityHashCode(getWinTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "tileO = "+(getTileO()!=null?Integer.toHexString(System.identityHashCode(getTileO())):"null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 12 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -4871491228092496389L ;
// line 49 ../../../../../TileO.ump
  public  String dieNumber ;

  
}
