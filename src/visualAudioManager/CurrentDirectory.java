package visualAudioManager;

/**
 * Created by silvia on 29.03.2016.
 */
public class CurrentDirectory {
    private String currentDirectory;
    private static CurrentDirectory ourInstance = new CurrentDirectory();

    public static CurrentDirectory getInstance() {
        return ourInstance;
    }
    private CurrentDirectory() {
        currentDirectory = System.getProperty("user.dir");
    }
    public String getCurrentDirectory() {
        return currentDirectory;
    }
    public void setCurrentDirectory(String directory) {
        this.currentDirectory = directory;
        System.out.println("[DEBUG] Changing current directory to: " + directory);
    }
}
