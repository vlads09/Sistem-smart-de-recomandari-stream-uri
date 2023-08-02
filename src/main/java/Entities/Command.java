package Entities;

import java.util.ArrayList;

public class Command extends Entity {
    private ArrayList<String> commandDescription = new ArrayList<>();

    public Command(int id, String name) {
        super(id, name);
    }

    public ArrayList<String> getCommands() {
        return commandDescription;
    }

    public void setCommands(ArrayList<String> commands) {
        this.commandDescription = commands;
    }
}
