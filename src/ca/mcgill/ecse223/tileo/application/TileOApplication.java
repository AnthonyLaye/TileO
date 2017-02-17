package ca.mcgill.ecse223.tileo.application;

import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.TileO;

import ca.mcgill.ecse223.tileo.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.tileo.controller.TileOController;



public class TileOApplication {
    
    private static TileO tileo;
    public static String SavedFolder = "savedGames/";

    public static void main(String args[]) {
    
    
    }


    public static TileO getTileO() {
        if (tileo==null)
            return tileo = new TileO();
        return tileo;
    }

    public static void setCurrentGame(Game aGame) {
        if (tileo != null)
            tileo.setCurrentGame(aGame);
    }

    public static void save(String filename) {
    	Game game = tileo.getCurrentGame();

        PersistenceObjectStream.setFilename(cleanFilename(filename, game.getMode()));
        PersistenceObjectStream.serialize(game); 
    }

    public static Game load(String filename) {
        PersistenceObjectStream.setFilename(filename);
        Game game = (Game) PersistenceObjectStream.deserialize();
        return game;
    }
    
    public static String cleanFilename(String filename, Game.Mode mode) {
        String cleanFName;

        cleanFName = filename.replace('/', '_');
        cleanFName = cleanFName.replaceAll("[^0-9a-zA-Z]", "_");
        
        if (mode == Game.Mode.DESIGN)
        	return SavedFolder + cleanFName + ".design";
        return SavedFolder + cleanFName + ".game";
    }
}
