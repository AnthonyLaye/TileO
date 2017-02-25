package ca.mcgill.ecse223.tileo.controller;


import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.exception.InvalidInputException; 
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.Player;


public class TileOController {
    
    public TileOController() {
    }
    
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
        for (Tile aTile : selectedGame.getTiles()) aTile.setHasBeenVisited(false);
        for (Player aPlayer : selectedGame.getPlayers()) {
            aPlayer.setCurrentTile(aPlayer.getStartingTile());
            aPlayer.getCurrentTile().setHasBeenVisited(true);
        }
        selectedGame.setCurrentPlayer(selectedGame.getPlayers().get(0));
        selectedGame.setCurrentConnectionPieces(Game.SpareConnectionPieces);
        selectedGame.setMode(Game.Mode.GAME);
    }

    public void land(Tile tile) throws InvalidInputException{
        /* Initiates when a player lands on a tile */

        tile.land();
    }

    public void addRegularTile(int x, int y, Game game){

        TileO tileO = TileOApplication.getTileO();

        try{
            tileO.addNormalTile(x, y, game);
            //TileOApplication.save();
        }catch (RuntimeException e){

            System.out.print("Error");
        }
    }

    public void addActionTile(int x, int y, Game game, int inactivityPeriod){

        TileO tileO = TileOApplication.getTileO();

        try{
            tileO.addActionTile(x, y, game, inactivityPeriod);
            //TileOApplication.save();
        }catch (RuntimeException e){

            System.out.print("Error");
        }
    }

    public void addHiddenTile(int x, int y, Game game){

        TileO tileO = TileOApplication.getTileO();

        try{
            tileO.addWinTile(x, y, game);
            //TileOApplication.save();
        }catch (RuntimeException e){

            System.out.print("Error");
        }
    }

    public void removeTile(Tile tile){

        TileO tileO = TileOApplication.getTileO();
        tileO.removeTile(tile);
    }

    public boolean saveGame(String filename) {
        /* Saves the current game  */

        try {
            TileOApplication.save(filename);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

<<<<<<< HEAD
=======

>>>>>>> 3e1b6bcd79cc6cf9aebe203622ca70ed3cde7799
    public Game loadGame(String filename) throws InvalidInputException {
        /* Loads a game  */
        
        TileO tileo = TileOApplication.getTileO();
        Game loadedGame = TileOApplication.load(filename);

        if (loadedGame == null)
            throw new InvalidInputException("The game you selected does not exists");
    
        tileo.addGame(loadedGame);
        tileo.setCurrentGame(loadedGame);

        return loadedGame;
    }
    
    public Game newGame(int nPlayer) throws InvalidInputException{
    	if (nPlayer < Game.minimumNumberOfPlayers())
    		throw new InvalidInputException("Not enough players");
    	if (nPlayer > Game.maximumNumberOfPlayers())
    		throw new InvalidInputException("Too many players");
    	
    	TileO tileo = TileOApplication.getTileO();
    	
    	Game game = new Game(0, tileo);
    	int n = 0;
    	while (nPlayer > 0){
    		try {
    			new Player(n, game);
    			nPlayer--;
    		}
    		catch (RuntimeException e) {}
    		n++;
    	}
    	game.setMode(Game.Mode.DESIGN);
    	tileo.setCurrentGame(game);
    	return game;
    }

}
