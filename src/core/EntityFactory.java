package core;

public class EntityFactory {
	
	public EntityFactory() {
		
	}
	
	public Hero createHero() {
		return new Hero(0, "Hero!", 10, 3);
	}
	
	public Monster createMonster() {
		Monster monster1 = new Monster(1, "Monster1!", 10,1);
		monster1.addLoot(new Loot(new Item(1,"Item test", "this is a test item"), 1));
		return monster1;
	}
}
