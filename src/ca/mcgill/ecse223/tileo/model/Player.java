/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

// line 3 "../../../../../model.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private String name;

  //Player Associations
  private PlayerPiece playerPiece;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aName)
  {
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public PlayerPiece getPlayerPiece()
  {
    return playerPiece;
  }

  public boolean hasPlayerPiece()
  {
    boolean has = playerPiece != null;
    return has;
  }

  public boolean setPlayerPiece(PlayerPiece aNewPlayerPiece)
  {
    boolean wasSet = false;
    if (playerPiece != null && !playerPiece.equals(aNewPlayerPiece) && equals(playerPiece.getPlayer()))
    {
      //Unable to setPlayerPiece, as existing playerPiece would become an orphan
      return wasSet;
    }

    playerPiece = aNewPlayerPiece;
    Player anOldPlayer = aNewPlayerPiece != null ? aNewPlayerPiece.getPlayer() : null;

    if (!this.equals(anOldPlayer))
    {
      if (anOldPlayer != null)
      {
        anOldPlayer.playerPiece = null;
      }
      if (playerPiece != null)
      {
        playerPiece.setPlayer(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    PlayerPiece existingPlayerPiece = playerPiece;
    playerPiece = null;
    if (existingPlayerPiece != null)
    {
      existingPlayerPiece.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "playerPiece = "+(getPlayerPiece()!=null?Integer.toHexString(System.identityHashCode(getPlayerPiece())):"null")
     + outputString;
  }
}