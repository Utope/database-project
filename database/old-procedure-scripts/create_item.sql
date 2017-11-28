CREATE DEFINER=`root`@`localhost` FUNCTION `create_item`(nam varchar(45), des varchar(255)) RETURNS int(11)
BEGIN
	   insert into item (name, description) VALUES (nam, des);
RETURN (select id from item where name = nam AND description = des);
END