package Database;

import Entities.Command;
import Entities.Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TXTTemplate extends FileTemplate {

    @Override
    public HashMap<Integer, Entity> readFile(File file, String fileName) {
        HashMap<Integer, Entity> data = new HashMap<>();
        try {
            Scanner myReader = new Scanner(file);
            int key = 0;
            while (myReader.hasNext()) {
                String[] dataC = myReader.nextLine().split(" ");
                Command command = new Command(Integer.parseInt(dataC[0]), dataC[1]);
                command.setCommands(new ArrayList<>(List.of(dataC)));
                data.put(key++, command);
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
