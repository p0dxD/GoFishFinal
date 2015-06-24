/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.players;

/**
 *
 * @author Joseph
 */
public class Human extends Player{
    
    public Human(){
    }
    public Human(String name){
        super(name);
    }
    public Human(String name, int size){
        super(name,size);
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
////        Human test = new Human("Jose");
////        System.out.println("Name: "+test.getName());
////        test.sortHand();
////        System.out.println("Sorted: "+test.getHand());
////        test.increaseScore();
////        System.out.println(test.getScore());
////        System.out.println();
//        Human testTwo = new Human("Jesus",50);
//        System.out.println("Name: "+testTwo.getName());
//        testTwo.sortHand();
//        System.out.println("Sorted: "+testTwo.getHand());
//        System.out.println("Sorted: "+testTwo.getHand().size());
//        testTwo.increaseScore();
//        System.out.println(testTwo.getScore());
//        System.out.println(testTwo.contains("K"));
//        System.out.println(testTwo.contains("2"));
//        System.out.println(testTwo.contains("1"));
//        System.out.println(testTwo.hasMatchingFour());
//        System.out.println("-----------------------------------------------");
//        System.out.println("Sorted: "+testTwo.getHand());
//        System.out.println("Sorted: "+testTwo.getHand().size());
//    }
    
}
