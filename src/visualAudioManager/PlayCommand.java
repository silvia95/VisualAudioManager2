package visualAudioManager;

import com.rometools.rome.feed.module.DCModule;
import com.sun.xml.internal.ws.util.xml.CDATA;

import java.awt.*;
import java.io.File;

public class PlayCommand implements Command {

    private String fileName;

    public PlayCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        String filePath = CurrentDirectory.getInstance().getCurrentDirectory() + "/" + fileName;
        File file = new File(filePath);
        try {
            Desktop.getDesktop().open(file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
