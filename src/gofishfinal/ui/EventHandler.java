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
public class EventHandler {
    private GoFishGUI ui;
    
    public EventHandler(GoFishGUI ui){
        this.ui = ui;
    }
    public void respondToSwitchScreenRequest(ScreenState state){
        ui.changeSpace(state);
    }
}
