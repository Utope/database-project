package core;

import database.Repository;
import java.util.Iterator;

import core.commands.CommandHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import ui.MainGame;
import ui.login;

public class Game implements Runnable{
	//private static Player player;
	private static CommandHandler commandHandler;
	private static Battle currentBattle;
	private static EntityFactory entityFactory = new EntityFactory();
	private static Player player;
	private static MainGame screen;
        
	private static boolean cont = true;
	private static boolean loaded = false;
        
        public Game(String username, String password){
            Game.load(username, password);
        }
        public static void load(String username, String password){
        
        Game.player = Repository.Instance().loadPlayer(username, password);
            
        Game.cont = true;
        Game.loaded = true;
        }
        
        public static void setScreen(MainGame screen){
            Game.screen = screen;
        }
        
	public static Player getPlayer() {
		if(Game.player == null) {
			try {
				throw new Exception("Can't getPlayer when player is null. Must load from Database first.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return Game.player;
	}
	
	public static EntityFactory getEntityFactory() {
		return Game.entityFactory;
	}
	
	public static CommandHandler getCommandHandler() {
		if(Game.commandHandler == null) {
			Game.commandHandler = new CommandHandler();
		}
		return Game.commandHandler;
	}
	
	public static void step(){
		Game.currentBattle.step();
		Game.getCommandHandler().execute();
	}
        
        public static void loop(){
            if(Game.currentBattle == null){
                Game.currentBattle = new Battle(Game.player.getCurrentHero(), entityFactory.createMonster());
            }
            
		
		
		while(Game.cont) {
			
			Game.step();
			
			if(!Game.currentBattle.alive()) {
				if(Game.player.getCurrentHero().isAlive()) {
					Game.currentBattle = new Battle(Game.player.getCurrentHero(), entityFactory.createMonster());
				}else {
					Game.cont = false;
				}
			}
		}
		
		Iterator it = Game.getPlayer().getInventory().iterator();
		while(it.hasNext()) {
			Item item = (Item) it.next();
			System.out.println(item.getName());
		}
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
           // Game.loop();
            
            Game.screen.repaint();
        }
        
        Game.screen.dispose();
    }
	
}
