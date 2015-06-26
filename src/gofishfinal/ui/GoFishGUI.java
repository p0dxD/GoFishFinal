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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Joseph
 */
public class GoFishGUI {

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
    private EventHandler eventHandler;
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
        eventHandler = new EventHandler(this);
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
        human = new Human("", 7);
        computer = new Computer("", 7);
        //init pane and menu bar
        deckPane = new StackPane();
        humanPane = new StackPane();
        computerPane = new StackPane();
        mainGamePane = new BorderPane();
        centerGamePane = new Pane();
        gamePane = new BorderPane();
        computerLabel = new Label("Test with a bigger text that will take so much space");
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
        computerImageContainer.clear();
        computerPane.getChildren().clear();
        computerPane.setAlignment(Pos.CENTER_LEFT);
        computerPane.setTranslateX(75);
        for (int i = 0; i < computer.getHand().size(); i++) {
            Image img = new Image(new File(cardsPath + computer.getHand().get(i).getImageName()).toURI().toString());
//            Image img = new Image(new File(cardsPath +"back.jpg").toURI().toString());//back ^^front
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
        humanImageContainer.clear();
        humanPane.getChildren().clear();
        humanPane.setAlignment(Pos.CENTER_LEFT);
        humanPane.setTranslateX(75);
        for (int i = 0; i < human.getHand().size(); i++) {
            Image img = new Image(new File(cardsPath + human.getHand().get(i).getImageName()).toURI().toString());
            ImageView tets = new ImageView(img);
            tets.translateXProperty().set(i * ((width - (i * 10)) / ((human.getHand().size()))));
            tets.setFitWidth(70);
            tets.setFitHeight(90);
            Tooltip.install(tets, new Tooltip(human.getHand().get(i).getRank()));
            tets.setId(human.getHand().get(i).getRank());
            humanImageContainer.add(tets);
            humanPane.getChildren().add(tets);
        }
        System.out.println(deck.size());
//        if(!human.isIsTurn()){
        human.setIsTurn(true);
        eventHandler.gameLogic("human");
//        }
    }

    public void processPlayerMove() {
        humanLabel.setText("Pick a Card");
        updateLabelLocation();
        if (human.isIsTurn()) {
            for (ImageView i : humanImageContainer) {
                i.setOnMouseClicked(e -> {
                    humanPick = i.getId();
                    humanLabel.setText("You picked " + humanPick + " . Lets see if the computer has it.");
                    updateLabelLocation();
                    if (computer.contains(humanPick) > 0) {
                        computerLabel.setText("I do have " + humanPick);
                        updateLabelLocation();
                        computer.removeFromHandAndHandToPlayer(humanPick, human);
                        updateDisplay();
                    } else {
                        computerLabel.setText("I do not have " + humanPick + ". Go Fish!");
                        updateLabelLocation();
                        human.endTurn();
                        processPlayerMove();
                        deckPane.setDisable(false);
                        deckPane.setOnMouseClicked(deck -> {
                            human.getCardFromDeck();
                            updateDisplay();
                            human.endTurn();
                            processPlayerMove();
                            deckPane.setDisable(true);
                            computer.setIsTurn(true);
                            processComputerTurn();
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
                System.out.println(human.contains(computerPick) > 0);
                if (i.getId().equals(computerPick) && human.contains(computerPick) > 0) {
                    human.removeFromHandAndHandToPlayer(computerPick, computer);
                    computerLabel.setText("Thank you.");
                    updateLabelLocation();
                    updateDisplay();
                    //here add a delay
                    processComputerTurn();
                }else if(!(i.getId().equals(computerPick)) && human.contains(computerPick) < 0){
                    computerLabel.setText("That's not it");
                    updateLabelLocation();
                }
            });
                centerGamePane.setDisable(false);
                centerGamePane.setOnMouseClicked(ea -> {
                if(human.contains(computerPick) < 0){//does not contain it
                        computer.getCardFromDeck();
                        computer.endTurn();
                        updateDisplay();//THIS NEEDS FIX AROUND HERE
                        human.setIsTurn(true);
                        centerGamePane.setDisable(true);
                        computerLabel.setText("Your Turn");
                        updateLabelLocation();
                        processPlayerMove();
                    }else{
                    computerLabel.setText("Check again");
                    updateLabelLocation();
                    
                }
                    });
            
        }

        return humanClicked;
    }
    public void makeComputerGoFish(){
        
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
