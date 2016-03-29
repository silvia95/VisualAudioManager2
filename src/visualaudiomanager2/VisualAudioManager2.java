/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualaudiomanager2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Ana-Maria
 */
public class VisualAudioManager2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
            Commands instanceOfCommands = new Commands();
            String[] commandList = {"quit","list","cd..","cd","play","find","rename","fav","report","info"};
            
            while(true){
                String newCommand = null;
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    newCommand = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    
                    if(newCommand.equals(commandList[0])) {
                        break;
                    }
                    
                    if(newCommand.equals(commandList[1])) {
                        instanceOfCommands.list();
                        continue;
                    }
                    
                    if(newCommand.equals(commandList[2])) {
                        instanceOfCommands.cdUpper();
                        continue;
                    }
                    
                    String commandParts[] = newCommand.split(" ",2);
                    String command = new String(commandParts[0]);
                    String file = new String(commandParts[1]);
                    if(command.equals(commandList[3])){
                        instanceOfCommands.cd(file);
                        continue;
                    }
                    if(command.equals(commandList[4])) {
                        instanceOfCommands.playFile(file);
                        
                        continue;
                    }
                    if(command.equals(commandList[5])) {
                        instanceOfCommands.find(file);
                        continue;
                    }
                    /*String doubleArgument[] = file.split("->",2);
                    String arg1 = new String(doubleArgument[0]);
                    String arg2 = new String(doubleArgument[1]);
                    if(command.equals(commandList[6])) {
                        instanceOfCommands.changeFileName(arg1,arg2);
                        continue;
                    }
                    */
                    if(command.equals(commandList[6])) {
                        instanceOfCommands.FileSerialize(file);
                        continue;
                    }
                    if(command.equals(commandList[7])) {
                        instanceOfCommands.FileDeserialize();
                        continue;
                    }
                    if(command.equals(commandList[8])) {
                        instanceOfCommands.info(file);
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

    }
    
}
