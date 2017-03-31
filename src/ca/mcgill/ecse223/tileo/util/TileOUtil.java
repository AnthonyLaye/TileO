package ca.mcgill.ecse223.tileo.util;

import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.RollDieActionCard;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TileOUtil {
	public  static Game createGame(int nConn, int nCards, int nRows, int nPlayers, boolean swt, TileO tileo, TileOController controller) {
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
        tiles[0][0].delete();
        tiles[0][0] = new ActionTile(0,0, game, 4);
        
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
	
	public static Tile getTileClosestToWin(ArrayList<Tile> tiles) {
		if (tiles.size()==0)
			throw new RuntimeException("Method cannot be called with a list of size 0");
		Tile minTile = null;
		int min = 999;
    	for (Tile t: tiles) {
        	int n = t.getShortestPathToWin().size();
        	if (n!=0 && n<min) { // n is 0 if there's no path
        		min = n;
        		minTile = t;
        	}
        }
        return minTile;
	}
	
	public static Player getPlayerClosestToWin(ArrayList<Player> players) {
		ArrayList<Player> copy = new ArrayList<Player>(players);
		HashMap<Tile, Player> map = new HashMap<Tile, Player>();
		for (Player p: players)
			map.put(p.getCurrentTile(), p);
		Tile closest = TileOUtil.getTileClosestToWin(new ArrayList<Tile>(map.keySet()));
		if (closest == null) {
			Random rand = new Random();
			return copy.get(rand.nextInt(copy.size()));
		}
		else 
			return map.get(closest);
	}
	
	public static Tile getTileClosestManhattanToWin(ArrayList<Tile> tiles) {
		int min = 999;
		Tile minTile = null;
		for (Tile t: tiles) {
			int dist = t.manhattanToWin();
			if (dist < min) {
				min = dist;
				minTile = t;
			}
		}
		return minTile;
	}
}
