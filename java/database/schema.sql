BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, neo;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE neo (
    db_id serial PRIMARY KEY,
    nasa_id varchar(255) NOT NULL,
    user_id int NOT NULL,
    neo_date TIMESTAMP,
    neo_name varchar(255),
    potentially_hazardous boolean,
    estimated_max_diameter_km numeric (8, 8),
    close_approach_date DATE,
    miss_distance_km numeric(8, 8),
    velocity_km_per_sec numeric(8, 8),
    CONSTRAINT FK_neo_users FOREIGN KEY (user_id)
      REFERENCES users(user_id)
);
COMMIT TRANSACTION;
