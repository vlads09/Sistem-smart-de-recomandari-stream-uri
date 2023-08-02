package Commands;

import Entities.Entity;
import Entities.Streams;

import java.util.ArrayList;
import java.util.HashMap;

public class DELETEStreamCommand implements Commands {
    private final ArrayList<String> stream;

    public DELETEStreamCommand(ArrayList<String> stream, HashMap<Integer, Entity> dataStreams) {
        this.stream = stream;
        this.dataStreams = dataStreams;
    }

    private final HashMap<Integer, Entity> dataStreams;

    public void deleteStream() {
        int streamId = Integer.parseInt(stream.get(2));
        int streamerId = Integer.parseInt(stream.get(0));
        if (dataStreams.containsKey(Integer.parseInt(stream.get(2))) &&
                ((Streams) dataStreams.get(streamId)).getStreamerId() == streamerId) {
            dataStreams.remove(streamId);
        }
    }

    @Override
    public void execute() {
        deleteStream();
    }
}
