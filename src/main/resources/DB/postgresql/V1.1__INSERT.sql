INSERT INTO adresse (id, plz, ort)
VALUES
    ('20000000-0000-0000-0000-000000000000','75417','Mühlacker'),
    ('20000000-0000-0000-0000-000000000001','11111','Stuttgart'),
    ('20000000-0000-0000-0000-000000000020','22222','Karlsruhe'),
    ('20000000-0000-0000-0000-000000000030','33333','München'),
    ('20000000-0000-0000-0000-000000000040','44444','Sindelfingen');

INSERT INTO autohaus (id, name, standort, telefonnummer, email, adresse_id)
VALUES
    ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'Autohaus Müller', 'Stuttgart', '07112 1234567', 'info@autohaus-mueller.de', '20000000-0000-0000-0000-000000000000'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d480', 'Autohaus Schmidt', 'Berlin', '0303 9876543', 'kontakt@autohaus-schmidt.de', '20000000-0000-0000-0000-000000000001'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d481', 'Autohaus König', 'Hamburg', '04034 1112223', 'service@autohaus-koenig.de', '20000000-0000-0000-0000-000000000020'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d482', 'Autohaus Meier', 'München', '08934 4445556', 'info@autohaus-meier.de', '20000000-0000-0000-0000-000000000030'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d483', 'Autohaus Weber', 'Frankfurt', '06934 7778889', 'kontakt@autohaus-weber.de', '20000000-0000-0000-0000-000000000040');

INSERT INTO auto (id, modell, baujahr, besitzer, preis, autohaus_id)
VALUES
    ('10000000-0000-0000-0000-000000000001', 'Audi A3', 2020, 'Max Mustermann', 25000.00, 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
    ('10000000-0000-0000-0000-000000000002', 'BMW 320i', 2018, 'Lisa Müller', 22000.00, 'f47ac10b-58cc-4372-a567-0e02b2c3d480'),
    ('10000000-0000-0000-0000-000000000003', 'Mercedes C200', 2021, 'Hans König', 30000.00, 'f47ac10b-58cc-4372-a567-0e02b2c3d481'),
    ('10000000-0000-0000-0000-000000000004', 'VW Golf', 2019, 'Sophie Weber', 20000.00, 'f47ac10b-58cc-4372-a567-0e02b2c3d482');

INSERT INTO mitarbeiter (id, name, geburtsdatum, position, gehalt, autohaus_id)
VALUES
    ('4f21c4d7-27fa-47bb-8f6a-3e3bd2d58c71', 'Max Mustermann', '1990-06-15', 'Verkäufer', 45000.00, 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
    ('5f31c4d7-27fb-48cc-8f7b-4e4bd3d58c72', 'Lisa Müller', '1985-09-20', 'Managerin', 65000.00, 'f47ac10b-58cc-4372-a567-0e02b2c3d480'),
    ('6f41c4d7-27fc-49dd-8f8c-5e5bd4d58c73', 'Hans König', '1995-04-10', 'Mechaniker', 38000.00, 'f47ac10b-58cc-4372-a567-0e02b2c3d481'),
    ('7f51c4d7-27fd-50ee-8f9d-6e6bd5d58c74', 'Sophie Weber', '2000-01-15', 'Kundendienst', 30000.00,  'f47ac10b-58cc-4372-a567-0e02b2c3d482'),
    ('8f61c4d7-27fe-51ff-9f0e-7f7bd6d58c75', 'Paul Schmidt', '1993-08-25', 'Verkäufer', 41000.00,  'f47ac10b-58cc-4372-a567-0e02b2c3d483'),
    ('9f71c4d7-27ff-52gg-9f1f-8g8bd7d58c76', 'Clara Braun', '1998-03-12', 'Assistentin', 34000.00, 'f47ac10b-58cc-4372-a567-0e02b2c3d483');
