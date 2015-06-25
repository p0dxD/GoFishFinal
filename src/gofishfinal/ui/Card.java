/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.ui;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author Joseph
 */
public class Card{
    public static int CARDS_MADE = 0;
    
//    private ImageView imageView;
//    private String imageName;
    private String rank;
    private String suit;
    private boolean flipped;
    
    
    public Card(){
        this("","");
    }
    public Card(String rank){
        this.rank = rank;
        CARDS_MADE++;
    }

    public Card(String suit, String rank){
        this.suit = suit;
        this.rank = rank;
        CARDS_MADE++;
    }
    public void setRank(String rank){
        this.rank = rank;
    }
    public void setSuit(String suit){
        this.suit = suit;
    }
    public String getImageName(){
        return "Playing_card_"+getSuit()+"_"+getRank()+".jpg";
    }
    public String getRank(){
        return rank;
    }
    public String getSuit(){
        return suit;
    }
    public void flip(){
        flipped = !flipped;
        
    }
    public boolean isFlipped(){
        return flipped;
    }
//    public ImageView getImageView(){
//        return  new ImageView(new Image(new File
//        ("images/cards/"+getImageName()).toURI().toString()));
//    }
    @Override
    public String toString(){
        return "Rank: "+getRank()+"\nSuit: "+getSuit()+
                "\nFlipped: "+ isFlipped()+"\nImage call: "+getImageName();
    }
    
    

//    public static void main(String[] args){
//        Card test = new Card();
//        System.out.println(test);
//        
//        Card testTwo = new Card("2","diamond");
//        testTwo.flip();
//        System.out.println(testTwo);
//        System.out.println("Cards we have: "+Card.CARDS_MADE);
//    }


}
