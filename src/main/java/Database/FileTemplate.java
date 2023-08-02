//template pattern
package Database;

import Entities.Entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;


public abstract class FileTemplate {

    public final HashMap<Integer, Entity> processFile(String fileName) {
        File file = openFile(fileName);
        return readFile(file, fileName);
    }

    public File openFile(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) return file;
            else throw new FileNotFoundException("File does not exist!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public abstract HashMap<Integer, Entity> readFile(File file, String fileName);

}

