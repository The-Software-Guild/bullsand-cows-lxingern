CREATE TABLE IF NOT EXISTS games (
	game_id INT PRIMARY KEY,
    answer CHAR(4),
    ongoing TINYINT
);

CREATE TABLE IF NOT EXISTS rounds (
	round_id INT,
	game_id INT,
    guess CHAR(4),
    timestamp DATETIME,
    result CHAR(6),
    PRIMARY KEY (round_id, game_id),
    FOREIGN KEY (game_id)
    	REFERENCES games(game_id)
);