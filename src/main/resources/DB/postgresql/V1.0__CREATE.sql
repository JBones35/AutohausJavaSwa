CREATE TABLE IF NOT EXISTS adresse (
                                       id          uuid PRIMARY KEY USING INDEX TABLESPACE autohausspace,
                                       plz         text NOT NULL CHECK(plz~'\D{5}'),
                                       ort         text NOT NULL
) TABLESPACE autohausspace;
CREATE INDEX IF NOT EXISTS adresse_plz_idx ON adresse(plz) TABLESPACE autohausspace;

CREATE TABLE IF NOT EXISTS autohaus (
                                        id          uuid PRIMARY KEY USING INDEX TABLESPACE autohausspace,
                                        name        text NOT NULL,
                                        standort    text NOT NULL,
                                        telefonnummer text NOT NULL,
                                        email       text NOT NULL UNIQUE USING INDEX TABLESPACE autohausspace,
                                        adresse_id  text NOT NULL UNIQUE USING INDEX TABLESPACE autohausspace REFERENCES adresse
) TABLESPACE autohausspace;
CREATE INDEX IF NOT EXISTS autohaus_email_idx ON autohaus(email) TABLESPACE autohausspace;

CREATE TABLE IF NOT EXISTS auto (
                                    id          text PRIMARY KEY USING INDEX TABLESPACE autohausspace,
                                    modell      text NOT NULL,
                                    baujahr     integer,
                                    besitzer    text NOT NULL,
                                    preis       decimal NOT NULL,
                                    autohaus_id text REFERENCES autohaus
)TABLESPACE autohausspace;
CREATE INDEX IF NOT EXISTS auto_autohaus_id_idx ON auto(autohaus_id) TABLESPACE autohausspace;

CREATE TABLE IF NOT EXISTS mitarbeiter (
                                           id          text PRIMARY KEY USING INDEX TABLESPACE autohausspace,
                                           name        text NOT NULL,
                                           geburtsdatum date CHECK(geburtsdatum < current_date),
                                           position    text NOT NULL,
                                           gehalt      decimal NOT NULL,
                                           autohaus_id text NOT NULL USING INDEX TABLESPACE autohausspace REFERENCES autohaus
)TABLESPACE autohausspace;
CREATE INDEX IF NOT EXISTS mitarbeiter_autohaus_id_idx ON mitarbeiter(autohaus_id) TABLESPACE autohausspace;
