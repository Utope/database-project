CREATE PROCEDURE `add_item_instance_to_entity_inventory` (In entityId INT, In itemInstanceId INT,IN dropChance FLOAT, Out entityInventoryId INT)
BEGIN
	insert into entity_inventory (entity_id, item_instance_id, dropChance) VALUES
    (entityId, itemInstanceId, dropChance);
    select LAST_INSTERT_ID();
END
