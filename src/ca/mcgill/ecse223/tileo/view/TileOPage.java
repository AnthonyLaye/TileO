package ca.mcgill.ecse223.tileo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.WindowConstants;
import javax.swing.SwingConstants;

import ca.mcgill.ecse223.tileo.exception.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Game;

import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;


public class TileOPage extends JFrame{
    
	private static final long serialVersionUID = -6882114745313007613L;
	
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
    private JButton quitButton;
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

    private static final int NumberOfCards = Game.NumberOfActionCards;

    
    public TileOPage() {
        init();
        renderGame();
    }
    
    private void init() {
        
        
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
                extraTurnCardSpinner = new JSpinner();
                newConnectionCardLabel = new JLabel();
                newConnectionCardSpinner = new JSpinner();;
                removeConnectionCardLabel = new JLabel();
                removeConnectionCardSpinner  = new JSpinner();
                teleportCardLabel = new JLabel();
                teleportCardSpinner = new JSpinner();
                loseTurnCardLabel = new JLabel();
                loseTurnCardSpinner = new JSpinner();
                totalCardLabel = new JLabel();
                numberOfCardsLabel = new JLabel();

        // control
        saveButton = new JButton();
        quitButton = new JButton();
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
        quitButton.setText("Quit");
        newGameButton.setText("New game");
        restartButton.setText("Restart game");
        startGameButton.setText("Start game");
        clearDesignButton.setText("Clear");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                saveActionPerformed(e);
            }
        });
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                quitActionPerformed(e);
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

        // layout
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
                .addComponent(quitButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {rollDieButton, newGameButton, saveButton, restartButton, quitButton});
        

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
                    .addComponent(quitButton)
                )
            )
        );
        
        pack();
    }


    private void welcome() {    

        // INITIALIZATION
        welNewGameButton = new JButton("New game");
        welLoadDesignButton = new JButton("Load a design");
        welLoadGameButton = new JButton("Load a game");
        welExitButton = new JButton("Exit");


        // LAYOUT
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
    
    private void renderGame() {
        modeLabel.setText("Game mode");
        actionTipLabel.setText("Roll the die !");
        currentPlayerNameLabel.setText("Player 1");
    }


    private void rollDieActionPerformed(java.awt.event.ActionEvent e) {

        TileOController tileOController = new TileOController();

        //tileOController.rollDie();

    }
    private void saveActionPerformed(java.awt.event.ActionEvent e) {

        TileOController tileOController = new TileOController();

        //String fileName = fileNameLabel.getText();
        //tileOController.saveGame(fileName);

    }
    private void quitActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void newGameActionPerformed(java.awt.event.ActionEvent e) {

        TileOController tileOController = new TileOController();

        try{
            Game game = (Game) e.getSource();
            tileOController.startGame(game);
        }
        catch(InvalidInputException e1){

            System.out.print("Error invalid Game");
        }

    }
    private void restartActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void moveActionPerformed(java.awt.event.ActionEvent e) {

        TileOController tileOController = new TileOController();

        Tile tile = (Tile) e.getSource();

        try{
            tileOController.land(tile);
        }
        catch (InvalidInputException e1){
            System.out.print("Error");
        }
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

        TileOController tileOController = new TileOController();
        Tile tile = (Tile) e.getSource();
        Game game = tile.getGame();

        tileOController.addRegularTile(tile.getX(), tile.getY(), game);
    }
    private void addActionTileActionPerformed(java.awt.event.ActionEvent e) {

        TileOController tileOController = new TileOController();
        Tile tile = (Tile) e.getSource();
        Game game = tile.getGame();
        int inactivityPeriod = 5;

        tileOController.addActionTile(tile.getX(), tile.getY(), game, inactivityPeriod);
    }
    private void addHiddenTileActionPerformed(java.awt.event.ActionEvent e) {

        TileOController tileOController = new TileOController();
        Tile tile = (Tile) e.getSource();
        Game game = tile.getGame();

        tileOController.addHiddenTile(tile.getX(), tile.getY(), game);

    }
    private void addConnectionActionPerformed(java.awt.event.ActionEvent e) {

        TileOController tileOController = new TileOController();
        Connection connection = (Connection) e.getSource();

        //tileOController.addConnection(connection);
    }
    private void removeConnectionActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void removeTileActionPerformed(java.awt.event.ActionEvent e) {

        TileOController tileOController = new TileOController();
        Tile tile = (Tile) e.getSource();

        tileOController.removeTile(tile);
    }
    private void setStartingTileActionPerformed(java.awt.event.ActionEvent e, int n) {
    }
    private void startGameActionPerformed(java.awt.event.ActionEvent e) {
    }
    private void clearDesignActionPerformed(java.awt.event.ActionEvent e) {
    }
}
