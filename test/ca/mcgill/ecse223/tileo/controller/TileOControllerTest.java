package ca.mcgill.ecse223.tileo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.*;

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
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.Connection;

public class TileOControllerTest {

	private static String saveFName = "allo";
	private static String loadFName = TileOApplication.SavedFolder + "allo.game";
	/*
    @BeforeClass
    public static void setUpOnce() {
    }
    */
	
    @Before
    public void setUp() {
    	File f = new File(loadFName);
    	f.delete();
    }
	
	@Test
	public void testPersistence() {
        TileOController controller = new TileOController();
        TileO tileo = TileOApplication.getTileO();
        Game game = createGame(10, 32, 5, 2, true, tileo, controller);
        game.setMode(Game.Mode.GAME);
        tileo.setCurrentGame(game);
        
        // save the game
        controller.saveGame(saveFName);
        File f = new File(loadFName);
        
        assertTrue(f.exists());
        assertEquals(loadFName, game.getFilename());
        
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
		assertEquals(loadedGame.numberOfTiles(), nTiles);
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
    
    @Test
    public void playTurnTest() {
        // Only checks for the possible moves,
        // TODO complete test when the controller method is implemented
    	TileOController controller = new TileOController();
    	TileO tileo = TileOApplication.getTileO();
    	Game game = createGame(10, 31, 5, 2, true, tileo, controller);
    	Player p = game.getPlayer(0);
    	p.setCurrentTile(game.getTile(0));

    	List<Tile> possibleMoves = p.getPossibleMoves(2);
        List<Tile> legalMoves = new ArrayList<Tile>();
        legalMoves.add(game.getTile(2));
        legalMoves.add(game.getTile(6));
        legalMoves.add(game.getTile(10));

    	for (Tile aTile : possibleMoves) {
            if (!legalMoves.contains(aTile))
                fail();
            legalMoves.remove(aTile);
        }
    	
    	if (legalMoves.size()!=0) fail();
    }
    
    
    public Game createGame(int nConn, int nCards, int nRows, int nPlayers, boolean swt, TileO tileo, TileOController controller) {
        // Helper to create a game with certain characteristics, useful for errors check

        Game game = new Game(nConn);
        tileo.addGame(game);
        game.setMode(Game.Mode.GAME);
        Deck d = game.getDeck(); // populate deck
        for(int i=0; i<nCards; ++i)
            new RollDieActionCard("", d);
        Tile[][] tiles = new Tile[nRows][nRows];
        for (int y=0; y<nRows; ++y){ // add tiles
            for (int x=0; x<nRows; ++x)
                tiles[x][y] = new NormalTile(x,y,game);
        }
        if (swt) {
        	WinTile wt;
        	tiles[nRows-1][nRows-1].delete();
            tiles[nRows-1][nRows-1] = wt = new WinTile(nRows-1,nRows-1,game);
            game.setWinTile(wt);
        }
        for (int i=0; i<nPlayers; ++i) // add players
            game.addPlayer(i);
        // connect everything from
        for (int y=0; y<nRows; ++y) {
        	for (int x=0; x<nRows; ++x) {
        		Connection conn;
        		
        		if (x < nRows-1){
        			conn = new Connection(game); // left-right
        			conn.addTile(tiles[x][y]);
        			conn.addTile(tiles[x+1][y]);
        		}
        		if (y < nRows-1){
        			conn = new Connection(game); // top-down
        			conn.addTile(tiles[x][y]);
        			conn.addTile(tiles[x][y+1]);
        		}
        	}
        }
        return game;
    }
}
