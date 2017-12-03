/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.logging.Level;
import java.util.logging.Logger;
import ui.MainGame;
import ui.StatsScreen;

/**
 *
 * @author robert
 */
public class StatsScreenMain implements Runnable {

    private static StatsScreen sceen;
    
    @Override
    public void run() {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                StatsScreenMain.sceen = new StatsScreen();
                StatsScreenMain.sceen.setVisible(true);
            }
        });
         
         while(StatsScreenMain.sceen == null){
            try {
                System.out.println("Sleeping for a second to let Gui catch up");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
