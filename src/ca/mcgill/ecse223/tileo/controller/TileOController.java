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
        
        if (deck.numberOfCards() != Game.NumberOfActionCards)
            error+= "The deck needs to have 32 cards ";
        if (selectedGame.getWinTile() == null)
            error+="There is no WinTile selected ";
        if (selectedGame.numberOfPlayers() < selectedGame.minimumNumberOfPlayers())
            error+="Needs minimum two players ";
        for (Player aPlayer : selectedGame.getPlayers())
            if (aPlayer.getStartingTile()==null)
                error+="Missing starting tile for player "+aPlayer.getNumber()+" ";
        
        if (!error.equals(""))
            throw new InvalidInputException(error.trim());

        TileOApplication.setCurrentGame(selectedGame);
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

    public boolean saveGame(String filename) {
        /* Saves the current game  */

        try {
            TileOApplication.save(filename);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

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
}
