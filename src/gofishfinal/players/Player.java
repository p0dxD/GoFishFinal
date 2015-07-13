package gofishfinal.players;

import gofishfinal.ui.Card;
import gofishfinal.ui.Deck;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Jose Rodriguez
 */
public class Player implements Comparable<Player>, Serializable{

    public static Deck instance;
    public static HeapSort sortInstance;

    private ArrayList<Card> hand = new ArrayList<>();
    private int handSize = 5;
    private String name;
    private HeapSort sort;
    private String nameOfCardObtainedFromDeck = null;
    private int score = 0;
    private boolean isTurn;

    private boolean containsIt;
    public Player() {
        this("");
    }

    public Player(String name) {
        this(name, 5);
    }

    public Player(String name, int handSize) {
        this.handSize = handSize;
        this.name = name;
        instance = getInstance();
        sortInstance = getInstanceSort();
        createHand();
    }

    public void createHand() {
        for (int i = 0; i < handSize; i++) {
            hand.add(i, instance.getCardFromDeck());
        }
        sortHand();
        System.out.println(instance.getDeckSize());
    }

    public void getCardFromDeck() {
        if(!instance.isEmpty()){
        Card cardFromDeck = instance.getCardFromDeck();
        nameOfCardObtainedFromDeck = cardFromDeck.getRank();
        hand.add(cardFromDeck);
        sortHand();
        }else{
            System.out.println("Its empty now");
        }
    }
    public String getRankOfDeckCard(){
        return nameOfCardObtainedFromDeck;
    }
    public void sortHand() {
        sortInstance.sort(getHand());
    }

    public void increaseScore() {
        score++;
    }

    public boolean contains(String rank) {
        containsIt = false;
        for(Card card: this.getHand()){
            if(card.getRank().equals(rank)){
                containsIt = true;
            }
        }
        return containsIt;      
//        int lo = 0;
//        int high = getHand().size() - 1;
//
//        while (lo <= high) {
//            int mid = lo + (high - lo) / 2;
//            if (rank.compareTo(getHand().get(mid).getRank()) < 0) {
//                high = mid - 1;
//            } else if (rank.compareTo(getHand().get(mid).getRank()) > 0) {
//                lo = mid + 1;
//            } else {
//                return true;
//            }
//        }
//        return false;

    }

    public boolean hasMatchingFour() {
        boolean hasFour = false;
        String[] cardOptions = {"A", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "J", "Q", "K"};
        int goal = 4;
        final Map<String, Integer> countMap = new HashMap<>();
        for (String cardNames : cardOptions) {
            int count = 0;
            for (int j = 0; j < getHand().size(); j++) {
                if (countMap.containsKey(cardNames) && getHand().get(j).getRank().equals(cardNames)) {
                    count = countMap.get(cardNames) + 1;
                } else {
                    count = 1;
                }
                if (getHand().get(j).getRank().equals(cardNames)) {
                    countMap.put(cardNames, count);
                }
            }
        }
        for(Map.Entry<String, Integer> entry: countMap.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value == goal){
                System.out.print("Key: " + key);
                System.out.println( " value" + value);
                increaseScore();
                removeFromHand(key);
                hasFour =  true;
            }
        }
        return hasFour;
    }
    public void removeFromHand(String card){
        ArrayList<Card> temp = new ArrayList<>();
        for (Card cards : getHand()) {
            if (!cards.getRank().equals(card)) {
                temp.add(cards);
            }
        }
        hand = temp;
        System.out.println("Removed " + card);
    }
    public void removeFromHandAndHandToPlayer(String card,Player player){
        ArrayList<Card> temp = new ArrayList<>();
        for (Card cards : getHand()) {
            if (!cards.getRank().equals(card)) {
                temp.add(cards);
            }else{
                player.getHand().add(cards);
            }
        }
        
        hand = temp;
        player.sortHand();
        System.out.println("Removed " + card);
    }
    public void addToHand(Card card){
        getHand().add(card);
        sortHand();
    }
    public int getScore() {
        return score;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
            Deck.DECKS_MADE++;
        }
        return instance;
    }

    private HeapSort getInstanceSort() {
        if (sortInstance == null) {
            sortInstance = new HeapSort();
        }
        return sortInstance;
    }

    public boolean isIsTurn() {
        return isTurn;
    }
    public void setIsTurn(boolean isTurn) {
        this.isTurn  = isTurn;
    }

    public void endTurn() {
        if(isTurn){
        isTurn = !isTurn;
        }else{
            System.out.println("Not its turn");
        }
    }

    /**
     * To compare the scores see who wins
     * @param t
     * @return 
     */
    @Override
    public int compareTo(Player t) {
        if(score < t.score){
            return -1;
        }else if(score > t.score){
            return 1;
        }else{
            return 0;
        }
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        Player player = new Player("test", 7);
//        player.getCardFromDeck();
//        for (int i = 0; i < player.getHand().size(); i++) {
//            System.out.println(player.getHand().get(i));
//        }
//        player.sortHand();
//        System.out.println("--------------Sorted--------------------");
//        for (int i = 0; i < player.getHand().size(); i++) {
//            System.out.println(player.getHand().get(i));
//        }
//        System.out.println("----------------------------------");
//        Player playerTwo = new Player("test", 7);
//        playerTwo.getCardFromDeck();
//        for (int i = 0; i < playerTwo.getHand().size(); i++) {
//            System.out.println(playerTwo.getHand().get(i));
//        }
//        playerTwo.sortHand();
//        System.out.println("--------------Sorted--------------------");
//        for (int i = 0; i < playerTwo.getHand().size(); i++) {
//            System.out.println(playerTwo.getHand().get(i));
//        }
////        System.out.println(Integer.parseInt(""));
//        System.out.println("3".compareTo("2"));
////        System.out.println(player);
////        Player playerTwo = new Player();
////        System.out.println(Deck.DECKS_MADE);
//    }

}
