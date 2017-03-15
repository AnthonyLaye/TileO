#TileO 
Group14

The main method is in src/ca/mcgill/ecse223/tileo/application/TileOApplication

### Design mode
In design mode when you click, for example, on the Add Tile button, you can add as many tile without having to reclick on the button.
It's the same principle for the other buttons

### Umple

##### Changes to make when generating code from Umple
- Game: public Tile addTile(int aX, int aY) -> commented out
- Deck: public ActionCard addCard(String aInstructions) -> commented out
- Change TileOController's package to ca.mcgill.ecse223.tileo.controller and move it
- TileOController: add throws InvalidInputException to startGame(), 
  selectNewTile(), land(), playRollDieActionCard(), playConnectTilesActionCard(),
  playRemoveConnectionActionCard(), playTeleportActionCard(), playLoseTurnActionCard()
- ActionTile: change boolean land() to void land(), remove class abstract 

##Contributors
- Anthony Laye
- Sam Cleland
- James Tang
- Younes Boubekeur
- Gabriel Turenne
- Vincent d'Orsonnens
