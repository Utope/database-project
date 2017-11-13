package core;

import java.util.Iterator;

import core.commands.CommandHandler;

public class Game {
	private static Player player;
	private static CommandHandler commandHandler;
	private static Battle currentBattle;
	private static EntityFactory entityFactory = new EntityFactory();
	
	
	
	private static boolean cont = true;
	
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
	
	public static void loadPlayer() {
		Game.player = new Player();
		Game.player.setCurrentHero(entityFactory.createHero());
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
	
	public static void main(String[] args) {
		
		Game.loadPlayer();
		Game.currentBattle = new Battle(Game.player.getCurrentHero(), entityFactory.createMonster());
		
		
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
	
}
