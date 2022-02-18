SELECT * FROM pet_test.BUS;

INSERT INTO pet_test.BUS
(`NAME`,
`PHONE`,
`ADDRESS`,
`TAX_ID`,
`DATE`,
`ACCOUNT`,
`PASSWORD`,
`INTRO`,
`PHOTO`,
`FB`,
`IG`,
`WEBSITE`,
`PAYMENT_PROVIDE`)
VALUES
('DOGGYFOOD','0912345678','Dexing E.Rd, Taipei','12345678', NOW(), 'doggy@gmail.com','123456',null,null,'doggyfb','doggyig','doggyweb','1101'),
('KITTYFOOD','0910987654','Fuxing E.Rd, Taoyuan','98765432', NOW(), 'kitty@gmail.com','654321',null,null,'kittyfb','kittyig','kittyweb','1100'),
('FISHFOOD','0987654321','Shilin, Taipei','88888888', NOW(), 'fish@gmail.com','888888',null,null,'fishfb','fishig','fishweb','1111');

DELETE FROM pet_test.BUS WHERE BUS_ID = 1;
