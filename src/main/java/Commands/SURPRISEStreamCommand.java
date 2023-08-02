package Commands;

import Comparators.ComparatorDate;
import Entities.Entity;
import Entities.Streams;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SURPRISEStreamCommand implements Commands {
    private final List<Integer> streams;
    private final HashMap<Integer, Entity> dataStreams;
    private final String type;
    private final HashMap<Integer, Entity> dataStreamers;

    public SURPRISEStreamCommand(List<Integer> streams, HashMap<Integer, Entity> dataStreams, String type,
                                 HashMap<Integer, Entity> dataStreamers) {
        this.streams = streams;
        this.dataStreams = dataStreams;
        this.type = type;
        this.dataStreamers = dataStreamers;
    }

    public void surpriseStream() {
        ArrayList<Integer> streamersIds = new ArrayList<>();
        JsonArray surprises = new JsonArray(3);
        for (Integer streamId : streams) {
            Integer streamerId = ((Streams) dataStreams.get(streamId)).getStreamerId();
            if (!streamersIds.contains(streamerId)) streamersIds.add(streamerId);
        }

        ArrayList<Streams> possibleSurprises = new ArrayList<>();
        for (Map.Entry<Integer, ? extends Entity> stream : dataStreams.entrySet()) {
            switch (type) {
                case "SONG":
                    if (!streamersIds.contains(((Streams) stream.getValue()).getStreamerId()) &&
                            ((Streams) stream.getValue()).getStreamType() == 1) {
                        possibleSurprises.add((Streams) stream.getValue());
                    }
                    break;
                case "PODCAST":
                    if (!streamersIds.contains(((Streams) stream.getValue()).getStreamerId()) &&
                            ((Streams) stream.getValue()).getStreamType() == 2) {
                        possibleSurprises.add((Streams) stream.getValue());
                    }
                    break;
                case "AUDIOBOOK":
                    if (!streamersIds.contains(((Streams) stream.getValue()).getStreamerId()) &&
                            ((Streams) stream.getValue()).getStreamType() == 3) {
                        possibleSurprises.add((Streams) stream.getValue());
                    }
                    break;
                default:
                    break;
            }
        }
        possibleSurprises.sort(new ComparatorDate());

        for (int i = 0; i < possibleSurprises.size(); i++) {
            if (i == 3) break;
            for (Map.Entry<Integer, ? extends Entity> streamer : dataStreamers.entrySet()) {
                if (streamer.getValue().getId() == possibleSurprises.get(i).getStreamerId()) {
                    String artistName = streamer.getValue().getName();
                    surprises.add(possibleSurprises.get(i).format(artistName));
                }
            }
        }
        System.out.println(surprises);
    }

    @Override
    public void execute() {
        surpriseStream();
    }
}
