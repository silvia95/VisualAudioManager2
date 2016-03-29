package visualAudioManager;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindCommand implements Command {
    private String name;

    public FindCommand(String name) {
        this.name = name;
    }

    public void searchFile(String currentDir, String pattern){
        try {
            Pattern filePattern = Pattern.compile(pattern);
            File dir = new File(currentDir);
            File[] listDir = dir.listFiles();

            if (listDir != null) {
                for (File aListDir : listDir) {
                    if (aListDir.isFile()) {
                        Matcher isInPattern = filePattern.matcher(aListDir.getName());
                        if (isInPattern.find()) {
                            System.out.println("File " + aListDir.getName() + " was found in " + currentDir);
                            return;
                        }
                    }
                    if (aListDir.isDirectory())
                        searchFile(aListDir.getPath(), pattern);
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void execute() {
        searchFile(CurrentDirectory.getInstance().getCurrentDirectory(), name);
    }
}
