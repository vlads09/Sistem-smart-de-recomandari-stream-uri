package Entities;


public class Streamers extends Entity {
    private final int streamerType;

    public Streamers(int streamerType, int id, String name) {
        super(id, name);
        this.streamerType = streamerType;
    }

    public int getStreamerType() {
        return streamerType;
    }
}
