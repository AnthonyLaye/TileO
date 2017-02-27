package ca.mcgill.ecse223.tileo.view;

import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import ca.mcgill.ecse223.tileo.exception.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.TileOController;


public class TileOPage extends JFrame{
    
	private static final long serialVersionUID = -6882114745313007613L;
    private static final int NumberOfCards = Game.NumberOfActionCards;
	
    // welcome
    private JButton welNewGameButton;
    private JButton welLoadDesignButton;
    private JButton welLoadGameButton;
    private JButton welExitButton;
    private JSpinner numberOfPlayerSpinner;
    private JLabel numberOfPlayerLabel;
    private JSpinner welBoardSizeSpinner;
    private JLabel welBoardSizeLabel;
    private JLabel tileOLabel;
    
    // mode
    private JLabel modeLabel;

    // action
    private JLabel actionLabel;
    private JLabel actionStatusLabel;
    private JLabel actionTipLabel;
    private JLabel actionError;
        // game
    	private JPanel gameButtonsPanel;
        private JButton rollDieButton;
            // card
            private JButton rollDieCardButton;
        // design
        private JTabbedPane designTabbedPane;
            // tile
            private JButton addRegularTileButton;
            private JButton addActionTileButton;
            private JButton addHiddenTileButton;
            private JButton addConnectionButton;
            private JButton removeTileButton;
            private JButton removeConnectionButton;
            private JSpinner inactivitySpinner;
            // player
            private JButton setStartingTile1Button;
            private JButton setStartingTile2Button;
            private JButton setStartingTile3Button;
            private JButton setStartingTile4Button;
            private JButton[] setStartingTileButtons;
            // deck
            private JLabel extraTurnCardLabel;
            private JSpinner extraTurnCardSpinner;
            private JLabel newConnectionCardLabel;
            private JSpinner newConnectionCardSpinner;
            private JLabel removeConnectionCardLabel;
            private JSpinner removeConnectionCardSpinner;
            private JLabel teleportCardLabel;
            private JSpinner teleportCardSpinner;
            private JLabel totalCardLabel;
            private JLabel numberOfCardsLabel;
            // board
            private JLabel boardSizeLabel;
            private JSpinner boardSizeSpinner;


    // control
    private JButton saveButton;
    private JButton menuButton;
        // design
        private JButton startGameButton;
        private JButton clearDesignButton;
        // game
        private JButton newGameButton;
        private JButton restartButton;
    
    // board
    private JLabel currentPlayerLabel;
    private JLabel currentPlayerNameLabel;
    private BoardVisualizer board;
    


    // data elements
    private String waitingFor = "";
    private ArrayList<Tile> possibleTiles = null;


    public TileOPage() {
        init();
        welcome();
    }


    private void init() {

    	// welcome

        getContentPane().setBackground(Color.CYAN);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Makes game  fullscreen
        this.setUndecorated(true); // This would remove the option to minimize, exit.. etc

    	welNewGameButton = new JButton("New game");
        welNewGameButton.setBorder((new LineBorder(Color.BLACK)));

        welLoadDesignButton = new JButton("Load a design");
        welLoadDesignButton.setBorder((new LineBorder(Color.BLACK)));

        welLoadGameButton = new JButton("Load a game");
        welLoadGameButton.setBorder((new LineBorder(Color.BLACK)));

        welExitButton = new JButton("Exit");
        welExitButton.setBorder((new LineBorder(Color.BLACK)));

        numberOfPlayerSpinner = new JSpinner(new SpinnerNumberModel(2,2,4,1));
        ((JSpinner.DefaultEditor) numberOfPlayerSpinner.getEditor()).getTextField().setEditable(false);
        numberOfPlayerLabel = new JLabel("Number of players");

        welBoardSizeSpinner = new JSpinner(new SpinnerNumberModel(10,5,20,1));
        ((JSpinner.DefaultEditor) welBoardSizeSpinner.getEditor()).getTextField().setEditable(false);
        welBoardSizeLabel = new JLabel("Board size");

        tileOLabel = new JLabel("  Tile-O");
        welNewGameButton.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		newGameActionPerformed(e);
        	}
        });
        welLoadDesignButton.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		loadDesignActionPerformed(e);
        	}
        });
        welLoadGameButton.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		loadGameActionPerformed(e);
        	}
        });
        welExitButton.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent e){
        		System.exit(0);
        	}
        });
        
        // mode
        modeLabel = new JLabel();

        // action
        actionLabel = new JLabel();
        actionStatusLabel = new JLabel();
        actionTipLabel = new JLabel();
        actionError = new JLabel();
            // game
        	gameButtonsPanel = new JPanel();
            rollDieButton = new JButton();
                // card
                rollDieCardButton = new JButton();
            // design
                // tile
                addRegularTileButton = new JButton();
                addActionTileButton = new JButton();
                addHiddenTileButton = new JButton();
                addConnectionButton = new JButton();
                removeTileButton = new JButton();
                removeConnectionButton = new JButton();
                inactivitySpinner = new JSpinner(new SpinnerNumberModel(5,1,10,1));
                // player
                setStartingTile1Button = new JButton();
                setStartingTile2Button = new JButton();
                setStartingTile3Button = new JButton();
                setStartingTile4Button = new JButton();
                setStartingTileButtons = new JButton[4];
                setStartingTileButtons[0] = setStartingTile1Button; setStartingTileButtons[1] = setStartingTile2Button; setStartingTileButtons[2] = setStartingTile3Button; setStartingTileButtons[3] = setStartingTile4Button; 
                // deck
                extraTurnCardLabel = new JLabel();
                newConnectionCardLabel = new JLabel();
                removeConnectionCardLabel = new JLabel();
                teleportCardLabel = new JLabel();
                totalCardLabel = new JLabel();
                numberOfCardsLabel = new JLabel();
                extraTurnCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                newConnectionCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                removeConnectionCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                teleportCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                JSpinner[] spinners = {extraTurnCardSpinner, newConnectionCardSpinner, removeConnectionCardSpinner, teleportCardSpinner};
                for (int i=0; i<4; ++i){
                	spinners[i].addChangeListener(new javax.swing.event.ChangeListener(){
                		public void stateChanged(javax.swing.event.ChangeEvent e){
                			int n = updateNumberOfCards();
                			if (n > NumberOfCards) {
                				JSpinner sp = (JSpinner)e.getSource();
                				sp.setValue((int)sp.getValue()-n+NumberOfCards);
                			}
                			updateNumberOfCards();
                		}
                	});
                }
                // board
                boardSizeLabel = new JLabel();
                boardSizeSpinner = new JSpinner();
                
        // control
        saveButton = new JButton();
        menuButton = new JButton();
            // design
            startGameButton = new JButton();
            clearDesignButton = new JButton();
            // game
            newGameButton = new JButton();
            restartButton = new JButton();
    
        // board
        currentPlayerLabel = new JLabel();
        currentPlayerNameLabel = new JLabel();
        board = new BoardVisualizer();
        
        
        

        // global settings and listeners
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("TileO");


        // basic game actions
        actionLabel.setText("Actions");
        actionError.setForeground(Color.RED);
        rollDieButton.setText("Roll die");
        rollDieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                rollDieActionPerformed(e);
            }
        });
        
        // action cards
        rollDieCardButton.setText("Roll die");
        rollDieCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                rollDieCardActionPerformed(e);
            }
        });

        // design-tile
        addRegularTileButton.setText("Add tile");
        addActionTileButton.setText("Add action tile");
        addHiddenTileButton.setText("Add hidden tile");
        addConnectionButton.setText("Add connection");
        removeTileButton.setText("Remove tile");
        removeConnectionButton.setText("Remove connection");
        addRegularTileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addRegularTileActionPerformed(e, false, -1, -1);
            }
        });
        addActionTileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addActionTileActionPerformed(e, false, -1, -1);
            }
        });
        addHiddenTileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addHiddenTileActionPerformed(e, false, -1, -1);
            }
        });
        addConnectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addConnectionActionPerformed(e, false, null, null);
            }
        });
        removeConnectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                removeConnectionActionPerformed(e, false, null, null);
            }
        });
        removeTileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                removeTileActionPerformed(e, false, null);
            }
        });

        // design-player
        setStartingTile1Button.setText("Player 1");
        setStartingTile2Button.setText("Player 2");
        setStartingTile3Button.setText("Player 3");
        setStartingTile4Button.setText("Player 4");
        setStartingTile1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setStartingTileActionPerformed(e, false, 1, null);
            }
        });
        setStartingTile2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setStartingTileActionPerformed(e, false, 2, null);
            }
        });
        setStartingTile3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setStartingTileActionPerformed(e, false, 3, null);
            }
        });
        setStartingTile4Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setStartingTileActionPerformed(e, false, 4, null);
            }
        });

        // design-deck
        extraTurnCardLabel.setText("Extra turn");
        newConnectionCardLabel.setText("New connection");
        removeConnectionCardLabel.setText("Remove connection");
        teleportCardLabel.setText("Teleport");
        totalCardLabel.setText("Total");
        numberOfCardsLabel.setText("0/"+NumberOfCards);

        // design-board
        boardSizeLabel.setText("Board size");
        boardSizeSpinner.setModel(new SpinnerNumberModel(board.getBoardSize(), 5, 20, 1));
        boardSizeSpinner.addChangeListener(new javax.swing.event.ChangeListener(){
            public void stateChanged(javax.swing.event.ChangeEvent e){
                board.setBoardSize((int)boardSizeSpinner.getValue());
            }
        });
        
        // control
        saveButton.setText("Save");
        menuButton.setText("Menu");
        newGameButton.setText("New game");
        restartButton.setText("Restart game");
        startGameButton.setText("Start game");
        clearDesignButton.setText("Clear");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                saveActionPerformed(e);
            }
        });
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                welcome();
            }
        });
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                newGameActionPerformed(e);
            }
        });
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                restartActionPerformed(e);
            }
        });
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                startGameActionPerformed(e);
            }
        });
        clearDesignButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                clearDesignActionPerformed(e);
            }
        });

        // board
        currentPlayerLabel.setText("Current player: ");     
    }
    
    
    // Welcome page rendering
    private void welcome() {    
    	getContentPane().removeAll();

    	JPanel center = new JPanel();

    	getContentPane().setLayout(new GridBagLayout());

        add(center);
        center.add(tileOLabel);
        center.add(numberOfPlayerLabel);
        center.add(welBoardSizeLabel);
        center.add(welLoadDesignButton);
        center.add(welLoadGameButton);
        center.add(welExitButton);
        center.add(welNewGameButton);
        center.add(numberOfPlayerSpinner);
        center.add(welBoardSizeSpinner);

        center.setBackground(Color.pink);

        GroupLayout layout = new GroupLayout(center);
        center.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        tileOLabel.setFont(new Font("Serif", Font.PLAIN, 150));
        numberOfPlayerLabel.setFont(new Font("Serif", Font.PLAIN, 45));
        welBoardSizeLabel.setFont(new Font("Serif", Font.PLAIN, 45));
        welLoadDesignButton.setFont(new Font("Serif", Font.PLAIN, 45));
        welLoadGameButton.setFont(new Font("Serif", Font.PLAIN, 45));
        welExitButton.setFont(new Font("Serif", Font.PLAIN, 45));
        welNewGameButton.setFont(new Font("Serif", Font.PLAIN, 45));

        numberOfPlayerSpinner.setFont(new Font("Serif", Font.PLAIN, 80));
        welBoardSizeSpinner.setFont(new Font("Serif", Font.PLAIN, 80));


        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(tileOLabel, 400, 500, 600)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(numberOfPlayerLabel, 400, 500, 600)
                    .addComponent(welBoardSizeLabel,  400, 500, 600)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(numberOfPlayerSpinner, 100, 200, 300)
                    .addComponent(welBoardSizeSpinner, 100, 200, 300)
                )
            	.addComponent(welNewGameButton, 400, 500, 600)
                .addComponent(welLoadDesignButton, 400, 500, 600)
                .addComponent(welLoadGameButton,  400, 500, 600)
                .addComponent(welExitButton,  400, 500, 600)
            )
        );
        
        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
                {numberOfPlayerLabel, numberOfPlayerSpinner, welNewGameButton,
                welLoadDesignButton, welLoadGameButton, welExitButton, welBoardSizeLabel,
                welBoardSizeSpinner});

        layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[]
                {numberOfPlayerLabel, numberOfPlayerSpinner, welNewGameButton,
                welLoadDesignButton, welLoadGameButton, welExitButton,
                welBoardSizeSpinner, welBoardSizeLabel});

        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(tileOLabel, 250, 300, 350)
            .addComponent(welNewGameButton, 150, 200 , 250)
            .addGroup(layout.createParallelGroup()
                .addComponent(numberOfPlayerLabel, 150, 200 , 250)
                .addComponent(welBoardSizeLabel, 150, 200 , 250)
            )
            .addGroup(layout.createParallelGroup()
                .addComponent(numberOfPlayerSpinner, 100, 200, 300)
                .addComponent(welBoardSizeSpinner, 100, 200, 300)
            )
           	.addComponent(welLoadDesignButton, 150, 200 , 250)
            .addComponent(welLoadGameButton, 150, 200 , 250)
            .addComponent(welExitButton, 150, 200 , 250)
        );

        pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    // Create basic game layout
    private void initGameLayout(){

    	getContentPane().removeAll();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(currentPlayerLabel)
                    .addComponent(currentPlayerNameLabel)
                )
                .addComponent(board)
            )
            .addComponent(modeLabel)
            .addGroup(layout.createParallelGroup()
            	.addComponent(actionLabel)
                .addComponent(gameButtonsPanel)
                .addComponent(actionStatusLabel)
                .addComponent(actionError)
                .addComponent(actionTipLabel)
                .addComponent(newGameButton)
                .addComponent(restartButton)
                .addComponent(saveButton)
                .addComponent(menuButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
        {newGameButton, saveButton, restartButton, menuButton, gameButtonsPanel, actionStatusLabel, actionTipLabel});
        

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(modeLabel)
            .addGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(currentPlayerLabel)
                        .addComponent(currentPlayerNameLabel)
                    )
                    .addComponent(board)
                )
                .addGroup(layout.createSequentialGroup()
                	.addComponent(actionLabel)
                    .addComponent(gameButtonsPanel)
                    .addComponent(actionStatusLabel)
                    .addComponent(actionTipLabel)
                    .addComponent(actionError)
                    .addComponent(newGameButton)
                    .addComponent(restartButton)
                    .addComponent(saveButton)
                    .addComponent(menuButton)
                )
            )
        );
        pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    
    private void renderLayout(Game game) {

        modeLabel.setText(game.getMode().toString());
        currentPlayerNameLabel.setText("Player "+game.indexOfPlayer(game.getCurrentPlayer()));
        gameButtonsPanel.removeAll();
        board.setGame(game);

        switch (game.getMode()) {      
            case GAME:
            	actionTipLabel.setText("Roll the die !");
            	actionStatusLabel.setText("");
            	gameButtonsPanel.add(rollDieButton);
                break;
            case GAME_WON:
            	modeLabel.setText("Game won !");
            	currentPlayerNameLabel.setText("None");
            	actionStatusLabel.setText("Player "+game.indexOfPlayer(game.getCurrentPlayer())+" won !");
                actionTipLabel.setText("");
                break;
            case GAME_ROLLDIEACTIONCARD:
            	actionTipLabel.setText("Roll the die");
            	actionStatusLabel.setText("Roll die action card");
            	gameButtonsPanel.add(rollDieCardButton);
                break;
            case GAME_CONNECTTILESACTIONCARD:
            	actionTipLabel.setText("Select two tiles you want to connect");
            	actionStatusLabel.setText("Add a connection action card");
            	board.setWaitForConn(true);
            	setWaitingFor("newconncard");
            	break;
            case GAME_REMOVECONNECTIONACTIONCARD:
            	actionTipLabel.setText("Select two tiles you want to disconnect");
            	actionStatusLabel.setText("Remove a connection action card");
            	board.setWaitForConn(true);
            	setWaitingFor("rmconncard");
                break;
            case GAME_TELEPORTACTIONCARD:
            	actionTipLabel.setText("Select a new tile");
            	actionStatusLabel.setText("Teleport action card");
            	board.setWaitForTile(true);
            	setWaitingFor("newtilecard");
            	break;
            case DESIGN:
            	renderDesign(game);
            	break;
        }
    }
    
    // Design rendering
    public void renderDesign(Game game) {

    	getContentPane().removeAll();

        modeLabel.setText("Designer mode");
        currentPlayerNameLabel.setText("Designer");
        
        designTabbedPane = makeDesignPane(game.numberOfPlayers());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(currentPlayerLabel)
                    .addComponent(currentPlayerNameLabel)
                )
                .addComponent(board)
            )
            .addComponent(modeLabel)
            .addGroup(layout.createParallelGroup()
                .addComponent(actionLabel)
                .addComponent(designTabbedPane)              
                .addComponent(actionStatusLabel)
                .addComponent(actionError)
                .addComponent(actionTipLabel)
                .addComponent(startGameButton)
                .addComponent(clearDesignButton)
                .addComponent(saveButton)
                .addComponent(menuButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
        {startGameButton, saveButton, clearDesignButton, menuButton, designTabbedPane});

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(modeLabel)
            .addGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(currentPlayerLabel)
                        .addComponent(currentPlayerNameLabel)
                    )
                    .addComponent(board)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(actionLabel)
                    .addComponent(designTabbedPane)                   
                    .addComponent(actionStatusLabel)
                    .addComponent(actionError)
                    .addComponent(actionTipLabel)
                    .addComponent(startGameButton)
                    .addComponent(clearDesignButton)
                    .addComponent(saveButton)
                    .addComponent(menuButton)
                )
            )
        );

        pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private JTabbedPane makeDesignPane(int nPlayers) {
    	JTabbedPane tabbed = new JTabbedPane();
    	JPanel tilePanel = new JPanel();
    	JPanel playerPanel = new JPanel();
    	JPanel deckPanel = new JPanel();
    	JPanel boardPanel = new JPanel();    	
        
        // TILES
    	GroupLayout tileLayout = new GroupLayout(tilePanel);
    	tilePanel.setLayout(tileLayout);
    	tileLayout.setAutoCreateGaps(true);
        tileLayout.setAutoCreateContainerGaps(true);
    	tileLayout.setHorizontalGroup(tileLayout.createSequentialGroup()
    		.addGroup(tileLayout.createParallelGroup()
    			.addComponent(addRegularTileButton)
    			.addComponent(addActionTileButton)
    			.addComponent(addHiddenTileButton)
    			.addComponent(addConnectionButton)
    		)
    		.addGroup(tileLayout.createParallelGroup()
    			.addComponent(removeTileButton)
    			.addComponent(removeConnectionButton)
    			.addComponent(inactivitySpinner)
    		)    		
    	);
    	tileLayout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
    	        {addRegularTileButton, addActionTileButton, addHiddenTileButton, addConnectionButton, removeTileButton, removeConnectionButton, inactivitySpinner});
    	tileLayout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[]
    	        {addRegularTileButton, addActionTileButton, addHiddenTileButton, addConnectionButton, removeTileButton, removeConnectionButton, inactivitySpinner});
    	tileLayout.setVerticalGroup(tileLayout.createSequentialGroup()
    		.addGroup(tileLayout.createParallelGroup()
    			.addComponent(addRegularTileButton)
    			.addComponent(removeTileButton)
    		)
    		.addGroup(tileLayout.createParallelGroup()
        			.addComponent(addHiddenTileButton)
        			.addComponent(removeConnectionButton)
        	)
    		.addGroup(tileLayout.createParallelGroup()
    			.addComponent(addActionTileButton)
    	    	.addComponent(inactivitySpinner)	
    		)
    		.addComponent(addConnectionButton)
    	);
    	inactivitySpinner.setVisible(false);
    	
    	
    	// PLAYER
    	JPanel startingTilePanel = new JPanel();
    	startingTilePanel.setLayout(new BoxLayout(startingTilePanel, BoxLayout.PAGE_AXIS));
    	for (int i=0; i<nPlayers; ++i)
    		startingTilePanel.add(setStartingTileButtons[i]);   	
    	GroupLayout playerLayout = new GroupLayout(playerPanel);
    	playerPanel.setLayout(playerLayout);
    	playerLayout.setAutoCreateGaps(true);
    	playerLayout.setAutoCreateContainerGaps(true);
    	playerLayout.setHorizontalGroup(playerLayout.createSequentialGroup()
    	    .addComponent(startingTilePanel)
    	);
    	playerLayout.setVerticalGroup(playerLayout.createSequentialGroup()
    		.addComponent(startingTilePanel)
    	);
    	
    	// DECK
    	GroupLayout deckLayout = new GroupLayout(deckPanel);
    	deckPanel.setLayout(deckLayout);
    	deckLayout.setAutoCreateGaps(true);
    	deckLayout.setAutoCreateContainerGaps(true);
    	deckLayout.setHorizontalGroup(deckLayout.createSequentialGroup()
    		.addGroup(deckLayout.createParallelGroup()
    			.addComponent(extraTurnCardLabel)
    			.addComponent(newConnectionCardLabel)
    			.addComponent(removeConnectionCardLabel)
    			.addComponent(teleportCardLabel)
    			.addComponent(totalCardLabel)
    		)
    		.addGroup(deckLayout.createParallelGroup()
    			.addComponent(extraTurnCardSpinner)
    			.addComponent(newConnectionCardSpinner)
    			.addComponent(removeConnectionCardSpinner)
    			.addComponent(teleportCardSpinner)
    			.addComponent(numberOfCardsLabel)
    		)
    	);
    	deckLayout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[]
    	        {extraTurnCardLabel, newConnectionCardLabel, removeConnectionCardLabel, teleportCardLabel, totalCardLabel, extraTurnCardSpinner, newConnectionCardSpinner, removeConnectionCardSpinner, teleportCardSpinner, numberOfCardsLabel});
        deckLayout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
    	        {extraTurnCardLabel, newConnectionCardLabel, removeConnectionCardLabel, teleportCardLabel, totalCardLabel, extraTurnCardSpinner, newConnectionCardSpinner, removeConnectionCardSpinner, teleportCardSpinner, numberOfCardsLabel});
    	deckLayout.setVerticalGroup(deckLayout.createSequentialGroup()
    		.addGroup(deckLayout.createParallelGroup()
    			.addComponent(extraTurnCardLabel)
    			.addComponent(extraTurnCardSpinner)
    		)
    		.addGroup(deckLayout.createParallelGroup()
        			.addComponent(newConnectionCardLabel)
        			.addComponent(newConnectionCardSpinner)
        	)
    		.addGroup(deckLayout.createParallelGroup()
        			.addComponent(removeConnectionCardLabel)
        			.addComponent(removeConnectionCardSpinner)
        	)
    		.addGroup(deckLayout.createParallelGroup()
        			.addComponent(teleportCardLabel)
        			.addComponent(teleportCardSpinner)
        	)
    		.addGroup(deckLayout.createParallelGroup()
        			.addComponent(totalCardLabel)
        			.addComponent(numberOfCardsLabel)
       		)
    	);

        // BOARD
        GroupLayout boardLayout = new GroupLayout(boardPanel);
        boardPanel.setLayout(boardLayout);
        boardLayout.setAutoCreateGaps(true);
        boardLayout.setAutoCreateContainerGaps(true);
        
        boardLayout.setHorizontalGroup(boardLayout.createSequentialGroup()
            .addComponent(boardSizeLabel)
            .addComponent(boardSizeSpinner)
        );
        boardLayout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[]
    	        {boardSizeLabel, boardSizeSpinner});
        boardLayout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
    	        {boardSizeLabel, boardSizeSpinner});
        boardLayout.setVerticalGroup(boardLayout.createSequentialGroup()
            .addGroup(boardLayout.createParallelGroup()
                .addComponent(boardSizeLabel)
                .addComponent(boardSizeSpinner)
            )
        );

    	tabbed.addTab("Tile", tilePanel);
    	tabbed.addTab("Player", playerPanel);
    	tabbed.addTab("Deck", deckPanel);
    	tabbed.addTab("Board", boardPanel);
    	 	
    	return tabbed;
    }
    
    private int updateNumberOfCards() {
    	int n = (int)extraTurnCardSpinner.getValue() + (int)newConnectionCardSpinner.getValue() + (int)removeConnectionCardSpinner.getValue() + (int)teleportCardSpinner.getValue();
    	numberOfCardsLabel.setText(n+"/"+NumberOfCards);
    	return n;
    }
    

    // ~~~~~~~~~~~~~~~ LISTENERS ~~~~~~~~~~~~~~~~~~~~~~
    
    // design
    private void addRegularTileActionPerformed(java.awt.event.ActionEvent e, boolean
    selected, int x, int y) {
        actionError.setText("");
    	if (!selected) { 
            actionTipLabel.setText("Select an empty square to add a tile");
            board.setWaitForCoord(true);
            setWaitingFor("regular");
        }
        else {
        	Game game = TileOApplication.getTileO().getCurrentGame();
            TileOController tileOController = new TileOController();            
            tileOController.addRegularTile(x, y, game);
            renderLayout(game);
        }
    }
    
    private void addActionTileActionPerformed(java.awt.event.ActionEvent e, boolean selected, int x, int y) {
        actionError.setText("");
    	if (!selected){
    		actionTipLabel.setText("Add an action tile, you can change the inactivity period");
    		board.setWaitForCoord(true);
    		setWaitingFor("action");
    		inactivitySpinner.setVisible(true);
    		inactivitySpinner.setValue(5);
    	}
    	else {
    		Game game = TileOApplication.getTileO().getCurrentGame();
    		TileOController toc = new TileOController();
    		toc.addActionTile(x, y, game, (int)inactivitySpinner.getValue());
    		renderLayout(game);
    	}
        
    }
    
    private void addHiddenTileActionPerformed(java.awt.event.ActionEvent e, boolean selected, int x, int y) {
        actionError.setText("");
    	if (TileOApplication.getTileO().getCurrentGame().getWinTile()!=null) {
    		actionError.setText("The win tile is already selected, delete it first");
    		return;
    	}
    	
    	else if (!selected){
    		actionTipLabel.setText("Select an empty square to add the hidden tile");
    		board.setWaitForCoord(true);
    		setWaitingFor("hidden");
    	}
    	else {
    		Game game = TileOApplication.getTileO().getCurrentGame();
    		TileOController tileOController = new TileOController();
    		tileOController.addHiddenTile(x, y, game);
    		renderLayout(game);
    	}
    }
    
    private void addConnectionActionPerformed(java.awt.event.ActionEvent e, boolean selected, Tile t1, Tile t2) {
        actionError.setText("");
    	if (!selected) {
    		actionTipLabel.setText("Select two adjacent tiles to connect them");
    		board.setWaitForConn(true);
    		setWaitingFor("connection");
    	}
    	else {
    		try{
    			Game game = TileOApplication.getTileO().getCurrentGame();
    			TileOController toc = new TileOController();
    			toc.addConnection(t1, t2, game);
    			renderLayout(game);
    		}
    		catch (InvalidInputException err) {
    			actionError.setText(err.getMessage());
    		}
    	}
    }
    
    private void removeConnectionActionPerformed(java.awt.event.ActionEvent e, boolean selected, Tile t1, Tile t2) {
        actionError.setText("");
    	if (!selected) {
    		actionTipLabel.setText("Select two connected tiles to remove a connection");
    		board.setWaitForConn(true);
    		setWaitingFor("rmconn");
    	}
    	else {
    		try {
    			Game game = TileOApplication.getTileO().getCurrentGame();
    			TileOController toc = new TileOController();
    			toc.removeConnection(t1, t2, game);
    			renderLayout(game);
    		}
    		catch (InvalidInputException err) {
    			actionError.setText(err.getMessage());
    		}
    	}
    }
    
    private void removeTileActionPerformed(java.awt.event.ActionEvent e, boolean selected, Tile t) {
        actionError.setText("");
    	if (!selected) {
    		actionTipLabel.setText("Select a tile to delete");
    		board.setWaitForTile(true);
    		setWaitingFor("rmtile");
    	}
    	else {
    		Game game = TileOApplication.getTileO().getCurrentGame();
    		TileOController toc = new TileOController();
    		toc.removeTile(t, game);
			renderLayout(game);
    		
    	}
    }
    
    private void setStartingTileActionPerformed(java.awt.event.ActionEvent e, boolean selected, int n, Tile t) {
        actionError.setText("");
    	if (!selected) {
    		actionTipLabel.setText("Select a starting tile for player "+n);
    		board.setWaitForTile(true);
    		setWaitingFor("player"+n);
    	}
    	else {
    		try {
    			Game game = TileOApplication.getTileO().getCurrentGame();
    			TileOController toc = new TileOController();
    			toc.setStartingTile(n-1, t, game);
    			renderLayout(game);
    		}
    		catch (InvalidInputException err) {
    			actionError.setText(err.getMessage());
    		}
    	}
    }
    
   
    // game
    private void startGameActionPerformed(java.awt.event.ActionEvent e) {
        try {
        	TileOController toc = new TileOController();
        	Game game = TileOApplication.getTileO().getCurrentGame();
        	
        	toc.createDeck((int)extraTurnCardSpinner.getValue(), (int)newConnectionCardSpinner.getValue(), (int)removeConnectionCardSpinner.getValue(), (int)teleportCardSpinner.getValue(), game);
        	
            toc.startGame(game);
            board.setGame(game);
            actionError.setText("");
            initGameLayout();
            renderLayout(game);
        } catch (InvalidInputException err) {
        	actionError.setText(err.getMessage());
        }
    }
    
    private void rollDieActionPerformed(java.awt.event.ActionEvent e) {
        TileOController tileOController = new TileOController();
        possibleTiles = tileOController.rollDie();
        actionStatusLabel.setText(""); // die number ?
        actionTipLabel.setText("Select a new tile");
        board.setPossibleTiles(possibleTiles);
        board.setWaitForTile(true);
        setWaitingFor("move");
    }
    
    private void landActionPerformed(java.awt.event.ActionEvent e, Tile t) {
    	if (possibleTiles.contains(t)){
    		possibleTiles = null;
    		board.setPossibleTiles(null);
    		setWaitingFor("");
    		
    		try {
    			TileOController toc = new TileOController();
        		toc.land(t);
        		renderLayout(t.getGame());
    		}
    		catch (InvalidInputException err) {
    			System.out.println(err.getMessage());
    		}
    	}
    	else {
    		actionError.setText("Select a legal tile");
    	}
    }
    
    private void teleportCardActionPerformed(java.awt.event.ActionEvent e, Tile t) {
    	try {
    		TileOController toc = new TileOController();
    		toc.playTeleportActionCard(t);
    		setWaitingFor("");
    		renderLayout(TileOApplication.getTileO().getCurrentGame());
    	}
    	catch (InvalidInputException err) {
    		actionError.setText(err.getMessage());
    	}
    }
    private void removeConnectionCardActionPerformed(java.awt.event.ActionEvent e, Tile t1, Tile t2) {
    	try {
    		TileOController toc = new TileOController();
    		toc.playRemoveConnectionActionCard(t1, t2);
    		setWaitingFor("");
    		renderLayout(TileOApplication.getTileO().getCurrentGame());
    	}
    	catch (InvalidInputException err) {
    		actionError.setText(err.getMessage());
    	}
    }
    private void addConnectionCardActionPerformed(java.awt.event.ActionEvent e, Tile t1, Tile t2) {
    	try {
    		TileOController toc = new TileOController();
    		toc.playConnectTilesActionCard(t1, t2);
    		setWaitingFor("");
    		renderLayout(TileOApplication.getTileO().getCurrentGame());
    	}
    	catch (InvalidInputException err) {
    		actionError.setText(err.getMessage());
    	}
    }
    private void rollDieCardActionPerformed(java.awt.event.ActionEvent e) {
    	try {
    		TileOController toc = new TileOController();
    		possibleTiles = toc.playRollDieActionCard();
    	    actionStatusLabel.setText(""); // die number ?
    	    actionTipLabel.setText("Select a new tile");
    	    board.setPossibleTiles(possibleTiles);
    	    board.setWaitForTile(true);
    	    setWaitingFor("move");
    	}
    	catch (InvalidInputException err) {
    		actionError.setText(err.getMessage());
    	}
    }
    
    
    //controls 
    private void newGameActionPerformed(java.awt.event.ActionEvent e) {
        try{
        	TileOController toc = new TileOController();
        	int nPlayers = (int) numberOfPlayerSpinner.getValue();
            Game game = toc.newGame(nPlayers);
            actionStatusLabel.setText("");
            board.setBoardSize((int)welBoardSizeSpinner.getValue());
            boardSizeSpinner.setValue((int)welBoardSizeSpinner.getValue());
            extraTurnCardSpinner.setValue(0);
            newConnectionCardSpinner.setValue(0);
            removeConnectionCardSpinner.setValue(0);
            teleportCardSpinner.setValue(0);
            numberOfCardsLabel.setText("0/"+NumberOfCards);
            renderLayout(game);
        }
        catch(InvalidInputException err){
            System.out.print(err.getMessage());
        }
    }
    private void saveActionPerformed(java.awt.event.ActionEvent e) {
    	try {
    		TileOController toc = new TileOController();
    		toc.saveGame("");
    	}
    	catch (Exception err) {
    		actionError.setText(err.getMessage());
    	}
    }
    private void loadGameActionPerformed(java.awt.event.ActionEvent e){
    }
    private void loadDesignActionPerformed(java.awt.event.ActionEvent e){
    }
    private void restartActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void clearDesignActionPerformed(java.awt.event.ActionEvent e) {
    }



    // signals
    // hackish but it works, kinda
    public void coordSignal(int x, int y) {
    	if (x!=-1){
            if (waitingFor.equals("regular"))
            	addRegularTileActionPerformed(null, true, x, y); 
            else if (waitingFor.equals("hidden"))
            	addHiddenTileActionPerformed(null, true, x, y);
            else if (waitingFor.equals("action"))
            	addActionTileActionPerformed(null, true, x, y);
        
        }
    }
    
    public void tileSignal(Tile t) {
    	if (t!=null) {
    		if (waitingFor.equals("rmtile"))
    			removeTileActionPerformed(null, true, t);
    		if (waitingFor.equals("player1"))
    			setStartingTileActionPerformed(null, true, 1, t);
    		if (waitingFor.equals("player2"))
    			setStartingTileActionPerformed(null, true, 2, t);
    		if (waitingFor.equals("player3"))
    			setStartingTileActionPerformed(null, true, 3, t);
    		if (waitingFor.equals("player4"))
    			setStartingTileActionPerformed(null, true, 4, t);
    		if (waitingFor.equals("move"))
    			landActionPerformed(null, t);
    		if (waitingFor.equals("newtilecard"))
    			teleportCardActionPerformed(null, t);
    	}
    }
    
    public void connSignal(Tile t1, Tile t2) {
    	if (t1!=null&&t2!=null){
    		if (waitingFor.equals("connection"))
    			addConnectionActionPerformed(null, true, t1, t2);
    		else if(waitingFor.equals("rmconn"))
    			removeConnectionActionPerformed(null, true, t1, t2);
    		else if(waitingFor.equals("newconncard"))
				addConnectionCardActionPerformed(null, t1, t2);
    		else if(waitingFor.equals("rmconncard"))
    			removeConnectionCardActionPerformed(null, t1, t2);
    		
    	}
    }
    
    private void setWaitingFor(String s) {
    	if (waitingFor.equals("action") && !s.equals("action")){
    		inactivitySpinner.setVisible(false);
    	}
    	waitingFor = s;
    }

}
