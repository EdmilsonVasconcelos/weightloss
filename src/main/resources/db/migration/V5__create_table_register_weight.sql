CREATE TABLE REGISTER_WEIGHT (
    id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    weight FLOAT DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date DATETIME DEFAULT CURRENT_TIMESTAMP,
    created DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated DATETIME DEFAULT CURRENT_TIMESTAMP,
    diet_id bigint NOT NULL,

    FOREIGN KEY (diet_id)
        REFERENCES DIET(id)
)