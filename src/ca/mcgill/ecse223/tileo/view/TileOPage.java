package ca.mcgill.ecse223.tileo.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import javax.swing.JScrollPane;

import ca.mcgill.ecse223.tileo.exception.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.computer.ComputerPlayer;
import ca.mcgill.ecse223.tileo.computer.CompThread;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.TileOController;


public class TileOPage extends JFrame{
    
	private static final long serialVersionUID = -6882114745313007613L;
    private static final int NumberOfCards = Game.NumberOfActionCards;
    private static final Font font60 = new Font("Rockwell", Font.PLAIN, 60);
    private static final Font font45 = new Font("Rockwell", Font.PLAIN, 45);
    private static final Font font150 = new Font("Rockwell", Font.PLAIN, 150);
    private static final Font font80 = new Font("Rockwell", Font.PLAIN, 80);
    private static final Font font50 = new Font("Rockwell", Font.PLAIN, 50);
    private static final Font font20 = new Font("Rockwell", Font.PLAIN, 15);
    private static final Font font30 = new Font("Rockwell", Font.PLAIN, 25);
    
    
    
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
    private JButton welContinueButton;
    
    // mode
    private JLabel modeLabel;

    // action
    private JLabel actionLabel;
    private JLabel actionStatusLabel;
    private JLabel actionTipLabel;
    private JLabel actionError;
        // game
    	
        private JButton rollDieButton;
        private JPanel playerColour;
            // card
        	private JButton loseTurnCardButton;
            private JButton rollDieCardButton;
            private JButton removeRandomTileCardButton;
            private JButton turnInactiveCardButton;
            private JSpinner chooseAdditionalMoveCardSpinner;
            private JButton revealTileCardButton;
            private JButton winTileHintCardButton;
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
            private JButton randomDesignButton;
            // player
            JPanel startingTilePanel;
            JPanel setComputerPanel;
            JPanel computerTypePanel;
            private JButton setStartingTile1Button;
            private JButton setStartingTile2Button;
            private JButton setStartingTile3Button;
            private JButton setStartingTile4Button;
            private JButton[] setStartingTileButtons;
            private JRadioButton setComputer1RadioButton;
            private JRadioButton setComputer2RadioButton;
            private JRadioButton setComputer3RadioButton;
            private JRadioButton setComputer4RadioButton;
            private JRadioButton[] setComputerRadioButtons;
            private String[] computerTypes = {"Random", "Grandma", "Hacker"};
            private JComboBox computerType1ComboBox;
            private JComboBox computerType2ComboBox;
            private JComboBox computerType3ComboBox;
            private JComboBox computerType4ComboBox;
            private JComboBox[] computerTypeComboBoxes;
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
            private JLabel removeRandomTileCardLabel;
            private JSpinner removeRandomTileCardSpinner;
            private JLabel turnInactiveCardLabel;
            private JSpinner turnInactiveCardSpinner;
            private JLabel chooseAdditionalCardLabel;
            private JSpinner chooseAdditionalCardSpinner;
            private JLabel revealTileCardLabel;
            private JSpinner revealTileCardSpinner;
            private JLabel winTileHintCardLabel;
            private JSpinner winTileHintCardSpinner;
            private JLabel sendToStartCardLabel;
            private JSpinner sendToStartCardSpinner;
            private JLabel swapPositionCardLabel;
            private JSpinner swapPositionCardSpinner;
            private JLabel totalCardLabel;
            private JLabel numberOfCardsLabel;
            private JButton randomCardsButton;
            private JButton fillCardsButton;
            private JButton resetDeckButton;
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
    
    // board
    private JLabel currentPlayerLabel;
    private JLabel currentPlayerNameLabel;
    private JLabel connectionsLeftLabel;
    public BoardVisualizer board;
    


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
        getContentPane().setBackground(Color.WHITE); // white
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Makes game  fullscreen
        
	// Remove welNewGameButton.setBorder((new LineBorder(Color.BLACK))); due to 
	// compatibility on Mac OS, buttons not rendered correctly
	// ToDo: verify the updated view on windows  
	    
    	welNewGameButton = new JButton("New game");
        //welNewGameButton.setBorder((new LineBorder(Color.BLACK)));

        welLoadDesignButton = new JButton("Load a design");
        //welLoadDesignButton.setBorder((new LineBorder(Color.BLACK)));

        welLoadGameButton = new JButton("Load a game");
        //welLoadGameButton.setBorder((new LineBorder(Color.BLACK)));

        welExitButton = new JButton("Exit");
        //welExitButton.setBorder((new LineBorder(Color.BLACK)));
        
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
        
        welContinueButton = new JButton();
        welContinueButton.setText("Continue");

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
        welContinueButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		Game game = TileOApplication.getTileO().getCurrentGame();
        		if (game != null) {
        			initGameLayout();
        			renderLayout(game);
        		}
        		else {
        			welError.setText("No current game to continue");
        		}
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
            rollDieButton = new JButton();
                // card
            	loseTurnCardButton = new JButton();
                rollDieCardButton = new JButton();
                removeRandomTileCardButton = new JButton();
                turnInactiveCardButton = new JButton();
                chooseAdditionalMoveCardSpinner = new JSpinner(new SpinnerNumberModel(1,1,6,1));
                revealTileCardButton = new JButton();
                winTileHintCardButton = new JButton();
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
                randomDesignButton = new JButton();
                // player
                setStartingTile1Button = new JButton();
                setStartingTile2Button = new JButton();
                setStartingTile3Button = new JButton();
                setStartingTile4Button = new JButton();
                setStartingTileButtons = new JButton[4];
                setStartingTileButtons[0] = setStartingTile1Button; setStartingTileButtons[1] = setStartingTile2Button; setStartingTileButtons[2] = setStartingTile3Button; setStartingTileButtons[3] = setStartingTile4Button; 
                setComputer1RadioButton = new JRadioButton("Computer 1");
                setComputer2RadioButton = new JRadioButton("Computer 2");
                setComputer3RadioButton = new JRadioButton("Computer 3");
                setComputer4RadioButton = new JRadioButton("Computer 4");
                setComputerRadioButtons = new JRadioButton[4];
                setComputerRadioButtons[0] = setComputer1RadioButton; setComputerRadioButtons[1] = setComputer2RadioButton; setComputerRadioButtons[2] = setComputer3RadioButton; setComputerRadioButtons[3] = setComputer4RadioButton;
                computerType1ComboBox = new JComboBox<>(computerTypes);
                computerType2ComboBox = new JComboBox<>(computerTypes);
                computerType3ComboBox = new JComboBox<>(computerTypes);
                computerType4ComboBox = new JComboBox<>(computerTypes);
                computerTypeComboBoxes = new JComboBox[4];
                computerTypeComboBoxes[0]=computerType1ComboBox;
                computerTypeComboBoxes[1]=computerType2ComboBox;
                computerTypeComboBoxes[2]=computerType3ComboBox;
                computerTypeComboBoxes[3]=computerType4ComboBox;
                // deck
                extraTurnCardLabel = new JLabel();
                newConnectionCardLabel = new JLabel();
                removeConnectionCardLabel = new JLabel();
                teleportCardLabel = new JLabel();
                loseTurnCardLabel = new JLabel();
                removeRandomTileCardLabel = new JLabel();
                turnInactiveCardLabel = new JLabel();
                chooseAdditionalCardLabel = new JLabel();
                revealTileCardLabel = new JLabel();
                winTileHintCardLabel = new JLabel();
                sendToStartCardLabel = new JLabel();
                swapPositionCardLabel = new JLabel();
                totalCardLabel = new JLabel();
                numberOfCardsLabel = new JLabel();
                
                extraTurnCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                newConnectionCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                removeConnectionCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                teleportCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                loseTurnCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                removeRandomTileCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                turnInactiveCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                chooseAdditionalCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                revealTileCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                winTileHintCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                sendToStartCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                swapPositionCardSpinner = new JSpinner(new SpinnerNumberModel(0,0,32,1));
                
                // dont change this ordering
                JSpinner[] spinners = {extraTurnCardSpinner, newConnectionCardSpinner, removeConnectionCardSpinner, 
                		teleportCardSpinner, loseTurnCardSpinner, removeRandomTileCardSpinner, 
                		turnInactiveCardSpinner, chooseAdditionalCardSpinner, revealTileCardSpinner,
                		sendToStartCardSpinner, winTileHintCardSpinner, swapPositionCardSpinner};
                for (int i=0; i<spinners.length; ++i){
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
                randomCardsButton = new JButton();
                fillCardsButton = new JButton();
                resetDeckButton = new JButton();
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
    
        // board
        currentPlayerLabel = new JLabel();
        currentPlayerNameLabel = new JLabel();
        connectionsLeftLabel = new JLabel();
        board = new BoardVisualizer();
        

        // global settings and listeners
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("TileO");


        // basic game actions
        actionLabel.setText("Actions");
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
        removeRandomTileCardButton.setText("Remove random tile");
        removeRandomTileCardButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		removeRandomTileCardActionPerformed();
        	}
        });
        turnInactiveCardButton.setText("Make inactive");
        turnInactiveCardButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		turnInactiveCardActionPerformed();
        	}
        });
        chooseAdditionalMoveCardSpinner.addChangeListener(new javax.swing.event.ChangeListener(){
        	public void stateChanged(javax.swing.event.ChangeEvent e) {
        		JSpinner spinner = (JSpinner) e.getSource();
        		chooseAdditionalMoveCardActionPerformed((int)spinner.getValue());
        	}
        });
        revealTileCardButton.setText("End turn");
        revealTileCardButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		revealTileCardActionPerformed();
        	}
        });
        winTileHintCardButton.setText("End turn");
        winTileHintCardButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		winTileHintCardActionPerformed();
        	}
        });

        // design-tile

        addRegularTileButton.setText("Add tile");
        addActionTileButton.setText("Add action tile");
        addHiddenTileButton.setText(
        "<html><font><b>Add </b></font><font color=#db02db><b>hidden button</b></font></html>"
        );
        addConnectionButton.setText("Add connection");
        removeTileButton.setText("Remove tile");
        removeConnectionButton.setText("Remove connection");
        randomDesignButton.setText("Random design");
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
        randomDesignButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                randomDesign();
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
        for (int i=0; i<setComputerRadioButtons.length; ++i) {
            int buttonNum = i;
            setComputerRadioButtons[i].addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent e) {
                    if (e.getStateChange()==1) { // isComputer selected
                        String type = (String)computerTypeComboBoxes[buttonNum].getSelectedItem();
                        setPlayerAsComputerActionPerformed(buttonNum, type);
                        computerTypeComboBoxes[buttonNum].setVisible(true);
                    }
                    else {
                        setComputerAsPlayerActionPerformed(buttonNum);
                        computerTypeComboBoxes[buttonNum].setVisible(false);
                    }
                }
            });
        }
        for (int i=0; i<computerTypeComboBoxes.length; ++i) {
            int comboBoxNumber = i;
            computerTypeComboBoxes[i].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    JComboBox box = (JComboBox) e.getSource();
                    String type = (String) box.getSelectedItem();
                    setPlayerAsComputerActionPerformed(comboBoxNumber, type);
                }
            });
        
        }

        // design-deck
        extraTurnCardLabel.setText("Extra turn");
        newConnectionCardLabel.setText("New connection");
        removeConnectionCardLabel.setText("Remove connection");
        teleportCardLabel.setText("Teleport");
        removeRandomTileCardLabel.setText("Remove random tile");
        loseTurnCardLabel.setText("Lose turn");
        turnInactiveCardLabel.setText("Turn inactive");
        chooseAdditionalCardLabel.setText("Choose additional move");;
        revealTileCardLabel.setText("Reveal tile");
        winTileHintCardLabel.setText("Win tile hint");
        sendToStartCardLabel.setText("Send to start");
        swapPositionCardLabel.setText("Swap position");
        totalCardLabel.setText("Total");
        numberOfCardsLabel.setText("0/"+NumberOfCards);
        randomCardsButton.setText("Random");
        randomCardsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setRandomDeck();
            }
        });
        fillCardsButton.setText("Fill");
        fillCardsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                fillDeck();
            }
        });
        resetDeckButton.setText("Reset");
        resetDeckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                resetDeck();
            }
        });
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
        
        
        
        
        // set all the fonts
        tileOLabel.setFont(font150);
        numberOfPlayerLabel.setFont(font45);
        welBoardSizeLabel.setFont(font45);
        welLoadDesignButton.setFont(font45);
        welLoadGameButton.setFont(font45);
        welExitButton.setFont(font45);
        welNewGameButton.setFont(font45);
        welContinueButton.setFont(font45);
        numberOfPlayerSpinner.setFont(font80);
        welBoardSizeSpinner.setFont(font80);
        
        modeLabel.setFont(font45);
        
        actionLabel.setFont(font45);
        actionStatusLabel.setFont(font20);
        actionTipLabel.setFont(font20);
        actionError.setFont(font20);
        
        rollDieButton.setFont(font20);
        loseTurnCardButton.setFont(font20);
        removeRandomTileCardButton.setFont(font20);
        turnInactiveCardButton.setFont(font20);
        chooseAdditionalMoveCardSpinner.setFont(font20);
        revealTileCardButton.setFont(font20);
        winTileHintCardButton.setFont(font20);
        
        addRegularTileButton.setFont(font20);
        addActionTileButton.setFont(font20);
        addHiddenTileButton.setFont(font20);
        addConnectionButton.setFont(font20);
        removeTileButton.setFont(font20);
        removeConnectionButton.setFont(font20);
        inactivitySpinner.setFont(font20);
        inactivityLabel.setFont(font20);
        randomDesignButton.setFont(font20);
        
        setStartingTile1Button.setFont(font20);
        setStartingTile2Button.setFont(font20);
        setStartingTile3Button.setFont(font20);
        setStartingTile4Button.setFont(font20);
        computerType1ComboBox.setFont(font20);
        computerType2ComboBox.setFont(font20);
        computerType3ComboBox.setFont(font20);
        computerType4ComboBox.setFont(font20);
        setComputer1RadioButton.setFont(font20);
        setComputer2RadioButton.setFont(font20);
        setComputer3RadioButton.setFont(font20);
        setComputer4RadioButton.setFont(font20);
        
        extraTurnCardLabel.setFont(font20);
        newConnectionCardLabel.setFont(font20);
        removeConnectionCardLabel.setFont(font20);
        teleportCardLabel.setFont(font20);
        loseTurnCardLabel.setFont(font20);
        removeRandomTileCardLabel.setFont(font20);
        turnInactiveCardLabel.setFont(font20);
        chooseAdditionalCardLabel.setFont(font20);
        revealTileCardLabel.setFont(font20);
        winTileHintCardLabel.setFont(font20);
        sendToStartCardLabel.setFont(font20);
        swapPositionCardLabel.setFont(font20);
        totalCardLabel.setFont(font20);
        numberOfCardsLabel.setFont(font20);
        randomCardsButton.setFont(font20);
        fillCardsButton.setFont(font20);
        resetDeckButton.setFont(font20);
        for (int i=0; i<spinners.length; ++i)
        	spinners[i].setFont(font20);
        
        boardSizeLabel.setFont(font20);
        boardSizeSpinner.setFont(font20);
        
        saveButton.setFont(font30);
        menuButton.setFont(font30);
        startGameButton.setFont(font30);
        clearDesignButton.setFont(font30);
        newGameButton.setFont(font30);
        
        currentPlayerLabel.setFont(font60);
        currentPlayerNameLabel.setFont(font60);
        connectionsLeftLabel.setFont(font50);

        
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
        center.add(welContinueButton);
        center.add(welExitButton);
        center.add(welNewGameButton);
        center.add(numberOfPlayerSpinner);
        center.add(welBoardSizeSpinner);

        center.setBackground(Color.decode("#c62828")); // red

        GroupLayout layout = new GroupLayout(center);
        center.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


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
                .addComponent(welContinueButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(welExitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(welError, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );
        
        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
                {numberOfPlayerLabel, numberOfPlayerSpinner, welNewGameButton,
                welLoadDesignButton, welLoadGameButton, welExitButton, welBoardSizeLabel,
                welBoardSizeSpinner, welError, welContinueButton});

        layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[]
                {numberOfPlayerLabel, numberOfPlayerSpinner, welNewGameButton,
                welLoadDesignButton, welLoadGameButton, welExitButton,
                welBoardSizeSpinner, welBoardSizeLabel, welError, welContinueButton});

        
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
            .addComponent(welContinueButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(welExitButton,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(welError, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        actionStatusLabel.setText("");
        actionTipLabel.setText("");
        actionError.setText("");
        setWaitingFor("");
        if (TileOApplication.getTileO().getCurrentGame() == null) welContinueButton.setVisible(false);
        else welContinueButton.setVisible(true);
    }

    // Create basic game layout
    private void initGameLayout(){

    	getContentPane().removeAll();
		
        connectionsLeftLabel.setText("Connections left: " + TileOApplication.getTileO().getCurrentGame().getCurrentConnectionPieces());

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
                .addComponent(connectionsLeftLabel)
                .addComponent(modeLabel)

            )
            .addGroup(layout.createParallelGroup()
            	.addComponent(actionLabel)
                .addComponent(rollDieButton)
                .addComponent(loseTurnCardButton)
                .addComponent(rollDieCardButton)
                .addComponent(removeRandomTileCardButton)
                .addComponent(turnInactiveCardButton)
                .addComponent(chooseAdditionalMoveCardSpinner)
                .addComponent(revealTileCardButton)
                .addComponent(winTileHintCardButton)
                .addComponent(actionStatusLabel)
                .addComponent(actionError)
                .addComponent(actionTipLabel)
                .addComponent(newGameButton)
                .addComponent(saveButton)
                .addComponent(menuButton)
            )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
        {newGameButton, saveButton, menuButton, actionStatusLabel, actionTipLabel, revealTileCardButton, winTileHintCardButton,
        loseTurnCardButton, rollDieCardButton, removeRandomTileCardButton, turnInactiveCardButton, chooseAdditionalMoveCardSpinner});
        
        layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {
        newGameButton, chooseAdditionalMoveCardSpinner // so that the spinner isnt ridiculously big
        });

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
                    .addComponent(connectionsLeftLabel)
                )
                .addGroup(layout.createSequentialGroup()
                	.addComponent(actionLabel)
                    .addComponent(rollDieButton)
                    .addComponent(loseTurnCardButton)
                    .addComponent(rollDieCardButton)
                    .addComponent(removeRandomTileCardButton)
                    .addComponent(turnInactiveCardButton)
                    .addComponent(chooseAdditionalMoveCardSpinner)
                    .addComponent(revealTileCardButton)
                    .addComponent(winTileHintCardButton)
                    .addComponent(actionStatusLabel)
                    .addComponent(actionTipLabel)
                    .addComponent(actionError)
                    .addComponent(newGameButton)
                    .addComponent(saveButton)
                    .addComponent(menuButton)
                )
            )
        );
        
        revealTileCardButton.setVisible(false);
        winTileHintCardButton.setVisible(false);
        pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    
    public void renderLayout(Game game) {

        modeLabel.setText(game.getMode().toString());
        currentPlayerNameLabel.setText("Player "+ (game.indexOfPlayer(game.getCurrentPlayer())+ 1));
        connectionsLeftLabel.setText("Connections left: " + TileOApplication.getTileO().getCurrentGame().getCurrentConnectionPieces());
        board.setGame(game);

        if(game.hasCurrentPlayer()){
            String colour = String.valueOf(game.getCurrentPlayer().getColor());
            if(colour.equals("RED"))
                playerColour.setBackground(Color.decode("#c62828"));
            if(colour.equals("BLUE"))
                playerColour.setBackground(Color.decode("#1565c0"));
            if(colour.equals("GREEN"))
                playerColour.setBackground(Color.decode("#2e7d32"));
            if(colour.equals("YELLOW"))
                playerColour.setBackground(Color.decode("#ffa000"));
        }
        actionError.setText("");
        
        // set all button to invisible
        rollDieButton.setVisible(false);
        loseTurnCardButton.setVisible(false);
        rollDieCardButton.setVisible(false);
        removeRandomTileCardButton.setVisible(false);
        turnInactiveCardButton.setVisible(false);
        chooseAdditionalMoveCardSpinner.setVisible(false);
        revealTileCardButton.setVisible(false);
        winTileHintCardButton.setVisible(false);

        switch (game.getMode()) {      
            case GAME:
            	actionTipLabel.setText("Roll the die !");
            	actionStatusLabel.setText("");
                if (game.getCurrentPlayer() instanceof ComputerPlayer)
                    startComputerTurn(game);
                else 
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
                if (game.getCurrentPlayer() instanceof ComputerPlayer)
                    startComputerTurn(game);
                else {
            	    rollDieCardButton.setVisible(true);
                }
                break;
            case GAME_CONNECTTILESACTIONCARD:
            	actionTipLabel.setText("Select two tiles you want to connect");
            	actionStatusLabel.setText("Add a connection action card");
                if (game.getCurrentPlayer() instanceof ComputerPlayer) 
                    startComputerTurn(game);
            	else {
                    board.setWaitForConn(true);
            	    setWaitingFor("newconncard");
            	}
                break;
            case GAME_REMOVECONNECTIONACTIONCARD:
            	actionTipLabel.setText("Select two tiles you want to disconnect");
            	actionStatusLabel.setText("Remove a connection action card");
                if (game.getCurrentPlayer() instanceof ComputerPlayer) 
                    startComputerTurn(game);
            	else {
                    board.setWaitForConn(true);
            	    setWaitingFor("rmconncard");
                }
                break;
            case GAME_TELEPORTACTIONCARD:
            	actionTipLabel.setText("Select a new tile");
            	actionStatusLabel.setText("Teleport action card");
                if (game.getCurrentPlayer() instanceof ComputerPlayer) 
                    startComputerTurn(game);
            	else {
                    board.setWaitForTile(true);
            	    setWaitingFor("teleportcard");
            	}
                break;
            case GAME_LOSETURNACTIONCARD:
            	actionTipLabel.setText("You lose your next turn");
            	actionStatusLabel.setText("Lose turn action card");
                if (game.getCurrentPlayer() instanceof ComputerPlayer) 
                    startComputerTurn(game);
            	else {
            	    loseTurnCardButton.setVisible(true);
            	}
                break;
            case GAME_REMOVERANDOMTILEACTIONCARD:
            	actionTipLabel.setText("Click on the button");
            	actionStatusLabel.setText("Remove a random tile action card");
                if (game.getCurrentPlayer() instanceof ComputerPlayer) 
                    startComputerTurn(game);
                else {
            	    removeRandomTileCardButton.setVisible(true);
                }
                break;
            case GAME_TURNINACTIVEACTIONCARD:
            	actionTipLabel.setText("Click on the button");
            	actionStatusLabel.setText("Make all action tiles inactive");
                if (game.getCurrentPlayer() instanceof ComputerPlayer) 
                    startComputerTurn(game);
                else {
                    turnInactiveCardButton.setVisible(true);
                }
                break;
            case GAME_CHOOSEADDITIONALMOVEACTIONCARD:
            	actionTipLabel.setText("Select a number");
            	actionStatusLabel.setText("Move to a new tile");
                if (game.getCurrentPlayer() instanceof ComputerPlayer)
                	startComputerTurn(game);
                else {
                	chooseAdditionalMoveCardSpinner.setVisible(true);
                	chooseAdditionalMoveCardSpinner.setValue(2); // so it changes and display the tiles
                	chooseAdditionalMoveCardSpinner.setValue(1);
                }
            	break;
            case GAME_REVEALTILEACTIONCARD:
            	actionTipLabel.setText("Select a tile to see its type");
            	actionStatusLabel.setText("Reveal a tile");
            	if (game.getCurrentPlayer() instanceof ComputerPlayer)
            		startComputerTurn(game);
            	else {
            		board.setWaitForTile(true);
            		setWaitingFor("reveal");
            	}
            	break;
            case GAME_SENDBACKTOSTARTACTIONCARD:
            	actionTipLabel.setText("Choose a player");
            	actionStatusLabel.setText("Send a player back to their starting tile");
            	if (game.getCurrentPlayer() instanceof ComputerPlayer)
            		startComputerTurn(game);
            	else {
            		board.setWaitForTile(true);
            		setWaitingFor("sendstart");
            	}
            	break;
            case GAME_SWAPPOSITIONACTIONCARD:
                actionTipLabel.setText("Choose a player");
                actionStatusLabel.setText("Swap positions with another player");
                if (game.getCurrentPlayer() instanceof ComputerPlayer)
                    startComputerTurn(game);
                else {
                    board.setWaitForTile(true);
                    setWaitingFor("swapplayer");
                }
                break;
            case GAME_WINTILEHINTACTIONCARD:
            	actionStatusLabel.setText("See if a tile or its neighbours is the win tile");
            	actionTipLabel.setText("Choose a tile");
            	if (game.getCurrentPlayer() instanceof ComputerPlayer)
            		startComputerTurn(game);
            	else {
            		board.setWaitForTile(true);
            		setWaitingFor("winhint");
            	}
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
        playerColour.setBackground(Color.CYAN);

        inactivityLabel.setText("Inactivity Period: ");
        
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
                .addComponent(randomDesignButton)
    		)
    		.addGroup(tileLayout.createParallelGroup()
    			.addComponent(removeTileButton)
                .addComponent(addConnectionButton)
    			.addComponent(removeConnectionButton)
    			.addComponent(inactivitySpinner)
    		)    		
    	);
    	tileLayout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
    	        {randomDesignButton, addRegularTileButton, addActionTileButton, addHiddenTileButton, addConnectionButton, removeTileButton, removeConnectionButton, inactivitySpinner});
    	tileLayout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[]
    	        {randomDesignButton, addRegularTileButton, addActionTileButton, addHiddenTileButton, addConnectionButton, removeTileButton, removeConnectionButton, inactivitySpinner});
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
            .addComponent(randomDesignButton)

    	);
    	
    	
    	// PLAYER
    	startingTilePanel = new JPanel();
        setComputerPanel = new JPanel();
        computerTypePanel = new JPanel();

    	startingTilePanel.setLayout(new BoxLayout(startingTilePanel, BoxLayout.PAGE_AXIS));
    	setComputerPanel.setLayout(new BoxLayout(setComputerPanel, BoxLayout.PAGE_AXIS));
        computerTypePanel.setLayout(new BoxLayout(computerTypePanel, BoxLayout.PAGE_AXIS));

        for (int i=0; i<nPlayers; ++i) {
    		startingTilePanel.add(setStartingTileButtons[i]);   	
            setComputerPanel.add(setComputerRadioButtons[i]);
            computerTypePanel.add(computerTypeComboBoxes[i]);
        }
        GroupLayout playerLayout = new GroupLayout(playerPanel);
    	playerPanel.setLayout(playerLayout);
    	playerLayout.setAutoCreateGaps(true);
    	playerLayout.setAutoCreateContainerGaps(true);
    	playerLayout.setHorizontalGroup(playerLayout.createSequentialGroup()
    	    .addComponent(startingTilePanel)
            .addComponent(setComputerPanel)
            .addComponent(computerTypePanel)
    	);
    	playerLayout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {setComputerPanel, computerTypePanel});
        playerLayout.setVerticalGroup(playerLayout.createSequentialGroup()
    		.addGroup(playerLayout.createParallelGroup()
                .addComponent(startingTilePanel)
                .addComponent(setComputerPanel)
                .addComponent(computerTypePanel)
            )
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
    			.addComponent(removeRandomTileCardLabel)
    			.addComponent(turnInactiveCardLabel)
    			.addComponent(chooseAdditionalCardLabel)
    			.addComponent(revealTileCardLabel)
    			.addComponent(winTileHintCardLabel)
    			.addComponent(sendToStartCardLabel)
    			.addComponent(swapPositionCardLabel)
    			.addComponent(totalCardLabel)
                .addComponent(randomCardsButton)
                .addComponent(fillCardsButton)
                .addComponent(resetDeckButton)
    		)
    		.addGroup(deckLayout.createParallelGroup()
    			.addComponent(extraTurnCardSpinner)
    			.addComponent(newConnectionCardSpinner)
    			.addComponent(removeConnectionCardSpinner)
    			.addComponent(teleportCardSpinner)
    			.addComponent(loseTurnCardSpinner)
    			.addComponent(removeRandomTileCardSpinner)
    			.addComponent(turnInactiveCardSpinner)
    			.addComponent(chooseAdditionalCardSpinner)
    			.addComponent(revealTileCardSpinner)
    			.addComponent(winTileHintCardSpinner)
    			.addComponent(sendToStartCardSpinner)
    			.addComponent(swapPositionCardSpinner)
    			.addComponent(numberOfCardsLabel)
    		)
    	);
    	deckLayout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[]
    	        {extraTurnCardLabel, newConnectionCardLabel, removeConnectionCardLabel,
                teleportCardLabel, removeRandomTileCardLabel, totalCardLabel, extraTurnCardSpinner,
                newConnectionCardSpinner, removeConnectionCardSpinner,
                teleportCardSpinner, numberOfCardsLabel, loseTurnCardLabel,
                loseTurnCardSpinner, removeRandomTileCardSpinner, randomCardsButton, fillCardsButton, resetDeckButton,
                turnInactiveCardLabel, turnInactiveCardSpinner, chooseAdditionalCardLabel, 
                revealTileCardLabel, winTileHintCardLabel, sendToStartCardLabel, swapPositionCardLabel,
                chooseAdditionalCardSpinner, revealTileCardSpinner, winTileHintCardSpinner,
                sendToStartCardSpinner, swapPositionCardSpinner});
        deckLayout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
    	        {extraTurnCardLabel, newConnectionCardLabel, removeConnectionCardLabel,
                teleportCardLabel, removeRandomTileCardLabel, totalCardLabel, extraTurnCardSpinner,
                newConnectionCardSpinner, removeConnectionCardSpinner,
                teleportCardSpinner, numberOfCardsLabel, loseTurnCardLabel,
                loseTurnCardSpinner, removeRandomTileCardSpinner, randomCardsButton, fillCardsButton, resetDeckButton,
                turnInactiveCardLabel, turnInactiveCardSpinner, chooseAdditionalCardLabel, 
                revealTileCardLabel, winTileHintCardLabel, sendToStartCardLabel, swapPositionCardLabel,
                chooseAdditionalCardSpinner, revealTileCardSpinner, winTileHintCardSpinner,
                sendToStartCardSpinner, swapPositionCardSpinner});
    	
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
    				.addComponent(removeRandomTileCardLabel)
    				.addComponent(removeRandomTileCardSpinner)
    		)
    		.addGroup(deckLayout.createParallelGroup()
    				.addComponent(turnInactiveCardLabel)
    				.addComponent(turnInactiveCardSpinner)
    		)
    		.addGroup(deckLayout.createParallelGroup()
    				.addComponent(chooseAdditionalCardLabel)
    				.addComponent(chooseAdditionalCardSpinner)
    		)
    		.addGroup(deckLayout.createParallelGroup()
    				.addComponent(revealTileCardLabel)
    				.addComponent(revealTileCardSpinner)
    		)
    		.addGroup(deckLayout.createParallelGroup()
    				.addComponent(winTileHintCardLabel)
    				.addComponent(winTileHintCardSpinner)
    		)
    		.addGroup(deckLayout.createParallelGroup()
    				.addComponent(sendToStartCardLabel)
    				.addComponent(sendToStartCardSpinner)
    		)
    		.addGroup(deckLayout.createParallelGroup()
    				.addComponent(swapPositionCardLabel)
    				.addComponent(swapPositionCardSpinner)
    		)
    		.addGroup(deckLayout.createParallelGroup()
        			.addComponent(totalCardLabel)
        			.addComponent(numberOfCardsLabel)
       		)
            .addComponent(randomCardsButton)
            .addComponent(fillCardsButton)
            .addComponent(resetDeckButton)
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
        int n = (int)extraTurnCardSpinner.getValue() + (int)newConnectionCardSpinner.getValue() + (int)removeConnectionCardSpinner.getValue() + (int)teleportCardSpinner.getValue()+(int)loseTurnCardSpinner.getValue()+(int)removeRandomTileCardSpinner.getValue()+(int)turnInactiveCardSpinner.getValue() + (int)chooseAdditionalCardSpinner.getValue() + (int)revealTileCardSpinner.getValue() + (int)winTileHintCardSpinner.getValue() + (int)sendToStartCardSpinner.getValue() + (int)swapPositionCardSpinner.getValue();
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
    	
    	if (!selected){
    		actionTipLabel.setText("Select an empty square to add the hidden tile");
    		board.setWaitForCoord(true);
    		setWaitingFor("hidden");
    	}
    	else {
    		Game game = TileOApplication.getTileO().getCurrentGame();
    	    
            WinTile wt = game.getWinTile();
    	    if (wt!=null) toc.removeTile(wt, game);
            
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

    private void setPlayerAsComputerActionPerformed(int playerNum, String type) {
        TileOApplication.getTileO().getCurrentGame().swapPlayerForComputer(playerNum, type);
    }

    private void setComputerAsPlayerActionPerformed(int playerNum) {
        TileOApplication.getTileO().getCurrentGame().swapComputerForPlayer(playerNum);
    }

    private void updateCards(int nCards, int cardType) {
        actionError.setText("");
        actionTipLabel.setText("");
        actionStatusLabel.setText("");
        try {
            toc.updateCards(nCards, cardType);
        }
        catch (InvalidInputException err){
            actionError.setText(err.getMessage());
        }
    }
    
    private void setRandomDeck() {
        resetDeck();
        fillDeck();
    }

    private void resetDeck() {
        extraTurnCardSpinner.setValue(0);
        newConnectionCardSpinner.setValue(0);
        removeConnectionCardSpinner.setValue(0);
        teleportCardSpinner.setValue(0);
        loseTurnCardSpinner.setValue(0);
        removeRandomTileCardSpinner.setValue(0);
        turnInactiveCardSpinner.setValue(0);
        chooseAdditionalCardSpinner.setValue(0);
        revealTileCardSpinner.setValue(0);
        sendToStartCardSpinner.setValue(0);
        winTileHintCardSpinner.setValue(0);
        swapPositionCardSpinner.setValue(0);
    }

    private void fillDeck() {
        int nRoll = (int) extraTurnCardSpinner.getValue(); 
        int nConn = (int) newConnectionCardSpinner.getValue(); 
        int nRmConn = (int) removeConnectionCardSpinner.getValue(); 
        int nTele = (int) teleportCardSpinner.getValue(); 
        int nLose = (int) loseTurnCardSpinner.getValue(); 
        int nRmTile = (int) removeRandomTileCardSpinner.getValue();
        int nTurnInactive = (int) turnInactiveCardSpinner.getValue();
        int nAddMove = (int) chooseAdditionalCardSpinner.getValue();
        int nReveal = (int) revealTileCardSpinner.getValue();
        int nHint = (int) winTileHintCardSpinner.getValue();
        int nStart = (int) sendToStartCardSpinner.getValue();
        int nSwap = (int) swapPositionCardSpinner.getValue();
        int total = nRoll + nConn + nRmConn + nTele + nLose + nRmTile + nTurnInactive + nAddMove + nReveal + nHint + nStart + nSwap;
        int nCardsLeft = 32 - total;
        int n;

        Random rand = new Random();
        if (nCardsLeft > 6) {
	        if (nRoll == 0 && nCardsLeft>0) {
	            n = rand.nextInt(nCardsLeft/6);
	            extraTurnCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
	        if (nConn == 0 && nCardsLeft>0) {
	            n = rand.nextInt(nCardsLeft/6);
	            newConnectionCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
	        if (nRmConn == 0 && nCardsLeft>0) {
	            n = rand.nextInt(nCardsLeft/4);
	            removeConnectionCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
	        if (nLose == 0 && nCardsLeft>0){
	            n = rand.nextInt(nCardsLeft/4);
	            loseTurnCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
	        if (nTele == 0 && nCardsLeft>0){
	            n = rand.nextInt(nCardsLeft/2);
	            teleportCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
	        if (nRmTile == 0 && nCardsLeft>0){
	            n = rand.nextInt(nCardsLeft/2);
	            removeRandomTileCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
	        if (nTurnInactive == 0 && nCardsLeft>0){
	            n = rand.nextInt(nCardsLeft/2);
	            turnInactiveCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
	        if (nAddMove == 0 && nCardsLeft>0){
	        	n = rand.nextInt(nCardsLeft/2);
	            chooseAdditionalCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
	        if (nReveal == 0 && nCardsLeft>0){
	        	n = rand.nextInt(nCardsLeft);
	            revealTileCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
	        if (nHint == 0 && nCardsLeft>0){
	        	n = rand.nextInt(nCardsLeft);
	            winTileHintCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
	        if (nStart == 0 && nCardsLeft>0){
	        	n = rand.nextInt(nCardsLeft);
	            sendToStartCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
	        if (nSwap == 0 && nCardsLeft>0){
	        	n = rand.nextInt(nCardsLeft);
	            swapPositionCardSpinner.setValue(n);
	            nCardsLeft -= n;
	        }
        }
        while (nCardsLeft>0) {
            n = rand.nextInt(12);
            switch (n) {
                case 0:
                    extraTurnCardSpinner.setValue((int)extraTurnCardSpinner.getValue()+1);
                case 1:
                    newConnectionCardSpinner.setValue((int)newConnectionCardSpinner.getValue()+1);
                case 2:
                    removeConnectionCardSpinner.setValue((int)removeConnectionCardSpinner.getValue()+1);
                case 3:
                    loseTurnCardSpinner.setValue((int)loseTurnCardSpinner.getValue()+1);
                case 4:
                    teleportCardSpinner.setValue((int)teleportCardSpinner.getValue()+1);
                case 5:
                    removeRandomTileCardSpinner.setValue((int)removeRandomTileCardSpinner.getValue()+1);
                case 6:
                	turnInactiveCardSpinner.setValue((int)turnInactiveCardSpinner.getValue()+1);
                case 7:
                	chooseAdditionalCardSpinner.setValue((int)chooseAdditionalCardSpinner.getValue()+1);
                case 8:
                	revealTileCardSpinner.setValue((int)revealTileCardSpinner.getValue()+1);
                case 9:
                	sendToStartCardSpinner.setValue((int)sendToStartCardSpinner.getValue()+1);                	
                case 10:
                	winTileHintCardSpinner.setValue((int)winTileHintCardSpinner.getValue()+1);
                case 11:
                	swapPositionCardSpinner.setValue((int)swapPositionCardSpinner.getValue()+1);
            }
            nCardsLeft--;
        }
    }

    private void randomDesign() {
        clearDesignActionPerformed(null);
        
        Game game = TileOApplication.getTileO().getCurrentGame();
        Random rand = new Random();
        int size = (int) boardSizeSpinner.getValue();
        ArrayList<int[]> unused = new ArrayList<int[]>();


        // first add a wintile
        toc.addHiddenTile(rand.nextInt(size), rand.nextInt(size), game);

        int minTiles = (size*size)-(4*size);
        int maxTiles = size*size;
        int nTiles = rand.nextInt(maxTiles-minTiles)+minTiles;
        int nActionTiles = rand.nextInt(nTiles/2);
        float tileProb = nTiles/((float)size*size);
        float actionTileProb = nActionTiles/(float)nTiles;
        
        System.out.println("Tile prob: " + tileProb);
        System.out.println("Action tile prob: " +actionTileProb);

        boolean wasAdded;
        // traverse horizontally to add tiles
        for (int x=0; x<size; ++x) {
            if (nTiles<=0) break;
            for (int y=0; y<size; ++y) {
                if (nTiles<=0) break;
                wasAdded = false;
                if (game.getTileAtXY(x, y)==null) {
                    if (rand.nextFloat() < tileProb) {
                        if (nActionTiles>0 && rand.nextFloat() < actionTileProb) {
                            toc.addActionTile(x,y,game, rand.nextInt(7)+1);
                            nActionTiles--;
                            wasAdded = true;
                        }
                        if (!wasAdded)
                            toc.addRegularTile(x,y,game);
                        nTiles--;
                    }
                    else {
                        int[] tmp = {x,y};
                        unused.add(tmp);
                    }
                } 
            }
        }

        if (nTiles!=0) {
            while (nTiles!=0) {
                int[] pos = unused.get(rand.nextInt(unused.size()));
                if (nActionTiles>0 && rand.nextFloat() < actionTileProb) {
                    toc.addActionTile(pos[0], pos[1], game, rand.nextInt(7)+1);
                    nActionTiles--;
                }
                else
                    toc.addRegularTile(pos[0], pos[1], game);
                nTiles--;
            } 
        }

        // then add connections
        ArrayList<Tile> neighbors;
        int nConn;
        Tile t2;
        for (Tile t: game.getTiles()) {
            neighbors = t.getDisconnectedNeighbors();
            if (neighbors.size()==0) continue;
            nConn = rand.nextInt(neighbors.size()+1);
            
            for (int i=0; i<nConn; ++i) {
            	if (rand.nextFloat() < 0.90) {
                    t2 = neighbors.get(rand.nextInt(neighbors.size()));
                    
                    
                    int dx = t.getX() - t2.getX();
                    int dy = t.getY() - t2.getY();
                    Connection conn=null;
                    if (((dx==0&&(dy==1||dy==-1))||(dy==0&&(dx==1||dx==-1))) && t!=t2 && t!=null && t2!=null) {
                  		conn = new Connection(game);
                  		conn.addTile(t);
                  		conn.addTile(t2);
                  		wasAdded = true;
                  	} 
                    neighbors.remove(t2);                   
                    
                    if (conn!=null && conn.numberOfTiles()!=Connection.maximumNumberOfTiles()) {
                    	System.out.println("Caught an invalid connection");
                    	conn.delete();
                    }
                }
            	
            }  
        }

        //Delete isolated tiles
        for(int i=0; i<game.numberOfTiles(); ++i){
            if(game.getTile(i).getNeighbours(size).size() == 0 && !(game.getTile(i) instanceof  WinTile)) {
            	toc.removeTile(game.getTile(i), game);
            	i--;
            }
        }

        //Ensure wintile is not isolated
        Tile wt = game.getWinTile();
        if (wt.numberOfConnections()==0) {
        	System.out.println("Win tile was an island");
        	neighbors = wt.getDisconnectedNeighbors();
        	Connection conn = new Connection(game);
        	conn.addTile(wt);
        	conn.addTile(neighbors.get(rand.nextInt(neighbors.size())));
        }

        // finally chose the starting tiles
        for (Player p: game.getPlayers()) {
            boolean selected = false;
            Tile t;
            while (!selected) {
                t = game.getTile(rand.nextInt(game.numberOfTiles()));
                if (!(t instanceof WinTile)&&(t.getConnections().size()!=0)) {
                    p.setStartingTile(t);
                    selected = true;
                }
                else if((t instanceof ActionTile)){
                	p.setStartingTile(t);
                	selected= true;
                }
            }
        }
    
        setRandomDeck();
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

    private void startComputerTurn(Game game) {
        CompThread t = new CompThread();
        t.start();
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
    
    private void removeRandomTileCardActionPerformed() {
    	Game game = TileOApplication.getTileO().getCurrentGame();
    	toc.playRemoveRandomTileActionCard(game);
    	renderLayout(game);
    }
    
    private void turnInactiveCardActionPerformed() {
    	Game game = TileOApplication.getTileO().getCurrentGame();
    	toc.playTurnInactiveActionCard(game);
    	renderLayout(game);
    }
    
    private void chooseAdditionalMoveCardActionPerformed(int die) {
    	if (die > 6) die = 6;
    	if (die < 1) die = 1;
    	
    	toc.playChooseAdditionalMoveActionCard(die);
    	possibleTiles = toc.getPossibleTiles();
    	board.setPossibleTiles(possibleTiles);
    	board.setWaitForTile(true);
    	setWaitingFor("move");
    }
    
    private void revealTile(Tile t) {
    	String type = toc.revealTile(t);
    	ArrayList<Tile> tmp = new ArrayList<Tile>();
    	tmp.add(t);
    	board.setPossibleTiles(tmp);
    	setWaitingFor("");
    	actionTipLabel.setText("");
    	actionStatusLabel.setText("Tile is: "+type);
    	revealTileCardButton.setVisible(true);
    }
    
    private void revealTileCardActionPerformed() {
    	board.setPossibleTiles(null);
    	revealTileCardButton.setVisible(false);
    	actionStatusLabel.setText("");
    	toc.playRevealTileActionCard();
    	renderLayout(TileOApplication.getTileO().getCurrentGame());
    }
    
    private void winTileHint(Tile t) {
    	try {
    		boolean hint = toc.winTileHint(t);
    		ArrayList<Tile> tmp = new ArrayList<Tile>();
        	tmp.add(t);
        	board.setPossibleTiles(tmp);
        	setWaitingFor("");
        	actionTipLabel.setText("");
        	if (hint){ 
        		actionStatusLabel.setText("Win tile hint is positive");
        	}
        	else{
        		actionStatusLabel.setText("Win tile hint is negative");
        	}
        	winTileHintCardButton.setVisible(true);
    	}
    	catch (InvalidInputException err) {
    		actionError.setText(err.getMessage());
    	}
    }
    
    private void winTileHintCardActionPerformed() {
    	board.setPossibleTiles(null);
    	winTileHintCardButton.setVisible(false);
    	actionStatusLabel.setText("");
    	toc.playWinTileHintActionCard();
    	renderLayout(TileOApplication.getTileO().getCurrentGame());
    }
    
    private void sendBackToStartCardActionPerformed(Tile t) {
    	try {
    		toc.playSendBackToStartActionCard(t);
    		setWaitingFor("");
    		actionError.setText("");
    		renderLayout(t.getGame());
    	}
    	catch (InvalidInputException err) {
    		actionError.setText(err.getMessage());
    	}
    }

    private void swapPlayerPositionCardActionPerformed(Tile t) {
        try {
            toc.playSwapPositionActionCard(t);
            setWaitingFor("");
            actionError.setText("");
            renderLayout(t.getGame());
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
            removeRandomTileCardSpinner.setValue(0);
            turnInactiveCardSpinner.setValue(0);
            chooseAdditionalCardSpinner.setValue(0);
            revealTileCardSpinner.setValue(0);
            winTileHintCardSpinner.setValue(0);
            sendToStartCardSpinner.setValue(0);
            swapPositionCardSpinner.setValue(0);
            numberOfCardsLabel.setText("0/"+NumberOfCards);
            for (int i=0; i<nPlayers; ++i){
                setComputerRadioButtons[i].setSelected(false);
                computerTypeComboBoxes[i].setVisible(false);
            }
            
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
    	TileOApplication.initDir(TileOApplication.SavedFolder);
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
    	TileOApplication.initDir(TileOApplication.SavedFolder);
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
                n = d.numberOfCardsForType(5);
                removeRandomTileCardSpinner.setValue(n);
                n = d.numberOfCardsForType(6);
                turnInactiveCardSpinner.setValue(n);
                n = d.numberOfCardsForType(7);
                chooseAdditionalCardSpinner.setValue(n);
                n = d.numberOfCardsForType(8);
                revealTileCardSpinner.setValue(n);
                n = d.numberOfCardsForType(10);
                winTileHintCardSpinner.setValue(n);
                n = d.numberOfCardsForType(9);
                sendToStartCardSpinner.setValue(n);
                n = d.numberOfCardsForType(11);
                swapPositionCardSpinner.setValue(n);

                // select radio buttons for computers
                for (int i=0; i<game.numberOfPlayers(); i++) {
                    if (game.getPlayer(i) instanceof ComputerPlayer) { 
                        setComputerRadioButtons[i].setSelected(true);
                        computerTypeComboBoxes[i].setVisible(true);
                    }
                    else {
                        setComputerRadioButtons[i].setSelected(false);
                        computerTypeComboBoxes[i].setVisible(false);
                    }
                }
    			renderLayout(game);
    		}
    		catch (InvalidInputException err) {
    			welError.setText(err.getMessage());
    		}
    	}
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

    public void rmTileSignal(Tile t) {
        removeTileActionPerformed(null, true, t);
    }
    
    public void tileSignal(Tile t) {
    	if (t!=null) {
    		if (waitingFor.equals("rmtile"))
    			removeTileActionPerformed(null, true, t);
    		else if (waitingFor.equals("player1"))
    			setStartingTileActionPerformed(null, true, 1, t);
    		else if (waitingFor.equals("player2"))
    			setStartingTileActionPerformed(null, true, 2, t);
    		else if (waitingFor.equals("player3"))
    			setStartingTileActionPerformed(null, true, 3, t);
    		else if (waitingFor.equals("player4"))
    			setStartingTileActionPerformed(null, true, 4, t);
    		else if (waitingFor.equals("move"))
    			landActionPerformed(null, t);
    		else if (waitingFor.equals("teleportcard"))
    			teleportCardActionPerformed(null, t);
    		else if (waitingFor.equals("reveal"))
    			revealTile(t);
    		else if (waitingFor.equals("sendstart"))
    			sendBackToStartCardActionPerformed(t);
            else if (waitingFor.equals("swapplayer"))
                swapPlayerPositionCardActionPerformed(t);
            else if (waitingFor.equals("winhint"))
            	winTileHint(t);
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
