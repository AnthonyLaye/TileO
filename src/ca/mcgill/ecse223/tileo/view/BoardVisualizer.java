package ca.mcgill.ecse223.tileo.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Connection;

class BoardVisualizer extends JPanel {
	private static final long serialVersionUID = 5603047513304210547L;
	
    
    // ui
    private List<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
    private List<Rectangle2D> bgRectangles = new ArrayList<Rectangle2D>();
    private HashMap<Player.Color, Color> pieceColors;
    
    private static final int LINEX = 30;
    private static final int LINEY = 30;
    private static final int TILEW = 40;
    private static final int TILEH = 40;
    private static final int SPACING = 20;
    private static final int CONNW = 16; // WTF
    private static final int CONNH = 5;



    // data elements
    private Game game;
    private HashMap<Rectangle2D, Tile> tiles;
    private Tile selectedTile;
    private boolean waitForTile = false;
    private boolean waitForConn = false;
    private boolean waitForCoord = false;
    private int size = 5;
    private Tile tileForConn1 = null;
    private Tile tileForConn2 = null;
    private ArrayList<Tile> possibleTiles;

    public BoardVisualizer() {
        super();
        init();
    }

    public void init() {
        game = null;;
        tiles = new HashMap<Rectangle2D, Tile>();
        selectedTile = null;

        setBackground(Color.pink);

        pieceColors = new HashMap<Player.Color, Color>();
        pieceColors.put(Player.Color.BLUE, Color.BLUE);
        pieceColors.put(Player.Color.GREEN, Color.GREEN);
        pieceColors.put(Player.Color.RED, Color.RED);
        pieceColors.put(Player.Color.YELLOW, Color.YELLOW);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                for (Rectangle2D rect: rectangles) {
                    if (rect.contains(x,y)) {
                        selectedTile = tiles.get(rect);
                        break;
                    }
                }
                int tX = -1;
                int tY = -1;
                for (Rectangle2D rect: bgRectangles) {
                    if (rect.contains(x,y)) {
                        tX = (x-LINEX)/(TILEW+SPACING);
                        tY = (y-LINEY)/(TILEH+SPACING);
                        break;
                    }
                }
                
                if (waitForCoord) {
                	if (selectedTile!=null){
                		tX=-1;
                		tY=-1;
                	}
                    TileOApplication.getTileOPage().coordSignal(tX, tY);
                }
                
                if (waitForConn) {
                	if (tileForConn1==null)
                		tileForConn1 = selectedTile;
                	else {
                		tileForConn2 = selectedTile;
                		TileOApplication.getTileOPage().connSignal(tileForConn1, tileForConn2);
                		tileForConn1 = null;
                		tileForConn2 = null;
                	}
                }
                
                if (waitForTile) {
                	TileOApplication.getTileOPage().tileSignal(selectedTile);
                }
            }
        });
    }

    public void setBoardSize(int s) {
        size = s;
        TileOController tileOController = new TileOController();

        for(Tile tile: tiles.values() ){

            if(tile.getX() >= s || tile.getY() >= s)
                tileOController.removeTile(tile, game);
        }
        repaint();
    }
    public int getBoardSize() {
        return size;
    }
    public void setWaitForTile(boolean b) {
        if (b) {
        	waitForConn = false;
        	waitForCoord = false;
        }
        waitForTile = b;
    }
    public void setWaitForConn(boolean b) {
    	if (b) {
    		waitForTile = false;
    		waitForCoord = false;
    	}
    	tileForConn1 = null;
    	tileForConn2 = null;
    	waitForConn = b;
    }
    public void setWaitForCoord(boolean b) {
    	if (b) {
    		waitForTile = false;
    		waitForConn = false;
    	}
    	waitForCoord = b;
    }
    
    public void setPossibleTiles(ArrayList<Tile> possibleTiles) {
    	this.possibleTiles = possibleTiles;
    	repaint();
    }
    
    public void setGame(Game game) {
        this.game = game;
        tiles = new HashMap<Rectangle2D, Tile>();
        selectedTile = null;
        repaint();
    }

    private void doDrawing(Graphics g) {
        if (game == null) return;

        Graphics2D g2d = (Graphics2D) g.create();
        BasicStroke thickStroke = new BasicStroke(4);
        g2d.setStroke(thickStroke);

        rectangles.clear();
        tiles.clear();
        bgRectangles.clear();
        
        // background rects
        if (game.getMode() == Game.Mode.DESIGN) {
            for (int x=0; x<size; ++x){
                for (int y=0; y<size; ++y) {
                    Rectangle2D rect = new Rectangle2D.Float(
                        LINEX+x*(TILEW+SPACING),
                        LINEY+y*(TILEH+SPACING),
                        TILEW,TILEH
                    );    
                    bgRectangles.add(rect);
                    g2d.setColor(Color.WHITE);
                    g2d.fill(rect);
                    g2d.draw(rect);
                }         
            }
        }


        // tiles
        for (Tile tile: game.getTiles()) {
            int x = tile.getX();
            int y = tile.getY();

            Rectangle2D rect = new Rectangle2D.Float(
                LINEX+x*(TILEW+SPACING),
                LINEY+y*(TILEH+SPACING),
                TILEW,
                TILEH
            );
            rectangles.add(rect);
            tiles.put(rect, tile);
            
            // Choose color
            g2d.setColor(Color.WHITE);
            if (tile instanceof WinTile){
            	if (game.getMode() == Game.Mode.DESIGN || game.getMode()==Game.Mode.GAME_WON)
            		g2d.setColor(Color.YELLOW);
            }
            if (tile instanceof ActionTile){
            	if (game.getMode() == Game.Mode.DESIGN)
            		g2d.setColor(Color.RED);
            }
            if (tile.getHasBeenVisited())
            	g2d.setColor(Color.LIGHT_GRAY);
            
            if (possibleTiles!=null && possibleTiles.contains(tile))
            	g2d.setColor(Color.ORANGE);
            
            
            g2d.fill(rect);
            g2d.setColor(Color.BLACK);
            g2d.draw(rect);
            
            // Add inactivityPeriod for actionTile in designMode
            if (tile instanceof ActionTile){
            	if (game.getMode() == Game.Mode.DESIGN) {
            		g2d.setColor(Color.WHITE);
            		g2d.drawString(
            			new Integer(((ActionTile) tile).getInactivityPeriod()).toString(),
            			LINEX+x*(TILEW+SPACING)+TILEW/2,
            			LINEY+y*(TILEH+SPACING)+TILEH/2
            		);
            	}
            }
            
            // Connections
            for (Connection conn: tile.getConnections()) {
            	
            	// drawing connections twice but im fucking tired so...
            	
            	Tile other = tile == conn.getTile(0) ? conn.getTile(1):conn.getTile(0);
            	
            	boolean linex = y == other.getY();
            	boolean liney = x == other.getX();
            	boolean right = (x-other.getX()) < 0;
            	boolean down = (y-other.getY()) < 0;
            	
            	if (linex&&!right || liney&&!down) // fixed it you lazy piece of shit
            		continue;
            	
            	int offsetRight, offsetDown, w, h;
            	if (linex){
            		offsetRight = right ? TILEW:0;
            		offsetDown = TILEH/2;
            		w = CONNW;
            		h = CONNH;
            	}
            	else { // liney
            		offsetDown = down ? TILEH:0;
            		offsetRight = TILEW/2;
            		w = CONNH;
            		h = CONNW;
            	}
            	
            	Rectangle2D connPiece = new Rectangle2D.Float(
            		LINEX+x*(TILEW+SPACING)+offsetRight,
            		LINEY+y*(TILEH+SPACING)+offsetDown,
            		w,h
            	);
            	g2d.setColor(Color.DARK_GRAY);
            	g2d.fill(connPiece);
            	g2d.draw(connPiece);  
            }
        }  
        
        //players
        for (Player player: game.getPlayers()){
        	Tile t;
        	if (game.getMode() == Game.Mode.DESIGN)
        		t = player.getStartingTile();
        	else
        		t = player.getCurrentTile();
        	
        	if (t!=null){
        		Ellipse2D piece = new Ellipse2D.Float(
        			LINEX+t.getX()*(TILEW+SPACING),
        			LINEY+t.getY()*(TILEH+SPACING),
        			TILEW, TILEH
        		);
        		g2d.setColor(pieceColors.get(player.getColor()));
        		g2d.fill(piece);
        		g2d.draw(piece);
        	}
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
