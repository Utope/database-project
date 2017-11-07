
public class Main {
	public static void main(String[] args) {
		Entity hero1 = new Hero(0, "Hero!", 10, 3);
		Entity monster1 = new Monster(1, "Monster1!", 10,1);
		
		Battle battle = new Battle(hero1, monster1);
		
		while(battle.alive()) {
			battle.Step();
		}
		
		for(Action action: battle.getAllBattleActions()) {
			System.out.println(action.textView());
		}

		
	}
}
