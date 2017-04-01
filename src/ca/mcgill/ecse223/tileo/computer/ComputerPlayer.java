package ca.mcgill.ecse223.tileo.computer;

import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.Connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public abstract class ComputerPlayer extends Player
{
	public ComputerPlayer(int n, Game game) {
		super(n, game);
	}
	
    public void takeTurn() {
        System.out.println("Computer starts turn at tile " + getCurrentTile().getX()+"-"+getCurrentTile().getY());
        move(0);
        System.out.println("Computer landed on tile "+getCurrentTile().getX()+"-"+getCurrentTile().getY()+"\n");
    }

    public void playCard() {

	    if(this instanceof HackerPlayer)
	        getGame().setMode(Game.Mode.GAME_TELEPORTACTIONCARD);

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
            case GAME_REMOVERANDOMTILEACTIONCARD:
            	removeRandomTileCard();
            	break;
            case GAME_TURNINACTIVEACTIONCARD:
            	turnInactiveCard();
            	break;
            case GAME_CHOOSEADDITIONALMOVEACTIONCARD:
            	chooseAdditionalMove();
            	break;
            case GAME_REVEALTILEACTIONCARD:
            	reveal();
            	break;
            case GAME_SENDBACKTOSTARTACTIONCARD:
            	sendBackToStart();
            	break;
            //case GAME_WINTILEHINTACTIONCARD:
            //	winTileHint();
            //	break;
            case GAME_SWAPPOSITIONACTIONCARD:
            	swapPosition();
            	break;
            default:
                throw new RuntimeException("ERROR "+getGame().getMode()+": card not implemented for ai");
        }    
    }
    
    
    
    private void move(int type) {
        Tile newTile; 
        ArrayList<Tile> possibleTiles;
        
        if (type==0){ // roll die
            possibleTiles = getGame().rollDie();
            System.out.println("Computer rolled die and got "+getGame().dieNumber);
        }
        else if (type==1){ // teleport
            System.out.println("You're dead, maybe");
            possibleTiles = new ArrayList<Tile>(getGame().getTiles());
        }
        else if (type == 2) { // additional move
        	System.out.println("You're potentially fucked");
        	Set<Tile> set = new HashSet<Tile>();
        	for (int i=1; i<=6; ++i)
        		set.addAll(getPossibleMoves(i));
        	possibleTiles = new ArrayList<Tile>(set);
        	Collections.shuffle(possibleTiles);
        }
        
        else {
        	throw new RuntimeException("Invalid type "+type+" for computer player move");
        }
        
        if (possibleTiles.size()==0){
            System.out.println("Computer has no option");
            newTile = getCurrentTile();
        }
        else
            newTile = this.chooseTile(possibleTiles);

        newTile.land();
    }

    private void rollDieCard() {
        System.out.println("Computer plays RollDieActionCard");
        move(0);
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
        move(1);
        System.out.println("Computer landed on tile "+getCurrentTile().getX()+"-"+getCurrentTile().getY()+"\n");
        getGame().setNextCard();
    }
    private void removeRandomTileCard() {
    	System.out.println("Computer plays RemoveRandomTileActionCard\n");
    	getGame().removeRandomTile();
    	getGame().setNextCard();
    	getGame().setNextPlayer();
    	getGame().setMode(Game.Mode.GAME);
    }
    private void turnInactiveCard() {
    	System.out.println("Computer plays TurnInactiveActionCard\n");
    	for (Tile t: getGame().getTiles()) {
    		if (t instanceof ActionTile)
    			((ActionTile)t).deactivate();
    	}
    	getGame().setNextCard();
    	getGame().setNextPlayer();
    	getGame().setMode(Game.Mode.GAME);
    	
    }
    
    private void chooseAdditionalMove() {
    	System.out.println("Computer plays ChooseAdditionalMoveActionCard, you're potentially fucked");
    	move(2);
    	System.out.println("Computer landed on tile "+getCurrentTile().getX()+"-"+getCurrentTile().getY()+"\n");
    	getGame().setNextCard();
    }
    
    private void reveal() {
    	System.out.println("Computer plays RevealTileActionCard, nothing to do...\n");
    	getGame().setNextCard();
    	getGame().setNextPlayer();
    	getGame().setMode(Game.Mode.GAME);
    }
    
    private void sendBackToStart() {
    //will work on ways of improving functionality later
    	System.out.println("Computer is thinking of a player to send back...");
	Random rand = new Random();
	Player randomPlayer = getGame().getPlayers().get(rand.nextInt(getGame().numberOfPlayers()-1));
	while(randomPlayer==this){
		randomPlayer=getGame().getPlayers().get(rand.nextInt(getGame().numberOfPlayers()-1));
	}
	randomPlayer.setCurrentTile(randomPlayer.getStartingTile());
	getGame().setNextCard();
	getGame().setNextPlayer();
	getGame().setMode(Game.Mode.GAME);
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
    
    private void swapPosition() {
    	System.out.println("Computer plays SwapPositionActionCard");
    	HashMap<Tile, Player> map = new HashMap<Tile, Player>();
    	for (Player p: getGame().getPlayers()) {
    		if (this != p)
    			map.put(p.getCurrentTile(), p);
    	}
    	Tile t = chooseTile(new ArrayList<Tile>(map.keySet()));
    	Player p = map.get(t);
    	System.out.println("Computer swapped position with player "+p.getNumber()+"\n");
    	
    	p.setCurrentTile(getCurrentTile());
    	setCurrentTile(t);
    	getGame().setNextCard();
    	getGame().setNextPlayer();
    	getGame().setMode(Game.Mode.GAME);
    	
    }
    
    private void winTileHint() {
    	System.out.println("Computer plays WinTileHintActionCard, nothing to do because he knows it\n");
    	getGame().setNextCard();
    	getGame().setNextPlayer();
    	getGame().setMode(Game.Mode.GAME);
    }

    
    // Methods to overwrite to get specific behaviour
    
    protected abstract Tile chooseTile(List<Tile> possibleTiles);
    protected abstract ArrayList<Tile> newConnection();
    protected abstract Connection deleteConnection();
    
}
