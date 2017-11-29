/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import database.Repository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robert
 */
public class PlayerManager{
    private static PlayerManager playerManager = new PlayerManager();
    
    private ArrayList<Player> players;
    
    public static PlayerManager Instance(){
        return PlayerManager.playerManager;
    }
    
    private PlayerManager(){
        players = new ArrayList<Player>();
    }
    
    public boolean addPlayer(Player player){
        players.add(player);
        return true;
    }
    
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    
    public void init(){
        try {
            Repository.Instance().loadPlayers();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
