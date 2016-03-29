package visualAudioManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FavCommand implements Command {

    private String fileName;

    public FavCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("favlist.txt", true)));
            out.println(CurrentDirectory.getInstance().getCurrentDirectory() + "/" + fileName);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
