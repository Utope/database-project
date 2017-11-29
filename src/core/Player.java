package core;

import java.util.ArrayList;

public class Player {
    
    private int playerId;
    private String username;
    private String password;
    private Inventory inventory;
	
    public Player(int playerId, String username,String password, Inventory inventory){
            this.playerId = playerId;
            this.username = username;
            this.inventory = inventory;
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

	
	
}
