/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfinal.ui;

/**
 *
 * @author Joseph
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
        if(whoseTurn.equals("human")){
            ui.getHuman().setIsTurn(true);
            ui.processPlayerMove();
            
        }else{
            ui.getComputer().setIsTurn(true);
            ui.processComputerTurn();
        }
    }
    
}
