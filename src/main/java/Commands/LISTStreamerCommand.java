package Commands;

import Entities.Entity;
import Entities.Streams;
import com.google.gson.JsonArray;

import java.util.HashMap;
import java.util.Map;

public class LISTStreamerCommand implements Commands {

    private final int streamerId;

    private final String streamerName;

    private final HashMap<Integer, Entity> dataStreams;

    public LISTStreamerCommand(int streamerId, String streamerName, HashMap<Integer, Entity> dataStreams) {
        this.streamerId = streamerId;
        this.streamerName = streamerName;
        this.dataStreams = dataStreams;
    }

    public void list() {
        JsonArray streamersStreams = new JsonArray();
        for (Map.Entry<Integer, Entity> stream : dataStreams.entrySet()) {
            if (streamerId == ((Streams) stream.getValue()).getStreamerId()) {
                streamersStreams.add(((Streams) stream.getValue()).format(streamerName));
            }
        }
        System.out.println(streamersStreams);
    }

    public void execute() {
        list();
    }
}
