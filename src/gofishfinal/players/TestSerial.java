/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.players;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Joseph
 */
public class TestSerial extends Application {
        private Human human = new Human("",5);
    
    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        System.out.println("old size: "+human.getHand().size());
        System.out.println("old score: "+human.getScore());
        StackPane root = new StackPane();
        save();
        human.getCardFromDeck();
        human.increaseScore();
        System.out.println("New size: "+human.getHand().size());
        System.out.println("new score: "+human.getScore());
        load();
            
        
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public void load() throws ClassNotFoundException{
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/data.go"));
            Player player = (Player)in.readObject(); 
            String size = player.getHand().size()+"";
            int score = player.getScore();
            System.out.println("Size in saved file "+size+" score "+ score);
            in.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error with writing data");
        }        
    }
    public void save(){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/data.go"));
            out.writeObject(human);
            out.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error with writing data");
        }        
    }
}
