/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.ui;

import gofishfinal.players.Computer;
import gofishfinal.players.Human;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author Joseph
 */
public class GoFishGUI {
    private Stage primaryStage;
    //size 
    private int width;
    private int height;
    
    //Panes
    private BorderPane mainPane;
    private Pane splashScreenPane;
    private BorderPane aboutPane;
    private HBox topAbout;
    private BorderPane gamePane;
    private BorderPane mainGamePane;
    private StackPane deckPane;
    private StackPane humanPane;
    private StackPane computerPane;
    private Pane centerGamePane;
    //buttons
    private Button about;
    private Button play;
    private Button quit;
    private Button aboutBack;
    //sources for about
    private String aboutSource = "data/about.html";
    private WebView browser;
    private WebEngine webEngine;
    //for event handler
    private EventHandlerGoFish eventHandler;
    //for the images
    private ImageView splashImage;
    private String backgroundPath = "images/backgrounds/backgroundSplashOptional.gif";
    //for the game menu
    private final Menu gameMenu = new Menu("Go Fish Game Options");
    private MenuBar gameMenuBar;
    private MenuItem returnToSplashScreen;
    private MenuItem resetGame;
    private MenuItem exit;
    private MenuItem save;
    private MenuItem load;
    //game play sources
    private String cardsPath = "images/cards/";
    //players
    private Human human;
    private Computer computer;
    private ArrayList<Card> deck;
    private ArrayList<ImageView> computerImageContainer = new ArrayList<ImageView>();
    private ArrayList<ImageView> humanImageContainer = new ArrayList<ImageView>();
    //labels for game
    private Label computerLabel;
    private Label humanLabel;
    //human humanPick
    private String humanPick;
    private String computerPick;
    private boolean humanClicked = false;


    /**
     * Default constructor size 770, 500
     */
    public GoFishGUI() {
        this(700, 500);
    }
    public GoFishGUI(Stage stage){
        this(700,500);
        setPrimaryStage(stage);
    }
    /**
     * Constructor with specified sizes
     *
     * @param width of screen
     * @param height of screen
     */
    public GoFishGUI(int width, int height) {
        this.width = width;
        this.height = height;
        inits();
    }

    /**
     * Contains the inits of screens
     */
    public void inits() {
        System.out.println("Inside inits");
        initHandlers();
        initMainPane();
        initSplashScreen();
        initAbout();
        initGame();

        changeSpace(ScreenState.SPLASH_SCREEN);//start with splashscreen
    }
    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    /**
     * Inits the initial stage
     */
    public void initMainPane() {
        System.out.println("Inside initMainPane");
        mainPane = new BorderPane();
        setMainPane(mainPane);
        mainPane.resize(width, height);
    }

    public void initHandlers() {
        System.out.println("Inside initHandlers");
        eventHandler = new EventHandlerGoFish(this);
    }

    /**
     * Starts the splash screen
     */
    public void initSplashScreen() {
        System.out.println("Inside initSplashScreen");
        //initialize buttons and pane
        splashScreenPane = new Pane();
        about = new Button("About");
        play = new Button("Play");
        quit = new Button("Quit");
        //for the image
        splashImage = new ImageView(new Image(new File(backgroundPath).toURI().toString()));
        //set size of image
        splashImage.setFitHeight(height);
        splashImage.setFitWidth(width);
        //add to the pane
        splashScreenPane.getChildren().addAll(splashImage, about, play, quit);
        //set the location of buttons 
        about.setLayoutX(width / 2);
        about.setLayoutY(20);
        play.setLayoutX(width / 2);
        play.setLayoutY(height / 2);
        quit.setLayoutX(width / 2);
        quit.setLayoutY(height - 50);
        //set the action for the buttons
        about.setOnMouseClicked(e -> {//for about
            eventHandler.respondToSwitchScreenRequest(ScreenState.ABOUT_SCREEN);
        });
        play.setOnMouseClicked(e -> {//for play
            eventHandler.respondToSwitchScreenRequest(ScreenState.GAME_SCREEN);
        });
        quit.setOnMouseClicked(e -> {//for quit
            eventHandler.respondToSwitchScreenRequest(ScreenState.EXIT_REQUEST);
        });
        mainPane.setCenter(splashScreenPane);//put in in main pane
    }//end initSplashScreen

    /**
     * Inits the
     */
    public void initAbout() {
        System.out.println("Inside initAbout");
        //initialize web,pane,buttons
        aboutPane = new BorderPane();
        topAbout = new HBox();
        browser = new WebView();
        webEngine = browser.getEngine();
        aboutBack = new Button("BACK");
        //position the button and webengine
        webEngine.load(new File(aboutSource).toURI().toString());
        topAbout.getChildren().add(aboutBack);
        topAbout.setAlignment(Pos.CENTER);
        aboutPane.setTop(topAbout);
        aboutPane.setCenter(browser);
        //back button in about function
        aboutBack.setOnMouseClicked(e -> {
            eventHandler.respondToSwitchScreenRequest(ScreenState.SPLASH_SCREEN);
        });
        mainPane.setCenter(aboutPane);

    }//end initAbout

    public void initGame() {
        //players
        human = new Human("HUMAN", 7);
        computer = new Computer("COMPUTER", 7);
        //init pane and menu bar
        deckPane = new StackPane();
        humanPane = new StackPane();
        computerPane = new StackPane();
        mainGamePane = new BorderPane();
        centerGamePane = new Pane();
        gamePane = new BorderPane();
        computerLabel = new Label("Player starts first!");
        humanLabel = new Label("Human");
        gameMenuBar = new MenuBar();
        returnToSplashScreen = new MenuItem("Return to splash screen");
        resetGame = new MenuItem("Reset");
        save = new MenuItem("Save");
        load = new MenuItem("Load");
        exit = new MenuItem("Exit");
        //add to center game pane
        updateLabelLocation();
        centerGamePane.getChildren().addAll(computerLabel, humanLabel);
        //add it to the bar
        gameMenuBar.getMenus().add(gameMenu);
        gameMenu.getItems().addAll(returnToSplashScreen,
                resetGame, load, save, exit);
        //for the center of the game screen
        mainGamePane.setTop(computerPane);
        mainGamePane.setBottom(humanPane);
        mainGamePane.setLeft(deckPane);
        mainGamePane.setCenter(centerGamePane);
        gamePane.setTop(gameMenuBar);
        gamePane.setCenter(mainGamePane);
        //menu actions
        returnToSplashScreen.setOnAction(e -> {
            eventHandler.respondToSwitchScreenRequest(ScreenState.SPLASH_SCREEN);
        });
        exit.setOnAction(e -> {
            eventHandler.respondToSwitchScreenRequest(ScreenState.EXIT_REQUEST);
        });
        updateDisplay();
        eventHandler.gameLogic("human");
        mainPane.setCenter(gamePane);
    }

    public void updateLabelLocation() {
        computerLabel.setTranslateX(((width - 100) / 2) - (5 * (computerLabel.getText().length() / 2)));
        computerLabel.setTranslateY(20);
        humanLabel.setTranslateX(((width - 100) / 2) - (5 * (humanLabel.getText().length() / 2)));
        humanLabel.setTranslateY(height - 250);
    }

    public void updateDisplay() {
        updateDeckDisplay();
        updateComputerDisplay();
        updateHumanDisplay();
    }

    public void updateDeckDisplay() {
        deckPane.getChildren().clear();
        deck = Deck.getDeck();
        deckPane.setAlignment(Pos.TOP_CENTER);
        for (int i = 0; i < deck.size(); i++) {
//            Image img = new Image(new File(cardsPath + deck.get(i).getImageName()).toURI().toString());
            Image img = new Image(new File(cardsPath + "back.jpg").toURI().toString());//back ^^front
            ImageView tets = new ImageView(img);

            tets.translateYProperty().set(i * 6);
            tets.setFitWidth(70);
            tets.setFitHeight(90);

            deckPane.getChildren().add(tets);

        }
        System.out.println(deck.size());
    }

    public void updateComputerDisplay() {
        computerPane.getChildren().clear();
        computerImageContainer.clear();
        computerPane.setAlignment(Pos.CENTER_LEFT);
        computerPane.setTranslateX(75);
        for (int i = 0; i < computer.getHand().size(); i++) {
//            Image img = new Image(new File(cardsPath + computer.getHand().get(i).getImageName()).toURI().toString());
            Image img = new Image(new File(cardsPath +"back.jpg").toURI().toString());//back ^^front
            ImageView tets = new ImageView(img);
            tets.translateXProperty().set(i * ((width - (i * 10)) / ((computer.getHand().size()))));
            tets.setFitWidth(70);
            tets.setFitHeight(90);
            tets.setId(computer.getHand().get(i).getRank());
            computerImageContainer.add(tets);

//R27587
            computerPane.getChildren().add(tets);
        }
    }

    public void updateHumanDisplay() {
        humanPane.getChildren().clear();
        humanImageContainer.clear();
        
        humanPane.setAlignment(Pos.CENTER_LEFT);
        humanPane.setTranslateX(75);
        for (int i = 0; i < human.getHand().size(); i++) {
            Image img = new Image(new File(cardsPath + human.getHand().get(i).getImageName()).toURI().toString());
            ImageView tets = new ImageView(img);
            tets.translateXProperty().set(i * ((width - (i * 10)) / ((human.getHand().size()))));
            tets.setFitWidth(70);
            tets.setFitHeight(90);
            Tooltip.install(tets, new Tooltip("Pick "+human.getHand().get(i).getRank()));
            tets.setId(human.getHand().get(i).getRank());
            humanPane.getChildren().add(tets);
            System.out.print(tets.getId()+" ");
            humanImageContainer.add(tets);
        }
    }

    public void processPlayerMove() {
        human.setIsTurn(true);
        humanLabel.setText("Pick a Card");
        updateLabelLocation();
        if (human.isIsTurn()) {
            for (ImageView i : humanImageContainer) {
                i.setOnMouseClicked(e -> {
                    humanPick = i.getId();
                    humanLabel.setText("You picked " + humanPick + " . Lets see if the computer has it.");
                    updateLabelLocation();
                    if (computer.contains(humanPick)) {
                        computerLabel.setText("I do have " + humanPick);
                        updateLabelLocation();
                        computer.removeFromHandAndHandToPlayer(humanPick, human);
                        if(human.hasMatchingFour()){
                            //chekc if player has 4+increaes score
                            System.out.println("Inside has 4 matching human score: " + human.getScore());
                            human.showPopup(primaryStage, width, height);
                             human.closePopup();
                        }   
                        updateDisplay();
                        processPlayerMove();
                    } else {
                        computerLabel.setText("I do not have " + humanPick + ". Go Fish!");
                        updateLabelLocation();
                        human.endTurn();
                        processPlayerMove();
                        System.out.println("It does go through it");
                        deckPane.setDisable(false);
                            //IF FOR THE DECK PART ONCE HE HAS TO CLICK ON IT
                        deckPane.setOnMouseClicked(deck -> {
                            human.getCardFromDeck();//gets card from deck
                        if(human.hasMatchingFour()){
                            //chekc if player has 4+increaes score
                            System.out.println("Inside has 4 matching human score: " + human.getScore());
                            human.showPopup(primaryStage, width, height);
                            human.closePopup();
                        }   
                            if(human.getRankOfDeckCard().equals(humanPick)){
                                updateDisplay();
                                deckPane.setDisable(true);
                                processPlayerMove();
                            }else{
                            updateDisplay();
                            human.endTurn();
                            processPlayerMove();
                            deckPane.setDisable(true);
                            computer.setIsTurn(true);
                            processComputerTurn();
                            }
                            //here put the computer 
                        });
                    }
                    System.out.println(human.isIsTurn());
                });
            }
        } else {//here we dissable the player from clicking the cards
            System.out.println("Inside else allowPlayerClickableCards()");
            disableHumanCards(true);
        }
    }

    public void processComputerTurn() {
        computer.setIsTurn(true);
        System.out.println("Computer turn: " + computer.isIsTurn());
        if (computer.isIsTurn()) {
            computerPick = computer.makeMove();
            computerLabel.setText("Do you have " + computerPick + "?");
            humanLabel.setText("Click on the card if you have it. Else click center to make him go Fish.");
            updateLabelLocation();
            makeHumanCardsClickable();
        }
    }
    
    public boolean makeHumanCardsClickable() {
        disableHumanCards(false);
        System.out.println("Inside make human clickable" + humanImageContainer.size());
        for (ImageView i : humanImageContainer) {
            i.setOnMouseClicked(e -> {
                System.out.println((human.contains(computerPick)) + " " +i.getId()+" "+computerPick);
                if (i.getId().equals(computerPick) && human.contains(computerPick)) {
                    human.removeFromHandAndHandToPlayer(computerPick, computer);
                    computerLabel.setText("Thank you.");
                    updateLabelLocation();
                        if(computer.hasMatchingFour()){
                            //chekc if player has 4+increaes score
                            System.out.println("Inside has 4 matching computer score: " + computer.getScore());
                            computer.showPopup(primaryStage, width, height);
                            computer.closePopup();
                        }                      
                    updateDisplay();
                    //here add a delay
                    processComputerTurn();
                }else if(!(i.getId().equals(computerPick)) && !human.contains(computerPick)){
                    computerLabel.setText("That's not it. "+"Do you have " + computerPick + "?");
                    updateLabelLocation();
                }
            });
                centerGamePane.setOnMouseClicked(ea -> {
                if(!human.contains(computerPick)&&computer.isIsTurn()){//does not contain it
                        computer.getCardFromDeck();
                        computer.hasMatchingFour();
                        if(computer.getRankOfDeckCard().equals(computerPick)){
                            if(computer.hasMatchingFour()){
                            //chekc if player has 4+increaes score
                            System.out.println("Inside has 4 matching computer score: " + computer.getScore());
                            computer.showPopup(primaryStage, width, height);
                            computer.closePopup();
                        }  
                            updateDisplay();
                            processComputerTurn();
                        }else{
                        computer.endTurn();
                        updateDisplay();
                        human.setIsTurn(true);
                        computerLabel.setText("Your Turn");
                        updateLabelLocation();
                        processPlayerMove();
                        }
                    }else{
                    if(computer.isIsTurn()){
                            computerLabel.setText("Do you have " + computerPick + "?");
                        
                    }else{
                    computerLabel.setText("Try again");
                    updateLabelLocation();
                    }
                    
                }
            });
                
        }

        return humanClicked;
    }

    public void disableHumanCards(boolean status) {
        for (ImageView i : humanImageContainer) {
            i.setDisable(status);
        }
    }

    /**
     * Handles the screen changes
     *
     * @param uiScreen
     */
    public void changeSpace(ScreenState uiScreen) {
        System.out.println("Inside changeSpace");
        switch (uiScreen) {
            case SPLASH_SCREEN:
                mainPane.getChildren().clear();
                mainPane.setCenter(splashScreenPane);
                break;
            case ABOUT_SCREEN:
                mainPane.getChildren().clear();
                mainPane.setCenter(aboutPane);
                break;
            case GAME_SCREEN:
                mainPane.getChildren().clear();
                mainPane.setCenter(gamePane);
                break;
            case EXIT_REQUEST:
                System.exit(0);
                break;
        }
    }

    public String getPick() {
        return this.humanPick;
    }

    /**
     * Sets the pane to the specified
     *
     * @param mainPane pane to set
     */
    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }//end setMainPane

    /**
     * gets main pane
     *
     * @return pane
     */
    public BorderPane getMainPane() {
        return mainPane;
    }//end getMainPane
}
