CREATE TABLE attendance (
    id BIGSERIAL PRIMARY KEY,
    data TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL REFERENCES user(id)
)