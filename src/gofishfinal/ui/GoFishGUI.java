/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.ui;

import gofishfinal.file.FileGoFishLoader;
import gofishfinal.players.Computer;
import gofishfinal.players.Human;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
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
    private BorderPane playerSelectionPane;
    private BorderPane gamePane;
    private Pane mainGamePane;
    private StackPane deckPane;
    private Pane deckBackground;
    private Pane humanBackground;
    private Pane computerBackground;
    private Pane centerBackground;
//    private StackPane humanPane;
    private HBox humanPane;
//    private StackPane computerPane;
    private HBox computerPane;
    private Pane centerGamePane;
    //buttons
    private Button about;
    private Button play;
    private Button quit;
    private Button goButton;//getplayer
    private Button aboutBack;
    //sources for about
    private String aboutSource = "data/html/about.html";
    private WebView browser;
    private WebEngine webEngine;
    //sources for selection
    private RadioButton playerRadioButton;
    private RadioButton computerRadioButton;
    private RadioButton randomRadioButton;
    private final ToggleGroup group = new ToggleGroup();
    private Text playerSelectText;
    //for event handler and file loader
    private EventHandlerGoFish eventHandler;
    private FileGoFishLoader fileLoader;
    //for the images
    private ImageView splashImage;
    private String backgroundPath = "images/backgrounds/backgroundSplashOptional.gif";
    //for the game menu
    private final Menu gameMenu = new Menu("Go Fish Game Options");
    private MenuBar gameMenuBar;
    private MenuItem returnToSplashScreen;
    private MenuItem exit;
    private MenuItem save;
    private MenuItem load;
    private boolean gameGoing;

    //game play sources
    private String cardsPath = "images/cards/";
    //players
    private Human human;
    private Computer computer;
    private List<Card> deck;
    private List<ImageView> computerImageContainer = new ArrayList<>();
    private List<ImageView> humanImageContainer = new ArrayList<>();
    //labels for game
    private Label computerLabel;
    private Label humanLabel;
    //human humanPick
    private String humanPick;
    private String computerPick;
    private boolean humanClicked = false;
    //for the stage of winning 
    private Stage winStage;
    private StackPane winPane;
    private Text winningText;
    private Text scoreText;
    private Text score;
    private ImageView trophy;
    private String trophyPath = "images/win/";
    private Scene winScene;
    //popup//    
    private final Popup popup = new Popup();

    /**
     * Default constructor size 770, 500
     */
    public GoFishGUI() {
        this(700, 500);
    }

    public GoFishGUI(Stage stage) {
        this(700, 500);
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
        initPlayerSelection();
        initGame();

        changeSpace(ScreenState.SPLASH_SCREEN);//start with splashscreen
    }

    public void setPrimaryStage(Stage primaryStage) {
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
        fileLoader = new FileGoFishLoader(this);
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
        //TODO set the color of buttons
        play.getStyleClass().add("buttons");
        play.setStyle("-fx-background-color: #228b22");
        about.getStyleClass().add("buttons");
        quit.getStyleClass().add("buttons");
        quit.setStyle("-fx-background-color: #ff6347");
        //action for the buttons
        about.setOnMouseClicked(e -> {//for about
            eventHandler.respondToSwitchScreenRequest(ScreenState.ABOUT_SCREEN);
        });
//        about.setId("about");
//        quit.setId("quit");
        play.setOnMouseClicked(e -> {//for play
            eventHandler.respondToSwitchScreenRequest(ScreenState.PLAYER_SELECT);
        });
        quit.setOnMouseClicked(e -> {//for quit
            eventHandler.respondToSwitchScreenRequest(ScreenState.EXIT_REQUEST);
        });
        mainPane.setCenter(splashScreenPane);//put in in main pane
    }//end initSplashScreen

    public void initPlayerSelection() {
        playerSelectionPane = new BorderPane();
        goButton = new Button("Start");
        playerSelectionPane.getStyleClass().add("border-pane");
        playerSelectText = new Text("Select\n who\nshould\n start\n  first");
        playerRadioButton = new RadioButton("I want to start");
        computerRadioButton = new RadioButton("Computer");
        randomRadioButton = new RadioButton("Start randomly");
        playerSelectText.setId("select-player-text");
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
        goButton.setOnMouseClicked(e -> {
            updateDisplay();
            System.out.println(group.getSelectedToggle().getUserData().toString() + " starts");
            eventHandler.whoStarts(group.getSelectedToggle().getUserData().toString());//TODO WHO STARTS?
            eventHandler.respondToSwitchScreenRequest(ScreenState.GAME_SCREEN);
            gameGoing = true;
        });
        mainPane.setCenter(playerSelectionPane);
    }

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
//        humanPane = new StackPane();
        humanPane = new HBox();
//        computerPane = new StackPane();
        computerPane = new HBox();
        mainGamePane = new BorderPane();
        centerGamePane = new Pane();
        gamePane = new BorderPane();
        computerLabel = new Label("Player starts first!");
        humanLabel = new Label("Human");
        gameMenuBar = new MenuBar();
        returnToSplashScreen = new MenuItem("Return to splash screen");
        save = new MenuItem("Save");
        load = new MenuItem("Load");
        exit = new MenuItem("Exit");
        //add to center game pane
        updateLabelLocation();
        centerGamePane.getChildren().addAll(computerLabel, humanLabel);
        //put the background and colors
        //add it to the bar
        gameMenuBar.getMenus().add(gameMenu);
        gameMenu.getItems().addAll(returnToSplashScreen, load, save, exit);
        //for the center of the game screen
        //TODO here is where i modified for the pane
//        centerGamePane.setPrefSize(width, 300);
        centerGamePane.setTranslateX(80);
        centerGamePane.setTranslateY(80);
        humanPane.setTranslateX(160);
        humanPane.setTranslateY(height - 142);
        computerPane.setTranslateX(80);
        deckPane.setTranslateX(35);
        deckPane.setTranslateY(35);
        //PANE FOR COLORING THE INSIDE OF THE HBOX
        humanBackground = new Pane();
        computerBackground = new Pane();
        centerBackground = new Pane();
//        deckBackground.getChildren().add(deckPane);
        humanBackground.getChildren().add(humanPane);
        computerBackground.getChildren().add(computerPane);
        centerBackground.getChildren().add(centerGamePane);
        computerPane.setPrefWidth(width - 90);
        humanPane.setPrefWidth(width - 90);
        humanPane.setPrefHeight(116);
        computerPane.setPadding(new Insets(5, 0, 0, 0));
        humanPane.setPadding(new Insets(5, 0, 0, 0));
        //Set the colors with the css
        humanPane.setId("player-select");
        computerPane.setId("computer-select");
        mainGamePane.setId("game");
        gameMenu.getStyleClass().add("tool-bar");
        mainGamePane.getChildren().add(computerBackground);
        mainGamePane.getChildren().add(humanBackground);
        mainGamePane.getChildren().add(centerBackground);
        mainGamePane.getChildren().add(deckPane);
        humanPane.setOnMouseClicked(e -> System.out.println("it here"));
        deckPane.setOnMouseClicked(e -> System.out.println("it here too"));

//        humanPane.setStyle("-fx-background-color: red;");
//        centerGamePane.setId("center-game-pane");
//        humanPane.setId("player-pane");
//        computerPane.setId("computer-pane");
//        mainGamePane.setTop(computerPane);
//        mainGamePane.setBottom(humanPane);
//        mainGamePane.setLeft(deckPane);
//        mainGamePane.setCenter(centerGamePane);
        gamePane.setTop(gameMenuBar);
        gamePane.setCenter(mainGamePane);
        //menu actions
        returnToSplashScreen.setOnAction(e -> {
            eventHandler.respondToSwitchScreenRequest(ScreenState.SPLASH_SCREEN);
        });
        save.setOnAction(e->{
            fileLoader.save();
        });
        load.setOnAction(e->{
            fileLoader.load();
            if(human.isIsTurn()){
                processPlayerMove();
            }else{
                processComputerTurn();
            }
        });
        exit.setOnAction(e -> {
            eventHandler.respondToSwitchScreenRequest(ScreenState.EXIT_REQUEST);
        });
        mainPane.setCenter(gamePane);
    }
//--------------------Elements of display----------------------------------------

    public void updateLabelLocation() {
        computerLabel.setTranslateX(((width - 100) / 2) - (5 * (computerLabel.getText().length() / 2)));
        computerLabel.setTranslateY(40);
        humanLabel.setTranslateX(((width - 100) / 2) - (5 * (humanLabel.getText().length() / 2)));
        humanLabel.setTranslateY(height - 260);
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
//        computerPane.setAlignment(Pos.CENTER_LEFT);
//            computerPane.setAlignment(Pos.CENTER);
        computerPane.setTranslateX(75);
        for (int i = 0; i < computer.getHand().size(); i++) {
//            Image img = new Image(new File(cardsPath + computer.getHand().get(i).getImageName()).toURI().toString());
            Image img = new Image(new File(cardsPath + "back.jpg").toURI().toString());//back ^^front
            ImageView tets = new ImageView(img);
//            tets.translateXProperty().set(i * ((width - (i * 10)) / ((computer.getHand().size()))));
            tets.setFitWidth(70);
            tets.setFitHeight(90);
            tets.setId(computer.getHand().get(i).getRank());

            if (!gameGoing) {
                animateCards(tets, 500 * i + 1, 140, 200, -140, -200);
            }
            computerImageContainer.add(tets);

//R27587
            computerPane.getChildren().add(tets);

            HBox.setMargin(tets, new Insets(0, -2 * computer.getHand().size(), 0, 0));
        }
    }

    public void updateHumanDisplay() {
        humanPane.getChildren().clear();
        humanImageContainer.clear();
//        humanPane.setAlignment(Pos.CENTER_LEFT);
        humanPane.setTranslateX(75);
        for (int i = 0; i < human.getHand().size(); i++) {
            Image img = new Image(new File(cardsPath + human.getHand().get(i).getImageName()).toURI().toString());
            ImageView tets = new ImageView(img);
//            tets.translateXProperty().set(i * ((width - (i * 10)) / ((human.getHand().size()))));
            tets.setFitWidth(70);
            tets.setFitHeight(90);
            Tooltip.install(tets, new Tooltip("Pick " + human.getHand().get(i).getRank()));
            tets.setId(human.getHand().get(i).getRank());
            if (!gameGoing) {
                animateCards(tets, 500 * i + 1, 140, -200, -140, 200);
            }
//            System.out.print(tets.getId() + " ");
            humanPane.getChildren().add(tets);
            humanImageContainer.add(tets);
            HBox.setMargin(tets, new Insets(0, -2 * human.getHand().size(), 0, 0));
        }
    }

    /**
     * This method should be used in case the hand of the player or cmputer
     * becomes empty In that case we should fill it up with the starting amount
     * and continue normal
     *
     * @param player to fill with cards
     */
    public void fillHand(gofishfinal.players.Player player) {
        System.out.println("--------------inside fillHand------------");
        if (!deck.isEmpty()) {
            if (deck.size() >= 7) {
                for (int i = 0; i < 7; i++) {
                    player.getCardFromDeck();
                }
            } else {
                while (!deck.isEmpty()) {
                    player.getCardFromDeck();
                }
            }
        } else {
            System.out.println("The deck is empty");
            //TODO Add that it shows for player or computer that they ran out of cards
        }
    }

    /**
     * To animate the card movement
     *
     * @param card
     * @param duration
     * @param startAtx
     * @param startAty
     * @return
     */
    private ParallelTransition animateCards(ImageView card, int duration, int startAtx, int startAty, float transitionStartx, float transitionStarty) {
        card.setTranslateX(startAtx);
        card.setTranslateY(startAty);
        FadeTransition ft = new FadeTransition(Duration.millis(duration), card);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
//        ScaleTransition st = new ScaleTransition(Duration.millis(duration), card);
//        st.setByY(.0f);
//        st.setByX(.0f);
        TranslateTransition tt = new TranslateTransition(Duration.millis(duration), card);
        tt.setByX(transitionStartx);//set this to the opposite of where they start at
        tt.setByY(transitionStarty);//set this to the opposite of where it start too
        ParallelTransition pt = new ParallelTransition(card, ft, tt);
        pt.delayProperty().setValue(Duration.millis(duration));
        pt.play();
        return pt;
    }
//-----------------------------------end elements of display------------------------
//-------------------------player and computer logic--------------------------------

    public void processPlayerMove() {
//        human.setIsTurn(true);
        if (!isGameOver()) {
            humanLabel.setText("Pick a Card");
            updateLabelLocation();

            for (ImageView i : humanImageContainer) {
                i.setOnMouseClicked(e -> {
                    humanPick = i.getId();
                    humanLabel.setText("You picked " + humanPick + " . Lets see if the computer has it.");
                    updateLabelLocation();
                    if (computer.contains(humanPick)) {
                        computerLabel.setText("I do have " + humanPick);
                        updateLabelLocation();

                        computer.removeFromHandAndHandToPlayer(humanPick, human);
                        //CHECK IF COMPUTER HAS ENOUGH CARDS IF NOT FILL IT UP
                        if (computer.getHand().isEmpty()) {//if the hand of the player becomes empty we will it with initial amount(his turn again)
                            fillHand(computer);
                        }
                        if (human.hasMatchingFour()) {
                            //chekc if player has 4+increaes score
                            System.out.println("Inside has 4 matching human score: " + human.getScore());
                            showPopup(primaryStage, width, height, human.getName());
                            closePopup();
                            //CHECK IF player HAS ENOUGH CARDS IF NOT FILL IT UP
                            if (human.getHand().isEmpty()) {//if the hand of the player becomes empty we will it with initial amount(his turn again)
                                fillHand(human);
                            }
                        }
                        updateDisplay();
                        processPlayerMove();
                    } else {
                        disableHumanCards(true);
                        computerLabel.setText("I do not have " + humanPick + ". Go Fish!");
                        updateLabelLocation();
                        human.endTurn();
                        processPlayerMove();

                        deckPane.setDisable(false);
                        //IF FOR THE DECK PART ONCE HE HAS TO CLICK ON IT
                        deckPane.setOnMouseClicked(eck -> {
                            human.getCardFromDeck();//gets card from deck
                            if (human.hasMatchingFour()) {
//                                chekc if player has 4+increaes score
                                //CHECK IF player HAS ENOUGH CARDS IF NOT FILL IT UP
                                if (human.getHand().isEmpty()) {//if the hand of the player becomes empty we will it with initial amount(his turn again)
                                    fillHand(human);
                                }
                                System.out.println("Inside has 4 matching human score: " + human.getScore());
                                showPopup(primaryStage, width, height, human.getName());
                                closePopup();
                            }
                            System.out.println(human.getRankOfDeckCard().equals(humanPick) + " Human pick : " + humanPick + " rank from deck " + human.getRankOfDeckCard());
                            if (human.getRankOfDeckCard().equals(humanPick)) {
                                humanLabel.setText("Lucky the card you got from the deck was the same as guessed go again.");
                                updateDisplay();
                                deckPane.setDisable(true);
                                processPlayerMove();
                            } else {
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
        }
    }

    public void processComputerTurn() {
        computer.setIsTurn(true);
        System.out.println("Computer turn: " + computer.isIsTurn());
        if (computer.isIsTurn() && !isGameOver()) {
            computerPick = computer.makeMove();
            System.out.println("Inside processcomputer after computer turn: and computer pick is " + computerPick);
            computerLabel.setText("Do you have " + computerPick + "?");
            humanLabel.setText("Click on the card if you have it. Else click center to make him go Fish.");
            updateLabelLocation();
            computerHelper();
        }
    }

    private boolean computerHelper() {
        disableHumanCards(false);
        System.out.println("Inside make human clickable" + humanImageContainer.size());
        for (ImageView i : humanImageContainer) {
            i.setOnMouseClicked(e -> {
                System.out.println((human.contains(computerPick)) + " " + i.getId() + " " + computerPick);
                if (i.getId().equals(computerPick) && human.contains(computerPick)) {
                    human.removeFromHandAndHandToPlayer(computerPick, computer);
                    //CHECK IF HUMAN HAS ENOUGH CARDS IF NOT FILL IT UP
                    if (human.getHand().isEmpty()) {//if the hand of the player becomes empty we will it with initial amount(his turn again)
                        fillHand(human);
                    }
                    computerLabel.setText("Thank you.");
                    updateLabelLocation();
                    if (computer.hasMatchingFour()) {
                        //chekc if player has 4+increaes score
                        if (computer.getHand().isEmpty()) {//if the hand of the player becomes empty we will it with initial amount(his turn again)
                            fillHand(computer);
                        }
                        System.out.println("Inside has 4 matching computer score: " + computer.getScore());
                        showPopup(primaryStage, width, height, computer.getName());
                        closePopup();
                    }
                    updateDisplay();
                    //here add a delay
                    processComputerTurn();
                } else if (!(i.getId().equals(computerPick)) && !human.contains(computerPick)) {
                    computerLabel.setText("That's not it. " + "Do you have " + computerPick + "?");
                    updateLabelLocation();
                }
            });
            centerGamePane.setOnMouseClicked(ea -> {
                System.out.println("Inside centerGamePane clickable");
                if (!human.contains(computerPick) && computer.isIsTurn()) {//does not contain it
                    computer.getCardFromDeck();
//                    computer.hasMatchingFour();
                    if (computer.hasMatchingFour()) {
                        if (computer.getRankOfDeckCard().equals(computerPick)) {//TODO problem here
                            //chekc if player has 4+increaes score
                            if (computer.getHand().isEmpty()) {//if the hand of the player becomes empty we will it with initial amount(his turn again)
                                fillHand(computer);
                            }
                            System.out.println("Inside has 4 matching computer score: " + computer.getScore());
                            showPopup(primaryStage, width, height, computer.getName());
                            closePopup();
                        }
                        updateDisplay();
                        processComputerTurn();
                    } else {
                        computer.endTurn();
                        updateDisplay();
                        human.setIsTurn(true);
                        computerLabel.setText("Your Turn");
                        updateLabelLocation();
                        processPlayerMove();
                    }
                } else {
                    if (computer.isIsTurn()) {
                        computerLabel.setText("Do you have " + computerPick + "?");

                    } else {
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

    public gofishfinal.players.Player getHuman() {
        return human;
    }

    public gofishfinal.players.Player getComputer() {
        return computer;
    }
    public void setHuman(Human human) {
        this.human = human;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public boolean isGameOver() {
        if (deck.isEmpty() && human.getHand().isEmpty() && computer.getHand().isEmpty()) {
            System.out.println("Game Over");
            centerGamePane.getChildren().clear();
            displayWinMessage();
            return true;
        } else {
            System.out.println("Not over yet");
            return false;
        }
    }
//------------------------------end player and computer logic---------------------------
//------------------------------Winning message display---------------------------------

    /**
     * Stage to display the winning message
     */
    public void displayWinMessage() {
        winStage = new Stage();
        winPane = new StackPane();
        winPane.getStyleClass().add("stack-pane");
        winPane.setId("win-stack");
        if (human.compareTo(computer) > 0) {
            displayWinLoseMessagehelper(winPane, "You Win", false);
            winStage.setTitle("Congratulations");
        } else if (human.compareTo(computer) < 0) {
            displayWinLoseMessagehelper(winPane, "You Lost", true);
            winStage.setTitle("You lost");
        } else {
            displayWinLoseMessagehelper(winPane, "Draw", true);
            winStage.setTitle("It is a draw!");
        }
        winScene = new Scene(winPane, 200, 200);
        winScene.getStylesheets().add(new File("data/goFishStyle.css").toURI().toString());
        winStage.initStyle(StageStyle.TRANSPARENT);
        winStage.setScene(winScene);
        winStage.show();
        winScene.setOnMouseClicked(e -> {
            winStage.close();
        });
    }

    private void displayWinLoseMessagehelper(StackPane pane, String message, boolean lost) {
        winningText = new Text(message);
        scoreText = new Text("\n\n\nScore:");
        score = new Text("\n\n\n\n\n" + human.getScore() + "-" + computer.getScore());
        winningText.setId("text-win");
        scoreText.setId("score-win");
        score.setId("score");
        winningText.setFill(Color.LAWNGREEN);
        scoreText.setFill(Color.CHOCOLATE);
        score.setFill(Color.NAVY);
        //image of trophy
        if (lost) {
            trophy = new ImageView(new Image(new File(trophyPath + "smiley_sad.gif").toURI().toString()));
        } else {
            trophy = new ImageView(new Image(new File(trophyPath + "trophy.gif").toURI().toString()));
        }
        trophy.setFitHeight(60);
        trophy.setFitWidth(60);
        trophy.setTranslateY(40);
        pane.getChildren().addAll(winningText, trophy, scoreText, score);
    }
//----------------------------End winning message display-------------------------------------

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
            case PLAYER_SELECT:
                if (!gameGoing) {
                    mainPane.getChildren().clear();
                    mainPane.setCenter(playerSelectionPane);
                } else {
                    mainPane.getChildren().clear();
                    mainPane.setCenter(gamePane);
                }
                break;
            case GAME_SCREEN:
                mainPane.getChildren().clear();
                mainPane.setCenter(gamePane);
                break;
            case EXIT_REQUEST:
                System.exit(0);
                primaryStage.setOnCloseRequest((WindowEvent t) -> {
                    Platform.exit();
                });
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
//------------------------popup--------------------

    public void showPopup(Stage stage, int width, int height, String name) {
        final Circle circle = new Circle(50);
        final Text text = createText(name);
        circle.setStroke(Color.FORESTGREEN);
        circle.setStrokeWidth(10);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setFill(Color.AZURE);
        circle.relocate(0, 0);
        Tooltip.install(circle, new Tooltip("Click here to dismiss"));

        popup.setX(width - 50);
        popup.setY((height + 90) / 2);
        popup.getContent().addAll(circle, text);
        popup.show(stage);
        circle.setOnMouseClicked(e -> {
            popup.hide();
        });
    }

    private void centerText(Text text) {
        double W = text.getBoundsInLocal().getWidth();
        double H = text.getBoundsInLocal().getHeight();
        text.relocate(50 - W / 2, 50 - H / 2);//150 is radius
    }

    private Text createText(String name) {
        final Text text = new Text(name + "\n Scored");
        text.setFont(new Font(12));
        text.setBoundsType(TextBoundsType.VISUAL);
        centerText(text);
        return text;

    }

    public void closePopup() {
        new Timeline(new KeyFrame(
                Duration.millis(2000),
                e -> popup.hide()))
                .play();
    }

//-----------------------------------------------------
    
}
