
package gofishfinal.players;

/**
 *
 * @author Joseph
 */
public class Computer extends Player{
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
        return super.getHand().get((int)(Math.random()*super.getHand().size())).getRank();
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
