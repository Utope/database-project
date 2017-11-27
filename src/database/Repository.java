package database;
import core.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Repository {
	
	private static Repository repo = new Repository("jdbc:mysql://localhost:3306/DatabaseGame", "root", "root");
	private Connection conn;
	
	private Repository(String url, String username, String password){
		try{  
			Class.forName("com.mysql.jdbc.Driver").newInstance();  
			this.conn = DriverManager.getConnection(url, username, password); 
			//here sonoo is database name, root is username and password  
			
		}catch(Exception e){ 
			System.out.println(e);
		}  
	}
	
	public static Repository Instance() {
		return Repository.repo;
	}
        
        public Connection getConn(){
            return this.conn;
        }
        
        public boolean doPlayerCredentialsExits(String username, String password){
            
            Statement stmt = null;
             String query = "select username, password from player where username=\"" + username +"\" AND password=\"" + password + "\""; 
            try {
            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return false;
       }
	
}
