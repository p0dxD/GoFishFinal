/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.ui;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author Jose
 */
public class Deck {
    public static int DECKS_MADE = 0;
    private static ArrayList<Card> deck;
    private String[] suitOptions = {"club", "diamond", "heart", "spade"};
    private String[] rankOptions = {"J", "Q", "K", "A"};
    private String cardImagesPath = "images/cards";
    private Deck instance = null;
    private int sizeOfDeck;
    
    public Deck(){
        this(52);
    }
    public Deck(int sizeOfDeck){
        this.sizeOfDeck = sizeOfDeck;
        fill();
        shuffle();
    }
    public void fill() {
        deck = new ArrayList<>();
        while (Card.CARDS_MADE < sizeOfDeck) {
            deck.add(new Card());
        }
        int size = 0;
        for (int i = 0; i < 4; i++) {
            int rank = 2;
            for (int j = 0; j < 13; j++) {
                if (rank > 10) {
                    deck.get(size).setRank(rankOptions[rank - 11]);
                } else {
                    deck.get(size).setRank(rank + "");
                }
                deck.get(size++).setSuit(suitOptions[i]);
                rank++;
            }
        }
    }
    public static ArrayList<Card> getDeck(){
        return deck;
    }
    public void shuffle(){
        Object[] cards = deck.toArray();
        for(int i = 0; i < cards.length; i++){
            int r = i + (int)(Math.random()*cards.length-i);
            Object temp = cards[r];
            cards[r]  = cards[i];
            cards[i] = temp;
        }
        ListIterator it = deck.listIterator();
        for (Object card : cards) {
            it.next();
            it.set(card);
        }
    }
    public Card getCardFromDeck(){
        if(!deck.isEmpty()){
            return deck.remove(deck.size()-1);
        }else{
            System.out.println("Is empty");
            return null;
        }
    }
    public int getDeckSize(){
        return deck.size();
    }
    public boolean isEmpty(){
        return deck.isEmpty();
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        Deck test = new Deck();
//        Card card = test.getCardFromDeck();
//        System.out.println(card+"\nSize: "+test.getDeckSize());
//        Card cardTwo = test.getCardFromDeck();
//        System.out.println(cardTwo+"\nSize: "+test.getDeckSize());
//
////        test.fill();
//    }

}
