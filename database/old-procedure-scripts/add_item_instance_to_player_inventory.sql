CREATE DEFINER=`root`@`localhost` PROCEDURE `add_Item_Instance_to_player_Inventory`(In pId INT, In iiId INT, OUT piId INT)
BEGIN
	insert into player_inventory (player_id, item_instance_id) VALUES (pId, iiId);
    select LAST_INSERT_ID();
END