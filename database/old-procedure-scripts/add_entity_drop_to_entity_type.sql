CREATE PROCEDURE `add_entity_drop_to_entity_type` (In entityTypeId INT, In entityDropsId INT)
BEGIN
	insert into entity_type_has_entity_drops (entity_type_id, entity_drops_id) Values (entityTypeId, entityDropsId);
END
