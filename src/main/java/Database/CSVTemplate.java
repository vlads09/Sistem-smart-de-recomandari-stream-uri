package Database;

import Entities.Entity;
import Entities.Streamers;
import Entities.Streams;
import Entities.Users;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVTemplate extends FileTemplate {

    @Override
    public HashMap<Integer, Entity> readFile(File file, String fileName) {
        try {
            HashMap<Integer, Entity> data = new HashMap<>();
            CSVReader myReader = new CSVReader(new FileReader(file));
            int key = 0;
            String[] line;
            if (fileName.split("/")[4].equals("users.csv")) {
                while ((line = myReader.readNext()) != null) {
                    if (key == 0) {
                        key++;
                        continue;
                    }
                    List<Integer> list = new ArrayList<>();
                    ArrayList<String> listOfStreams = new ArrayList<>(List.of(line[2].replace("\"", "").split(" ")));
                    for (String listOfStream : listOfStreams) {
                        list.add(Integer.valueOf(listOfStream));
                    }
                    Users user = new Users(Integer.parseInt(line[0]), line[1], list);
                    data.put(Integer.parseInt(line[0]), user);
                }
            } else if (fileName.split("/")[4].equals("streamers.csv")) {
                while ((line = myReader.readNext()) != null) {
                    if (key == 0) {
                        key++;
                        continue;
                    }
                    Streamers streamer = new Streamers(Integer.parseInt(line[0]), Integer.parseInt(line[1]), line[2]);
                    data.put(Integer.parseInt(line[1]), streamer);
                }
            } else {
                while ((line = myReader.readNext()) != null) {
                    if (key == 0) {
                        key++;
                        continue;
                    }
                    Streams stream = new Streams(new ArrayList<>(List.of(line)));
                    data.put(Integer.parseInt(line[1]), stream);
                }
            }
            return data;
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
