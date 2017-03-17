package ca.mcgill.ecse223.tileo.computer;

import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.model.Connection;

import java.io.Serializable;
import java.lang.Thread;
import java.util.ArrayList;


public class ComputerPlayer extends Player implements Serializable 
{
	private static final long serialVersionUID = -7282718229884668931L;

	public ComputerPlayer(int number, Game game) {
        super(number, game);
    }

    public void takeTurn() {
        Tile newTile;
        ArrayList<Tile> possibleTiles;
        
        System.out.println("Computer starts turn at tile " + getCurrentTile().getX()+"-"+getCurrentTile().getY());
        possibleTiles = getGame().rollDie();
        System.out.println("Computer rolled die and got "+getGame().dieNumber);
        if (possibleTiles.size()==0) 
            newTile = getCurrentTile();
        else 
            newTile = chooseTile(possibleTiles);
        newTile.land();
        System.out.println("Computer landed on tile "+newTile.getX()+"-"+newTile.getY());
        
        if (newTile instanceof NormalTile)
            System.out.println("Computer finished turn");
        if (newTile instanceof WinTile)
            System.out.println("Computer won !");
    } 

    
    // Methods to overwrite to get specific behaviour

    private Tile chooseTile(ArrayList<Tile> possibleTiles) {
        // stupid behaviour
        System.out.println("Thinking ...");
        return possibleTiles.get(0);
    }
    private ArrayList<Tile> newConnection() {
        throw new RuntimeException("Not implemented");
    }
    private Connection deleteConnection() {
        throw new RuntimeException("Not implemented");
    }
}





