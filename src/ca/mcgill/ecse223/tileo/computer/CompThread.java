package ca.mcgill.ecse223.tileo.computer;

import ca.mcgill.ecse223.tileo.computer.ComputerPlayer;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.application.TileOApplication;

public class CompThread extends Thread {
    public void run() {
        try{sleep(TileOApplication.SLEEP_TIME);}
        catch (Exception err) {}
        Game game = TileOApplication.getTileO().getCurrentGame();
        ComputerPlayer p = (ComputerPlayer)game.getCurrentPlayer();
        switch (game.getMode()) {
            case GAME:
                p.takeTurn();
                break;
            default:
                p.playCard();
        }
        TileOApplication.getTileOPage().renderLayout(game);
    }
}
