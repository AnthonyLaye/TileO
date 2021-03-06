package ca.mcgill.ecse223.tileo.computer;

import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GodPlayer extends ComputerPlayer implements Serializable
{
	private static final long serialVersionUID = -7956850151322043897L;

	public GodPlayer (int number, Game game) {
        super(number, game);
    }

    protected Tile chooseTile(List<Tile> possibleTiles) {
        /*
            Defines the behaviour when this player has to choose a new tile to move to
            from a list of tiles.
        */
        System.out.println("God is thinking for a new tile...");
        
        Game game = getGame();
        Random rand = new Random();
        
        ArrayList<Tile> unvisitedActions = new ArrayList<Tile>();
        ArrayList<Tile> visitedActions = new ArrayList<Tile>();
        ArrayList<Tile> unvisitedNormals = new ArrayList<Tile>();
        ArrayList<Tile> visitedNormals = new ArrayList<Tile>();
        
        for (Tile aTile: possibleTiles) {
            if (aTile instanceof WinTile)
                return aTile;
        }

        // classify tiles
        for (Tile aTile: possibleTiles) {
            if (!(aTile.getHasBeenVisited())) {
                if (aTile instanceof ActionTile && ((ActionTile)aTile).getInactivityStatus()==ActionTile.InactivityStatus.Active)
                    unvisitedActions.add(aTile);
                else
                    unvisitedNormals.add(aTile);
            }
            else {
                if (aTile instanceof ActionTile && ((ActionTile)aTile).getInactivityStatus()==ActionTile.InactivityStatus.Active)
                    visitedActions.add(aTile);
                else
                    visitedNormals.add(aTile);
            }
        }
            
        // choose one
        if (unvisitedActions.size()!=0)
            return unvisitedActions.get(rand.nextInt(unvisitedActions.size()));
        else if (unvisitedNormals.size()!=0)
            return unvisitedNormals.get(rand.nextInt(unvisitedNormals.size()));
        else if (visitedActions.size()!=0)
            return visitedActions.get(rand.nextInt(visitedActions.size()));
        else
            return visitedNormals.get(rand.nextInt(visitedNormals.size()));
        
    }

    protected ArrayList<Tile> newConnection() {
        /*
            Must return two tiles in an ArrayList that are adjacent to connect them.
            I added t.getDisconnectedNeighbors() in Tile.java to get all the adajcent
            tiles to t that are not connected with t, it can be useful
        */
        System.out.println("God is thinking for a new connection...");

        ArrayList<Tile> connection = new ArrayList<Tile>(); 
        ArrayList<Tile> neighbors;
        Random rand = new Random();
        Game game = getGame();
        Tile t = game.getWinTile();
        boolean wasConnected = false;
            
        neighbors = t.getDisconnectedNeighbors();
        if (neighbors.size() != 0) {
            connection.add(t);
            connection.add(neighbors.get(rand.nextInt(neighbors.size())));
        }

        else {
            while (!wasConnected) {
                t = game.getTile(rand.nextInt(game.numberOfTiles()));
                if (t.numberOfConnections() < 4) {
                    neighbors = t.getDisconnectedNeighbors();
                    if (neighbors.size()>0) {
                        connection.add(t);
                        connection.add(neighbors.get(rand.nextInt(neighbors.size())));
                        wasConnected = true;
                    }
                }
            }
        }

        return connection;
    }

    /*protected void sendBackToStart(){
    	System.out.println("God is thinking of who to send back...");
    	ArrayList<Player> otherPlayers = new ArrayList<Player>();
    	for(Player p: getGame().getPlayers()) {
    		if(p!=this) otherPlayers.add(p);
    	}
    	Tile closest=sendPlayer(otherPlayers);
    	for(Player p:otherPlayers){
    		if(p.getCurrentTile()==closest){
    			p.setCurrentTile(p.getStartingTile());
    		}
    	}
    	getGame().setNextCard();
    	getGame().setNextPlayer();
    	getGame().setMode(Game.Mode.GAME);
    	
    }
    protected Tile sendPlayer(ArrayList<Player> otherPlayers){
    	Tile closest= otherPlayers.get(0).getCurrentTile();
    	Tile win = getGame().getWinTile();
    	for(Player p: otherPlayers){
    		Tile t= p.getCurrentTile();
    		if(Math.abs(t.getX()+t.getY()-win.getX()-win.getY())
					  <Math.abs(closest.getX()+closest.getY()-win.getX()-win.getY())){
    			closest = t;
    		}
    	}
		return closest;
    	
    }*/
    
    protected Connection deleteConnection() {
        /*
            Return a connection object that will be deleted
            The method name sucks, but dont delete the connection here
        */
        System.out.println("God is thinking to delete a connection...");
        
        Game game = getGame();
        Tile t;
        List<Connection> conns;
        Random rand = new Random();
        
        for (Player p: game.getPlayers()) {
            if (this != p) {
                t = p.getCurrentTile();
                conns = t.getConnections();
                if (conns.size()!=0)
                    return conns.get(rand.nextInt(conns.size()));
            }
        }
        
        return game.getConnection(rand.nextInt(game.numberOfConnections()));
    }

	protected Player choosePlayer(ArrayList<Player> players) {
		Random rand = new Random();
		Player randomPlayer = players.get(rand.nextInt(players.size()));
		return randomPlayer;
		
		/*Tile t;
		Tile closest=this.getCurrentTile();
		Tile win= getGame().getWinTile();
		for(Player p: getGame().getPlayers()){
			if(this!=p){
				t= p.getCurrentTile();
				if(Math.abs(t.getX()+t.getY()-win.getX()-win.getY())
				  <Math.abs(closest.getX()+closest.getY()-closest.getY())){
					closest=t;
					
				}
			}
		}
		if(this.getCurrentTile()==closest){
			closest=this.getStartingTile();
			for(Player p: getGame().getPlayers()){
				if(this!=p){
					t= p.getCurrentTile();
					if(Math.abs(t.getX()+t.getY()-win.getX()-win.getY())
					  <Math.abs(closest.getX()+closest.getY()-closest.getY())){
						closest=t;
						
					}
				}
			}
		}
		for(Player p: getGame().getPlayers()){
			if(p!=this&&p.getCurrentTile()==closest){
				p.setCurrentTile(p.getStartingTile());
				getGame().setNextCard();
				getGame().setNextPlayer();
				getGame().setMode(Game.Mode.GAME);
				
			}
		}
		*/
	}
	
}
