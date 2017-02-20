package ca.mcgill.ecse223.tileo.application;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.Game;

public class TileOApplicationTest {

    private static String saveFName = "test";
    private static String loadFName = "savedGames/test.game";

    @Before
    public void setUp() {
        File f = new File(loadFName);
        f.delete();
        TileO t = TileOApplication.getTileO();
        t.delete();
    }

    @Test
    public void cleanFilenameTest() {
    
    
    }

} 
