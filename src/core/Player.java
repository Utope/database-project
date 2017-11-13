package core;

import java.util.ArrayList;

public class Player {
	private int playerId;
	private Hero currentHero;
	private Inventory inventory;
	private Battle currentBattle;
	
	public Player(){
		this.inventory = new Inventory();
	}
	
	public int getPlayerId() {
		return this.playerId;
	}
	
	public Hero getCurrentHero() {
		return this.currentHero;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public void setCurrentHero(Hero hero) {
		this.currentHero = hero;
	}
	
	public void setCurrentBattle(Battle battle) {
		this.currentBattle = battle;
	}
	
	
}
