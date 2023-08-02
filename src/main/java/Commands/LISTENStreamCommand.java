package Commands;

import Entities.Entity;
import Entities.Streams;
import Entities.Users;

import java.util.ArrayList;
import java.util.HashMap;

public class LISTENStreamCommand implements Commands {
    private final ArrayList<String> stream;
    private final HashMap<Integer, Entity> dataStreams;
    private final HashMap<Integer, Entity> dataUsers;

    public LISTENStreamCommand(ArrayList<String> stream, HashMap<Integer, Entity> dataStreams, HashMap<Integer, Entity> dataUsers) {
        this.stream = stream;
        this.dataStreams = dataStreams;
        this.dataUsers = dataUsers;
    }

    public void listenStream() {
        int userId = Integer.parseInt(stream.get(0));
        int streamId = Integer.parseInt(stream.get(2));
        if (dataUsers.containsKey(userId)) {
            if (!((Users) dataUsers.get(userId)).getStreams().contains(streamId)) {
                ((Users) dataUsers.get(userId)).getStreams().add(streamId);
            }
            ((Streams) dataStreams.get(streamId)).setNoOfStreams(((Streams) dataStreams.get(streamId)).getNoOfStreams() + 1);
        }
    }

    @Override
    public void execute() {
        listenStream();
    }
}
