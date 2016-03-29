package visualAudioManager;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by silvia on 29.03.2016.
 */
public class InfoCommand implements Command {

    private String fileName;

    public InfoCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        String filePath = CurrentDirectory.getInstance().getCurrentDirectory() + "/" + fileName;
        try {
            InputStream input = new FileInputStream(new File(filePath));
            DefaultHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Mp3Parser parser = new Mp3Parser();
            ParseContext parseContext = new ParseContext();
            parser.parse(input, handler, metadata, parseContext);
            input.close();

            System.out.print("[DEBUG] Metadata names: ");
            for (String mtd : metadata.names())
                System.out.print(mtd + " ");
            System.out.println();

            System.out.println("[' " + fileName +" ']");
            System.out.println("---------------------------------");
            System.out.println("Titlu: " + metadata.get("title"));
            System.out.println("Artist: " + metadata.get("xmpDM:artist"));
            System.out.println("Compozitor: " + metadata.get("xmpDM:composer"));
            System.out.println("Gen: " + metadata.get("xmpDM:genre"));
            System.out.println("Album: " + metadata.get("xmpDM:album"));
            System.out.println("---------------------------------");
        } catch (IOException | SAXException | TikaException e) {
            e.printStackTrace();
        }
    }
}
