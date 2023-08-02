package Commands;

import Comparators.ComparatorListenings;
import Entities.Entity;
import Entities.Streams;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RECOMMENDStreamCommand implements Commands {
    private final List<Integer> streams;
    private final HashMap<Integer, Entity> dataStreams;
    private final String type;
    private final HashMap<Integer, Entity> dataStreamers;

    public RECOMMENDStreamCommand(List<Integer> streams, HashMap<Integer, Entity> dataStreams, String type,
                                  HashMap<Integer, Entity> dataStreamers) {
        this.streams = streams;
        this.dataStreams = dataStreams;
        this.type = type;
        this.dataStreamers = dataStreamers;
    }

    public void recommendStream() {
        ArrayList<Integer> streamersIds = new ArrayList<>();
        JsonArray recommendations = new JsonArray(5);
        for (Integer streamId : streams) {
            Integer streamerId = ((Streams) dataStreams.get(streamId)).getStreamerId();
            if (!streamersIds.contains(streamerId)) streamersIds.add(streamerId);
        }
        ArrayList<Streams> possibleRecommendations = new ArrayList<>();

        for (Integer streamerId : streamersIds) {
            for (Map.Entry<Integer, ? extends Entity> stream : dataStreams.entrySet()) {
                switch (type) {
                    case "SONG":
                        if (((Streams) stream.getValue()).getStreamerId() == streamerId && !streams.contains(stream.getKey()) &&
                                ((Streams) stream.getValue()).getStreamType() == 1) {
                            possibleRecommendations.add((Streams) stream.getValue());
                        }
                        break;
                    case "PODCAST":
                        if (((Streams) stream.getValue()).getStreamerId() == streamerId && !streams.contains(stream.getKey()) &&
                                ((Streams) stream.getValue()).getStreamType() == 2) {
                            possibleRecommendations.add((Streams) stream.getValue());
                        }
                        break;
                    case "AUDIOBOOK":
                        if (((Streams) stream.getValue()).getStreamerId() == streamerId && !streams.contains(stream.getKey()) &&
                                ((Streams) stream.getValue()).getStreamType() == 3) {
                            possibleRecommendations.add((Streams) stream.getValue());
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        //am toate melodiile neascultate de la artisti ascultati
        possibleRecommendations.sort(new ComparatorListenings());

        for (int i = 0; i < possibleRecommendations.size(); i++) {
            if (i == 5) break;
            for (Integer streamerId : streamersIds) {
                if (possibleRecommendations.get(i).getStreamerId() == streamerId) {
                    String artistName = dataStreamers.get(streamerId).getName();
                    recommendations.add(possibleRecommendations.get(i).format(artistName));
                }
            }
        }
        System.out.println(recommendations);
    }

    @Override
    public void execute() {
        recommendStream();
    }
}
