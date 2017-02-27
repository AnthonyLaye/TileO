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

public class TileOController {
    
    public TileOController() {
    }
    
    // Design
    public Game newGame(int nPlayer) throws InvalidInputException{
    	// YOUNES
    	throw new InvalidInputException("Not implemented");
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
    	// JAMES
    	throw new InvalidInputException("Not implemented");
    }
    
    public void removeConnection(Tile t1, Tile t2, Game game) throws InvalidInputException {
    	// JAMES
    	throw new InvalidInputException("Not implemented");
    }
    
    public void setStartingTile(int nPlayer, Tile t, Game game) throws InvalidInputException {
    	// YOUNES
    	throw new InvalidInputException("Not implemented");
    }
    
    public void createDeck(int nExtraTurn, int nNewConn, int nRmConn, int nTel, Game game) throws InvalidInputException{
    	// GABRIEL
    	throw new InvalidInputException("Not implemented");
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
    	// GABRIEL
    	return null;
    }

    public void land(Tile tile) throws InvalidInputException{
        /* Initiates when a player lands on a tile */

        tile.land();
    }
    
    public ArrayList<Tile> playRollDieActionCard() throws InvalidInputException {
    	// SAM
    	throw new InvalidInputException("Not implemented");
    	
    }
    
    public void playConnectTilesActionCard(Tile t1, Tile t2) throws InvalidInputException {
    	// SAM
    	throw new InvalidInputException("Not implemented");
    }
    
    public void playRemoveConnectionActionCard(Tile t1, Tile t2) throws InvalidInputException {
    	// SAM
    	throw new InvalidInputException("Not implemented");
    }
    
    public void playTeleportActionCard(Tile t) throws InvalidInputException {
    	// SAM
    	throw new InvalidInputException("Not implemented");
    }

    
    // Controls
    public boolean saveGame(String filename) {
        try {
            TileOApplication.save(filename);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
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
