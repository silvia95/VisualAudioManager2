/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualaudiomanager2;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Ana-Maria
 */
public class Commands implements Command {
   private String directorCurent = new String("C:\\Users\\Ana-Maria\\Documents\\NetBeansProjectVisualAudioManager2\\src");


    /**
     * Getter pentru directorCurent
     * @return path-ul Directorului Curent
     */
   @Override
    public String getDirectorCurent(){

        return directorCurent;

    }

    /**
     * Listeaza fisierele audio si directoarele din directorul curent.
     */
   @Override
    public void list() {

        File dir = new File(directorCurent);
        File[] listDir = dir.listFiles();

        System.out.println("Directories:");

        for(int i = 0; i < listDir.length ; i++) {


               if (listDir[i].isDirectory()) {
                   System.out.println(listDir[i].getName());
               }
        }

        System.out.println("Files: ");

        for(int i = 0; i < listDir.length ; i++){


                if( listDir[i].isFile() ){
                    if(listDir[i].getName().contains(".wav")||listDir[i].getName().contains(".mp3")||listDir[i].getName().contains(".flac")){
                        System.out.println(listDir[i].getName());
                    }
                }

        }

    }

    /**
     * Seteaza ca director curent directorul precedent.
     */
   @Override
    public void cdUpper(){
        directorCurent = directorCurent.substring(0, directorCurent.lastIndexOf("\\"));
    }

    /**
     * Seteaza ca director curent directorul dat ca parametru.
     */
   @Override
    public void cd(String directorAccesat){
        File dir = new File(directorCurent);
        File[] listDir = dir.listFiles();
        for (int i = 0; i < listDir.length; i++) {
            if(listDir[i].isDirectory()){
                directorCurent += "\\";
                directorCurent += directorAccesat;
                break;
            }
        }

    }

    /**
     * Porneste folosind aplicatia implicita sistemului de operare fisierul audio dat ca parametru.
     */
   @Override
    public void playFile(String file){
        String path = directorCurent;
        path += "\\";
        path += file;
        File fisier = new File(path);
        try{
            Desktop.getDesktop().open(fisier);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Redenumirea unui fisier audio.
     */
   @Override
    public void changeFileName(String fileName, String newName){
        try {

            String initialFileExtension = (fileName.substring(fileName.lastIndexOf(".") + 1)).trim();
            String newFileExtension = (fileName.substring(fileName.lastIndexOf(".") + 1)).trim();

            if (!(initialFileExtension.trim()).equals(newFileExtension.trim()))
                throw new PersonalException("Extensia noului fisier e diferita de cea initiala!");

            String path = directorCurent;
            path += "\\";
            path += fileName;
            File fisier = new File(path);
            String newPath = directorCurent;
            newPath += "\\";
            newPath += newName;
            File newFileName = new File(newPath);

            try {
                if (!fisier.exists()) {
                    System.out.println("Fisier inexistent!");
                } else {
                    if (!fisier.renameTo(newFileName)) {
                        System.out.println("Fisierul nu a fost redenumit!");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (PersonalException exceptieFormat){
            System.out.println(exceptieFormat.getMessage());
        }
    }


    /**
     * Cautarea recursiva incepand cu directorul curent si continuand in subdirecotarele
     * acestuia. Cautarea se face in directurul dat ca parametru si cu pattern-ul parametru
     * pentru pattern folosindu-se expresii regulate.
     */
   @Override
    public void searchFile(String currentDir, String pattern){

        try {

            if (currentDir.equals("C:"))
                throw new PersonalException("Am terminat cautarea!");
            Pattern filePattern = Pattern.compile(pattern);
            File dir = new File(currentDir);
            File[] listDir = dir.listFiles();
            for (int i = 0; i < listDir.length; i++) {
                if (listDir[i].isFile()) {
                    Matcher isInPattern = filePattern.matcher(listDir[i].getName());
                    if (isInPattern.find()) {
                        System.out.println("File " + listDir[i].getName() + " was found in " + currentDir);
                    }

                }

            }

            String highterDir = currentDir.substring(0, currentDir.lastIndexOf("\\"));
            searchFile(highterDir, pattern);
        }catch (PersonalException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Apeleaza metoda searchFile folosind directorul curent
     */
   @Override
    public void find(String pattern){
        searchFile(directorCurent,pattern);
    }
    /*public void fav(String fileName){
        try {

			String content = fileName;

			File file = new File("favourite.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("The file has been added to favourite!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
   @Override
     public void FileSerialize (String fileName) {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("SerializedFile.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(fileName);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in SerializeFile.ser");
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }
   @Override
    public void FileDeserialize () {
        try
        {
            FileInputStream fileIn = new FileInputStream("SerializeFile.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            String result = (String) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("\nFavorite songs are:\n"+result);
        }catch(IOException i)
        {
            i.printStackTrace();
        }catch(ClassNotFoundException c)
        {
            System.out.println("File not found!");
            c.printStackTrace();
        }
    }
    /**
     *Listeaza informatii despre fisierul dat ca parametru
     */
   @Override
    public void info (String fileName) {
        try {
            InputStream input = new FileInputStream (new File(directorCurent + "\\" + fileName));
            DefaultHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Mp3Parser parser = new Mp3Parser();
            ParseContext parseContext = new ParseContext();
            parser.parse(input, handler, metadata, parseContext);
            input.close();
            System.out.println("[' " + fileName +" ']");
            System.out.println("---------------------------------");
            System.out.println("Titlu: " + metadata.get("title "));
            System.out.println("Artist: " + metadata.get("xmpDM:artist "));
            System.out.println("Compozitor: " + metadata.get("xmpDM:composer "));
            System.out.println("Gen: " + metadata.get("xmpDM:genre "));
            System.out.println("Album: " + metadata.get("xmpDM:album "));
            System.out.println("---------------------------------");            
        } catch (IOException | SAXException | TikaException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
