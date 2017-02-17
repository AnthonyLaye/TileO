package ca.mcgill.ecse223.tileo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.exception.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.RollDieActionCard;
import ca.mcgill.ecse223.tileo.model.Deck;

public class TileOControllerTest {

	private static String saveFName = "test";
	private static String loadFName = TileOApplication.SavedFolder + "test.game";
	/*
    @BeforeClass
    public static void setUpOnce() {
    }
    */
	
    @Before
    public void setUp() {
    	File f = new File(loadFName);
    	f.delete();
        
        Player.resetMap();
    }
	
	@Test
	public void testPersistence() {
        TileOController controller = new TileOController();
        TileO tileo = TileOApplication.getTileO();
        Game game = createGame(10, 32, 5, 2, true, tileo, controller);
        game.setMode(Game.Mode.GAME);
        TileOApplication.setCurrentGame(game);
        
        // save the game
        controller.saveGame(saveFName);
        File f = new File(loadFName);
        assertTrue(f.exists());
        
        // now load the saved game
        try {
        	Game loadedGame = controller.loadGame(loadFName);
        	checkLoadedGame(loadedGame, 10, Game.Mode.GAME, 2, 25);
        } catch (InvalidInputException e) {
        	System.out.println(e.getMessage());
            fail();
        }
        f.delete();
        tileo.removeGame(game);
    }
	private void checkLoadedGame(Game loadedGame, int connPieces, Game.Mode mode, int nPlayers, int nTiles){
		assertEquals(loadedGame.getCurrentConnectionPieces(), connPieces);
		assertEquals(loadedGame.getMode(), mode);
		assertEquals(loadedGame.numberOfPlayers(), nPlayers);
		assertEquals(loadedGame.numberOfTiles(), nTiles+1); //+1 is the WinTile
		assertNotNull(loadedGame.getDeck());
		assertNotNull(loadedGame.getDie());
	}
	
	@Test
	public void testPersistenceReinitialization() {
		
	}


    @Test
    public void startGameTestGood() {
        TileOController controller = new TileOController();
        TileO tileo = TileOApplication.getTileO();
      
        Game game = createGame(10, 32, 5, 2, true, tileo, controller);
        int t=0;  // set startingTile
        for (Player aPlayer : game.getPlayers()) {
            aPlayer.setStartingTile(game.getTile(t++));
        }
        try {
            controller.startGame(game);
        } catch (InvalidInputException e) {
            fail();
        }
        // TODO check if deck is shuffled
    }

    @Test
    public void startGameTestMissingCards() {
        TileOController controller = new TileOController();
        TileO tileo = TileOApplication.getTileO();
        Game game = createGame(10, 31, 5, 2, true, tileo, controller);
        int t=0;
        for (Player aPlayer : game.getPlayers()) {
            aPlayer.setStartingTile(game.getTile(t++));
        }
        try {
            controller.startGame(game);
            fail();
        } catch (InvalidInputException e) {
            assertEquals(e.getMessage(), "The deck needs to have 32 cards");
        }
    }


    @Test public void startGameTestAllErrors() {
        TileOController controller = new TileOController();
        TileO tileo = TileOApplication.getTileO();
        Game game = createGame(10, 31, 5, 1, false, tileo, controller);
        try {
            controller.startGame(game);
            fail();
        } catch (InvalidInputException e) {
            assertEquals(e.getMessage(), "The deck needs to have 32 cards There is no WinTile selected Needs minimum two players Missing starting tile for player 0");
        }
        
    }
    
    public Game createGame(int nConn, int nCards, int nRows, int nPlayers, boolean swt, TileO tileo, TileOController controller) {
        // Helper to create a game with certain characteristics, useful for errors check

        Game game = new Game(nConn, tileo);
        game.setMode(Game.Mode.GAME);
        Deck d = game.getDeck(); // populate deck
        for(int i=0; i<nCards; ++i)
            new RollDieActionCard("", d);
        NormalTile[][] tiles = new NormalTile[nRows][nRows];
        for (int i=0; i<nRows; ++i){ // add tiles
            for (int j=0; j<nRows; ++j)
                tiles[i][j] = new NormalTile(i,j,game);
        }
        if (swt) {
            WinTile wt = new WinTile(nRows-1,nRows-1,game);
            game.setWinTile(wt);
        }
        for (int i=0; i<nPlayers; ++i) // add players
            game.addPlayer(i);

        return game;
    }
}
