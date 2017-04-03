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

public class TemplatePlayer extends ComputerPlayer implements Serializable
{
	private static final long serialVersionUID = -7956850151322043897L;

	public TemplatePlayer (int number, Game game) {
        super(number, game);
    }

    protected Tile chooseTile(List<Tile> possibleTiles) {
        /*
            Defines the behaviour when this player has to choose a new tile to move to
            from a list of tiles.
            PossibleTiles is sure to be of size >= 1
        */
        throw new RuntimeException("Choose tile not implemented"); 
    }

    protected ArrayList<Tile> newConnection() {
        /*
            Must return two tiles in an ArrayList that are adjacent to connect them.
            I added t.getDisconnectedNeighbors() in Tile.java to get all the adajcent
            tiles to t that are not connected with t, it can be useful
        */
        throw new RuntimeException("New connection not implemented"); 
    }

    protected Connection deleteConnection() {
        /*
            Return a connection object that will be deleted
            The method name sucks, but dont delete the connection here
        */
        throw new RuntimeException("Delete connection not implemented"); 
    }
    
    protected Player choosePlayer(ArrayList<Player> players) {
		/*
		 * Return a player that the computer wants to send back to its 
		 * starting position
		 * */
    	throw new RuntimeException("Choose player not implemented");
	}
}
