package ca.mcgill.ecse223.tileo.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ca.mcgill.ecse223.tileo.model.Game;

public class PersistenceObjectStream {
    
    private static String filename;

    public static void serialize(Game game) {
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(filename);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(game);
            objOut.close();
            fileOut.close();
        } catch (Exception e) {
            throw new RuntimeException("Could not save game data to '"+filename+"'");
        }
    }

    public static Object deserialize() {
        Object obj = null;
        ObjectInputStream objIn;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            objIn = new ObjectInputStream(fileIn);
            obj = objIn.readObject();
            objIn.close();
            fileIn.close();
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }

    public static void setFilename(String newFilename) {
        filename = newFilename;
    }

}
