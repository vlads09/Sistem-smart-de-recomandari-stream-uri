package Entities;

import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Streams extends Entity {
    private final int streamType;
    private final int streamGenre;
    private long noOfStreams;
    private final int streamerId;
    private final long length;
    private final long dateAdded;

    public Streams(ArrayList<String> info) {
        super(Integer.parseInt(info.get(1)), info.get(7));
        this.streamType = Integer.parseInt(info.get(0));
        this.streamGenre = Integer.parseInt(info.get(2));
        this.noOfStreams = Long.parseLong(info.get(3));
        this.streamerId = Integer.parseInt(info.get(4));
        this.length = Long.parseLong(info.get(5));
        this.dateAdded = Long.parseLong(info.get(6));
    }

    public Streams(int streamerId, int streamType, int id, int streamGenre, long length, String name) {
        super(id, name);
        this.streamerId = streamerId;
        this.streamType = streamType;
        this.length = length;
        this.streamGenre = streamGenre;
        this.dateAdded = Instant.now().getEpochSecond();
    }

    public void setNoOfStreams(long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public int getStreamType() {
        return streamType;
    }

    public int getStreamGenre() {
        return streamGenre;
    }

    public long getNoOfStreams() {
        return noOfStreams;
    }

    public int getStreamerId() {
        return streamerId;
    }

    public long getLength() {
        return length;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public JsonObject format(String name) {
        JsonObject obj = new JsonObject();
        obj.addProperty("id", String.valueOf(this.getId()));
        obj.addProperty("name", this.getName());
        obj.addProperty("streamerName", name);
        obj.addProperty("noOfListenings", String.valueOf(this.getNoOfStreams()));
        String minutes = String.valueOf(this.getLength() / 60);
        if (minutes.length() == 1) minutes = "0" + minutes;
        String seconds = String.valueOf(this.getLength() % 60);
        if (seconds.length() == 1) seconds = "0" + seconds;
        if (Integer.parseInt(minutes) >= 60) {
            String hours = String.valueOf(this.getLength() / 3600);
            if (hours.length() == 1) hours = "0" + hours;
            minutes = String.valueOf((this.getLength() % 3600) / 60);
            if (minutes.length() == 1) minutes = "0" + minutes;
            obj.addProperty("length", hours + ":" + minutes + ":" + seconds);
        } else obj.addProperty("length", minutes + ":" + seconds);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date(this.getDateAdded() * 1000);
        obj.addProperty("dateAdded", formatter.format(date));
        return obj;
    }
}
