CREATE FUNCTION `create_player` (un varchar(45), pwd varchar(45))
RETURNS INTEGER
BEGIN
	insert into player (username, password) VALUES (un, pwd);
RETURN (select id from player where username = un AND password = pwd);
END
