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

public class HackerPlayer extends ComputerPlayer implements Serializable
{
    private static final long serialVersionUID = -7956850151322043897L;

    public HackerPlayer (int number, Game game) {
        super(number, game);
    }

    protected Tile chooseTile(List<Tile> possibleTiles) {
        /*
            Defines the behaviour when this player has to choose a new tile to move to
            from a list of tiles.
        */

        System.out.println("Hacker is cheating...");

        Game game = getGame();

        for(Tile tile: possibleTiles){
            if (tile instanceof WinTile)
                return tile;
        }

        for(Tile tile: possibleTiles){
            if (tile instanceof ActionTile)
                return tile;
        }




        Random rand = new Random();
        return possibleTiles.get(rand.nextInt(possibleTiles.size()));
    }

    protected ArrayList<Tile> newConnection() {
        /*
            Must return two tiles in an ArrayList that are adjacent to connect them.
            I added t.getDisconnectedNeighbors() in Tile.java to get all the adajcent
            tiles to t that are not connected with t, it can be useful
        */

        System.out.println("Hacker is thinking for a new connection...not that he has to");
        Tile t;
        Game game = getGame();
        ArrayList<Tile> neigbors, connection = new ArrayList<Tile>();
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
        /*
            Return a connection object that will be deleted
            The method name sucks, but dont delete the connection here
        */

        System.out.println("Hacker is thinking to delete a connection... maybe he'll just delete all of them");
        Random rand = new Random();
        return getGame().getConnection(rand.nextInt(getGame().numberOfConnections()));

    }
    
    protected Player choosePlayer(ArrayList<Player> players) {
    	System.out.println("Hacker is thinking to send a player back to its starting position...");
    	Random rand = new Random();
    	Player randomPlayer = players.get(rand.nextInt(players.size()));
		return randomPlayer;
	}
}
