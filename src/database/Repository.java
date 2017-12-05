package database;

import core.Action;
import core.ActionAttack;
import core.ActionDie;
import core.ActionDropItem;
import core.Battle;
import core.Entity;
import core.EntityItemDrop;
import core.EntityManager;
import core.EntityType;
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
    
    public ArrayList<String> getEntityTypeDrops(EntityType type){
        
        ArrayList<String> items = new ArrayList<>();
        
        try {
            Statement stmt = this.conn.createStatement();
            String query = "select item.name from entity_type "
                          + "inner join entity_type_drop on entity_type.id = entity_type_drop.entity_type_id "
                          + "inner join item on entity_type_drop.item_id = item.id "
                          + "where entity_type.id = " + type.getId() ;
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                items.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    public void addEntityDropToEntityType(Item item, EntityType entityType, int dropChance, int minCount, int maxCount){
        	
        try {
            // (select id from item where name = itemName), #itemId
            //  (select id from entity_type where name = entityTypeName), #entity name
            // /dropchanc,
            // minCount,
            // maxCount);

            Statement stmt = this.conn.createStatement();
            String query = "insert into entity_type_drop (item_id, entity_type_id, dropChance, minCount, maxCount) Values ("
                    + item.getItemId() + ","
                    + entityType.getId() + ","
                    + dropChance + ","
                    + minCount + ","
                    + maxCount + ")";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getPlayerInventory(Player player){
       ArrayList<String> itemNames = new ArrayList<>();
        try {
            
            Statement stmt = this.conn.createStatement();
            String query =  "select item.name from player "
                    + "inner join player_inventory on player.id = player_inventory.player_id "
                    + "inner join item_instance on player_inventory.item_instance_id = item_instance.id "
                    + "inner join item on item_instance.item_id = item.id "
                    + "where player.id=" + player.getPlayerId();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                itemNames.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return itemNames;
    }
    
    public void executeQuery(String query){
         try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createNewEntityType(String name, String description, int base_attack, int base_defense, int base_hit, int base_health){
        try {
            Statement stmt = this.conn.createStatement();
            String query = "insert into entity_type (name, description, base_attack, base_defense, base_hit, base_health)Values("
                    + "\"" + name + "\"" + ","
                    + "\"" + description + "\"" + ","
                    + base_attack + ","
                    + base_defense + ","
                    + base_hit + ","
                    + base_health +")";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveBattle(Battle battle){
         try {
            
            
            for(Action action : battle.getCompletedActions()){
                Statement stmt = this.conn.createStatement();
                String query = "";
                if(action.getParentAction() == null){
                    query = "Insert into action (entity_id, parent_action_id, timestamp) Values ("
                    + action.getEntity().getEntityId() + ","
                    + null + ","
                    + "\"" + action.getTimestamp() + "\""
                    + ")";
                }else{
                    query = "Insert into action (entity_id, parent_action_id, timestamp) Values ("
                    + action.getEntity().getEntityId() + ","
                    + action.getParentAction().getId() + ","
                    + "\"" + action.getTimestamp() + "\""
                    + ")";
                }
                
                stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
            
                action.setId(rs.getInt(1));
            }
            
            for(Action action : battle.getCompletedActions()){
                Statement stmt = this.conn.createStatement();
                String query = "";
                
                if(action instanceof ActionAttack){
                    ActionAttack actionAttack = (ActionAttack) action;
                    query = "Insert into action_attack (actionId, damage, succesful) Values ("
                            + actionAttack.getId() + ","
                            + actionAttack.getDamageDone() + ","
                            + actionAttack.getSuccesful() 
                            + ")";
                }else if(action instanceof ActionDie){
                    ActionDie actionDie = (ActionDie) action;
                    query = "Insert into action_die (actionId, entity_id) Values("
                            + actionDie.getId() + ","
                            + actionDie.getKilledBy().getEntityId()
                            +")";
                }else if(action instanceof ActionDropItem){
                    ActionDropItem actionDropItem = (ActionDropItem) action;
                    query = "Insert into action_dropItem (actionId, item_instance_id) Values("
                            + actionDropItem.getId() + ","
                            + actionDropItem.getItemInstance().getItemInstanceId()
                            + ")";
                }
                stmt.executeUpdate(query);
            }
            
            Statement stmt = this.conn.createStatement();
            String q= "Insert into battle (startTime, endTime) values("
                    + "\"" + battle.getCompletedActions().get(0).getTimestamp() + "\"" + ","
                    + "\"" + battle.getCompletedActions().get(battle.getCompletedActions().size() - 1).getTimestamp() + "\""
                    + ")";
           stmt.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
           ResultSet rs = stmt.getGeneratedKeys();
           rs.next();
           int battleId = rs.getInt(1);
           
           for(Action action : battle.getCompletedActions()){
               Statement s = this.conn.createStatement();
               String query = "insert into battle_action (action_id, battle_id) values ("
                       + action.getId() + ","
                       + battleId 
                       + ")";
               s.executeUpdate(query);
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createName(String name){
        try {
            Statement stmt = this.conn.createStatement();
            String query = "insert into name_list (name) Values ( \""+ name + "\")";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getAllRandomNames(){
        ArrayList<String> names = new ArrayList<>();
        try {
            Statement stmt = this.conn.createStatement();
            String query = "Select name from name_list";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                names.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return names;
    }
    
    public String getRandomName(){
        try {
            Statement stmt = this.conn.createStatement();
            String query = "Select name from name_list ORDER BY RAND() LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getString("name");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "FAILED TO GET NAME";
    }
   
    
   // public ICommand createAction(){
        
    //}
    
    public Entity createEntity(EntityType entityType, Player player){
        try {
            Statement stmt = this.conn.createStatement();
            
            String playerId = null;
            if(player != null){
                playerId = String.valueOf(player.getPlayerId());
            }
            
            String name = this.getRandomName();
            
            String query = "insert into entity "
                    + "(player_id, entity_type_id, name, health, currentHealth, attack, defense, hit) values "
                    + "(" + playerId + "," 
                    + "\"" + entityType.getId() + "\"" + ","
                    + "\"" + name + "\"" + "," // add random name pick here
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
                    name, // rand name also goes here
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

            ItemInstance itemInstance = new ItemInstance(rs.getInt(1), item, timestamp, null, null);            
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

            Player player = new Player(rs.getInt(1), username, password); 
            return player;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    public ItemInstance addInstanceItemToInventory(ItemInstance itemInstance, Object inventoryHolder){
         try {
            Statement stmt = this.conn.createStatement();
            
            if(inventoryHolder instanceof Entity){
                Entity entity = (Entity) inventoryHolder;
                String query = "insert into entity_inventory (item_instance_id, entity_id) values (" + "\"" + itemInstance.getItemInstanceId() + "\"" + "," + "\"" + entity.getEntityId() + "\"" + ")";
                stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                // possibly do check here to see if entity was already there
                itemInstance.setEntity(entity);

            }else if(inventoryHolder instanceof Player){
                Player player = (Player)inventoryHolder;
                String query = "insert into player_inventory (item_instance_id, player_id) values (" + "\"" + itemInstance.getItemInstanceId() + "\"" + "," + "\"" + player.getPlayerId() + "\"" + ")";
                stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
            // possibly do check here to see if player was already there
                itemInstance.setPlayer(player);
            }
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
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
                
                ItemManager.Instance().init();
                
                while(rs2.next()){
                    
                    Item item = ItemManager.Instance().findItemById(rs2.getInt("item_id"));
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
            
            /*
            SELECT timestamp
            FROM randomTable
            ORDER BY timestamp ASC;
            */
            while (rs.next()) {
                
                Player player = new Player(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                
                Statement stmt2 = this.conn.createStatement();
                String query2 = "select item_instance_id from player_inventory where player_id=" + player.getPlayerId();
                ResultSet rs2 = stmt2.executeQuery(query2);
                
                
                while (rs2.next()) {
                    
                    ItemInstance itemInstance = ItemManager.Instance().findItemInstanceById(rs2.getInt("item_instance_id"));
                    
                    if(itemInstance != null){
                        itemInstance.setPlayer(player);
                    }
                    
                }
                
                players.add(player);
               
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
                
                Player player = PlayerManager.Instance().findPlayerById(rs.getInt("player_id"));
                
                EntityType entityType = EntityManager.Instance().findEntityTypeById(rs.getInt("entity_type_id"));
                
                if(entityType == null){
                    throw new Exception("EntityType doesnt exist for entity_type_id. database is courupt or entityTypes arnt loaded");
                }
                
                Entity entity = new Entity(rs.getInt("id"), player, entityType, rs.getString("name"), rs.getInt("health"), rs.getInt("currentHealth"), rs.getInt("attack"), rs.getInt("defense"), rs.getInt("hit"));
                
                Statement stmt2 = this.conn.createStatement();
                String query2 = "select item_instance_id from entity_inventory where entity_id=" + entity.getEntityId();
                ResultSet rs2 = stmt2.executeQuery(query2);
                
                
                while (rs2.next()) {
                   
                    ItemInstance itemInstance = ItemManager.Instance().findItemInstanceById(rs2.getInt("item_instance_id"));
                    
                    if(itemInstance != null){
                        itemInstance.setEntity(entity);
                    }
                }
                
                entitys.add(entity);
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
                
                itemInstances.add(new ItemInstance(rs.getInt("id"), item, rs.getString("created"), null, null));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return itemInstances;
        
    }
    
    public void deleteAllItemInstancesAndClearInventoys(){
       
        try {
            
            Statement stmt2 = this.conn.createStatement();
            String query2 = "delete from player_inventory";
            stmt2.execute(query2);
            
            Statement stmt3 = this.conn.createStatement();
            String query3 = "delete from entity_inventory";
            stmt3.execute(query3);
            
            Statement stmt = this.conn.createStatement();
            String query = "delete from item_instance";
            stmt.execute(query);
            

            
            ItemManager.Instance().getItem_instances().clear();
            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
    
    
    
    public static void main(String[] args) {
        ItemManager.Instance().init();
        PlayerManager.Instance().init();
        EntityManager.Instance().init();
        
        ItemManager itemManager = ItemManager.Instance();
        EntityManager entityManager = EntityManager.Instance();
        PlayerManager playerManager = PlayerManager.Instance();
        
        Repository repo = Repository.Instance();
        
        EntityManager.Instance().createEntity(
                EntityManager.Instance().findEntityTypeByName("Goblin"), 
                null
        );
        
        
        
        ArrayList<Entity> entitys = EntityManager.Instance().getEntitys();
        for(Entity entity : entitys){
            System.out.println(entity);
            
            ArrayList<ItemInstance> itemInstances = ItemManager.Instance().getEntityInventory(entityManager.findEntityById(entity.getEntityId()));
            for(ItemInstance inst : itemInstances){
                System.out.println(inst);
            }
        }
        
        
        
      // itemManager.setItemInstanceOwner(
        //      itemManager.createItemInstance(itemManager.findItemByName("Bread"), itemManager.getTimestamp()), 
          //    entityManager.findEntityById(3));
        
        //ItemManager.Instance().createItemInstance(ItemManager.Instance().findItemByName("Bread"), new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
       
        
        

        
       // ItemInstance itemInstance = itemManager.findItemInstanceById(12);
       // System.out.println(itemInstance.getItemInstanceId());
        
        

        
        
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
