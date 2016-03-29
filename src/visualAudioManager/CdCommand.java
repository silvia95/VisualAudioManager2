package visualAudioManager;
import org.w3c.dom.CDATASection;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class CdCommand implements Command {
    private String accessedDirectory;

    public CdCommand(String accessedDirectory) {
        this.accessedDirectory = accessedDirectory;
    }

    @Override
    public void execute() {
        if (accessedDirectory.equals("")) {
            System.out.println("[DEBUG] Changing to home");
            CurrentDirectory.getInstance().setCurrentDirectory(System.getProperty("user.dir"));
        } else if (accessedDirectory.charAt(0) == '/') {
            System.out.println("[DEBUG] Absolute path");
            CurrentDirectory.getInstance().setCurrentDirectory(accessedDirectory);
        } else {
            System.out.println("[DEBUG] Relative path");
            List<String> directories = new LinkedList<>(Arrays.asList(accessedDirectory.split("/")));
            List<String> splitDirectories = new LinkedList<>(Arrays.asList(CurrentDirectory.getInstance().getCurrentDirectory().split("/")));

            // Remove space inserted by split due to initial /
            splitDirectories.remove(0);

            System.out.print("[DEBUG] Parameters: ");
            for (String directory :directories)
                System.out.print(directory + " ");
            System.out.println();
            System.out.print("[DEBUG] PWD: ");
            for (String directory :splitDirectories)
                System.out.print(directory + " ");
            System.out.println();


            for (String directory : directories) {
                if (!directory.equals("..")) {
                    splitDirectories.add(directory);
                } else
                    splitDirectories.remove(splitDirectories.size() - 1);
            }

            StringBuilder builder = new StringBuilder();
            for (String directory : splitDirectories) {
                builder.append("/");
                builder.append(directory);
            }

            CurrentDirectory.getInstance().setCurrentDirectory(builder.toString());
        }
    }
}
