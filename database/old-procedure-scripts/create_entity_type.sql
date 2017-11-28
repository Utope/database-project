CREATE PROCEDURE `create_entity_type` (In typeName varchar(45), IN typeDescription varchar(255), IN base_attack INT, IN base_defense INT, IN base_hit INT, IN base_health INT)
BEGIN
	insert into entity_type (name, description, base_attack, base_defense, base_hit, base_health) 
    Values (typeName,typeDescription, base_attack, base_defense, base_hit, base_health);
    select Last_INSERT_ID();
END
