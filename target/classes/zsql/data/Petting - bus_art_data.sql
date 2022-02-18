SELECT * FROM pet_test.BUS_ART;

INSERT INTO pet_test.BUS_ART
(`BUS_ID`,
`TITLE`,
`ARTICLE`,
`TIME`)
VALUES
('1', 'HERO-MAMA', 'This is an aritcle1.', '2021-01-18'),
('2', 'HERO-MAMA', 'This is an aritcle2.', '2021-01-19'),
('3', 'TOMA-PRO', 'This is an aritcle3.', '2021-01-20');

DELETE FROM pet_test.BUS_ART WHERE ART_ID = 1;