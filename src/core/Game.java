package core;

import database.Repository;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import ui.MainGame;
import ui.login;

public class Game implements Runnable{
	//private static Player player;
        
        private static Game game;
        private static Player player;
        private static boolean cont = true;
        private static boolean loaded = false;
        private static MainGame screen;
        
        public static Game Instance(){
            if(game == null){
                game = new Game(Game.player);
            }
            return game;
        }
        
        public Game(Player player){
            Game.player = player;
        }
        
        
        public void setScreen(MainGame screen){
            this.screen = screen;
        }
        
	public Player getPlayer() {
		return this.player;
	}
		
	public static void main(String[] args) {
            
		
	}

    @Override
    public void run() {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Game.screen = new MainGame();
                Game.screen.setVisible(true);
            }
        });
        
        
        
        
        while(Game.screen == null){
            try {
                System.out.println("Sleeping for a second to let Gui catch up");
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while(Game.cont){
            Game.screen.getTestLabel().setText(String.valueOf(System.currentTimeMillis()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(Game.player.getUsername());
            
            
            
            Game.screen.repaint();
        }
        
        Game.screen.dispose();
    }
	
}
