CREATE DEFINER=`root`@`localhost` PROCEDURE `create_item_instance`(IN item_instance_itemId INT, OUT item_instanceId INT)
BEGIN
	insert into item_instance (item_id, created) VALUES (item_instance_itemId, CURRENT_TIMESTAMP);
	select LAST_INSERT_ID();
END