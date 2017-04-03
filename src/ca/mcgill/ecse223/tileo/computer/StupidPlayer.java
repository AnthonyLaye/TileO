package ca.mcgill.ecse223.tileo.computer;

import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StupidPlayer extends ComputerPlayer implements Serializable
{
	private static final long serialVersionUID = -7956850151322043897L;

	public StupidPlayer (int number, Game game) {
        super(number, game);
    }

    protected Tile chooseTile(List<Tile> possibleTiles) {
        /* stupid behaviour
            returns a tile to move onto
        */
        System.out.println("Stupid is thinking for a new tile...");
        Random rand = new Random();
        return possibleTiles.get(rand.nextInt(possibleTiles.size()));
    }

    protected ArrayList<Tile> newConnection() {
        // still stupid
        // returns two adjacent tile to connect
        System.out.println("Stupid is thinking for a new connection...");
        Tile t;
        ArrayList<Tile> neigbors, connection = new ArrayList<Tile>();
        Game game = getGame();
        Random rand = new Random();
        boolean wasConnected = false;
        int n;

        while (!wasConnected) {
            n = rand.nextInt(game.numberOfTiles());
            t = game.getTile(n);

            if (t.numberOfConnections() < 4) {
                neigbors = t.getDisconnectedNeighbors();
                if (neigbors.size()>0) {
                    n = rand.nextInt(neigbors.size());
                    connection.add(t);
                    connection.add(neigbors.get(n));
                    wasConnected = true;
                }
            }
        }
        return connection;
    }

    protected Connection deleteConnection() {
        // again, stupid
        // returns a connection to delete
        System.out.println("Stupid is thinking to delete a connection");
        Random rand = new Random();
        return getGame().getConnection(rand.nextInt(getGame().numberOfConnections()));
    }
    
    protected Player choosePlayer(ArrayList<Player> players) {
    	System.out.println("Stupid is thinking to send a player back to its starting position...");
    	Random rand = new Random();
    	Player randomPlayer = players.get(rand.nextInt(players.size()));
		return randomPlayer;
	}
}
