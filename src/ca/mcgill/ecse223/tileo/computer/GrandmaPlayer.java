package ca.mcgill.ecse223.tileo.computer;

import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.util.TileOUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrandmaPlayer extends ComputerPlayer implements Serializable
{
	private static final long serialVersionUID = -7956850151322043897L;

	public GrandmaPlayer (int number, Game game) {
        super(number, game);
    }

    protected Tile chooseTile(List<Tile> possibleTiles) {
        /*
            Defines the behaviour when this player has to choose a new tile to move to
            from a list of tiles.
        */
        System.out.println("Grandma is thinking for a new tile...");
        
        ArrayList<Tile> allTiles = new ArrayList<>(possibleTiles);
        
        ArrayList<Tile> unvisitedActions = new ArrayList<Tile>();
        ArrayList<Tile> visitedActions = new ArrayList<Tile>();
        ArrayList<Tile> unvisitedNormals = new ArrayList<Tile>();
        ArrayList<Tile> visitedNormals = new ArrayList<Tile>();

        // classify tiles
        for (Tile aTile: possibleTiles) {
        	if (aTile instanceof WinTile)
                return aTile;
        	
        	else if (!(aTile.getHasBeenVisited())) {
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
        
        // try to find one with the shortest path to the WinTile
        Tile t = TileOUtil.getTileClosestToWin(allTiles);
        if (t != null) {
        	System.out.println("Closest tile to win is "+t.getX()+"-"+t.getY()+" and is "+t.getShortestPathToWin().size()+" rolls away");
        	return t;
        }
        
        // choose another one that can't reach the goal state
        if (unvisitedActions.size()!=0) {
            t = TileOUtil.getTileClosestManhattanToWin(unvisitedActions);
            System.out.println("Closest unvisited action tile to win is "+t.getX()+"-"+t.getY()+" and is "+t.manhattanToWin()+" manhattan away");
        }
        else if (visitedActions.size()!=0) {
        	t = TileOUtil.getTileClosestManhattanToWin(visitedActions);
        	System.out.println("Closest visited action tile to win is "+t.getX()+"-"+t.getY()+" and is "+t.manhattanToWin()+" manhattan away");
        }
        else if (unvisitedNormals.size()!=0){
        	t = TileOUtil.getTileClosestManhattanToWin(unvisitedNormals);
        	System.out.println("Closest unvisited normal tile to win is "+t.getX()+"-"+t.getY()+" and is "+t.manhattanToWin()+" manhattan away");
        }
        else {
        	t = TileOUtil.getTileClosestManhattanToWin(visitedNormals);
        	System.out.println("Closest visited normal tile to win is "+t.getX()+"-"+t.getY()+" and is "+t.manhattanToWin()+" manhattan away");
        }
        return t;
    }

    protected ArrayList<Tile> newConnection() {
        /*
            Must return two tiles in an ArrayList that are adjacent to connect them.
            I added t.getDisconnectedNeighbors() in Tile.java to get all the adajcent
            tiles to t that are not connected with t, it can be useful
        */
        System.out.println("Grandma is thinking for a new connection...");

        ArrayList<Tile> connection = new ArrayList<Tile>(); 
        ArrayList<Tile> neighbors;
        Random rand = new Random();
        Game game = getGame();
        Tile t = game.getWinTile();
        boolean wasConnected = false;
            
        neighbors = t.getDisconnectedNeighbors();
        if (neighbors.size() != 0) {
        	int shortestPath = 999;
        	Tile shortestPathTile=neighbors.get(0);
        	for (Tile otherTile: neighbors) {
        		Connection conn = new Connection(getGame());
        		conn.addTile(t);
        		conn.addTile(otherTile);
        		int size = getCurrentTile().getShortestPathToWin().size();
        		if (size>0 && size<shortestPath) {
        			shortestPath = size;
        			shortestPathTile = otherTile;
        		}
        		conn.delete();
        	}
            connection.add(t);
            connection.add(shortestPathTile);
        }

        else { // make it look a path instead
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

    protected Connection deleteConnection() {
        /*
            Return a connection object that will be deleted
            The method name sucks, but don't delete the connection here
        */
        System.out.println("Grandma is thinking to delete a connection...");
        
        Game game = getGame();
        Tile t;
        List<Connection> conns;
        Random rand = new Random();
        ArrayList<Player> others = new ArrayList<Player>();
        
        for (Player p: game.getPlayers()) {
            if (this != p)
                others.add(p);
        }
        while(others.size()>0) { // try to isolate the closest player to win
	        Player p = TileOUtil.getPlayerClosestToWin(others);
	        t = p.getCurrentTile();
	        conns = t.getConnections();
	        if (conns.size()!=0)
	            return conns.get(rand.nextInt(conns.size()));
	        else
	        	others.remove(p);
        }
        
        ArrayList<Tile> shortestPath = getCurrentTile().getShortestPathToWin(); // don't delete a connection on your path to goal
        ArrayList<Connection> possibleConns = new ArrayList<Connection>();
        for (Connection conn: game.getConnections()) {
        	if (!(shortestPath.contains(conn.getTile(0)) || shortestPath.contains(conn.getTile(1)))) {
        		possibleConns.add(conn);
        	}
        }
        if (possibleConns.size()!=0) 
        	return possibleConns.get(rand.nextInt(possibleConns.size()));
        else 
        	return game.getConnection(rand.nextInt(game.numberOfConnections()));
    }
    
    protected Player choosePlayer(ArrayList<Player> players) {
    	System.out.println("Grandma is thinking to send a player back to its starting position...");
    	Player minPlayer = players.get(0);
    	int min = 999;
    	for (Player p: players) {
    		int pathSize = p.getCurrentTile().getShortestPathToWin().size();
    		if (pathSize != 0 && pathSize<min) {
    			min = pathSize;
    			minPlayer = p;
    		}
    	}
    	return minPlayer;
	}
}
