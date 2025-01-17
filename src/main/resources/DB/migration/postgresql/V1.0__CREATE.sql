CREATE TABLE IF NOT EXISTS adresse (
                                       id          uuid PRIMARY KEY USING INDEX TABLESPACE autohausspace,
                                       plz         text NOT NULL CHECK(plz~'\d{5}'),
                                       stadt       text NOT NULL,
                                       strasse    text NOT NULL,
                                       hausnummer  text NOT NULL
) TABLESPACE autohausspace;
CREATE INDEX IF NOT EXISTS adresse_plz_idx ON adresse(plz) TABLESPACE autohausspace;

CREATE TABLE IF NOT EXISTS autohaus (
                                        id          uuid PRIMARY KEY USING INDEX TABLESPACE autohausspace,
                                        version       integer NOT NULL DEFAULT 0,
                                        name        text NOT NULL,
                                        telefonnummer text NOT NULL,
                                        email       text NOT NULL UNIQUE USING INDEX TABLESPACE autohausspace,
                                        adresse_id  uuid NOT NULL UNIQUE USING INDEX TABLESPACE autohausspace REFERENCES adresse,
                                        username      text,
                                        erzeugt       timestamp NOT NULL,
                                        aktualisiert  timestamp NOT NULL
) TABLESPACE autohausspace;
CREATE INDEX IF NOT EXISTS autohaus_email_idx ON autohaus(email) TABLESPACE autohausspace;

CREATE TABLE IF NOT EXISTS auto (
                                    id          uuid PRIMARY KEY USING INDEX TABLESPACE autohausspace,
                                    autohaus_id uuid REFERENCES autohaus,
                                    idx       integer NOT NULL DEFAULT 0
)TABLESPACE autohausspace;
CREATE INDEX IF NOT EXISTS auto_autohaus_id_idx ON auto(autohaus_id) TABLESPACE autohausspace;
