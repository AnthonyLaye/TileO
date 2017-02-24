package ca.mcgill.ecse223.tileo.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import javax.swing.GroupLayout;
import javax.swing.WindowConstants;
import javax.swing.SwingConstants;

import ca.mcgill.ecse223.tileo.model.Game;

import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.util.TileOUtil;


public class TileOPage extends JFrame{
    
	private static final long serialVersionUID = -6882114745313007613L;
    private static final int NumberOfCards = Game.NumberOfActionCards;
	
    // welcome
    private JButton welNewGameButton;
    private JButton welLoadDesignButton;
    private JButton welLoadGameButton;
    private JButton welExitButton;
    
    // mode
    private JLabel modeLabel;

    // action
    private JLabel actionLabel;
    private JLabel actionStatusLabel;
    private JLabel actionTipLabel;
        // game
        private JButton rollDieButton;
        private JButton moveButton;
        private JButton drawCardButton;
        private JButton nextTurnButton;
            // card
            private JButton teleportCardButton;
            private JButton removeConnectionCardButton;
            private JButton addConnectionCardButton;
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
            // player
            private JButton setStartingTile1Button;
            private JButton setStartingTile2Button;
            private JButton setStartingTile3Button;
            private JButton setStartingTile4Button;
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
    private JLabel board;
    


    // data elements
    private int selectedTile = -1;

    
    public TileOPage() {
        init();
        welcome();
    }
    
    private void init() {
    	
    	// welcome
    	welNewGameButton = new JButton("New game");
        welLoadDesignButton = new JButton("Load a design");
        welLoadGameButton = new JButton("Load a game");
        welExitButton = new JButton("Exit");
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
            // game
            rollDieButton = new JButton();
            moveButton = new JButton();
            drawCardButton = new JButton();
            nextTurnButton = new JButton();
                // card
                teleportCardButton = new JButton();
                removeConnectionCardButton = new JButton();
                addConnectionCardButton = new JButton();
                rollDieCardButton = new JButton();
            // design
                // tile
                addRegularTileButton = new JButton();
                addActionTileButton = new JButton();
                addHiddenTileButton = new JButton();
                addConnectionButton = new JButton();
                removeTileButton = new JButton();
                removeConnectionButton = new JButton();
                // player
                setStartingTile1Button = new JButton();
                setStartingTile2Button = new JButton();
                setStartingTile3Button = new JButton();
                setStartingTile4Button = new JButton();
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
                JSpinner[] spinners = {extraTurnCardSpinner, newConnectionCardSpinner, removeConnectionCardSpinner, teleportCardSpinner, loseTurnCardSpinner};
                for (int i=0; i<5; ++i){
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
                
                
            designTabbedPane = makeDesignPane();
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
        board = new JLabel();
        
        
        

        // global settings and listeners
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("TileO");


        // basic game actions
        actionLabel.setText("Actions");
        rollDieButton.setText("Roll die");
        moveButton.setText("Move");
        drawCardButton.setText("Draw");
        nextTurnButton.setText("Finish turn");
        rollDieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                rollDieActionPerformed(e);
            }
        });
        moveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                moveActionPerformed(e);
            }
        });
        drawCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                drawCardActionPerformed(e);
            }
        });
        nextTurnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                nextTurnActionPerformed(e);
            }
        });
        
        // action cards
        teleportCardButton.setText("Teleport");
        removeConnectionCardButton.setText("Remove connection");
        addConnectionCardButton.setText("Add connection");
        rollDieCardButton.setText("Roll die");
        teleportCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                teleportCardActionPerformed(e);
            }
        });
        removeConnectionCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                removeConnectionCardActionPerformed(e);
            }
        });
        addConnectionCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addConnectionCardCardActionPerformed(e);
            }
        });
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
                addRegularTileActionPerformed(e);
            }
        });
        addActionTileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addActionTileActionPerformed(e);
            }
        });
        addHiddenTileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addHiddenTileActionPerformed(e);
            }
        });
        addConnectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addConnectionActionPerformed(e);
            }
        });
        removeConnectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                removeConnectionActionPerformed(e);
            }
        });
        removeTileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                removeTileActionPerformed(e);
            }
        });

        // design-player
        setStartingTile1Button.setText("Player 1");
        setStartingTile2Button.setText("Player 2");
        setStartingTile3Button.setText("Player 3");
        setStartingTile4Button.setText("Player 4");
        setStartingTile1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setStartingTileActionPerformed(e, 1);
            }
        });
        setStartingTile2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setStartingTileActionPerformed(e, 2);
            }
        });
        setStartingTile3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setStartingTileActionPerformed(e, 3);
            }
        });
        setStartingTile4Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setStartingTileActionPerformed(e, 4);
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
        board.setText("Imagine a nice board if you can");       
    }
    
    private void welcome() {    
        // LAYOUT
    	getContentPane().removeAll();
    	
        GroupLayout layout = new GroupLayout(getContentPane());  
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(welNewGameButton)
                .addComponent(welLoadDesignButton)
                .addComponent(welLoadGameButton)
                .addComponent(welExitButton)
            )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(welNewGameButton)
            .addComponent(welLoadDesignButton)
            .addComponent(welLoadGameButton)
            .addComponent(welExitButton)
        );

        pack(); 
    }


    private void renderLayout(Game game) {
        modeLabel.setText(game.getMode().toString());
        currentPlayerNameLabel.setText("Player "+game.indexOfPlayer(game.getCurrentPlayer()));
        game.setMode(Game.Mode.DESIGN);
        
        switch (game.getMode()) {      
            case GAME:
                renderGame(game);
                break;
            case GAME_WON:
                renderGameWon(game);
                break;
            case GAME_ROLLDIEACTIONCARD:
                renderRollCard(game);
                break;
            case GAME_CONNECTTILESACTIONCARD:
            	renderConnectCard(game);
                break;
            case GAME_REMOVECONNECTIONACTIONCARD:
            	renderRemoveCard(game);
                break;
            case GAME_TELEPORTACTIONCARD:
            	renderTeleportCard(game);
                break;
            case GAME_LOSETURNACTIONCARD:
                renderLoseTurnCard(game);
            	break;
            case DESIGN:
            	renderDesign(game);
            	break;
        }
    }

    private void renderGame(Game game) {
        getContentPane().removeAll();
        
        currentPlayerNameLabel.setText("Player :"+game.indexOfPlayer(game.getCurrentPlayer()));
        actionTipLabel.setText("Roll the die !");

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
                .addComponent(rollDieButton)
                .addComponent(actionStatusLabel)
                .addComponent(actionTipLabel)
                .addComponent(newGameButton)
                .addComponent(restartButton)
                .addComponent(saveButton)
                .addComponent(menuButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
        {rollDieButton, newGameButton, saveButton, restartButton, menuButton});
        

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
                    .addComponent(rollDieButton)
                    .addComponent(actionStatusLabel)
                    .addComponent(actionTipLabel)
                    .addComponent(newGameButton)
                    .addComponent(restartButton)
                    .addComponent(saveButton)
                    .addComponent(menuButton)
                )
            )
        );
        pack();
    }

    private void renderGameWon(Game game) {
        getContentPane().removeAll();
        
        modeLabel.setText("Game won !");
        currentPlayerNameLabel.setText("Player :None");
        actionStatusLabel.setText("Player "+game.indexOfPlayer(game.getCurrentPlayer())+" won !");
        actionTipLabel.setText("");

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
                .addComponent(actionStatusLabel)
                .addComponent(actionTipLabel)
                .addComponent(newGameButton)
                .addComponent(restartButton)
                .addComponent(menuButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
        {newGameButton, restartButton, menuButton});
        

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
                    .addComponent(actionStatusLabel)
                    .addComponent(actionTipLabel)
                    .addComponent(newGameButton)
                    .addComponent(restartButton)
                    .addComponent(menuButton)
                )
            )
        );
        pack();
        
    }
    
    public void renderRollCard(Game game){
        getContentPane().removeAll();
        
        modeLabel.setText("Game mode");
        currentPlayerNameLabel.setText("Player :"+game.indexOfPlayer(game.getCurrentPlayer()));
        actionStatusLabel.setText("Roll die action card");
        actionTipLabel.setText("Roll the die");
        
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
                .addComponent(rollDieCardButton)
                .addComponent(actionStatusLabel)
                .addComponent(actionTipLabel)
                .addComponent(newGameButton)
                .addComponent(restartButton)
                .addComponent(saveButton)
                .addComponent(menuButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
        {newGameButton, saveButton, restartButton, menuButton, rollDieCardButton});
        

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
                    .addComponent(rollDieCardButton)
                    .addComponent(actionStatusLabel)
                    .addComponent(actionTipLabel)
                    .addComponent(newGameButton)
                    .addComponent(restartButton)
                    .addComponent(saveButton)
                    .addComponent(menuButton)
                )
            )
        );
        
        pack();
    }
    
    private void renderConnectCard(Game game) {
    	getContentPane().removeAll();
        
        modeLabel.setText("Game mode");
        currentPlayerNameLabel.setText("Player :"+game.indexOfPlayer(game.getCurrentPlayer()));
        actionStatusLabel.setText("Connect tiles card");
        actionTipLabel.setText("Select two tiles you want to connect");
    	
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
                .addComponent(addConnectionCardButton)
                .addComponent(actionStatusLabel)
                .addComponent(actionTipLabel)
                .addComponent(newGameButton)
                .addComponent(restartButton)
                .addComponent(saveButton)
                .addComponent(menuButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
        {newGameButton, saveButton, restartButton, menuButton, addConnectionCardButton});
        

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
                    .addComponent(addConnectionCardButton)
                    .addComponent(actionStatusLabel)
                    .addComponent(actionTipLabel)
                    .addComponent(newGameButton)
                    .addComponent(restartButton)
                    .addComponent(saveButton)
                    .addComponent(menuButton)
                )
            )
        );
        
        pack();
    }
    
    public void renderRemoveCard(Game game) {
    	getContentPane().removeAll();
        
        modeLabel.setText("Game mode");
        currentPlayerNameLabel.setText("Player :"+game.indexOfPlayer(game.getCurrentPlayer()));
        actionStatusLabel.setText("Remove a connection card");
        actionTipLabel.setText("Select two tiles you want to disconnect");
    	
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
                .addComponent(removeConnectionCardButton)
                .addComponent(actionStatusLabel)
                .addComponent(actionTipLabel)
                .addComponent(newGameButton)
                .addComponent(restartButton)
                .addComponent(saveButton)
                .addComponent(menuButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
        {newGameButton, saveButton, restartButton, menuButton, removeConnectionCardButton});
        

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
                    .addComponent(removeConnectionCardButton)
                    .addComponent(actionStatusLabel)
                    .addComponent(actionTipLabel)
                    .addComponent(newGameButton)
                    .addComponent(restartButton)
                    .addComponent(saveButton)
                    .addComponent(menuButton)
                )
            )
        );
        
        pack();
    }
    
    public void renderLoseTurnCard(Game game) {
    	getContentPane().removeAll();
        
        modeLabel.setText("Game mode");
        currentPlayerNameLabel.setText("Player :"+game.indexOfPlayer(game.getCurrentPlayer()));
        actionStatusLabel.setText("Lose a turn card");
        actionTipLabel.setText("You lose you next turn...");
    	
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
                .addComponent(nextTurnButton)
                .addComponent(actionStatusLabel)
                .addComponent(actionTipLabel)
                .addComponent(newGameButton)
                .addComponent(restartButton)
                .addComponent(saveButton)
                .addComponent(menuButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
        {newGameButton, saveButton, restartButton, menuButton, nextTurnButton});
        

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
                    .addComponent(nextTurnButton)
                    .addComponent(actionStatusLabel)
                    .addComponent(actionTipLabel)
                    .addComponent(newGameButton)
                    .addComponent(restartButton)
                    .addComponent(saveButton)
                    .addComponent(menuButton)
                )
            )
        );
        
        pack();
    }
    
    public void renderTeleportCard(Game game) {
    	getContentPane().removeAll();
        
        modeLabel.setText("Game mode");
        currentPlayerNameLabel.setText("Player :"+game.indexOfPlayer(game.getCurrentPlayer()));
        actionStatusLabel.setText("Teleport card");
        actionTipLabel.setText("Select a new tile");
    	
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
                .addComponent(teleportCardButton)
                .addComponent(actionStatusLabel)
                .addComponent(actionTipLabel)
                .addComponent(newGameButton)
                .addComponent(restartButton)
                .addComponent(saveButton)
                .addComponent(menuButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
        {newGameButton, saveButton, restartButton, menuButton, teleportCardButton});
        

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
                    .addComponent(teleportCardButton)
                    .addComponent(actionStatusLabel)
                    .addComponent(actionTipLabel)
                    .addComponent(newGameButton)
                    .addComponent(restartButton)
                    .addComponent(saveButton)
                    .addComponent(menuButton)
                )
            )
        );
        
        pack();
    }
    
    public void renderDesign(Game game) {
    	getContentPane().removeAll();
        
        modeLabel.setText("Designer mode");
        currentPlayerNameLabel.setText("Designer");
        actionStatusLabel.setText("");
        actionTipLabel.setText("");
        
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
                
                //block
                
                /*
              
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
                 */
                
                
                .addComponent(actionStatusLabel)
                .addComponent(actionTipLabel)
                .addComponent(startGameButton)
                .addComponent(clearDesignButton)
                .addComponent(saveButton)
                .addComponent(menuButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
        {startGameButton, saveButton, clearDesignButton, menuButton});
        

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
                    /*
                    
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
                     */
                    
                    
                    .addComponent(actionStatusLabel)
                    .addComponent(actionTipLabel)
                    .addComponent(startGameButton)
                    .addComponent(clearDesignButton)
                    .addComponent(saveButton)
                    .addComponent(menuButton)
                )
            )
        );
        
        pack();
    	
    }
    
    private JTabbedPane makeDesignPane() {
    	JTabbedPane tabbed = new JTabbedPane();
    	JPanel tilePanel = new JPanel();
    	JPanel playerPanel = new JPanel();
    	JPanel deckPanel = new JPanel();
    	JPanel boardPanel = new JPanel();
    	
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
    		)    		
    	);
    	tileLayout.setVerticalGroup(tileLayout.createSequentialGroup()
    		.addGroup(tileLayout.createParallelGroup()
    			.addComponent(addRegularTileButton)
    			.addComponent(removeTileButton)
    		)
    		.addGroup(tileLayout.createParallelGroup()
        			.addComponent(addActionTileButton)
        			.addComponent(removeConnectionButton)
        	)
    		.addComponent(addHiddenTileButton)
    		.addComponent(addConnectionButton)
    	);
    	
    	GroupLayout playerLayout = new GroupLayout(playerPanel);
    	playerPanel.setLayout(playerLayout);
    	playerLayout.setAutoCreateGaps(true);
    	playerLayout.setAutoCreateContainerGaps(true);
    	playerLayout.setHorizontalGroup(playerLayout.createSequentialGroup()
    	    .addGroup(playerLayout.createParallelGroup()
    	    	.addComponent(setStartingTile1Button)
    	    	.addComponent(setStartingTile2Button)
    	    	.addComponent(setStartingTile3Button)
    	    	.addComponent(setStartingTile4Button)
    	    )
    	);
    	playerLayout.setVerticalGroup(playerLayout.createSequentialGroup()
    		.addComponent(setStartingTile1Button)
    		.addComponent(setStartingTile2Button)
    		.addComponent(setStartingTile3Button)
    		.addComponent(setStartingTile4Button)
    	);
    	
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
               
    	tabbed.addTab("Tile", tilePanel);
    	tabbed.addTab("Player", playerPanel);
    	tabbed.addTab("Deck", deckPanel);
    	tabbed.addTab("Board", boardPanel);
    	 	
    	return tabbed;
    }
    
    private int updateNumberOfCards() {
    	int n = (int)extraTurnCardSpinner.getValue() + (int)newConnectionCardSpinner.getValue() + (int)removeConnectionCardSpinner.getValue() + (int)teleportCardSpinner.getValue() + (int)loseTurnCardSpinner.getValue();
    	numberOfCardsLabel.setText(n+"/"+NumberOfCards);
    	return n;
    }
    

    private void rollDieActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void saveActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void newGameActionPerformed(java.awt.event.ActionEvent e) {
    	// tmp
    	Game game = TileOUtil.createGame(10, 32, 6, 3, true, TileOApplication.getTileO(), new TileOController());
    	renderLayout(game);
    }
    private void restartActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void moveActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void drawCardActionPerformed(java.awt.event.ActionEvent e) {
    }  
    private void nextTurnActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void teleportCardActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void removeConnectionCardActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void addConnectionCardCardActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void rollDieCardActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void addRegularTileActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void addActionTileActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void addHiddenTileActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void addConnectionActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void removeConnectionActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void removeTileActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void setStartingTileActionPerformed(java.awt.event.ActionEvent e, int n) {
    }
    private void startGameActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void clearDesignActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void loadDesignActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void loadGameActionPerformed(java.awt.event.ActionEvent e){
    }
}
