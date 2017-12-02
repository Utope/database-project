package core;

import java.util.ArrayList;

public class Player {
    
    private int playerId;
    private String username;
    private String password;
	
    public Player(int playerId, String username,String password){
            this.playerId = playerId;
            this.username = username;
            this.password = password;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String toString(){
        return "[Player " + this.playerId + ", username=" + this.username + "]";
    }
	
}
