package visualAudioManager;

public class CommandFactory {
    public static Command newCommand(String input) {
        System.out.println("[DEBUG] Received new command: " + input);
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String args = (parts.length == 2) ? parts[1] : "";

        System.out.println("[DEBUG] Splitted into |" + command + "| and |" + args);
        switch (command) {
            case "cd":
                return new CdCommand(args);
            case "list":
                return new ListCommand(args);
            case "pwd":
                return new PwdCommand();
            case "play":
                return new PlayCommand(args);
            case "info":
                return new InfoCommand(args);
            case "find":
                return new FindCommand(args);
            case "fav":
                return new FavCommand(args);
            default:
                return null;
        }
    }
}
