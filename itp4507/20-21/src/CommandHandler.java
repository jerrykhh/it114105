
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jerrykwok
 */
public class CommandHandler {

    private LinkedHashMap<String, CommandFactory> mapCmds;

    public CommandHandler() {
        this.mapCmds = new LinkedHashMap<String, CommandFactory>();
    }

    public void regCommand(String key, CommandFactory cmdFactory) {
        mapCmds.put(key, cmdFactory);
    }

    public void printCommandList() {
        System.out.print("Please enter command: [");
        System.out.print(String.join(" | ", mapCmds.keySet()));
        System.out.println("]");
        for (Map.Entry<String, CommandFactory> mapCmd : mapCmds.entrySet()) {
            System.out.print(mapCmd.getKey() + " = " + mapCmd.getValue().getCommnadDesc());
        }
        System.out.println();
    }
    
    public boolean run(String cmd) {
        CommandFactory commandFactory = mapCmds.get(cmd);
        if (commandFactory != null) {
            Command command = commandFactory.createCommand();
            command.execute();
            
            return true;
        }

        return false;
    }
}
