package Entities;

import java.util.List;

public class Users extends Entity {
    List<Integer> streams;

    public Users(int id, String name, List<Integer> streams) {
        super(id, name);
        this.streams = streams;
    }

    public List<Integer> getStreams() {
        return streams;
    }
}
