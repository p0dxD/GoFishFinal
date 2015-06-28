/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.main;

import gofishfinal.ui.GoFishGUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Joseph
 */
public class Main extends Application {
    private String title = "Go Fish";
    private int width = 700, height = 500;
    @Override
    public void start(Stage primaryStage) {
        GoFishGUI gui = new GoFishGUI(primaryStage);
        BorderPane pane = gui.getMainPane();
        Scene scene = new Scene(pane,width,height);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
