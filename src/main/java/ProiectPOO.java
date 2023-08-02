import Database.DataFactory;
import Entities.Entity;

import java.util.HashMap;

public class ProiectPOO {

    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Nothing to read here");
        } else if (args.length != 4) {
            System.out.println("You need 4 arguments");
        } else {
            String path = "src/main/resources/";
            DataFactory dataFactory = DataFactory.instance();
            HashMap<Integer, Entity> dataStreamers = dataFactory.createHashData(path + args[0]);
            HashMap<Integer, Entity> dataStreams = dataFactory.createHashData(path + args[1]);
            HashMap<Integer, Entity> dataUsers = dataFactory.createHashData(path + args[2]);
            HashMap<Integer, Entity> commands = dataFactory.createHashData(path + args[3]);

            Facade facade = new Facade(dataStreamers, dataStreams, dataUsers, commands);
            facade.run();
        }
    }
}
