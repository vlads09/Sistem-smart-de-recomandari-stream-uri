package Commands;

import Entities.Entity;
import Entities.Streams;

import java.util.ArrayList;
import java.util.HashMap;

public class ADDStreamCommand implements Commands {

    private final ArrayList<String> stream;

    private final HashMap<Integer, Entity> dataStreams;

    public ADDStreamCommand(ArrayList<String> stream, HashMap<Integer, Entity> dataStreams) {
        this.stream = stream;
        this.dataStreams = dataStreams;
    }

    public void addStream() {
        String name = "";
        for (int i = 6; i < stream.size(); i++) {
            if (i == stream.size() - 1) {
                name += stream.get(i);
                break;
            }
            name += stream.get(i) + " ";
        }
        dataStreams.put(Integer.parseInt(stream.get(3)), new Streams(Integer.parseInt(stream.get(0)),
                Integer.parseInt(stream.get(2)), Integer.parseInt(stream.get(3)), Integer.parseInt(stream.get(4)), Long.parseLong(stream.get(5)),
                name));
    }

    public void execute() {
        addStream();
    }
}
