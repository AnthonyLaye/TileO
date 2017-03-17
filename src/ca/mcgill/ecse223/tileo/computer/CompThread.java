package ca.mcgill.ecse223.tileo.computer;

import ca.mcgill.ecse223.tileo.computer.ComputerPlayer;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.application.TileOApplication;

public class CompThread extends Thread {
    public void run() {
        try{sleep(1000);}
        catch (Exception err) {}
        Game game = TileOApplication.getTileO().getCurrentGame();
        ComputerPlayer p = (ComputerPlayer)game.getCurrentPlayer();
        p.takeTurn();
        TileOApplication.getTileOPage().renderLayout(game);
    }
}
