//facade pattern

import Commands.*;
import Entities.Command;
import Entities.Entity;
import Entities.Users;

import java.util.ArrayList;
import java.util.HashMap;

public class Facade {
    private final HashMap<Integer, Entity> dataStreamers;
    private final HashMap<Integer, Entity> dataStreams;
    private final HashMap<Integer, Entity> dataUsers;
    private final HashMap<Integer, Entity> commands;

    public Facade(HashMap<Integer, Entity> dataStreamers, HashMap<Integer, Entity> dataStreams,
                  HashMap<Integer, Entity> dataUsers, HashMap<Integer, Entity> commands) {
        this.dataStreamers = dataStreamers;
        this.dataStreams = dataStreams;
        this.dataUsers = dataUsers;
        this.commands = commands;
    }

    public void run() {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals("LIST")) {
                for (int j = 0; j < dataUsers.size(); j++) {
                    if (dataUsers.containsKey(commands.get(i).getId())) {
                        ArrayList<String> streamsId = new ArrayList<>();
                        for (int k = 0; k < ((Users) dataUsers.get(commands.get(i).getId())).getStreams().size(); k++) {
                            streamsId.add(String.valueOf(((Users) dataUsers.get(commands.get(i).getId())).
                                    getStreams().get(k)));
                        }
                        new LISTUserCommand(streamsId, dataStreams, dataStreamers).execute();
                        break;
                    }
                }

                for (int j = 0; j < dataStreamers.size(); j++) {
                    if (dataStreamers.containsKey(commands.get(i).getId())) {
                        new LISTStreamerCommand(dataStreamers.get(commands.get(i).getId()).getId(),
                                dataStreamers.get(commands.get(i).getId()).getName(),
                                dataStreams).execute();
                        break;
                    }
                }
            } else if (commands.get(i).getName().equals("ADD")) {
                new ADDStreamCommand(((Command) commands.get(i)).getCommands(), dataStreams).execute();
            } else if (commands.get(i).getName().equals("DELETE")) {
                new DELETEStreamCommand(((Command) commands.get(i)).getCommands(), dataStreams).execute();
            } else if (commands.get(i).getName().equals("LISTEN")) {
                new LISTENStreamCommand(((Command) commands.get(i)).getCommands(), dataStreams, dataUsers).execute();
            } else if (commands.get(i).getName().equals("RECOMMEND")) {
                if (dataUsers.containsKey(commands.get(i).getId())) {
                    new RECOMMENDStreamCommand(((Users) dataUsers.get(commands.get(i).getId())).getStreams(),
                            dataStreams, ((Command) commands.get(i)).getCommands().get(2), dataStreamers).execute();
                }
            } else if (commands.get(i).getName().equals("SURPRISE") &&
                    dataUsers.containsKey(commands.get(i).getId())) {
                new SURPRISEStreamCommand(((Users) dataUsers.get(commands.get(i).getId())).getStreams(),
                        dataStreams, ((Command) commands.get(i)).getCommands().get(2), dataStreamers).execute();
            }
        }
    }
}
