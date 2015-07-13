/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.ui;

/**
 *
 * @author Jose
 */
public class EventHandlerGoFish {
    private GoFishGUI ui;
    
    public EventHandlerGoFish(GoFishGUI ui){
        this.ui = ui;
    }
    public void respondToSwitchScreenRequest(ScreenState state){
        ui.changeSpace(state);
    }
    public void whoStarts(String whoseTurn){
        if(whoseTurn.equalsIgnoreCase("human")){
            ui.getHuman().setIsTurn(true);
            ui.processPlayerMove();
            
        }else if("Random".equalsIgnoreCase(whoseTurn)){
            whoStarts(randomStart());
        }else{
            ui.getComputer().setIsTurn(true);
            ui.processComputerTurn();
        }
    }
    public String randomStart(){
        return ((int) ((Math.random()) * 2)==1)?"Human":"Computer";
    }
}
