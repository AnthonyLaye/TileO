
# TileO 
Group14

The main method is in src/ca/mcgill/ecse223/tileo/application/TileOApplication

### Design mode
In design mode when you click, for example, on the Add Tile button, you can add as many tile without having to reclick on the button.
It's the same principle for the other buttons

### Add a new AI behaviour
- 1. Copy (not overwrite!) the file TemplatePlayer.java in ca.mcgill.ecse223.tileo.computer and change its name to whatever you want. Best is something like NoobPlayer.java or TurboPlayer.java
- 2. Change the name of the class to the one you choose and implement the three methods in
  it, make sure not to change the method declarations.
- 3. In Game.java import your new file and add an if statement for your new type in
  swapPlayerForComputer(...). If your new type is NoobPlayer.java, the check should be:
  type.equals("Noob"). Then copy the code from inside another if statement and just change
  the type of player.
- 4. In TileOPage.java, around line 90, add a new String to the computerTypes array. To
  continue with the example, you would just add "Noob" inside the curly braces.
- 5. Go back to step 4 and make sure you spelled the String exactly the same as you did
  for your check in step 3.
- 6. That's it, the rest is done for you.

### Umple
# Do not generate code with Umple, it's not up to date 
##### Changes to make when generating code from Umple
- Game: public Tile addTile(int aX, int aY) -> commented out
- Deck: public ActionCard addCard(String aInstructions) -> commented out
- Change TileOController's package to ca.mcgill.ecse223.tileo.controller and move it
- TileOController: add throws InvalidInputException to startGame(), 
  selectNewTile(), land(), playRollDieActionCard(), playConnectTilesActionCard(),
  playRemoveConnectionActionCard(), playTeleportActionCard(), playLoseTurnActionCard()
- ActionTile: change boolean land() to void land(), remove class abstract 

### Contributors
- Anthony Laye
- Sam Cleland
- James Tang
- Younes Boubekeur
- Gabriel Turenne
- Vincent d'Orsonnens
