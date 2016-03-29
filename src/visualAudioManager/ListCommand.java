package visualAudioManager;

import java.io.File;

public class ListCommand implements Command {

    public String listDirectories;

    public ListCommand(String listDirectories) {
        this.listDirectories = listDirectories;
    }

    @Override
    public void execute() {
        System.out.println("[DEBUG] Executing ls..");
        File dir = new File(CurrentDirectory.getInstance().getCurrentDirectory());
        System.out.println("[DEBUG] " + dir);
        File[] listDir = dir.listFiles();

        if (listDir != null) {
            System.out.println("Directories:");

            for (File aListDir : listDir) {
                if (aListDir.isDirectory()) {
                    System.out.println(aListDir.getName());
                }
            }

            System.out.println("Files: ");

            for (File aListDir : listDir) {
                if (aListDir.isFile()) {
                    if (aListDir.getName().contains(".wav") || aListDir.getName().contains(".mp3") || aListDir.getName().contains(".flac")) {
                        System.out.println(aListDir.getName());
                    }
                }
            }
        }
    }
}
