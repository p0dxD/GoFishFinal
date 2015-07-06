/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.ui;

import java.io.File;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Joseph
 */
public class PlayerSelection extends Application {
    private Button goButton;
    private BorderPane playerSelectionPane;
    private RadioButton playerRadioButton;
    private RadioButton computerRadioButton;
    private RadioButton randomRadioButton;
    final ToggleGroup group = new ToggleGroup();
    private Text playerSelectText;

    @Override
    public void start(Stage primaryStage) {
        initPlayerSelection();
        Scene scene = new Scene(playerSelectionPane, 700, 500);
        scene.getStylesheets().add(new File("data/goFishStyle.css").toURI().toString());
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initPlayerSelection() {
        playerSelectionPane = new BorderPane();
        goButton = new Button("Start");
        playerSelectionPane.getStyleClass().add("border-pane");
        playerSelectText = new Text("Select\n who\nshould\n start\n  first");
        playerRadioButton = new RadioButton("I want to start");
        computerRadioButton = new RadioButton("Computer");
        randomRadioButton = new RadioButton("Start randomly");
        playerSelectText.setId("select-player-text");
        goButton.setId("green");
        playerSelectText.setFill(Color.CORNFLOWERBLUE);
        //set images
        ImageView humanImage = new ImageView(new Image(new File("images/radio/human.png").toURI().toString()));
        ImageView computerImage = new ImageView(new Image(new File("images/radio/computer.png").toURI().toString()));
        ImageView randomImage = new ImageView(new Image(new File("images/radio/random.png").toURI().toString()));
        humanImage.setFitHeight(60);
        humanImage.setFitWidth(60);
        computerImage.setFitHeight(60);
        computerImage.setFitWidth(60);
        randomImage.setFitHeight(60);
        randomImage.setFitWidth(60);
        playerRadioButton.setGraphic(humanImage);
        computerRadioButton.setGraphic(computerImage);
        randomRadioButton.setGraphic(randomImage);
        playerRadioButton.setToggleGroup(group);
        computerRadioButton.setToggleGroup(group);
        randomRadioButton.setToggleGroup(group);
        playerSelectionPane.setTop(computerRadioButton);
        playerSelectionPane.setCenter(playerRadioButton);
        playerSelectionPane.setBottom(randomRadioButton);
        //Stack for button
        playerSelectionPane.setRight(goButton);

        //select player 
        playerRadioButton.setSelected(true);
        playerRadioButton.requestFocus();
        //set user data for buttons
        playerRadioButton.setUserData("human");
        computerRadioButton.setUserData("computer");
        randomRadioButton.setUserData("random");
        //we color the child in each of the sides of the borderpane
        playerSelectionPane.setLeft(new StackPane(playerSelectText));
        playerSelectionPane.getTop().setId("computer-select");
        playerSelectionPane.getCenter().setId("player-select");
        playerSelectionPane.getBottom().setId("random-select");
        playerSelectionPane.getLeft().setId("text-select");
        playerSelectionPane.getRight().setId("text-select");
        BorderPane.setAlignment(playerRadioButton, Pos.CENTER);
        BorderPane.setAlignment(computerRadioButton, Pos.CENTER);
        BorderPane.setAlignment(randomRadioButton, Pos.CENTER);
        BorderPane.setAlignment(playerSelectText, Pos.CENTER);
        BorderPane.setAlignment(goButton, Pos.CENTER);
        //give action to the buttons
        System.out.println(group.getSelectedToggle().getUserData().toString());
        goButton.setOnMouseClicked(e->{
        System.out.println(group.getSelectedToggle().getUserData().toString());
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
