
-- customer
INSERT INTO `tbl_customer` VALUES (1,'Q4','111111111','ANH PHƯỚC');
INSERT INTO `tbl_customer` VALUES (2,'Q1','222222222','ANH QUÝ');
INSERT INTO `tbl_customer` VALUES (3,'HN','333333333','ANH SƠN');
INSERT INTO `tbl_customer` VALUES (4,'HCM','444444444','HOANG LONG');
INSERT INTO `tbl_customer` VALUES (5,'Hoan Kiem','555555555','HOA');

-- product
INSERT INTO `tbl_product` VALUES (1,100,'Sach bai tap',65000.600);
INSERT INTO `tbl_product` VALUES (2,100,'Sach bai giang',45000.300);
INSERT INTO `tbl_product` VALUES (3,120,'Sach tham khao',88000.500);
INSERT INTO `tbl_product` VALUES (4,40,'Sach suu tam',299000.900);
INSERT INTO `tbl_product` VALUES (5,20,'Sach ki niem',499000.900);

-- order
INSERT INTO `tbl_order` VALUES (1,'2022-06-25', 'CANCELLED',0,1);
INSERT INTO `tbl_order` VALUES (2,'2022-06-25', 'PAID',0,1);
INSERT INTO `tbl_order` VALUES (3,'2022-06-26', 'PAID',0,2);
INSERT INTO `tbl_order` VALUES (4,'2022-06-27', 'PAID',0,4);
INSERT INTO `tbl_order` VALUES (5,'2022-06-28', 'CREATED',0,5);

-- order Items
-- don so 1
INSERT INTO `tbl_order_item` VALUES( 1, 0, 5, 1, 1);
INSERT INTO `tbl_order_item` VALUES( 2, 0, 3, 1, 4);
-- don so 2
INSERT INTO `tbl_order_item` VALUES( 3, 0, 2, 2, 1);
INSERT INTO `tbl_order_item` VALUES( 4, 0, 2, 2, 4);
-- don so 3
INSERT INTO `tbl_order_item` VALUES( 5, 0, 5, 3, 1);
-- don so 4
INSERT INTO `tbl_order_item` VALUES( 6, 0, 5, 4, 1);
INSERT INTO `tbl_order_item` VALUES( 7, 0, 5, 4, 2);
-- don so 5
INSERT INTO `tbl_order_item` VALUES( 8, 0, 1, 5, 5);