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
import java.util.Random;
import java.util.List;

public abstract class ComputerPlayer extends Player
{
	public ComputerPlayer(int n, Game game) {
		super(n, game);
	}
	
    public void takeTurn() {
        System.out.println("Computer starts turn at tile " + getCurrentTile().getX()+"-"+getCurrentTile().getY());
        move(false);
        System.out.println("Computer landed on tile "+getCurrentTile().getX()+"-"+getCurrentTile().getY()+"\n");
    }

    private void move(boolean allTiles) {
        Tile newTile; 
        List<Tile> possibleTiles;
        
        if (allTiles)
            possibleTiles = getGame().getTiles();
        else
            possibleTiles = getGame().rollDie();
        if (possibleTiles.size()==0)
            newTile = getCurrentTile();
        else
            newTile = this.chooseTile(possibleTiles);

        newTile.land();
    }

    public void playCard() {
        switch (getGame().getMode()) {
            case GAME_ROLLDIEACTIONCARD:
                rollDieCard();
                break;
            case GAME_CONNECTTILESACTIONCARD:
                connectTilesCard();
                break;
            case GAME_REMOVECONNECTIONACTIONCARD:
                rmConnectionCard();
                break;
            case GAME_LOSETURNACTIONCARD:
                loseTurnCard();
                break;
            case GAME_TELEPORTACTIONCARD:
                teleportCard();
                break;
            default:
                throw new RuntimeException("ERROR "+getGame().getMode()+": card not implemented for ai");
        }    
    }

    private void rollDieCard() {
        System.out.println("Computer plays RollDieActionCard");
        move(false);
        System.out.println("Computer landed on tile "+getCurrentTile().getX()+"-"+getCurrentTile().getY()+"\n");
        getGame().setNextCard();
    }
    private void connectTilesCard() {
        System.out.println("Computer plays ConnectTilesActionCard");
        if (getGame().getCurrentConnectionPieces() == 0) {
            System.out.println("No more connection pieces to add\n");
            return;
        }
        ArrayList<Tile> tiles = this.newConnection();
        getGame().connectTiles(tiles.get(0), tiles.get(1));
        getGame().setNextPlayer();
        getGame().setNextCard();
        getGame().setMode(Game.Mode.GAME);
        getGame().setCurrentConnectionPieces(getGame().getCurrentConnectionPieces()-1);
        System.out.println("Computer connected "+tiles.get(0).getX()+"-"+tiles.get(0).getY()+" and "+tiles.get(1).getX()+"-"+tiles.get(1).getY()+"\n");
    }
    private void rmConnectionCard() {
        System.out.println("Computer plays RemoveConnectionCard");
        Connection conn = this.deleteConnection();
        Tile t1 = conn.getTile(0);
        Tile t2 = conn.getTile(1);
        getGame().disconnectTiles(t1, t2);
        getGame().setNextPlayer();
        getGame().setNextCard();
        getGame().setMode(Game.Mode.GAME);
        if (getGame().getCurrentConnectionPieces()<32)
            getGame().setCurrentConnectionPieces(getGame().getCurrentConnectionPieces()+1);
        System.out.println("Computer disconnected "+t1.getX()+"-"+t1.getY()+" and "+t2.getX()+"-"+t2.getY()+"\n");
    
    }
    private void loseTurnCard() {
        System.out.println("Computer plays LoseTurnActionCard");
        loseTurn();
        getGame().setNextCard();
        getGame().setNextPlayer();
        getGame().setMode(Game.Mode.GAME);
        System.out.println("Computer loses next turn\n");
    }
    private void teleportCard() {
        System.out.println("Computer plays TeleportActionCard");
        move(true);
        System.out.println("Computer landed on tile "+getCurrentTile().getX()+"-"+getCurrentTile().getY()+"\n");
        getGame().setNextCard();
    }

    
    // Methods to overwrite to get specific behaviour
    
    protected abstract Tile chooseTile(List<Tile> possibleTiles);
    protected abstract ArrayList<Tile> newConnection();
    protected abstract Connection deleteConnection();
    
}
