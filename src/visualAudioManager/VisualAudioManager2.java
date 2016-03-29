/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualAudioManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class VisualAudioManager2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
            
            while (true){
                String newCommand = null;
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    newCommand = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Command command = CommandFactory.newCommand(newCommand);
                    System.out.println("[DEBUG] Created command");
                    command.execute();
                    System.out.println("[DEBUG] Executed command");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

    }
    
}
