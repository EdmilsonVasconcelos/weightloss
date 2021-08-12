CREATE TABLE USER_PROFILES (
    user_id bigint NOT NULL,
    profiles_id bigint NOT NULL,

    FOREIGN KEY (user_id)
            REFERENCES USER(id),
    FOREIGN KEY (profiles_id)
                REFERENCES PROFILE(id)
)