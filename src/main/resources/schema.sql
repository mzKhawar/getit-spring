DROP TABLE IF EXISTS weight;

CREATE TABLE weight (

    weight_id bigint PRIMARY KEY,
    weight_in_pounds DECIMAL(5, 2) NOT NULL,
    recorded_at TIMESTAMP

);