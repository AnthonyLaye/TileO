package ca.mcgill.ecse223.tileo.application;

import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.tileo.view.TileOPage;

import java.io.*;

public class TileOApplication {
    
    private static TileO tileo;
    public static final String SavedFolder = "savedGames/";

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TileOPage().setVisible(true);
            }
        });  
    }


    public static TileO getTileO() {
        if (tileo==null)
            return tileo = new TileO();
        return tileo;
    }

    public static void setCurrentGame(Game aGame) {
        tileo.setCurrentGame(aGame);
    }

    public static void save(String filename) {
    	Game game = tileo.getCurrentGame();
        
        if (filename.equals("")) {
            if (game.getFilename() == null){ // first time this game is saved
                filename = createFilename(game);
                game.setFilename(filename);
            } else
                filename = game.getFilename();
        } else {
            filename = cleanFilename(filename, game.getMode());
            game.setFilename(filename);
        }
        
        PersistenceObjectStream.setFilename(filename);
        PersistenceObjectStream.serialize(game);
    }

    public static Game load(String filename) {
        PersistenceObjectStream.setFilename(filename);
        Game game = (Game) PersistenceObjectStream.deserialize();
        return game;
    }
    
    public static String cleanFilename(String filename, Game.Mode mode) {
        String cleanFName = filename.replaceAll("[^0-9a-zA-Z]", "_");
        
        if (mode == Game.Mode.DESIGN)
        	return SavedFolder + cleanFName + ".design";
        return SavedFolder + cleanFName + ".game";
    }

    private static String createFilename(Game game) {
        String ext;
        String base;
        int i=0;
        File f;

        if (game.getMode() == Game.Mode.DESIGN) {
            ext = ".design";
            base = "d";
        } else {
            ext = ".game";
            base = "g";
        }
        
        while (true) {
            f = new File(SavedFolder+base+i+ext);
            if (!f.exists())
                return SavedFolder+base+i+ext;
            ++i;
        }
    }
}