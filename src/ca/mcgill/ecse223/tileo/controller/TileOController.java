/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.controller;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.exception.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.ConnectTilesActionCard;
import ca.mcgill.ecse223.tileo.model.LoseTurnActionCard;
import ca.mcgill.ecse223.tileo.model.RemoveConnectionActionCard;
import ca.mcgill.ecse223.tileo.model.RollDieActionCard;
import ca.mcgill.ecse223.tileo.model.TeleportActionCard;
import ca.mcgill.ecse223.tileo.model.RemoveRandomTileActionCard;
import ca.mcgill.ecse223.tileo.model.RevealTileActionCard;
import ca.mcgill.ecse223.tileo.model.SendBackToStartActionCard;
import ca.mcgill.ecse223.tileo.model.SwapPositionActionCard;
import ca.mcgill.ecse223.tileo.model.WinTileHintActionCard;
import java.util.ArrayList;
import java.util.List;

// line 3 "../../../../../TileOControllerStates.ump"
public class TileOController
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TileOController Attributes
  private Tile currentTile;
  private ArrayList<Tile> possibleTiles;

  //TileOController State Machines
  public enum ControllerState { Design, Game }
  public enum ControllerStateGame { Null, StartOfTurn, WaitForTile, WaitForLanding, GameWon, LandedAction }
  private ControllerState controllerState;
  private ControllerStateGame controllerStateGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TileOController()
  {
    setControllerStateGame(ControllerStateGame.Null);
    setControllerState(ControllerState.Design);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentTile(Tile aCurrentTile)
  {
    boolean wasSet = false;
    currentTile = aCurrentTile;
    wasSet = true;
    return wasSet;
  }

  public boolean setPossibleTiles(ArrayList<Tile> aPossibleTiles)
  {
    boolean wasSet = false;
    possibleTiles = aPossibleTiles;
    wasSet = true;
    return wasSet;
  }

  public Tile getCurrentTile()
  {
    return currentTile;
  }

  public ArrayList<Tile> getPossibleTiles()
  {
    return possibleTiles;
  }

  public String getControllerStateFullName()
  {
    String answer = controllerState.toString();
    if (controllerStateGame != ControllerStateGame.Null) { answer += "." + controllerStateGame.toString(); }
    return answer;
  }

  public ControllerState getControllerState()
  {
    return controllerState;
  }

  public ControllerStateGame getControllerStateGame()
  {
    return controllerStateGame;
  }

  public boolean startGame(Game game) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    ControllerState aControllerState = controllerState;
    switch (aControllerState)
    {
      case Design:
        // line 37 "../../../../../TileOControllerStates.ump"
        doStartGame(game);
        setControllerState(ControllerState.Game);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean enterGame()
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case Null:
        setControllerStateGame(ControllerStateGame.StartOfTurn);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean exitGame()
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case StartOfTurn:
        setControllerStateGame(ControllerStateGame.Null);
        wasEventProcessed = true;
        break;
      case WaitForTile:
        setControllerStateGame(ControllerStateGame.Null);
        wasEventProcessed = true;
        break;
      case WaitForLanding:
        setControllerStateGame(ControllerStateGame.Null);
        wasEventProcessed = true;
        break;
      case GameWon:
        setControllerStateGame(ControllerStateGame.Null);
        wasEventProcessed = true;
        break;
      case LandedAction:
        setControllerStateGame(ControllerStateGame.Null);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean rollDie()
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case StartOfTurn:
        // line 44 "../../../../../TileOControllerStates.ump"
        doRollDie();
        setControllerStateGame(ControllerStateGame.WaitForTile);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean selectNewTile(Tile aTile) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case WaitForTile:
        // line 50 "../../../../../TileOControllerStates.ump"
        doSelectNewTile(aTile);
        setControllerStateGame(ControllerStateGame.WaitForLanding);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playChooseAdditionalMoveActionCard(int n)
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case WaitForTile:
        if (verifyGameMode(Game.Mode.GAME_CHOOSEADDITIONALMOVEACTIONCARD))
        {
        // line 53 "../../../../../TileOControllerStates.ump"
          doPlayChooseAdditionalMoveActionCard(n);
          setControllerStateGame(ControllerStateGame.WaitForTile);
          wasEventProcessed = true;
          break;
        }
        break;
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_CHOOSEADDITIONALMOVEACTIONCARD))
        {
        // line 89 "../../../../../TileOControllerStates.ump"
          doPlayChooseAdditionalMoveActionCard(n);
      				TileOApplication.getTileO().getCurrentGame().setNextCard();
          setControllerStateGame(ControllerStateGame.WaitForTile);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean land() throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case WaitForLanding:
        if (tileIsNormal())
        {
        // line 59 "../../../../../TileOControllerStates.ump"
          doLand(currentTile);
          setControllerStateGame(ControllerStateGame.StartOfTurn);
          wasEventProcessed = true;
          break;
        }
        if (tileIsWin())
        {
        // line 60 "../../../../../TileOControllerStates.ump"
          doLand(currentTile);
          setControllerStateGame(ControllerStateGame.GameWon);
          wasEventProcessed = true;
          break;
        }
        if (tileIsAction())
        {
        // line 61 "../../../../../TileOControllerStates.ump"
          doLand(currentTile);
          setControllerStateGame(ControllerStateGame.LandedAction);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playRollDieActionCard() throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_ROLLDIEACTIONCARD))
        {
        // line 68 "../../../../../TileOControllerStates.ump"
          doPlayRollDieActionCard();
          setControllerStateGame(ControllerStateGame.WaitForTile);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playConnectTilesActionCard(Tile t1,Tile t2) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_CONNECTTILESACTIONCARD))
        {
        // line 71 "../../../../../TileOControllerStates.ump"
          doPlayConnectTilesActionCard(t1, t2);
          setControllerStateGame(ControllerStateGame.StartOfTurn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playRemoveConnectionActionCard(Tile t1,Tile t2) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_REMOVECONNECTIONACTIONCARD))
        {
        // line 74 "../../../../../TileOControllerStates.ump"
          doPlayRemoveConnectionActionCard(t1, t2);
          setControllerStateGame(ControllerStateGame.StartOfTurn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playTeleportActionCard(Tile t) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_TELEPORTACTIONCARD))
        {
        // line 77 "../../../../../TileOControllerStates.ump"
          doPlayTeleportActionCard(t);
          setControllerStateGame(ControllerStateGame.WaitForLanding);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playLoseTurnActionCard() throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_LOSETURNACTIONCARD))
        {
        // line 80 "../../../../../TileOControllerStates.ump"
          doPlayLoseTurnActionCard();
          setControllerStateGame(ControllerStateGame.StartOfTurn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playRemoveRandomTileActionCard(Game game)
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_REMOVERANDOMTILEACTIONCARD))
        {
        // line 83 "../../../../../TileOControllerStates.ump"
          doPlayRemoveRandomTileActionCard(game);
          setControllerStateGame(ControllerStateGame.StartOfTurn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playTurnInactiveActionCard(Game currentGame)
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_TURNINACTIVEACTIONCARD))
        {
        // line 86 "../../../../../TileOControllerStates.ump"
          doPlayTurnInactiveActionCard(currentGame);
          setControllerStateGame(ControllerStateGame.StartOfTurn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playRevealTileActionCard()
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_REVEALTILEACTIONCARD))
        {
        // line 93 "../../../../../TileOControllerStates.ump"
          doPlayRevealTileActionCard();
          setControllerStateGame(ControllerStateGame.StartOfTurn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playSendBackToStartActionCard(Tile t) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_SENDBACKTOSTARTACTIONCARD))
        {
        // line 97 "../../../../../TileOControllerStates.ump"
          doPlaySendBackToStartActionCard(t);
          setControllerStateGame(ControllerStateGame.StartOfTurn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playSwapPositionActionCard(Tile t) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_SWAPPOSITIONACTIONCARD))
        {
        // line 101 "../../../../../TileOControllerStates.ump"
          doPlaySwapPositionActionCard(t);
          setControllerStateGame(ControllerStateGame.StartOfTurn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playWinTileHintActionCard()
  {
    boolean wasEventProcessed = false;
    
    ControllerStateGame aControllerStateGame = controllerStateGame;
    switch (aControllerStateGame)
    {
      case LandedAction:
        if (verifyGameMode(Game.Mode.GAME_WINTILEHINTACTIONCARD))
        {
        // line 105 "../../../../../TileOControllerStates.ump"
          doPlayWinTileHintActionCard();
          setControllerStateGame(ControllerStateGame.StartOfTurn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitControllerState()
  {
    switch(controllerState)
    {
      case Game:
        exitGame();
        break;
    }
  }

  private void setControllerState(ControllerState aControllerState)
  {
    controllerState = aControllerState;

    // entry actions and do activities
    switch(controllerState)
    {
      case Game:
        if (controllerStateGame == ControllerStateGame.Null) { setControllerStateGame(ControllerStateGame.StartOfTurn); }
        break;
    }
  }

  private void setControllerStateGame(ControllerStateGame aControllerStateGame)
  {
    controllerStateGame = aControllerStateGame;
    if (controllerState != ControllerState.Game && aControllerStateGame != ControllerStateGame.Null) { setControllerState(ControllerState.Game); }
  }

  public void delete()
  {}


  /**
   * guard
   */
  // line 115 "../../../../../TileOControllerStates.ump"
   private boolean verifyGameMode(Game.Mode aMode){
    return TileOApplication.getTileO().getCurrentGame().getMode()==aMode;
  }

  // line 118 "../../../../../TileOControllerStates.ump"
   private boolean tileIsNormal(){
    return currentTile instanceof NormalTile || (currentTile instanceof ActionTile && ((ActionTile)currentTile).getInactivityStatus()==ActionTile.InactivityStatus.Inactive);
  }

  // line 121 "../../../../../TileOControllerStates.ump"
   private boolean tileIsWin(){
    return currentTile instanceof WinTile;
  }

  // line 124 "../../../../../TileOControllerStates.ump"
   private boolean tileIsAction(){
    return currentTile instanceof ActionTile && ((ActionTile)currentTile).getInactivityStatus() == ActionTile.InactivityStatus.Active;
  }


  /**
   * Design
   */
  // line 130 "../../../../../TileOControllerStates.ump"
   public Game newGame(int nPlayer) throws InvalidInputException{
    if (nPlayer < Game.minimumNumberOfPlayers())
    		throw new InvalidInputException("Not enough players");
    	if (nPlayer > Game.maximumNumberOfPlayers())
    		throw new InvalidInputException("Too many players");
    	
    	TileO tileo = TileOApplication.getTileO();    	
    	Game game = new Game(0);
    	tileo.addGame(game);
    	
    	int n = 0;
    	while (n < nPlayer){
    		Player p = new Player(n, game);
    		p.setColorByNumber();
    		n++;
    	}
    	game.setMode(Game.Mode.DESIGN);
    	setControllerState(ControllerState.Design);
    	setControllerStateGame(ControllerStateGame.Null);
    	tileo.setCurrentGame(game);
    	tileo.setCurrentGameClone(null);
    	return game;
  }

  // line 154 "../../../../../TileOControllerStates.ump"
   public void addRegularTile(int x, int y, Game game){
    TileO tileO = TileOApplication.getTileO();

        try{
            tileO.addNormalTile(x, y, game);
        }catch (RuntimeException e){

            System.out.print("Error");
        }
  }

  // line 166 "../../../../../TileOControllerStates.ump"
   public void addActionTile(int x, int y, Game game, int inactivityPeriod){
    TileO tileO = TileOApplication.getTileO();

        try{
            tileO.addActionTile(x, y, game, inactivityPeriod);
        }catch (RuntimeException e){

            System.out.print("Error");
        }
  }

  // line 178 "../../../../../TileOControllerStates.ump"
   public void addHiddenTile(int x, int y, Game game){
    try{
            WinTile wt = new WinTile(x, y, game);
            game.setWinTile(wt);
            
        }catch (RuntimeException e){

            System.out.print("Error");
        }
  }

  // line 190 "../../../../../TileOControllerStates.ump"
   public void removeTile(Tile tile, Game game){
    TileO tileO = TileOApplication.getTileO();
        if (tile instanceof WinTile)
        	tileO.getCurrentGame().setWinTile(null);
        tileO.removeTile(tile);
  }

  // line 197 "../../../../../TileOControllerStates.ump"
   public void addConnection(Tile t1, Tile t2, Game game) throws InvalidInputException{
    if (t1.isConnectedWith(t2))
    		throw new InvalidInputException("Tiles are already connected");
    	if (!game.connectTiles(t1, t2))
    		throw new InvalidInputException("Selected tiles are not adjacent");
  }

  // line 204 "../../../../../TileOControllerStates.ump"
   public void removeConnection(Tile t1, Tile t2, Game game) throws InvalidInputException{
    if (!game.disconnectTiles(t1, t2))
    		throw new InvalidInputException("These tiles are not connected");
  }

  // line 209 "../../../../../TileOControllerStates.ump"
   public void setStartingTile(int nPlayer, Tile t, Game game) throws InvalidInputException{
    if (t!=null && t!=game.getWinTile()) {
    		Player p = game.getPlayer(nPlayer);
        	p.setStartingTile(t);
    	}
    	else
    		throw new InvalidInputException("Invalid tile");
  }

  // line 218 "../../../../../TileOControllerStates.ump"
   public void updateCards(int numberOfCards, int cardType) throws InvalidInputException{
    Game game = TileOApplication.getTileO().getCurrentGame();
    	Deck deck = game.getDeck();
    	int toAdd = numberOfCards - deck.numberOfCardsForType(cardType);
    
    	if (toAdd>0) deck.addCards (toAdd, cardType);
    	else if (toAdd<0) deck.rmCards(toAdd*-1, cardType);
  }


  /**
   * Game
   */
  // line 230 "../../../../../TileOControllerStates.ump"
   private void cloneGame(Game game){
    TileO tileo = TileOApplication.getTileO();
    	if (game.getMode() == Game.Mode.DESIGN) {
	    	if (game.getFilename() == null || game.getFilename() == "") return; // if you never saved this design dont clone it
	    	Game cloned = game.clone();
	    	tileo.addGame(cloned);
	    	tileo.setCurrentGame(cloned);
	    	saveGame(""); // will use the current name for the design
	    	game.setFilename(null); // make sure it's saved under a new name
	    	tileo.setCurrentGame(game);
    	}
    	else {
    		Game clone = game.clone();
    		clone.setFilename(null);
    		tileo.addGame(clone);
    		if (game != tileo.getCurrentGameClone()) // if you're not restarting
    			tileo.setCurrentGameClone(clone);
    		else { // if you're restarting
    			tileo.setCurrentGame(clone);
    		}
    	}
  }

  // line 253 "../../../../../TileOControllerStates.ump"
   private void doStartGame(Game selectedGame) throws InvalidInputException{
    /* Starts the selected game if it respects the rules */   
        
        String error = "";
        Deck deck = selectedGame.getDeck();
        if (deck.numberOfCards() != Game.NumberOfActionCards)
            error+= "The deck needs to have "+Game.NumberOfActionCards+" cards ";
        if (selectedGame.getWinTile() == null)
            error+="There is no WinTile selected ";
        if (selectedGame.numberOfPlayers() < selectedGame.minimumNumberOfPlayers())
            error+="Needs minimum two players ";
        if (selectedGame.numberOfPlayers() > selectedGame.maximumNumberOfPlayers())
            error+="Too many players ";
        for (Player aPlayer : selectedGame.getPlayers())
            if (aPlayer.getStartingTile()==null)
                error+="Missing starting tile for player "+aPlayer.getNumber()+" ";
        
        if (!error.equals(""))
            throw new InvalidInputException(error.trim());
            
        cloneGame(selectedGame); 

        deck.shuffle();
        deck.setCurrentCard(deck.getCard(0));
        for (Tile aTile : selectedGame.getTiles()) aTile.setHasBeenVisited(false);
        for (Player aPlayer : selectedGame.getPlayers()) {
            aPlayer.setCurrentTile(aPlayer.getStartingTile());
            aPlayer.getCurrentTile().setHasBeenVisited(true);
        }
        selectedGame.setCurrentPlayer(selectedGame.getPlayers().get(0));
        selectedGame.setCurrentConnectionPieces(Game.SpareConnectionPieces);
        selectedGame.setMode(Game.Mode.GAME);
        cloneGame(selectedGame); // for restarting purpose
  }

  // line 288 "../../../../../TileOControllerStates.ump"
   private void doRollDie(){
    Game game = TileOApplication.getTileO().getCurrentGame();
    	setPossibleTiles(game.rollDie());
  }

  // line 293 "../../../../../TileOControllerStates.ump"
   private void doSelectNewTile(Tile aTile) throws InvalidInputException{
    if (getPossibleTiles().contains(aTile) || getPossibleTiles().size()==0)
    		setCurrentTile(aTile);
    	else 
    		throw new InvalidInputException("Invalid tile");
  }

  // line 300 "../../../../../TileOControllerStates.ump"
   private void doLand(Tile tile) throws InvalidInputException{
    /* Initiates when a player lands on a tile */
        tile.land();
        setPossibleTiles(null);
        setCurrentTile(null);
  }

  // line 308 "../../../../../TileOControllerStates.ump"
   private void doPlayRollDieActionCard() throws InvalidInputException{
    Game currentGame= TileOApplication.getTileO().getCurrentGame();
        ArrayList<Tile> availableTiles=null;
        currentGame.setMode(Game.Mode.GAME_ROLLDIEACTIONCARD);
        Deck currentDeck= currentGame.getDeck();
        Player currentPlayer= currentGame.getCurrentPlayer();
        ActionCard currentCard = currentDeck.getCurrentCard();
        if(currentCard instanceof RollDieActionCard){
        	RollDieActionCard playCard = (RollDieActionCard) currentCard;
        	availableTiles= playCard.play();
        }
        currentGame.setNextCard();
        currentGame.setMode(Game.Mode.GAME);
        setPossibleTiles(availableTiles);
  }

  // line 326 "../../../../../TileOControllerStates.ump"
   private void doPlayConnectTilesActionCard(Tile t1, Tile t2) throws InvalidInputException{
    Game currentGame = TileOApplication.getTileO().getCurrentGame();
        currentGame.setMode(Game.Mode.GAME_CONNECTTILESACTIONCARD);
        Player currentPlayer= currentGame.getCurrentPlayer();
        Deck currentDeck = currentGame.getDeck();
        ActionCard currentCard= currentDeck.getCurrentCard();
        if (t1.isConnectedWith(t2))
    		throw new InvalidInputException("Tiles are already connected");
        boolean wasConnected = false;
        if(currentGame.getCurrentConnectionPieces() <= 0)
        	wasConnected = true;
        else if(currentCard instanceof ConnectTilesActionCard){
            ConnectTilesActionCard playCard = (ConnectTilesActionCard) currentCard;
            wasConnected =playCard.play(t1, t2);            
        }
        if(wasConnected){
            currentGame.setNextPlayer();
            currentGame.setNextCard();
            currentGame.setMode(Game.Mode.GAME);
			if(!(currentGame.getCurrentConnectionPieces() == 0))
            	currentGame.setCurrentConnectionPieces(currentGame.getCurrentConnectionPieces() - 1);
            else
            	currentGame.setCurrentConnectionPieces(0);
            
        }
        else{
            throw new InvalidInputException("Tiles not adjacent, choose another tile");
        }
  }

  // line 357 "../../../../../TileOControllerStates.ump"
   private void doPlayRemoveConnectionActionCard(Tile t1, Tile t2) throws InvalidInputException{
    Game currentGame = TileOApplication.getTileO().getCurrentGame();
        currentGame.setMode(Game.Mode.GAME_REMOVECONNECTIONACTIONCARD);

        Player currentPlayer=currentGame.getCurrentPlayer();
        Deck currentDeck = currentGame.getDeck();
        ActionCard currentCard= currentDeck.getCurrentCard();
        boolean wasRemoved = false;
        if(currentCard instanceof RemoveConnectionActionCard){
            RemoveConnectionActionCard playCard = (RemoveConnectionActionCard) currentCard;
             wasRemoved=  playCard.play(t1, t2);
        }
        if(wasRemoved){
            currentGame.setNextPlayer();
            currentGame.setNextCard();
            currentGame.setMode(Game.Mode.GAME);
        }
        else{
            throw new InvalidInputException("No connection between tiles");
        }
  }

  // line 380 "../../../../../TileOControllerStates.ump"
   private void doPlayTeleportActionCard(Tile t) throws InvalidInputException{
    Game currentGame = TileOApplication.getTileO().getCurrentGame();
        currentGame.setMode(Game.Mode.GAME_TELEPORTACTIONCARD);
        Deck currentDeck = currentGame.getDeck();
        ActionCard currentCard= currentDeck.getCurrentCard();
        if (currentCard instanceof TeleportActionCard){
        	TeleportActionCard playCard = (TeleportActionCard) currentCard;
        	setCurrentTile(t);
        }

        currentGame.setNextCard();
        currentGame.setMode(Game.Mode.GAME);
  }

  // line 395 "../../../../../TileOControllerStates.ump"
   private void doPlayLoseTurnActionCard() throws InvalidInputException{
    Game currentGame = TileOApplication.getTileO().getCurrentGame();
    	Deck d = currentGame.getDeck();
    	ActionCard c = d.getCurrentCard();
    	if (!(c instanceof LoseTurnActionCard))
    		throw new InvalidInputException("Invalid card");
    	((LoseTurnActionCard) c).play();
    	
    	currentGame.setNextCard();
        currentGame.setNextPlayer();
        currentGame.setMode(Game.Mode.GAME);
  }

  // line 408 "../../../../../TileOControllerStates.ump"
   private void doPlayRemoveRandomTileActionCard(Game game){
    Deck deck = game.getDeck();
    ActionCard currentCard = deck.getCurrentCard();
    
    if (currentCard instanceof RemoveRandomTileActionCard) {
      ((RemoveRandomTileActionCard) currentCard).play();
    }
    
    game.setNextPlayer();
    game.setNextCard();
    game.setMode(Game.Mode.GAME);
  }

  // line 421 "../../../../../TileOControllerStates.ump"
   private boolean doPlayTurnInactiveActionCard(Game currentGame){
    List<Tile> allTiles = currentGame.getTiles();
	boolean success = false;
	for(int i = 0; i< allTiles.size(); i++){
	Tile aTile = allTiles.get(i);
	if (aTile instanceof ActionTile){
		ActionTile aActionTile = (ActionTile) aTile;
		success = aActionTile.deactivate();
		}
	}
	
	currentGame.setNextPlayer();
    currentGame.setNextCard();
    currentGame.setMode(Game.Mode.GAME);

		return success;
  }

  // line 439 "../../../../../TileOControllerStates.ump"
   private void doPlayChooseAdditionalMoveActionCard(int n){
    Game game = TileOApplication.getTileO().getCurrentGame();
  	setPossibleTiles(game.getCurrentPlayer().getPossibleMoves(n));
  }

  // line 444 "../../../../../TileOControllerStates.ump"
   private void doPlayRevealTileActionCard(){
    Game game = TileOApplication.getTileO().getCurrentGame();
  	game.setNextPlayer();
  	game.setNextCard();
  	game.setMode(Game.Mode.GAME);
  }

  // line 451 "../../../../../TileOControllerStates.ump"
   public String revealTile(Tile t){
    Game game = TileOApplication.getTileO().getCurrentGame();
  	ActionCard c = game.getDeck().getCurrentCard();
  	if (c instanceof RevealTileActionCard) {
  		return ((RevealTileActionCard)c).play(t);
  	}
  	return "";
  }

  // line 460 "../../../../../TileOControllerStates.ump"
   private void doPlaySendBackToStartActionCard(Tile t) throws InvalidInputException{
    Game currentGame = TileOApplication.getTileO().getCurrentGame();
	  	Player currentPlayer = currentGame.getCurrentPlayer();
	  	ArrayList<Player> otherPlayers= new ArrayList<Player>();
	  	for(Player p:currentGame.getPlayers()){
	  		if(p!=currentPlayer) otherPlayers.add(p);
	  	}
	  	currentGame.setMode(Game.Mode.GAME_SENDBACKTOSTARTACTIONCARD);
	  	Deck gameDeck = currentGame.getDeck();
	  	ActionCard currentCard= gameDeck.getCurrentCard();
	  	boolean wasSentBack= false;
	  	if(currentCard instanceof SendBackToStartActionCard){
	  		SendBackToStartActionCard playCard = (SendBackToStartActionCard)currentCard;
	  		wasSentBack = playCard.play(t,otherPlayers);
	  	}
	  	if(wasSentBack){
	  		 currentGame.setNextPlayer();
	         currentGame.setNextCard();
	         currentGame.setMode(Game.Mode.GAME);
	  	}
	  	else{
	  		throw new InvalidInputException("Not a Player");
	  	}
  }

  // line 485 "../../../../../TileOControllerStates.ump"
   private void doPlaySwapPositionActionCard(Tile t) throws InvalidInputException{
    Game currentGame = TileOApplication.getTileO().getCurrentGame();
     	Player currentPlayer = currentGame.getCurrentPlayer();
     	ArrayList<Player> otherPlayers= new ArrayList<Player>();
     	for(Player p:currentGame.getPlayers()){
       		if(p!=currentPlayer) otherPlayers.add(p);
     	}
     	currentGame.setMode(Game.Mode.GAME_SWAPPOSITIONACTIONCARD);
     	Deck gameDeck = currentGame.getDeck();
     	ActionCard currentCard= gameDeck.getCurrentCard();
    	boolean wasSwapped= false;
     	if(currentCard instanceof SwapPositionActionCard){
       		SwapPositionActionCard playCard = (SwapPositionActionCard) currentCard;
       		wasSwapped = playCard.play(t,otherPlayers, currentPlayer);
     	}
     	if(wasSwapped){
       		currentGame.setNextPlayer();
       		currentGame.setNextCard();
 			currentGame.setMode(Game.Mode.GAME);
 		}
 		else{
 			throw new InvalidInputException("Not a Player");
 		}
  }

  // line 510 "../../../../../TileOControllerStates.ump"
   public boolean winTileHint(Tile t) throws InvalidInputException{
    Game game = t.getGame();
 		Deck deck = game.getDeck();
 		ActionCard card = deck.getCurrentCard();
 		
 		if (!(card instanceof WinTileHintActionCard))
 			throw new InvalidInputException("Card does not match");
		return ((WinTileHintActionCard)card).play(t);
  }

  // line 520 "../../../../../TileOControllerStates.ump"
   private void doPlayWinTileHintActionCard(){
    Game game = TileOApplication.getTileO().getCurrentGame();
  		game.setNextPlayer();
  		game.setNextCard();
  		game.setMode(Game.Mode.GAME);
  }


  /**
   * Controls
   */
  // line 529 "../../../../../TileOControllerStates.ump"
   public void restartGame() throws InvalidInputException{
    Game currentGameClone = TileOApplication.getTileO().getCurrentGameClone();
	   	if (currentGameClone == null) {
		   throw new InvalidInputException("No base game");
	   	}
	   	cloneGame(currentGameClone);
	   	setControllerState(ControllerState.Game);
	   	setControllerStateGame(ControllerStateGame.StartOfTurn);
  }

  // line 540 "../../../../../TileOControllerStates.ump"
   public String saveGame(String filename){
    TileO tileo = TileOApplication.getTileO();
	   Game currentGame = tileo.getCurrentGame();
	   
	   // save the clone if needed (in GAME mode and never saved)
	   if (currentGame.getMode() != Game.Mode.DESIGN  && currentGame.getFilename()==null) {
		   Game currentGameClone = tileo.getCurrentGameClone();
		   TileOApplication.save(filename, false);
		   String gameName = currentGame.getFilename();
		   String cloneName = gameName.split("/")[1].split("\\.")[0] + "_clone";
		   tileo.setCurrentGame(currentGameClone);
		   TileOApplication.save(cloneName, true);
		   tileo.setCurrentGame(currentGame);
	   }
	   else {
		   TileOApplication.save(filename, false);
	   }
       return TileOApplication.getTileO().getCurrentGame().getFilename();
  }

  // line 560 "../../../../../TileOControllerStates.ump"
   public Game loadGame(String filename) throws InvalidInputException{
    TileO tileo = TileOApplication.getTileO();
        Game loadedGame = TileOApplication.load(filename);

        if (loadedGame == null)
            throw new InvalidInputException("The game you selected does not exists");
            
        // removes the game if it exists
        for (int i=0; i<tileo.getGames().size(); ++i){
        	if (loadedGame == tileo.getGame(i)){
        		tileo.removeGame(tileo.getGame(i));
        		break;
            }
        }
    
        tileo.addGame(loadedGame);
        tileo.setCurrentGame(loadedGame);
        
        loadedGame.changeDie();
        
        switch (loadedGame.getMode()) {
        	case DESIGN:
        		setControllerState(ControllerState.Design);
        		setControllerStateGame(ControllerStateGame.Null);
        		tileo.setCurrentGameClone(null);
        		break;
        	case GAME_WON:
        		setControllerState(ControllerState.Game);
        		setControllerStateGame(ControllerStateGame.GameWon);
        		break;
        	case GAME:
        		setControllerState(ControllerState.Game);
        		setControllerStateGame(ControllerStateGame.StartOfTurn);
        		break;
        	case GAME_ROLLDIEACTIONCARD:
        	case GAME_CONNECTTILESACTIONCARD:
        	case GAME_REMOVECONNECTIONACTIONCARD:
        	case GAME_TELEPORTACTIONCARD:
        	case GAME_LOSETURNACTIONCARD:
        	case GAME_REMOVERANDOMTILEACTIONCARD:
        	case GAME_TURNINACTIVEACTIONCARD:
        	case GAME_REVEALTILEACTIONCARD:
        	case GAME_SENDBACKTOSTARTACTIONCARD:
        	case GAME_CHOOSEADDITIONALMOVEACTIONCARD:
        	case GAME_SWAPPOSITIONACTIONCARD:
        	case GAME_WINTILEHINTACTIONCARD:
        		setControllerState(ControllerState.Game);
        		setControllerStateGame(ControllerStateGame.LandedAction);
        		break;	
        }
        
        // load its clone
        if (loadedGame.getMode() != Game.Mode.DESIGN) {
        	Game clone = TileOApplication.load(TileOApplication.CloneFolder + loadedGame.getFilename().split("/")[1].split("\\.")[0]+"_clone.game");
    		if (clone!=null) System.out.println("Successfuly loaded clone game");
    		else System.out.println("Error loading clone game");
    		tileo.setCurrentGameClone(clone);
        }

        return loadedGame;
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "currentTile" + "=" + (getCurrentTile() != null ? !getCurrentTile().equals(this)  ? getCurrentTile().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "possibleTiles" + "=" + (getPossibleTiles() != null ? !getPossibleTiles().equals(this)  ? getPossibleTiles().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}