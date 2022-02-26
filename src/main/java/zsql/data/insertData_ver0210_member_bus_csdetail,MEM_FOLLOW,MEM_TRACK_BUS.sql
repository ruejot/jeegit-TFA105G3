-- =========各table假資料=====aim total 22 tables==============
-- `tableName`, [這裡新增的table假資料筆數]
-- 
-- `MEMBERS`, [15筆預設]
-- `BUS`, [9筆預設]
-- `CS_DETAIL`, [5筆預設]
-- `MEM_FOLLOW`, [10筆預設]
-- `MEM_TRACK_BUS`, [4筆預設]
-- =============================================


insert into MEMBERS(NAME, MOBILE, PHONE, ADDRESS, DATE, EMAIL, PASSWORD,NICKNAME,INTRO) 
values("林鴞", "0975123451", "022720811", "台北市中山區南京東路三段219號4樓", '2021-12-09 16:15:10', "tibaMem01@gmail.com", "password", "Apple","你才是蘋果~"),
("范希文", "0975123452", "022720812", "台北市中山區南京東路三段219號5樓", '2021-12-09 18:03:29', "tibaMem02@gmail.com", "password", "Brabra","我不是芭芭啦~"),
("黃文淑", "0975123453", "022720813", "台北市松山區敦化北路3號", '2021-12-16 00:41:28', "tibaMem03@gmail.com", "password", "Cindy","你才是辛蒂~"),
("羅黛依", "0975123454", "022720814", "台北市松山區八德路三段25號", '2021-12-16 14:53:26', "tibaMem04@gmail.com", "password", "Debby","我不是黛比~"),
("許慎奇", "0975123455", "022720815", "台北市士林區福林路60號1樓", '2021-12-17 00:00:01', "tibaMem05@gmail.com", "password", "Eason","你才是伊森~"),
("李硯", "0975123456", "022720816", "台北市松山區延吉街7-6號", '2021-12-17 23:59:59', "tibaMem06@gmail.com", "password", "Frank","我不是法蘭克~"),
("李之恩", "0975123457", "022720817", "新北市永和區保平路177巷6弄2號", '2021-12-21 06:17:00', "tibaMem07@gmail.com", "password", "Ginny","哥哥不要再欺負我~"),
("司馬君實", "0975123458", "022720818", "新北市瑞芳區豎崎路36號", '2021-12-21 09:43:10', "tibaMem08@gmail.com", "password", "Harry","我是外貌協會~"),
("陶艾威", "0975123459", "022720819", "桃園市中壢區復興路46號9樓", '2021-12-27 15:01:43', "tibaMem09@gmail.com", "password", "Ivy","你才是艾薇~"),
("王治嘉", "0975123460", "022720820", "新竹市東區光復路一段163巷53弄1之4號2樓", '2021-12-27 22:26:56', "tibaMem10@gmail.com", "password", "Jack","我不是傑克~"),
("盧媜彥", "0975123461", "022720821", "台中市南區學府路40號", '2022-01-04 03:26:41', "tibaMem11@gmail.com", "password", "Kimmy","你才是奇美~"),
("郭若思", "0975123462", "022720822", "彰化縣彰化市華山路16號", '2022-01-04 12:32:12', "tibaMem12@gmail.com", "password", "Laura","我不是蘿拉~"),
("張希載", "0975123463", "022720823", "雲林縣斗六市中山路292號", '2022-01-06 10:35:25', "tibaMem13@gmail.com", "password", "Mark","你才是馬克~"),
("陳諒妍", "0975123464", "022720824", "嘉義市西區民族路716號", '2022-01-06 09:23:06', "tibaMem14@gmail.com", "password", "Niki","我不是尼基~"),
("林騫", "0975123465", "022720825", "台南市安平區安平路376-1號", '2022-01-11 20:15:10', "tibaMem15@gmail.com", "password", "Oprah","你才是歐普拉~");


insert into BUS(NAME, PHONE, ADDRESS, TAX_ID, EMAIL, PASSWORD, 
INTRO, 
FB, IG, WEBSITE, PAYMENT_PROVIDE)
values('伊芸寵物坊', '28825251', '台北市士林區美崙街152巷2樓', '67670001','tibameBus01@gmail.com','password', 
'這是intro Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quae aliquid dignissimos temporibus, debitis totam consequatur eaque nesciunt incidunt et in!', 
'fbbus01','igbus01','www.tibameBus01.com.tw','1111');
insert into BUS(NAME, PHONE, ADDRESS, TAX_ID, EMAIL, PASSWORD, PAYMENT_PROVIDE)
values('pet caring inherit world', '28825252', '台北市大同區民權西路184巷11弄', '67670002','tibameBus02@gmail.com','password','0111'),
('吉德貓狗joy4 workshop', '28825253', '新北市板橋區實踐路5-1號3樓', '67670003','tibameBus03@gmail.com','password','1011'),
('FreshDOG暖暖店', '28825254', '基隆市暖暖區東碇路15號1樓', '67670004','tibameBus04@gmail.com','password','1101'),
('阿瑋狗糧', '28825256', '台北市文山區萬安街22巷7號', '67670007','tibameBus06@gmail.com','password','1111'),
('絲絲美容用品', '28825257', '台北市文山區萬安街22巷8弄', '67670008','tibameBus07@gmail.com','password','1112'),
('大同寵物生活用品店', '28825258', '台北市文山區萬安街22巷9弄', '67670009','tibameBus09@gmail.com','password','1113'),
('阿瑋貓糧', '28825210', '台北市文山區萬安街22巷22弄', '67670012','tibameBus9@gmail.com','password','1119'),
('水族世界', '28825259', '台北市文山區萬安街22巷10弄', '67670010','tibameBus012@gmail.com','password','1114');


insert into CS_DETAIL(MEMBER_ID, BUS_ID, CASE_TIME, FEEDBACK)
values('1','1','2022-02-01','貓砂買5包有免運嗎?'),
('2','1','2022-02-01','請問你們狗繩有沒有紅色款'),
('3','3','2022-02-02','春節有營業嗎'),
('4','4','2022-02-02','Hi,我之前下訂的到現在都還沒收到欸'),
('5','5','2022-02-02','可以網路下單後直接到你們店裡拿嗎?');

insert into MEM_FOLLOW(MEMBER_ID,FOLLOWEE,FRIENDSHIP) 
values(1, 4, "1"),
(1, 3, "2"),
(1, 4, "1"),
(1, 5, "2"),
(3, 1, "0"),
(6, 3, "2"),
(6, 4, "0"),
(3, 4, "0"),
(4, 5, "1"),
(4, 3, "1");

insert into MEM_TRACK_BUS (MEMBER_ID,BUS_ID) 
values(1, 3),(3,1),(4,1),(1,1);