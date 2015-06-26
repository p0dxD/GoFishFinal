/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.players;

import java.util.Random;

/**
 *
 * @author Joseph
 */
public class Computer extends Player{
    private static String pick = null;
    public Computer(){
        
    }
    public Computer(String name, int size){
        super(name, size);
    }
    /**
     * Checks if hand has the number
     * @return 
     */
    public String makeMove(){
        //(char)('a' + Math.random() * ('z'-'a' + 1)
        //char from a -z randomly
        pick = null;
        String[] cardOptions = {"A", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "J", "Q", "K"};
        
        int ran;
        while(pick==null){
            ran = (int)(Math.random()*cardOptions.length-1);
        if(super.contains(cardOptions[ran])>-1) {
            pick = cardOptions[ran];
            }
        }
        return pick;
    }
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        Computer  test = new Computer();
//        System.out.println(test.getHand());
//        while(pick==null){
//            test.makeMove();
//        }
//        System.out.println(pick);
//    }
    
}
