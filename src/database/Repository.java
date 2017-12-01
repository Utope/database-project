package database;

import core.Entity;
import core.EntityItemDrop;
import core.EntityManager;
import core.EntityType;
import core.Inventory;
import core.Item;
import core.ItemManager;
import core.ItemInstance;
import core.Player;
import core.PlayerManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


//Make sure using correct rs vs rs2


public class Repository {

    private static Repository repo = new Repository("jdbc:mysql://localhost:3306/DatabaseGame", "root", "root");
    private Connection conn;

    private Repository(String url, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection(url, username, password);
            //here sonoo is database name, root is username and password  

        } catch (Exception e) {
            System.out.println(e);
        }
        

    }

    public static Repository Instance() {
        return Repository.repo;
    }

    public Connection getConn() {
        return this.conn;
    }

    public boolean doPlayerCredentialsExits(String username, String password) {

        Statement stmt = null;
        String query = "select username, password from player where username=\"" + username + "\" AND password=\"" + password + "\"";
        try {
            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
        public Inventory loadPlayerInventory(String playerId){
            
        }
     */
    
    
   
    
   // public ICommand createAction(){
        
    //}
    
    public Entity createEntity(EntityType entityType, Player player){
        try {
            Statement stmt = this.conn.createStatement();
            
            String playerId = null;
            if(player != null){
                playerId = String.valueOf(player.getPlayerId());
            }
            
            
            String query = "insert into entity "
                    + "(player_id, entity_type_id, name, health, currentHealth, attack, defense, hit) values "
                    + "(" + ((playerId == null) ? null : "\"" + playerId + "\"") + "," 
                    + "\"" + entityType.getId() + "\"" + ","
                    + "\"" + "testies" + "\"" + "," // add random name pick here
                    + "\"" + entityType.getBase_health() + "\"" + ","
                    + "\"" + entityType.getBase_health() + "\"" + ","
                    + "\"" + entityType.getBase_attack()+ "\"" + ","
                    + "\"" + entityType.getBase_defense() + "\"" + ","                   
                    + "\"" + entityType.getBase_hit() + "\"" + ")";
            
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int entityId = rs.getInt(1);
            
            Entity entity = new Entity(
                    entityId, 
                    player, 
                    entityType, 
                    new Inventory(), 
                    "testies", // rand name also goes here
                    entityType.getBase_health(),
                    entityType.getBase_health(),
                    entityType.getBase_attack(),
                    entityType.getBase_defense(),
                    entityType.getBase_hit()
            );
            
            //ItemDropIterator        
            Iterator it = entityType.getItemDrops().iterator();
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            while(it.hasNext()){
                EntityItemDrop drop = (EntityItemDrop) it.next();
                if(drop.roleDrop()){
                    int count = drop.roleDropCount();
                    for(int i = 0; i < count ; i++){
                        ItemInstance inst = ItemManager.Instance().createItemInstance(drop.getItem(), timestamp);
                        Repository.Instance().addInstanceItemToInventory(inst, entity);
                        
                    }
                }
            }                      
            return entity;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    public ItemInstance createItemInstance(Item item, String timestamp){
        
        try {
            Statement stmt = this.conn.createStatement();
            String query = "insert into item_instance (created, item_id) values (" + "\"" + timestamp + "\"" + "," + "\"" + item.getItemId() + "\"" + ")";
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();

            ItemInstance itemInstance = new ItemInstance(rs.getInt(1), item, timestamp);            
            return itemInstance;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    public Item createItem(String name, String description){
        ItemManager manager = ItemManager.Instance();
        
        try {
            Statement stmt = this.conn.createStatement();
            String query = "insert into item (name, description) values (" + "\"" + name + "\"" + "," + "\"" + description + "\"" + ")";
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            System.out.println(rs.getInt(1));
            
            Item item = new Item(rs.getInt(1), name, description);
            return item;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
        
    }
    
    public Player createPlayer(String username, String password){
        try {
            Statement stmt = this.conn.createStatement();
            String query = "insert into player (username, password) values (" + "\"" + username + "\"" + "," + "\"" + password + "\"" + ")";
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();

            Player player = new Player(rs.getInt(1), username, password, new Inventory()); 
            return player;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    public void addInstanceItemToInventory(ItemInstance itemInstance, Entity entity){
         try {
            Statement stmt = this.conn.createStatement();
            String query = "insert into entity_inventory (item_instance_id, entity_id) values (" + "\"" + itemInstance.getItemInstanceId() + "\"" + "," + "\"" + entity.getEntityId() + "\"" + ")";
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            
            entity.getInventory().add(itemInstance);
            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addInstanceItemToInventory(ItemInstance itemInstance, Player player){
        try {
            Statement stmt = this.conn.createStatement();
            String query = "insert into player_inventory (item_instance_id, player_id) values (" + "\"" + itemInstance.getItemInstanceId() + "\"" + "," + "\"" + player.getPlayerId() + "\"" + ")";
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            
            player.getInventory().add(itemInstance);
            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public ArrayList<EntityType> getAllEntityTypes(){
        
        ArrayList<EntityType> entityTypes = new ArrayList<>();
        try {
            Statement stmt = this.conn.createStatement();
            String query = "select id, name, description, base_attack, base_defense, base_hit, base_health from entity_type";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                Statement stmt2 = this.conn.createStatement();
                String query2 = "select id, item_id, entity_type_id, dropChance, minCount, maxCount from entity_type_drop where entity_type_id=" + rs.getInt("id");
                ResultSet rs2 = stmt2.executeQuery(query2);
                
                ArrayList<EntityItemDrop> drops = new ArrayList<>();
                
                while(rs2.next()){
                    Item item = ItemManager.Instance().findItemById(rs.getInt("item_id"));
                    if(item != null){
                       drops.add(new EntityItemDrop(rs2.getInt("id"), item, rs2.getInt("dropChance"), rs2.getInt("minCount"), rs2.getInt("maxCount")));
                    }else{
                        throw new Exception("Searched for non existant entityType while trying to add entityDrop");
                    }
                }
                
                
                entityTypes.add(new EntityType(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("base_attack"),
                        rs.getInt("base_defense"),
                        rs.getInt("base_hit"),
                        rs.getInt("base_health"),
                        drops
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entityTypes;
    }
    
    
    public ArrayList<Player> getAllPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        try {
            PlayerManager manager = PlayerManager.Instance();
            Statement stmt = this.conn.createStatement();
            String query = "select id, username, password from player";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                int playerId = rs.getInt("id");
                
                Statement stmt2 = this.conn.createStatement();
                String query2 = "select item_instance_id from player_inventory where player_id=" + playerId;
                ResultSet rs2 = stmt2.executeQuery(query2);
                
                Inventory inventory = new Inventory();
                
                while (rs2.next()) {
                    
                    ItemInstance itemInstance = ItemManager.Instance().findItemInstanceById(rs.getInt("item_instance_id"));
                    
                    if(itemInstance != null){
                        inventory.add(itemInstance);
                    }
                    
                }
                
                players.add(new Player(playerId, rs.getString("username"), rs.getString("password"), inventory));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return players;
    }
    
    public ArrayList<Entity> getAllEntitys(){
        ArrayList<Entity> entitys = new ArrayList<>();
        try {
            Statement stmt = this.conn.createStatement();
            String query = "select id, player_id, entity_type_id, name, health, currentHealth, attack, defense, hit from entity";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                
                // find the associate player can be null for monsters
                int playerId = rs.getInt("player_id");
                int entityId = rs.getInt("id");
                int entityTypeId = rs.getInt("entity_type_id");
                
                Player player = PlayerManager.Instance().findPlayerById(rs.getInt("player_id"));
                
                EntityType entityType = EntityManager.Instance().getEntityTypeById(rs.getInt("entity_type_id"));
                
                if(entityType == null){
                    throw new Exception("EntityType doesnt exist for entity_type_id. database is courupt or entityTypes arnt loaded");
                }              
                
                Statement stmt2 = this.conn.createStatement();
                String query2 = "select item_instance_id from entity_inventory where entity_id=" + entityId;
                ResultSet rs2 = stmt2.executeQuery(query2);
                
                Inventory inventory = new Inventory();
                
                while (rs2.next()) {
                    ItemInstance itemInstance = ItemManager.Instance().findItemInstanceById(rs2.getInt("id"));
                    if(itemInstance != null){
                        inventory.add(itemInstance);
                    }
                }
                
                entitys.add(new Entity(
                        rs.getInt("id"),
                        player,
                        entityType,
                        inventory,
                        rs.getString("name"),
                        rs.getInt("health"),
                        rs.getInt("currenthealth"),
                        rs.getInt("attack"),
                        rs.getInt("defense"),
                        rs.getInt("hit")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entitys;
    }
        
    public ArrayList<Item> getAllItems(){
       
        ArrayList<Item> items = new ArrayList<>();
        
        try {
            Statement stmt = this.conn.createStatement();
            String query = "select id, name, description from item";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                items.add(new Item(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    public ArrayList<ItemInstance> getAllItemInstances(){
        
         ArrayList<ItemInstance> itemInstances = new ArrayList<>();
        
         try {
            
            Statement stmt = this.conn.createStatement();
            String query = "select id, item_id, created from item_instance";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                Item item = ItemManager.Instance().findItemById(rs.getInt("item_id"));
                if(item == null){
                    throw new Exception("Error ItemInstance exists without existing item. Database courupt or itemTypes is not loaded");
                }
                
                itemInstances.add(new ItemInstance(rs.getInt("id"), item, rs.getString("created")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itemInstances;
        
    }
    
    public static void main(String[] args) {
        ItemManager.Instance().init();
        PlayerManager.Instance().init();
        EntityManager.Instance().init();
        
      Iterator typeIt =  EntityManager.Instance().getEntitys().iterator();
        while(typeIt.hasNext()){
            Entity e = (Entity) typeIt.next();
            if(e.getEntityId() == 4){
                Iterator it = e.getInventory().iterator();
                while(it.hasNext()){
                    ItemInstance drop = (ItemInstance) it.next();
                    System.out.println(drop.getItem());
                }
            }
        }
        //Iterator it = null;
        /*

        it = EntityManager.Instance().getEntityTypes().iterator();
        System.out.println("~EntityTypes~");
        while (it.hasNext()) {
            EntityType entType = (EntityType) it.next();
            
            Iterator itt = entType.getItemDrops().iterator();
            while(itt.hasNext()){
                EntityItemDrop drop = (EntityItemDrop) itt.next();
                System.out.println(drop.getItem());
                System.out.println(drop.getDropChance());
                System.out.println(drop.getMinCount());
                System.out.println(drop.getMaxCount());
            }
        }
*/
       
    }
     
}
