package Commands;

import Entities.Entity;
import Entities.Streams;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.HashMap;

public class LISTUserCommand implements Commands {
    private final ArrayList<String> streamsId;

    private final HashMap<Integer, Entity> dataStreams;

    private final HashMap<Integer, Entity> dataStreamers;

    public LISTUserCommand(ArrayList<String> streamsId, HashMap<Integer, Entity> streams,
                           HashMap<Integer, Entity> streamers) {
        this.streamsId = streamsId;
        this.dataStreams = streams;
        this.dataStreamers = streamers;
    }

    public void list() {
        JsonArray usersStreams = new JsonArray();
        for (String s : streamsId) {
            if (dataStreams.containsKey(Integer.parseInt(s))) {
                int streamerId = ((Streams) dataStreams.get(Integer.parseInt(s))).getStreamerId();
                if (dataStreamers.containsKey(streamerId)) {
                    String streamerName = dataStreamers.get(streamerId).getName();
                    usersStreams.add(((Streams) dataStreams.get(Integer.parseInt(s))).format(streamerName));
                }
            }
        }
        System.out.println(usersStreams);
    }

    public void execute() {
        list();
    }
}
