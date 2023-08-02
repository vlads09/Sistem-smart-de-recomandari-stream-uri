//singleton + factory patterns
package Database;

import Entities.Entity;

import java.util.HashMap;

//factory + singleton
public class DataFactory {
    private static DataFactory dataFactory;

    private DataFactory() {
    }

    public static DataFactory instance() {
        if (dataFactory == null) dataFactory = new DataFactory();
        return dataFactory;
    }

    public HashMap<Integer, Entity> createHashData(String filename) {
        switch (filename.split("\\.")[1]) {
            case "csv": {
                return new CSVTemplate().processFile(filename);
            }
            case "txt": {
                return new TXTTemplate().processFile(filename);
            }
            default:
                try {
                    throw new UnknownFile("Unknown file type");
                } catch (UnknownFile e) {
                    System.out.println(e.getError());
                }
        }
        return new HashMap<>();
    }
}

