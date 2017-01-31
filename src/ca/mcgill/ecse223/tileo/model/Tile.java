/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 26 "../../../../../model.ump"
public class Tile
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final boolean ISWIN = false;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tile Attributes
  private int x;
  private int y;
  private boolean isActivated;

  //Tile Associations
  private PlayerPiece playerPiece;
  private Layout layout;
  private List<Connection> connections;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tile(int aX, int aY, boolean aIsActivated, Layout aLayout)
  {
    x = aX;
    y = aY;
    isActivated = aIsActivated;
    boolean didAddLayout = setLayout(aLayout);
    if (!didAddLayout)
    {
      throw new RuntimeException("Unable to create tile due to layout");
    }
    connections = new ArrayList<Connection>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setX(int aX)
  {
    boolean wasSet = false;
    x = aX;
    wasSet = true;
    return wasSet;
  }

  public boolean setY(int aY)
  {
    boolean wasSet = false;
    y = aY;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsActivated(boolean aIsActivated)
  {
    boolean wasSet = false;
    isActivated = aIsActivated;
    wasSet = true;
    return wasSet;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public boolean getIsActivated()
  {
    return isActivated;
  }

  public boolean isIsActivated()
  {
    return isActivated;
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

  public Layout getLayout()
  {
    return layout;
  }

  public Connection getConnection(int index)
  {
    Connection aConnection = connections.get(index);
    return aConnection;
  }

  public List<Connection> getConnections()
  {
    List<Connection> newConnections = Collections.unmodifiableList(connections);
    return newConnections;
  }

  public int numberOfConnections()
  {
    int number = connections.size();
    return number;
  }

  public boolean hasConnections()
  {
    boolean has = connections.size() > 0;
    return has;
  }

  public int indexOfConnection(Connection aConnection)
  {
    int index = connections.indexOf(aConnection);
    return index;
  }

  public boolean setPlayerPiece(PlayerPiece aNewPlayerPiece)
  {
    boolean wasSet = false;
    if (playerPiece != null && !playerPiece.equals(aNewPlayerPiece) && equals(playerPiece.getTile()))
    {
      //Unable to setPlayerPiece, as existing playerPiece would become an orphan
      return wasSet;
    }

    playerPiece = aNewPlayerPiece;
    Tile anOldTile = aNewPlayerPiece != null ? aNewPlayerPiece.getTile() : null;

    if (!this.equals(anOldTile))
    {
      if (anOldTile != null)
      {
        anOldTile.playerPiece = null;
      }
      if (playerPiece != null)
      {
        playerPiece.setTile(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setLayout(Layout aLayout)
  {
    boolean wasSet = false;
    if (aLayout == null)
    {
      return wasSet;
    }

    Layout existingLayout = layout;
    layout = aLayout;
    if (existingLayout != null && !existingLayout.equals(aLayout))
    {
      existingLayout.removeTile(this);
    }
    layout.addTile(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfConnections()
  {
    return 0;
  }

  public static int maximumNumberOfConnections()
  {
    return 4;
  }

  public boolean addConnection(Connection aConnection)
  {
    boolean wasAdded = false;
    if (connections.contains(aConnection)) { return false; }
    if (numberOfConnections() >= maximumNumberOfConnections())
    {
      return wasAdded;
    }

    connections.add(aConnection);
    if (aConnection.indexOfTile(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aConnection.addTile(this);
      if (!wasAdded)
      {
        connections.remove(aConnection);
      }
    }
    return wasAdded;
  }

  public boolean removeConnection(Connection aConnection)
  {
    boolean wasRemoved = false;
    if (!connections.contains(aConnection))
    {
      return wasRemoved;
    }

    int oldIndex = connections.indexOf(aConnection);
    connections.remove(oldIndex);
    if (aConnection.indexOfTile(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aConnection.removeTile(this);
      if (!wasRemoved)
      {
        connections.add(oldIndex,aConnection);
      }
    }
    return wasRemoved;
  }

  public boolean addConnectionAt(Connection aConnection, int index)
  {  
    boolean wasAdded = false;
    if(addConnection(aConnection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnections()) { index = numberOfConnections() - 1; }
      connections.remove(aConnection);
      connections.add(index, aConnection);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveConnectionAt(Connection aConnection, int index)
  {
    boolean wasAdded = false;
    if(connections.contains(aConnection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnections()) { index = numberOfConnections() - 1; }
      connections.remove(aConnection);
      connections.add(index, aConnection);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addConnectionAt(aConnection, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    PlayerPiece existingPlayerPiece = playerPiece;
    playerPiece = null;
    if (existingPlayerPiece != null)
    {
      existingPlayerPiece.delete();
    }
    Layout placeholderLayout = layout;
    this.layout = null;
    placeholderLayout.removeTile(this);
    ArrayList<Connection> copyOfConnections = new ArrayList<Connection>(connections);
    connections.clear();
    for(Connection aConnection : copyOfConnections)
    {
      if (aConnection.numberOfTiles() <= Connection.minimumNumberOfTiles())
      {
        aConnection.delete();
      }
      else
      {
        aConnection.removeTile(this);
      }
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "," +
            "isActivated" + ":" + getIsActivated()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "playerPiece = "+(getPlayerPiece()!=null?Integer.toHexString(System.identityHashCode(getPlayerPiece())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "layout = "+(getLayout()!=null?Integer.toHexString(System.identityHashCode(getLayout())):"null")
     + outputString;
  }
}