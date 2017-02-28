package ca.mcgill.ecse223.tileo.controller;


import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.exception.InvalidInputException; 
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.ConnectTilesActionCard;
import ca.mcgill.ecse223.tileo.model.LoseTurnActionCard;
import ca.mcgill.ecse223.tileo.model.RemoveConnectionActionCard;
import ca.mcgill.ecse223.tileo.model.RollDieActionCard;
import ca.mcgill.ecse223.tileo.model.TeleportActionCard;

import java.util.ArrayList;
import java.util.List;

public class TileOController {
    
    public TileOController() {
    }
    
    // Design
    public Game newGame(int nPlayer) throws InvalidInputException{
    	if (nPlayer < Game.minimumNumberOfPlayers())
    		throw new InvalidInputException("Not enough players");
    	if (nPlayer > Game.maximumNumberOfPlayers())
    		throw new InvalidInputException("Too many players");
    	
    	TileO tileo = TileOApplication.getTileO();    	
    	Game game = new Game(0, tileo);
    	
    	Player.Color[] colors = {Player.Color.RED, Player.Color.BLUE, Player.Color.GREEN, Player.Color.YELLOW};
    	Player.resetMap();
    	int n = 0;
    	while (nPlayer > 0){
    		try {
    			Player p = new Player(n, game);
    			p.setColor(colors[n]);
    			nPlayer--;
    		}
    		catch (RuntimeException e) {}
    		n++;
    	}
    	game.setMode(Game.Mode.DESIGN);
    	tileo.setCurrentGame(game);
    	return game;
    }
    
    public void addRegularTile(int x, int y, Game game){

        TileO tileO = TileOApplication.getTileO();

        try{
            tileO.addNormalTile(x, y, game);
        }catch (RuntimeException e){

            System.out.print("Error");
        }
    }

    public void addActionTile(int x, int y, Game game, int inactivityPeriod){

        TileO tileO = TileOApplication.getTileO();

        try{
            tileO.addActionTile(x, y, game, inactivityPeriod);
        }catch (RuntimeException e){

            System.out.print("Error");
        }
    }

    public void addHiddenTile(int x, int y, Game game){

        TileO tileO = TileOApplication.getTileO();

        try{
            WinTile wt = new WinTile(x, y, game);
            game.setWinTile(wt);
            
        }catch (RuntimeException e){

            System.out.print("Error");
        }
    }
    
    public void removeTile(Tile tile, Game game){

        TileO tileO = TileOApplication.getTileO();
        if (tile instanceof WinTile)
        	tileO.getCurrentGame().setWinTile(null);
        tileO.removeTile(tile);
    }
    
    public void addConnection(Tile t1, Tile t2, Game game) throws InvalidInputException {
    	if (t1.isConnectedWith(t2))
    		throw new InvalidInputException("Tiles are already connected");
    	if (!game.connectTiles(t1, t2))
    		throw new InvalidInputException("Selected tiles are not adjacent");
    }
    
    public void removeConnection(Tile t1, Tile t2, Game game) throws InvalidInputException {
    	if (!game.disconnectTiles(t1, t2))
    		throw new InvalidInputException("These tiles are not connected");
    }
    
    public void setStartingTile(int nPlayer, Tile t, Game game) throws InvalidInputException {
    	if (t!=null && t!=game.getWinTile()) {
    		Player p = game.getPlayer(nPlayer);
        	p.setStartingTile(t);
    	}
    	else
    		throw new InvalidInputException("Invalid tile");
    }
    
    public void createDeck(int nExtraTurn, int nNewConn, int nRmConn, int nTel, Game game) throws InvalidInputException{
    	if (nExtraTurn+nNewConn+nRmConn+nTel != 32) 
    		throw new InvalidInputException("Wrong number of action cards");
    	
    	Deck d = game.getDeck();
    	for (ActionCard card: d.getCards())
    		d.removeCard(card);
    	
    	for (int i=0;i<nExtraTurn;++i)
    		new RollDieActionCard("Roll the die for an extra turn", d);
    	for (int i=0;i<nNewConn;++i)
    		new ConnectTilesActionCard("Connect two tiles", d);
    	for (int i=0;i<nRmConn;++i)
    		new RemoveConnectionActionCard("Remove a connection", d);
    	for (int i=0;i<nTel;++i)
    		new TeleportActionCard("Move your piece to a new tile", d);
    }
    
    
    // Game
    public void startGame(Game selectedGame) throws InvalidInputException {
        /* Starts the selected game if it respects the rules */    
        
        String error = "";
        Deck deck = selectedGame.getDeck();
        System.out.println(deck.numberOfCards() + "  "+Game.NumberOfActionCards);
        if (deck.numberOfCards() != Game.NumberOfActionCards)
            error+= "The deck needs to have 32 cards ";
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
    }
    
    public ArrayList<Tile> rollDie() {
    	Game game = TileOApplication.getTileO().getCurrentGame();
    	return game.rollDie();
    }

    public void land(Tile tile) throws InvalidInputException{
        /* Initiates when a player lands on a tile */
        tile.land();
    }
    
    public ArrayList<Tile> playRollDieActionCard() throws InvalidInputException {

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
        currentDeck.setCurrentCard(currentDeck.getCard(currentDeck.indexOfCard(currentCard)+1));
        currentGame.setMode(Game.Mode.GAME);
        return availableTiles;
        
    }
    
    public void playConnectTilesActionCard(Tile t1, Tile t2) throws InvalidInputException {

        Game currentGame = TileOApplication.getTileO().getCurrentGame();
        currentGame.setMode(Game.Mode.GAME_CONNECTTILESACTIONCARD);
        Player currentPlayer= currentGame.getCurrentPlayer();
        Deck currentDeck = currentGame.getDeck();
        ActionCard currentCard= currentDeck.getCurrentCard();
        if (t1.isConnectedWith(t2))
    		throw new InvalidInputException("Tiles are already connected");
        if(currentCard instanceof ConnectTilesActionCard){
            ConnectTilesActionCard playCard = (ConnectTilesActionCard) currentCard;
            playCard.play(t1, t2);
        }
        currentGame.setCurrentPlayer(currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer)+1));
        currentDeck.setCurrentCard(currentDeck.getCard(currentDeck.indexOfCard(currentCard)+1));
        currentGame.setMode(Game.Mode.GAME);

    }
    
    public void playRemoveConnectionActionCard(Tile t1, Tile t2) throws InvalidInputException {
    	
        Game currentGame = TileOApplication.getTileO().getCurrentGame();
        currentGame.setMode(Game.Mode.GAME_REMOVECONNECTIONACTIONCARD);

        Player currentPlayer=currentGame.getCurrentPlayer();
        Deck currentDeck = currentGame.getDeck();
        ActionCard currentCard= currentDeck.getCurrentCard();
        if(currentCard instanceof RemoveConnectionActionCard){
            RemoveConnectionActionCard playCard = (RemoveConnectionActionCard) currentCard;
            playCard.play(t1, t2);
        }

        currentGame.setCurrentPlayer(currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer)+1));
        currentDeck.setCurrentCard(currentDeck.getCard(currentDeck.indexOfCard(currentCard)+1));
        currentGame.setMode(Game.Mode.GAME);
    }
    
    public void playTeleportActionCard(Tile t) throws InvalidInputException {

        Game currentGame = TileOApplication.getTileO().getCurrentGame();
        currentGame.setMode(Game.Mode.GAME_REMOVECONNECTIONACTIONCARD);
        Deck currentDeck = currentGame.getDeck();
        ActionCard currentCard= currentDeck.getCurrentCard();
        if (currentCard instanceof TeleportActionCard){
        	TeleportActionCard playCard = (TeleportActionCard) currentCard;
        	playCard.play(t);
        }
        currentDeck.setCurrentCard(currentDeck.getCard(currentDeck.indexOfCard(currentCard)+1));
        currentGame.setMode(Game.Mode.GAME);

    }

    
    // Controls
    public String saveGame(String filename) {
        TileOApplication.save(filename);
        return TileOApplication.getTileO().getCurrentGame().getFilename();
    }

    public Game loadGame(String filename) throws InvalidInputException {
        TileO tileo = TileOApplication.getTileO();
        Game loadedGame = TileOApplication.load(filename);

        if (loadedGame == null)
            throw new InvalidInputException("The game you selected does not exists");
    
        tileo.addGame(loadedGame);
        tileo.setCurrentGame(loadedGame);

        return loadedGame;
    }
}
