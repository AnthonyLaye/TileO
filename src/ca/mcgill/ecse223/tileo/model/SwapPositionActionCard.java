/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-b818740 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;
import ca.mcgill.ecse223.tileo.controller.*;

// line 1496 "../../../../../../../../ump/tmp343778/model.ump"
public class SwapPositionActionCard extends ActionCard {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public SwapPositionActionCard(String aInstructions, Deck aDeck) {
        super(aInstructions, aDeck);
    }

    //------------------------
    // INTERFACE
    //------------------------

    public void delete() {
        super.delete();
    }

    public Game.Mode getActionCardMode() {
        return Game.Mode.GAME_SWAPPOSITIONACTIONCARD;
    }

    public boolean play(Tile t, ArrayList<Player> otherPlayers, Player currentPlayer) {
        for (Player p : otherPlayers) {
            if (t == p.getCurrentTile()) {

                Tile firstTile = currentPlayer.getCurrentTile();

                p.setCurrentTile(firstTile);
                currentPlayer.setCurrentTile(t);
                return true;
            }
        }
        return false;
    }
}



