package ca.mcgill.ecse223.tileo.view;

import java.awt.*;
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
    
    TileOController toc;
    
	
    // welcome
    private JButton welNewGameButton;
    private JButton welLoadDesignButton;
    private JButton welLoadGameButton;
    private JButton welExitButton;
    private JLabel welError;
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
        //private JLabel playerColour;
        private JPanel playerColour;
            // card
        	private JButton loseTurnCardButton;
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
            private JLabel inactivityLabel;
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
            private JLabel loseTurnCardLabel;
            private JSpinner loseTurnCardSpinner;
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
    	
    	toc = new TileOController();

    	// welcome
        getContentPane().setBackground(Color.CYAN);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Makes game  fullscreen
        

    	welNewGameButton = new JButton("New game");
        welNewGameButton.setBorder((new LineBorder(Color.BLACK)));

        welLoadDesignButton = new JButton("Load a design");
        welLoadDesignButton.setBorder((new LineBorder(Color.BLACK)));

        welLoadGameButton = new JButton("Load a game");
        welLoadGameButton.setBorder((new LineBorder(Color.BLACK)));

        welExitButton = new JButton("Exit");
        welExitButton.setBorder((new LineBorder(Color.BLACK)));
        
        welError = new JLabel();
        welError.setText("");
        welError.setForeground(Color.RED);

        numberOfPlayerSpinner = new JSpinner(new SpinnerNumberModel(2,2,4,1));
        ((JSpinner.DefaultEditor) numberOfPlayerSpinner.getEditor()).getTextField().setEditable(false);
        numberOfPlayerLabel = new JLabel("Number of players");

        welBoardSizeSpinner = new JSpinner(new SpinnerNumberModel(10,5,20,1));
        ((JSpinner.DefaultEditor) welBoardSizeSpinner.getEditor()).getTextField().setEditable(false);
        welBoardSizeLabel = new JLabel("Board size");

        tileOLabel = new JLabel("Tile-O");

        playerColour = new JPanel();
        playerColour.setOpaque(true);

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
            	loseTurnCardButton = new JButton();
                rollDieCardButton = new JButton();
            // design
                // tile
                addRegularTileButton = new JButton();
                addActionTileButton = new JButton();
                addHiddenTileButton = new JButton();
                addConnectionButton = new JButton();
                removeTileButton = new JButton();
                removeConnectionButton = new JButton();
                inactivityLabel = new JLabel();
                inactivitySpinner = new JSpinner(new SpinnerNumberModel(5,1,10,1));
                ((JSpinner.DefaultEditor) inactivitySpinner.getEditor()).getTextField().setEditable(false);
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
                loseTurnCardLabel = new JLabel();
                totalCardLabel = new JLabel();
                numberOfCardsLabel = new JLabel();
                extraTurnCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                newConnectionCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                removeConnectionCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                teleportCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                loseTurnCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                // dont change this ordering
                JSpinner[] spinners = {extraTurnCardSpinner, newConnectionCardSpinner, removeConnectionCardSpinner, teleportCardSpinner, loseTurnCardSpinner};
                for (int i=0; i<5; ++i){
                	int cardType = i;
                    spinners[i].addChangeListener(new javax.swing.event.ChangeListener(){
                		public void stateChanged(javax.swing.event.ChangeEvent e){
                			JSpinner sp = (JSpinner)e.getSource();
                            int n = updateNumberOfCards();
                			if (n > NumberOfCards) {
                				sp.setValue((int)sp.getValue()-n+NumberOfCards);
                			}
                		    updateCards((int)sp.getValue(), cardType);
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
        actionLabel.setFont(new Font("Serif", Font.PLAIN, 60));
        actionError.setForeground(Color.RED);

        playerColour.setPreferredSize(new Dimension(20, 20));
        
        rollDieButton.setText("Roll die");
        rollDieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                rollDieActionPerformed(e);
            }
        });
        
        // action cards
        loseTurnCardButton.setText("Lose turn");
        loseTurnCardButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		loseTurnCardActionPerformed(e);
        	}
        });
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
        loseTurnCardLabel.setText("Lose turn");
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
                .addComponent(tileOLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(numberOfPlayerLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(welBoardSizeLabel,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(numberOfPlayerSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(welBoardSizeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
            	.addComponent(welNewGameButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(welLoadDesignButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(welLoadGameButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(welExitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(welError, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );
        
        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
                {numberOfPlayerLabel, numberOfPlayerSpinner, welNewGameButton,
                welLoadDesignButton, welLoadGameButton, welExitButton, welBoardSizeLabel,
                welBoardSizeSpinner, welError});

        layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[]
                {numberOfPlayerLabel, numberOfPlayerSpinner, welNewGameButton,
                welLoadDesignButton, welLoadGameButton, welExitButton,
                welBoardSizeSpinner, welBoardSizeLabel, welError});

        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(tileOLabel,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(welNewGameButton,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup()
                .addComponent(numberOfPlayerLabel,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(welBoardSizeLabel,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(layout.createParallelGroup()
                .addComponent(numberOfPlayerSpinner,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(welBoardSizeSpinner,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
           	.addComponent(welLoadDesignButton,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(welLoadGameButton,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(welExitButton,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(welError, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        actionStatusLabel.setText("");
        actionTipLabel.setText("");
        actionError.setText("");
        setWaitingFor("");
    }

    // Create basic game layout
    private void initGameLayout(){

    	getContentPane().removeAll();

        currentPlayerLabel.setFont(new Font("Serif", Font.PLAIN, 65));
        currentPlayerNameLabel.setFont(new Font("Serif", Font.PLAIN, 65));

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
                    .addComponent(playerColour, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addComponent(board)
                .addComponent(modeLabel)

            )
            .addGroup(layout.createParallelGroup()
            	.addComponent(actionLabel)
                .addComponent(rollDieButton)
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
                        .addComponent(playerColour, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
                    .addComponent(board)
                )
                .addGroup(layout.createSequentialGroup()
                	.addComponent(actionLabel)
                    .addComponent(rollDieButton)
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
        currentPlayerNameLabel.setText("Player "+ (game.indexOfPlayer(game.getCurrentPlayer())+ 1));
        gameButtonsPanel.removeAll();
        board.setGame(game);

        if(game.hasCurrentPlayer()){
            String colour = String.valueOf(game.getCurrentPlayer().getColor());
            if(colour.equals("RED"))
                playerColour.setBackground(Color.RED);
            if(colour.equals("BLUE"))
                playerColour.setBackground(Color.BLUE);
            if(colour.equals("GREEN"))
                playerColour.setBackground(Color.GREEN);
            if(colour.equals("YELLOW"))
                playerColour.setBackground(Color.YELLOW);
        }
        actionError.setText("");

        switch (game.getMode()) {      
            case GAME:
            	actionTipLabel.setText("Roll the die !");
            	actionStatusLabel.setText("");
                gameButtonsPanel.setVisible(false);
            	rollDieButton.setVisible(true);
                break;
            case GAME_WON:
            	modeLabel.setText("Game won !");
            	currentPlayerNameLabel.setText("None");
            	actionStatusLabel.setText("Player "+(game.indexOfPlayer(game.getCurrentPlayer())+ 1) +" won !");
                actionTipLabel.setText("");
                break;
            case GAME_ROLLDIEACTIONCARD:
            	actionTipLabel.setText("Roll the die");
            	actionStatusLabel.setText("Roll die action card");
            	gameButtonsPanel.add(rollDieCardButton);
            	gameButtonsPanel.setVisible(true);
            	rollDieCardButton.setVisible(true);
            	loseTurnCardButton.setVisible(false);
            	rollDieButton.setVisible(false);
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
            	setWaitingFor("teleportcard");
            	break;
            case GAME_LOSETURNACTIONCARD:
            	actionTipLabel.setText("You lose your next turn");
            	actionStatusLabel.setText("Lose turn action card");
            	gameButtonsPanel.add(loseTurnCardButton);
            	gameButtonsPanel.setVisible(true);
            	rollDieCardButton.setVisible(false);
            	loseTurnCardButton.setVisible(true);
            	break;
            case DESIGN:
                gameButtonsPanel.setVisible(true);
            	renderDesign(game);
            	break;
        }
    }
    
    // Design rendering
    public void renderDesign(Game game) {

    	getContentPane().removeAll();

        modeLabel.setText("Designer mode");
        currentPlayerNameLabel.setText("Designer");
        playerColour.setBackground(Color.pink);

        inactivityLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        inactivityLabel.setText("Inactivity Period: ");

        modeLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        currentPlayerNameLabel.setFont(new Font("Serif", Font.PLAIN, 65));
        currentPlayerLabel.setFont(new Font("Serif", Font.PLAIN, 65));
        
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
                    .addComponent(playerColour , GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addComponent(board)
                .addComponent(modeLabel)
            )
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
                        .addComponent(playerColour, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
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
                .addComponent(inactivityLabel)
    		)
    		.addGroup(tileLayout.createParallelGroup()
    			.addComponent(removeTileButton)
                .addComponent(addConnectionButton)
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
                    .addComponent(addConnectionButton)

        	)
    		.addGroup(tileLayout.createParallelGroup()
    			.addComponent(addActionTileButton)
    	    	.addComponent(removeConnectionButton)

    		)
            .addGroup(tileLayout.createParallelGroup()
                .addComponent(inactivitySpinner)
                .addComponent(inactivityLabel)

            )

    	);
    	
    	
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
    			.addComponent(loseTurnCardLabel)
    			.addComponent(totalCardLabel)
    		)
    		.addGroup(deckLayout.createParallelGroup()
    			.addComponent(extraTurnCardSpinner)
    			.addComponent(newConnectionCardSpinner)
    			.addComponent(removeConnectionCardSpinner)
    			.addComponent(teleportCardSpinner)
    			.addComponent(loseTurnCardSpinner)
    			.addComponent(numberOfCardsLabel)
    		)
    	);
    	deckLayout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[]
    	        {extraTurnCardLabel, newConnectionCardLabel, removeConnectionCardLabel, teleportCardLabel, totalCardLabel, extraTurnCardSpinner, newConnectionCardSpinner, removeConnectionCardSpinner, teleportCardSpinner, numberOfCardsLabel, loseTurnCardLabel, loseTurnCardSpinner});
        deckLayout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
    	        {extraTurnCardLabel, newConnectionCardLabel, removeConnectionCardLabel, teleportCardLabel, totalCardLabel, extraTurnCardSpinner, newConnectionCardSpinner, removeConnectionCardSpinner, teleportCardSpinner, numberOfCardsLabel, loseTurnCardLabel, loseTurnCardSpinner});
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
        			.addComponent(loseTurnCardLabel)
        			.addComponent(loseTurnCardSpinner)
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
    	int n = (int)extraTurnCardSpinner.getValue() + (int)newConnectionCardSpinner.getValue() + (int)removeConnectionCardSpinner.getValue() + (int)teleportCardSpinner.getValue()+(int)loseTurnCardSpinner.getValue();
    	numberOfCardsLabel.setText(n+"/"+NumberOfCards);
    	return n;
    }
    

    // ~~~~~~~~~~~~~~~ LISTENERS ~~~~~~~~~~~~~~~~~~~~~~
    
    // design
    private void addRegularTileActionPerformed(java.awt.event.ActionEvent e, boolean
    selected, int x, int y) {
        actionError.setText("");
        actionTipLabel.setText("");
        actionStatusLabel.setText("");
    	if (!selected) { 
            actionTipLabel.setText("Select an empty square to add a tile");
            board.setWaitForCoord(true);
            setWaitingFor("regular");
        }
        else {
        	Game game = TileOApplication.getTileO().getCurrentGame();           
            toc.addRegularTile(x, y, game);
            renderLayout(game);
        }
    }
    
    private void addActionTileActionPerformed(java.awt.event.ActionEvent e, boolean selected, int x, int y) {
        actionError.setText("");
        actionTipLabel.setText("");
        actionStatusLabel.setText("");
    	if (!selected){
    		actionTipLabel.setText("Add an action tile, you can change the inactivity period");
    		board.setWaitForCoord(true);
    		setWaitingFor("action");
    		inactivitySpinner.setValue(5);
    	}
    	else {
    		Game game = TileOApplication.getTileO().getCurrentGame();
    		toc.addActionTile(x, y, game, (int)inactivitySpinner.getValue());
    		renderLayout(game);
    	}
        
    }
    
    private void addHiddenTileActionPerformed(java.awt.event.ActionEvent e, boolean selected, int x, int y) {
        actionError.setText("");
        actionTipLabel.setText("");
        actionStatusLabel.setText("");
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
    		toc.addHiddenTile(x, y, game);
    		renderLayout(game);
    	}
    }
    
    private void addConnectionActionPerformed(java.awt.event.ActionEvent e, boolean selected, Tile t1, Tile t2) {
        actionError.setText("");
        actionTipLabel.setText("");
        actionStatusLabel.setText("");
    	if (!selected) {
    		actionTipLabel.setText("Select two adjacent tiles to connect them");
    		board.setWaitForConn(true);
    		setWaitingFor("connection");
    	}
    	else {
    		try{
    			Game game = TileOApplication.getTileO().getCurrentGame();
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
        actionTipLabel.setText("");
        actionStatusLabel.setText("");
    	if (!selected) {
    		actionTipLabel.setText("Select two connected tiles to remove a connection");
    		board.setWaitForConn(true);
    		setWaitingFor("rmconn");
    	}
    	else {
    		try {
    			Game game = TileOApplication.getTileO().getCurrentGame();
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
        actionTipLabel.setText("");
        actionStatusLabel.setText("");
    	if (!selected) {
    		actionTipLabel.setText("Select a tile to delete");
    		board.setWaitForTile(true);
    		setWaitingFor("rmtile");
    	}
    	else {
    		Game game = TileOApplication.getTileO().getCurrentGame();
    		toc.removeTile(t, game);
			renderLayout(game);
    		
    	}
    }
    
    private void setStartingTileActionPerformed(java.awt.event.ActionEvent e, boolean selected, int n, Tile t) {
        actionError.setText("");
        actionTipLabel.setText("");
        actionStatusLabel.setText("");
    	if (!selected) {
    		actionTipLabel.setText("Select a starting tile for player "+n);
    		board.setWaitForTile(true);
    		setWaitingFor("player"+n);
    	}
    	else {
    		try {
    			Game game = TileOApplication.getTileO().getCurrentGame();
    			toc.setStartingTile(n-1, t, game);
    			renderLayout(game);
    		}
    		catch (InvalidInputException err) {
    			actionError.setText(err.getMessage());
    		}
    	}
    }

    private void updateCards(int nCards, int cardType) {
        actionError.setText("");
        actionTipLabel.setText("");
        actionStatusLabel.setText("");
        try {
            toc.updateCards(nCards, cardType);
            TileOApplication.getTileO().getCurrentGame().getDeck().print();
        }
        catch (InvalidInputException err){
            actionError.setText(err.getMessage());
        }
    }
    
   
    // game
    private void startGameActionPerformed(java.awt.event.ActionEvent e) {
        try {
        	Game game = TileOApplication.getTileO().getCurrentGame();
        	
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
        toc.rollDie();
        possibleTiles = toc.getPossibleTiles();
        if (possibleTiles.size()==0) {
        	actionStatusLabel.setText("No possible moves, skip your turn");
        	landActionPerformed(null, TileOApplication.getTileO().getCurrentGame().getCurrentPlayer().getCurrentTile());
        }
        else {
            Game game = TileOApplication.getTileO().getCurrentGame();
            String dieNumber = game.dieNumber;
            actionStatusLabel.setText("Roll: " + dieNumber); // die number
        	actionTipLabel.setText("Select a new tile");
        	actionError.setText("");
        	board.setPossibleTiles(possibleTiles);
        	board.setWaitForTile(true);
        	setWaitingFor("move");
        	rollDieButton.setVisible(false);
        }
    }
    
    private void landActionPerformed(java.awt.event.ActionEvent e, Tile t) {
    	if (possibleTiles.contains(t) || possibleTiles.size()==0){
    		try {
    			toc.selectNewTile(t);
        		toc.land();
        		possibleTiles = null;
        		board.setPossibleTiles(null);
        		setWaitingFor("");
        		actionError.setText("");
        		renderLayout(t.getGame());
    		}
    		catch (InvalidInputException err) {
    			actionError.setText(err.getMessage());
    		}
    	}
    	else {
    		actionError.setText("Select a legal tile");
    	}
    }
    
    private void teleportCardActionPerformed(java.awt.event.ActionEvent e, Tile t) {
    	try {
    		toc.playTeleportActionCard(t);
    		toc.land();
    		setWaitingFor("");
    		actionError.setText("");
    		renderLayout(TileOApplication.getTileO().getCurrentGame());
    	}
    	catch (InvalidInputException err) {
    		actionError.setText(err.getMessage());
    	}
    }
    private void removeConnectionCardActionPerformed(java.awt.event.ActionEvent e, Tile t1, Tile t2) {
    	try {
    		toc.playRemoveConnectionActionCard(t1, t2);
    		setWaitingFor("");
    		actionError.setText("");
    		renderLayout(TileOApplication.getTileO().getCurrentGame());
    	}
    	catch (InvalidInputException err) {
    		actionError.setText(err.getMessage());
    	}
    }
    private void addConnectionCardActionPerformed(java.awt.event.ActionEvent e, Tile t1, Tile t2) {
    	try {
    		toc.playConnectTilesActionCard(t1, t2);
    		setWaitingFor("");
    		actionError.setText("");
    		renderLayout(TileOApplication.getTileO().getCurrentGame());
    	}
    	catch (InvalidInputException err) {
    		actionError.setText(err.getMessage());
    	}
    }
    private void rollDieCardActionPerformed(java.awt.event.ActionEvent e) {
    	try {
    		toc.playRollDieActionCard();
    		possibleTiles = toc.getPossibleTiles();
    		if (possibleTiles.size()==0) {
            	actionStatusLabel.setText("No possible moves, skip your turn");
            	landActionPerformed(null, TileOApplication.getTileO().getCurrentGame().getCurrentPlayer().getCurrentTile());
            }
    		else {
    			Game game = TileOApplication.getTileO().getCurrentGame();
                String dieNumber = game.dieNumber;
                actionStatusLabel.setText("Roll: " + dieNumber); // die number
    	    	actionTipLabel.setText("Select a new tile");
    	    	board.setPossibleTiles(possibleTiles);
    	    	board.setWaitForTile(true);
    	    	setWaitingFor("move");
    	    	rollDieCardButton.setVisible(false);
    		}
    	}
    	catch (InvalidInputException err) {
    		actionError.setText(err.getMessage());
    	}
    }
    
    private void loseTurnCardActionPerformed(java.awt.event.ActionEvent e) {
    	try {
    		toc.playLoseTurnActionCard();
    		actionError.setText("");
    		renderLayout(TileOApplication.getTileO().getCurrentGame());
    	} 
    	catch (InvalidInputException err) {
    		actionError.setText(err.getMessage());
    	}
    }
    
    
    //controls 
    private void newGameActionPerformed(java.awt.event.ActionEvent e) {
        try{
        	int nPlayers = (int) numberOfPlayerSpinner.getValue();
            Game game = toc.newGame(nPlayers);
            actionStatusLabel.setText("");
            board.setBoardSize((int)welBoardSizeSpinner.getValue());
            boardSizeSpinner.setValue((int)welBoardSizeSpinner.getValue());
            extraTurnCardSpinner.setValue(0);
            newConnectionCardSpinner.setValue(0);
            removeConnectionCardSpinner.setValue(0);
            teleportCardSpinner.setValue(0);
            loseTurnCardSpinner.setValue(0);
            numberOfCardsLabel.setText("0/"+NumberOfCards);
            renderLayout(game);
        }
        catch(InvalidInputException err){
            System.out.print(err.getMessage());
        }
    }
    private void saveActionPerformed(java.awt.event.ActionEvent e) {
    	try {
    		String f = toc.saveGame("");
    		actionStatusLabel.setText("Game saved to "+f);
    	}
    	catch (Exception err) {
    		actionError.setText(err.getMessage());
    	}
    }
    private void loadGameActionPerformed(java.awt.event.ActionEvent e){
    	TileOApplication.initDir();
    	FileDialog fd = new FileDialog(this, "Choose a game", FileDialog.LOAD);
    	String savedFolder = TileOApplication.SavedFolder;
    	fd.setDirectory(savedFolder);
    	fd.setFile("*.game");
    	fd.setVisible(true);
    	String fname = fd.getFile();
    	if (fname!=null){
    		try {
    			Game game = toc.loadGame(savedFolder+fname);
    			initGameLayout();
    			renderLayout(game);
    		}
    		catch (InvalidInputException err) {
    			welError.setText(err.getMessage());
    		}
    	}
    }
    private void loadDesignActionPerformed(java.awt.event.ActionEvent e){
    	TileOApplication.initDir();
    	FileDialog fd = new FileDialog(this, "Choose a design", FileDialog.LOAD);
    	String savedFolder = TileOApplication.SavedFolder;
    	fd.setDirectory(savedFolder);
    	fd.setFile("*.design");
    	fd.setVisible(true);
    	String fname = fd.getFile();
    	if (fname!=null){
    		try {
    			Game game = toc.loadGame(savedFolder+fname);
    			
    			int s = game.getMaxSize();
    			if (s<5) s=5;
    			board.setBoardSize(s);
    			boardSizeSpinner.setValue(s);

                // setup card spinners
                Deck d = game.getDeck();
                int n;

                n = d.numberOfCardsForType(0);
                extraTurnCardSpinner.setValue(n);
                n = d.numberOfCardsForType(1);
                newConnectionCardSpinner.setValue(n);
                n = d.numberOfCardsForType(2);
                removeConnectionCardSpinner.setValue(n);
                n = d.numberOfCardsForType(3);
                teleportCardSpinner.setValue(n);
                n = d.numberOfCardsForType(4);
                loseTurnCardSpinner.setValue(n);
    			
    			renderLayout(game);
    		}
    		catch (InvalidInputException err) {
    			welError.setText(err.getMessage());
    		}
    	}
    }
    private void restartActionPerformed(java.awt.event.ActionEvent e) {

        //actionError.setText("not implemented");
    }
    private void clearDesignActionPerformed(java.awt.event.ActionEvent e) {
        /* Sets the board size to 0, which clears everything on board thanks to the check in
           setBoardSize. BoardSize is then quickly reset to what it originally was. */

        int currentSize = board.getBoardSize();
        board.setBoardSize(0);
        board.setBoardSize(currentSize);
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
    		if (waitingFor.equals("teleportcard"))
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
    	waitingFor = s;
    }

}
