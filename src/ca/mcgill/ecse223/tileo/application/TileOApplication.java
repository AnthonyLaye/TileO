package ca.mcgill.ecse223.tileo.application;

import ca.mcgill.ecse223.tileo.model.*;

public class TileOApplication {
    
    private static TileO tileo;
    
    public static TileO getTileO() {
        if (tileo==null)
            return new TileO();
        return tileo;
    }
}
