package core;

import database.Repository;
import java.util.ArrayList;
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
        private static Battle battle = null;
        
        public static Game Instance(){
            if(game == null){
                game = new Game(Game.player);
                
            }
            return game;
        }
        
        public Game(Player player){
            Game.player = player;
            Game.cont = true;
            Game.game = this;
        }
        
        public Battle getBattle(){
            return Game.battle;
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
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while(Game.cont){
            if(EntityManager.Instance().getCurrentPlayerEntity(player) == null){
                PlayerManager.Instance().createNewEntityforPlayer(player);
                System.out.println(EntityManager.Instance().getCurrentPlayerEntity(player));
            }
            
            
            if(Game.battle == null){
                Game.battle = new Battle(
                        EntityManager.Instance().getCurrentPlayerEntity(player),
                        EntityManager.Instance().createEntity(
                                EntityManager.Instance().getRandomEntityType(), 
                                null
                        )
                );
            }
            
            if(Game.battle.isBattleOver()){
                Repository.Instance().saveBattle(Game.battle);
                if(Game.battle.playerEntity.getCurrentHealth() <= 0 &&Game.battle.computerEntity.getCurrentHealth() <= 0){
                     PlayerManager.Instance().createNewEntityforPlayer(player);
                     Game.battle = new Battle(
                            EntityManager.Instance().getCurrentPlayerEntity(player), EntityManager.Instance().createEntity(
                            EntityManager.Instance().getRandomEntityType(), 
                            player
                            )
                    );
                }else if(Game.battle.playerEntity.getCurrentHealth() <= 0){
                    PlayerManager.Instance().createNewEntityforPlayer(player);
                    Game.battle = new Battle(EntityManager.Instance().getCurrentPlayerEntity(player), EntityManager.Instance().findEntityById(Game.battle.computerEntity.getEntityId()));
                }else if(Game.battle.computerEntity.getCurrentHealth() <= 0){
                    Game.battle = new Battle(
                            EntityManager.Instance().getCurrentPlayerEntity(player), EntityManager.Instance().createEntity(
                            EntityManager.Instance().getRandomEntityType(), 
                            player
                            )
                    );
                }
                
            }
            
            Game.battle.battleStep();
            Game.battle.pollQueuedActions();
            
            ArrayList<Action> stepActions = Game.battle.getStepActions();
            
            for(Action action : stepActions){
                Game.screen.setText(action.getLog());
            }
            
            ArrayList<ItemInstance> playerItems = ItemManager.Instance().getPlayerInventory(player);
            String itemString = "";
            for(ItemInstance item : playerItems){
                itemString += "[" + item.getTimestamp() + "]" + " " + item.getItem().getName() + "\n";
            }
            
            Game.screen.setPlayerItems(itemString);
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }      
            
            
            Game.screen.repaint();
        }
        
        Game.screen.dispose();
    }
	
}
