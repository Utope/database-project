CREATE PROCEDURE `create_entity_drop` (In itemId INT, IN dropchanc FLOAT, minCount Int, maxCount Int, Out entityDropId INT)
BEGIN
	insert into entity_drops (item_id, dropChance, minCount, maxCount) Values 
    (itemId, dropchanc, minCount, maxCount);
    select LAST_INSERT_ID();
END
