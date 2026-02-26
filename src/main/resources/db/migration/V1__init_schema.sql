-- DROP TABLES --
DROP TABLE IF EXISTS manga_tag;

DROP TABLE IF EXISTS manga_staff;

DROP TABLE IF EXISTS cover;

DROP TABLE IF EXISTS manga;

DROP TABLE IF EXISTS tag;

DROP TABLE IF EXISTS staff;

DROP TABLE IF EXISTS publisher;

-- ENUMS
CREATE TYPE manga_status AS ENUM ('ONGOING', 'WAITING_FOR_NEW', 'COMPLETE');

CREATE TYPE manga_launch_status AS ENUM ('ONGOING', 'COMPLETED', 'HIATUS', 'CANCELED');

CREATE TYPE staff_role AS ENUM ('AUTHOR', 'ARTIST');

-- TABLES --
CREATE TABLE manga (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    status manga_status NOT NULL,
    launch_status manga_launch_status NOT NULL,
    imported BOOLEAN NOT NULL,
    total_volumes INTEGER NOT NULL,
    publisher_id BIGINT NOT NULL,
    release_date TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE cover (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    manga_id BIGINT NOT NULL,
    volume_number INTEGER NOT NULL,
    url VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE manga_staff (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    manga_id BIGINT NOT NULL,
    staff_id BIGINT NOT NULL,
    role staff_role NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE staff (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE manga_tag (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    manga_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE tag (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE publisher (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    image_url VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

-- FOREIGN KEYS --
ALTER TABLE manga
ADD CONSTRAINT fk_manga_publisher FOREIGN KEY (publisher_id) REFERENCES publisher (id) ON DELETE CASCADE;

ALTER TABLE cover
ADD CONSTRAINT fk_cover_manga FOREIGN KEY (manga_id) REFERENCES manga (id) ON DELETE CASCADE;

ALTER TABLE manga_staff
ADD CONSTRAINT fk_manga_staff_manga FOREIGN KEY (manga_id) REFERENCES manga (id) ON DELETE CASCADE;

ALTER TABLE manga_staff
ADD CONSTRAINT fk_manga_staff_staff FOREIGN KEY (staff_id) REFERENCES staff (id) ON DELETE CASCADE;

ALTER TABLE manga_tag
ADD CONSTRAINT fk_manga_tag_manga FOREIGN KEY (manga_id) REFERENCES manga (id) ON DELETE CASCADE;

ALTER TABLE manga_tag
ADD CONSTRAINT fk_manga_tag_tag FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE;

-- UNIQUE CONSTRAINT --
ALTER TABLE cover
ADD CONSTRAINT uq_cover_manga UNIQUE (manga_id, volume_number);

ALTER TABLE manga_staff
ADD CONSTRAINT uq_manga_staff UNIQUE (manga_id, staff_id, role);

ALTER TABLE manga_tag
ADD CONSTRAINT uq_manga_tag UNIQUE (manga_id, tag_id);

-- INDEXES --
CREATE INDEX idx_manga_publisher ON manga (publisher_id);

CREATE INDEX idx_cover_manga ON cover (manga_id);

CREATE INDEX idx_manga_staff_manga ON manga_staff (manga_id);

CREATE INDEX idx_manga_staff_staff ON manga_staff (staff_id);

CREATE INDEX idx_manga_tag_manga ON manga_tag (manga_id);

CREATE INDEX idx_manga_tag_tag ON manga_tag (tag_id);