package core;
import java.sql.*;

public class Main {
	public static void main(String[] args) {
		Hero hero1 = new Hero(0, "Hero!", 10, 3);
		Monster monster1 = new Monster(1, "Monster1!", 10,1);
		monster1.addLoot(new Loot(new Item(1,"Item test", "this is a test item"), 1));
		
		Battle battle = new Battle(hero1, monster1);
		
		while(battle.alive()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			battle.Step();
			
			for(Action action: battle.getBattleStepActions()) {
				if(action.textView() != null) {
					System.out.println(action.textView());
				}
			}
			
		}

	}
	/*
	public static void main(String[] args) {
		try{  
			Class.forName("com.mysql.jdbc.Driver").newInstance();  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/DatabaseGame","root","root");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from player");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
			
	}*/
}
