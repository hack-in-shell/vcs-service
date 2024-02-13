CREATE TABLE commits
(
    id         SERIAL PRIMARY KEY,
    hash       VARCHAR(40) UNIQUE NOT NULL,
    author     VARCHAR(255)       NOT NULL,
    committer  VARCHAR(255)       NOT NULL,
    message    TEXT               NOT NULL,
    parent     SERIAL, -- If a commit has a parent
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Optionally, you can create an index on the parent column for faster lookups
-- CREATE INDEX idx_parent_commit ON commits(parent);

-- Foreign key constraint to reference the commits table itself for the parent relationship
ALTER TABLE commits
    ADD CONSTRAINT fk_parent_commit
        FOREIGN KEY (parent) REFERENCES commits (id);

CREATE TABLE project_structure
(
    id             SERIAL PRIMARY KEY,
    commit_id      SERIAL, ---the commit where it is saved
    directory_tree JSONB NOT NULL,
    FOREIGN KEY (commit_id) REFERENCES commits (id)
);

CREATE TABLE temp_project_structure
(
    id             SERIAL PRIMARY KEY,
    commit_id      SERIAL, --- the commit where it is modified
    directory_tree JSONB NOT NULL,
    FOREIGN KEY (commit_id) REFERENCES commits (id)
);

CREATE TABLE file
(
    id        SERIAL PRIMARY KEY,
    commit_id SERIAL, ----commit file was created on
    path      VARCHAR(255) NOT NULL,
    FOREIGN KEY (commit_id) REFERENCES commits (id)
);

CREATE TABLE temp_file
(
    id        SERIAL PRIMARY KEY,
    commit_id SERIAL, ----commit file was created on
    path      VARCHAR(255) NOT NULL,
    FOREIGN KEY (commit_id) REFERENCES commits (id)
);

CREATE TABLE file_content
(
    id        SERIAL PRIMARY KEY,
    commit_id SERIAL,
    file_id   SERIAL NOT NULL,
    content   TEXT   NOT NULL,
    FOREIGN KEY (commit_id) REFERENCES commits (id)
);

CREATE TABLE temp_file_content
(
    id        SERIAL PRIMARY KEY,
    commit_id SERIAL, ---this means current commit where the change is made, future parent
    file_id   SERIAL NOT NULL,
    content   TEXT   NOT NULL,
    FOREIGN KEY (commit_id) REFERENCES commits (id)
);
)
