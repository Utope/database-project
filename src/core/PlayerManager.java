/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import database.Repository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
        
    }
    
    public Player createPlayer(String username, String password){
         Player player = Repository.Instance().createPlayer(username, password);
        if(player != null){
            players.add(player);
        }
        return player;
    }
    
    public void createNewEntityforPlayer(Player player){
        Repository.Instance().createEntity(EntityManager.Instance().getRandomEntityType(), player);
    }
    
    public Player findPlayerByUsername(String username){
        Iterator it = players.iterator();
        while(it.hasNext()){
            Player player = (Player) it.next();
            if(player.getUsername().equals(username)){
                return player;
            }
        }
        return null;
    }
    
    public Player findPlayerById(int id){
        Iterator it = players.iterator();
        while(it.hasNext()){
            Player player = (Player) it.next();
            if(player.getPlayerId() == id){
                return player;
            }
        }
        return null;
    }
        
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    
    public void init(){
        players = Repository.Instance().getAllPlayers();
    }
    
    public Iterator getPlayerIterator(){
        return this.players.iterator();
    }
}
