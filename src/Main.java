
public class Main {
	public static void main(String[] args) {
		Hero hero1 = new Hero(0, "Hero!", 10, 3);
		Monster monster1 = new Monster(1, "Monster1!", 10,1);
		monster1.addLoot(new Loot(new Item(1,"Item test", "this is a test item"), 1));
		
		Battle battle = new Battle(hero1, monster1);
		
		while(battle.alive()) {
			battle.Step();
		}
		
		for(Action action: battle.getBattleActions()) {
			System.out.println(action.textView());
		}

		
	}
}
