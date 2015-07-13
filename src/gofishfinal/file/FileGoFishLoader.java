/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.file;
import gofishfinal.players.Computer;
import gofishfinal.players.Human;
import gofishfinal.ui.GoFishGUI;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jose
 */
public class FileGoFishLoader {
    
    private String filePath = "data/go.fish";
    private GoFishGUI ui;
    public FileGoFishLoader(GoFishGUI ui){
        this.ui = ui;
    }
    public void save(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))){
            out.writeObject(ui.getHuman());
            out.writeObject(ui.getComputer());
            System.out.println("Done Saving");
        }catch(IOException e){
            System.out.println("Error with writing the data");
        }
    }
    public void load(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))){
            ui.setHuman((Human)in.readObject());
            ui.setComputer((Computer)in.readObject());
            ui.updateDisplay();
            System.out.println("Done Loading");
        }catch(IOException e){
            System.out.println("there was an error reading the file");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileGoFishLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
