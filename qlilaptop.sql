-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2020 at 06:01 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
SET FOREIGN_KEY_CHECKS = 0;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlilaptop`
--
CREATE DATABASE qlilaptop;
-- --------------------------------------------------------
USE qlilaptop;
--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `nhasanxuat` (
  `MANSX` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `TENNSX` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
INSERT INTO `nhasanxuat` VALUES ('2001','Dell'),('2002','Asus'),('2003','Acer');


CREATE TABLE `nhacungcap` (
  `MANCC` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `TENNCC` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `DIACHINCC` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `DIENTHOAI` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `nhacungcap` VALUES ('3001','PhongVu','1000000','a',1),('3002','LaptopWorld','2000000','b',1),('3003','BlackMarket','3000000','c',1);

CREATE TABLE `laptop` (
  `MALAPTOP` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MANSX` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MANCC` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `TEN` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `GIA` int(11) NOT NULL,
  `CPU` varchar(65) COLLATE utf8_unicode_ci NOT NULL,
  `RAM` varchar(65) COLLATE utf8_unicode_ci NOT NULL,
  `GPU` varchar(65) COLLATE utf8_unicode_ci NOT NULL,
  `MANHINH` varchar(65) COLLATE utf8_unicode_ci NOT NULL,
  `OCUNG` varchar(65) COLLATE utf8_unicode_ci NOT NULL,
  `IMG` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `laptop` VALUES 
 ('1001','2001','3001','Dell inspiron 7577',100,10250,'Intel® Core™ i7 7700H','32GB','NVIDIA® GeForce® GTX 1050ti','15.6  FHD IPS (1920 x 1080)','SSD 128GB + HDD 512GB','001.jpg')
,('1002','2001','3001','Dell Alienware M17',100,2400,'Intel® Core™ i7 8750H','32GB','NVIDIA® GeForce® GTX 1070','17.3  FHD IPS (1920 x 1080)','SSD 512GB + HDD 1TB','001.jpg')
,('1003','2001','3002','Dell G5 Inspiron 5590',100,1200,'Intel® Core™ i5 9300H','8GB','NVIDIA® GeForce® GTX 1650','15.6  FHD IPS (1920 x 1080)','SSD 128GB + HDD 1TB','001.jpg')
,('1004','2001','3003','Dell Gaming G3',100,1000,'Intel® Core™ i5 9300H','8GB','NVIDIA® GeForce® GTX 1050','15.6  FHD IPS (1920 x 1080)','SSD 256GB','001.jpg')
,('1005','2002','3002','Asus ROG Zephyrus G ',100,1400,'AMD Ryzen 7-3750H','8GB','NVIDIA® GeForce® GTX 1660ti','15.6  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1006','2002','3003','ASUS ROG STRIX G ',100,1650,'Intel® Core™ i7 9750H','8GB','NVIDIA® GeForce® RTX 2060','15.6  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1007','2002','3001','ASUS ROG MOTHERSHIP ',100,9000,'Intel® Core™ i9 9980H','64GB','NVIDIA® GeForce® RTX 2080','17.3  FHD IPS (1920 x 1080)','SSD 1TB','001.jpg')
,('1008','2002','3002','Asus ROG Zephyrus M ',100,2150,'Intel® Core™ i7 9750H','16GB','NVIDIA® GeForce® RTX 2060','15.6  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1009','2003','3003','Acer Predator Triton 900 ',100,3350,'Intel® Core™ i7 9750H','32GB','NVIDIA® GeForce® RTX 2080','17.3  FHD IPS (1920 x 1080)','SSD 1TB','001.jpg')
,('1010','2003','3001','Acer Predator Helios 300 ',100,1650,'Intel® Core™ i7 9750H','8GB','NVIDIA® GeForce® GTX 1660ti','15.6  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1011','2003','3002','Acer Nitro 5 ',100,1250,'Intel® Core™ i7 9750H','8GB','NVIDIA® GeForce® GTX 1650','15.6  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1012','2003','3003','Acer Predator 21 X ',100,10000,'Intel® Core™ i7 7820H','64GB','NVIDIA® GeForce® GTX 1080 x2','21  FHD IPS (1920 x 1080)','SSD 1TB + HDD 1TB','001.jpg')
,('1013','2001','3003','Dell Alienware M15 ',100,2300,'Intel® Core™ i9 8950H','16GB','NVIDIA® GeForce® GTX 1080','15.6  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1014','2001','3003','Dell Alienware Area 51 ',100,4350,'Intel® Core™ i9 9900K','32GB','NVIDIA® GeForce® RTX 2080','17.3  FHD IPS (1920 x 1080)','SSD 512GB + HDD 1TB','001.jpg')
,('1015','2002','3001','ASUS ROG STRIX SCAR II ',100,2900,'Intel® Core™ i7 8750H','16GB','NVIDIA® GeForce® GTX 1070','15.6  FHD IPS (1920 x 1080)','SSD 512GB + HDD 1TB','001.jpg')
,('1016','2003','3003','Acer Swift 7',100,2750,'Intel® Core™ i7 10850H','32GB','NVIDIA® GeForce® RTX 2070','15.6  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1017','2003','3001','ACER PREDATOR HELIOS 700',100,6500,'Intel® Core™ i9 9980HK','32GB','NVIDIA® GeForce® RTX 2080','17.3  FHD IPS (1920 x 1080)','SSD 2TB','001.jpg')
,('1018','2002','3002',' ASUS ROG Zephyrus G14',100,1650,'AMD Ryzen 7 4800HS','16GB','NVIDIA® GeForce® GTX 1650Ti','15.6  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1019','2001','3001','Dell Inspiron 5459',100,1200,'Intel® Core™ i7 7700H','8GB','NVIDIA® GeForce® GTX 1050Ti','15.6  FHD IPS (1920 x 1080)','SSD 128GB + HDD 1TB','001.jpg')
,('1020','2001','3001','Dell Inspiron 15',100,1200,'Intel® Core™ i7 7700H','8GB',' NVIDIA Gefore GTX 1050Ti','15.6  FHD IPS (1920 x 1080)','SSD 128GB + HDD 1TB','001.jpg')
,('1021','2002','3003','ASUS ROG G7',100,5000,'Intel® Core™ i9 9980HK','32GB','NVIDIA® GeForce® RTX 2080','17.3  FHD IPS (1920 x 1080)','SSD 1.5TB','001.jpg')
,('1022','2001','3002','Dell XPS 15 7590',100,2600,'Intel® Core™ i7 9750H','16GB','NVIDIA Gefore GTX 1650','15.6  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1023','2003','3003','Acer Predator 17 X',100,2136,'Intel® Core™ i7 7700H','16GB','NVIDIA Gefore GTX 1080','17.3  FHD IPS (1920 x 1080)','SSD 256GB + HDD 1TB','001.jpg')
,('1024','2002','3001','ASUS ROG Strix Hero III',100,1923,'Intel® Core™ i7 9750H','16GB','NVIDIA® GeForce® RTX 2070','15.6  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1025','2002','3003','ASUS ROG GX800',100,7500,'Intel® Core™ i7 7820H','64GB','NVIDIA GeForce GTX 1080 x2','18.4  FHD IPS (1920 x 1080)','SSD 1.5TB','001.jpg')
,('1026','2002','3002','ASUS ROG Zephyrus G14',100,1650,'AMD Ryzen 7 4800HS','16GB','NVIDIA GeForce GTX 1650Ti','14  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1027','2002','3001','ASUS TUF A15',100,1550,'AMD Ryzen 7 4800H','16GB','NVIDIA GeForce RTX 2060','15.6  FHD IPS (1920 x 1080)','SSD 1024GB','001.jpg')
,('1028','2003','3002','Acer Swift 5',100,1400,'Intel Core i7-1065G7','8GB','Intel® Iris® Plus Graphics','14  FHD IPS (1920 x 1080)','SSD 512GB','001.jpg')
,('1029','2003','3001','Acer Aspire E5',100,1000,'Intel® Core™ i7 8850U','4GB',' NVIDIA Geforce MX130','15.6  FHD IPS (1920 x 1080)','HDD 1TB','001.jpg')
,('1030','2001','3003','Dell Inspiron 5490',100,1100,'Intel Core i7-10510U','8GB','NVIDIA GeForce MX230','14  FHD IPS (1920 x 1080)','HDD 512GB','001.jpg');


CREATE TABLE `khachhang` (
  `MAKH` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `HOKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TENKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SDT` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
INSERT INTO `khachhang` VALUES 
 ('0001','Khổng','Uy','0524791392',1)
,('0002','Hàng ','Lam','01220299791',1)
,('0003','Ngân ','Quỳnh','84934469133',1)
,('0004','Song','Linh','01209871310',1)
,('0005','Triệu','Nhi','0383328301',1)
,('0006','Doãn ','Vỹ','0913002337',1)
,('0007','Cát','Chấn','84220016639',1)
,('0008','Đoàn','Băng Thiên','84660643701',1)
,('0009','Cù','Nhân','0765582496',1)
,('0010','Đàm','Liên Sáng','02814422157',1)
,('0011','Mạc','Nguyên','84124258521',1)
,('0012','Mạch ','Hội','0899975884',1)
,('0013','Thái','Lam Ngân','0124 667 01',1)
,('0014','Hoàng','Yên','01276698515',1)
,('0015','Chu','Chí Nhu','0969509887',1)
,('0016','Lâm ','Hành','01222839156',1)
,('0017','Lò','Nguyệt','01262471150',1)
,('0018','Tiếp ','Phụng Hoàng','03201037255',1)
,('0019','Mạch','Kiều','02301631021',1)
,('0020','Mai','Sĩ Khang','84689573609',1)
,('0021','Phạm','Bạch','0604016224',1)
,('0022','Cung','Mi','84168512102',1)
,('0023','Khương','Hiếu Điền','0790751053',1)
,('0024','Kha','Phương Diệu','0982204725',1)
,('0025','Chung','Hiệp Bằng','01990074694',1)
,('0026','Viên','Trang Liên','0965620036',1)
,('0027','Thân','Từ Hoài','03202626857',1)
,('0028','Võ','Bảo','0361141622',1)
,('0029','Lê','Toản','0675806247',1)
,('0030','Giáp','An Lâm','0963176874',1);


CREATE TABLE `nhanvien` (
  `MANV` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `HONV` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `TENNV` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `PHAI` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `DIACHI` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NAMSINH` int(4) NOT NULL,
  `IMG` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
INSERT INTO nhanvien VALUES
 ('4001','Doãn','Sương','Nữ','8 Phố Đan Lộc Phong, Phường Dao Châu, Huyện Nhung ',2000,'001.jpg',1)
,('4002','Vương','Yến Hoàng','Nữ','9, Ấp Nhậm Nhã, Ấp Như Thành, Huyện Lã Đắk Nông',1990,'001.jpg',1)
,('4003','Kiều','Hoài Hành','Nam','5065 Phố Ung Phúc Liêm, Thôn Nhã Ngân, Huyện Bá Đồ',1991,'001.jpg',1)
,('4004','Phi','Trúc','Nữ','8566 Phố Thy, Xã 50, Quận 46 Ninh Bình',1991,'001.jpg',1)
,('4005','Lư','Khải Huấn','Nam','8, Ấp 28, Phường Nghị Duyên Vinh, Quận Quảng Cái N',1889,'001.jpg',1)
,('4006','Triệu','Bổng','Nam','84, Thôn Cù Trà Thể, Xã Hậu Tuyến, Huyện Khiếu Trư',1992,'001.jpg',1)
,('4007','Song','Loan','Nữ','77 Phố Diệu, Xã Yên Thoại Ngà, Quận Di Lực Lâm Đồn',2000,'001.jpg',1)
,('4008','La ','Trầm Nhân','Nữ','54 Phố Thiều, Thôn Mâu Quyết, Huyện Khanh Lễ Cần T',2001,'001.jpg',1)
,('4009','Khúc','Cảnh','Nam','0131, Ấp 3, Phường Đan, Huyện Ty Điện Biên',1999,'001.jpg',1)
,('4010','Đôn','Thùy Xuân','Nữ','93 Phố Triệu, Xã 09, Huyện 92 An Giang',1998,'001.jpg',1);

CREATE TABLE `chuongtrinhkhuyenmai` (
  `MAKM` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `TENKM` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `NGAYBD` date NOT NULL,
  `NGAYKT` date NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `chuongtrinhkhuyenmai` VALUES 
 ('5001','Space Exploration Sale 2020','2022-07-18','2022-07-21',1)
,('5002','Summer Sale 2020','2020-07-25','2020-08-02',1)
,('5003','Halloween Sale 2020','2020-10-26','2020-11-01',1)
,('5004','Autumn Sale 2020','2020-11-22','2020-11-28',1)
,('5005','Winter Sale 2020 (Cozy Cottage)','2020-12-21','2021-01-04',1)
,('5006','Lunar New Year Sale 2021','2021-02-15','2021-02-19',1)
,('5007','Spring Cleaning Event 2021','2021-05-24','2021-05-28',0)
,('5008','Summer Sale 2021','2021-06-21','2021-07-05',0)
,('5009','World Enviroment Sale 2021','2021-10-11','2021-10-12',0)
,('5010','Halloween Sale 2021','2021-10-29','2021-11-01',0);

CREATE TABLE `chitietkm` (
  `MAKM` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MALAPTOP` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `CACHTINH` float NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO chitietkm VALUES
(5001	,1001	,0.3	,1),
(5001	,1002	,0.3	,1),
(5001	,1003	,0.3	,1),
(5002	,1004	,0.4	,1),
(5002	,1005	,0.4	,1),
(5002	,1006	,0.4	,1),
(5003	,1007	,0.3	,1),
(5003	,1008	,0.3	,1),
(5003	,1009	,0.3	,1),
(5004	,1010	,0.4	,1),
(5004	,1011	,0.4	,1),
(5004	,1012	,0.4	,1),
(5005	,1013	,0.4	,1),
(5005	,1014	,0.4	,1),
(5005	,1015	,0.4	,1),
(5006	,1016	,0.5	,1),
(5006	,1017	,0.5	,1),
(5006	,1018	,0.5	,1),
(5007	,1019	,0.3	,1),
(5007	,1020	,0.3	,1),
(5007	,1021	,0.3	,1),
(5008	,1022	,0.4	,1),
(5008	,1023	,0.4	,1),
(5008	,1024	,0.4	,1),
(5009	,1025	,0.2	,1),
(5009	,1026	,0.2	,1),
(5009	,1027	,0.2	,1),
(5010	,1028	,0.2	,1),
(5010	,1029	,0.2	,1),
(5010	,1030	,0.2	,1);
			

CREATE TABLE `hoadon` (
  `MAHD` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MAKH` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MANV` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `NGAYHD` date NOT NULL,
  `MAKM` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `GIAGOC` int(11) NOT NULL,
  `TONGTIEN` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `hoadon` VALUES 
 ('6001','0003','4002','2020-06-11','5001',19850,13895)
,('6002','0010','4001','2020-06-13','5002',21550,12930)
,('6003','0011','4002','2020-06-14','5003',31150,21805)
,('6004','0012','4003','2020-06-15','5005',38450,23070)
,('6005','0005','4002','2020-06-16','5009',39050,39050)
,('6006','0002','4003','2020-06-26','5008',22250,13350)
,('6007','0012','4010','2020-06-27','5007',28250,28250)
,('6008','0014','4005','2020-05-13','5003',25500,17850)
,('6009','0021','4003','2020-05-15','5007',30695,30695)
,('6010','0011','4001','2020-04-01','5009',28600,28600)
,('6011','0013','4003','2020-06-28','5009',12360,12360)
,('6012','0027','4010','2020-07-07','5005',32150,19290)
,('6013','0028','4006','2020-07-08','5010',6700,6700)
,('6014','0021','4001','2020-07-09','5010',17200,17200)
,('6015','0013','4001','2020-07-10','5005',56700,34010)
,('6016','0014','4002','2020-07-11','5004',19950,11970)
,('6017','0015','4004','2020-07-12','5002',24250,14550)
,('6018','0012','4003','2020-07-13','5003',42150,29505)
,('6019','0015','4005','2020-07-14','5002',13050,10440)
,('6020','0021','4010','2020-07-15','5001',22250,15575)
,('6021','0015','4005','2020-07-16','5008',27418,27418)
,('6022','0011','4007','2020-07-17','5004',25400,15240)
,('6023','0019','4009','2020-07-18','5010',15100,15100)
,('6024','0014','4008','2020-07-19','5001',22250,15575)
,('6025','0013','4009','2020-07-20','5004',23300,13980)
,('6026','0011','4002','2020-07-21','5006',27850,13925)
,('6027','0018','4005','2020-07-22','5009',56800,56800)
,('6028','0030','4007','2020-07-23','5007',12461,12461)
,('6029','0029','4009','2020-07-24','5006',45150,22575)
,('6030','0013','4001','2020-07-25','5010',20700,20700);

CREATE TABLE `chitiethoadon` (
  `MAHD` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MALAPTOP` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `DONGIA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `chitiethoadon` VALUES 
  ('6001','1001',1,10250)
 ,('6001','1002',2,2400)
 ,('6001','1003',4,1200)
 ,('6002','1004',3,1000)
 ,('6002','1005',5,1400)
 ,('6002','1006',7,1650)
 ,('6003','1007',2,9000)
 ,('6003','1008',3,2150)
 ,('6003','1009',2,3350)
 ,('6004','1013',6,2300)
 ,('6004','1014',5,4350)
 ,('6004','1015',1,2900)
 ,('6005','1025',2,7500)
 ,('6005','1026',8,1650)
 ,('6005','1027',7,1550)
 ,('6006','1001',1,10250)
 ,('6006','1002',3,2400)
 ,('6006','1003',4,1200)
 ,('6007','1004',5,1000)
 ,('6007','1005',6,1400)
 ,('6007','1006',9,1650)
 ,('6008','1007',1,9000)
 ,('6008','1008',3,2150)
 ,('6008','1009',3,3350)
 ,('6009','1019',4,2600)
 ,('6009','1020',5,2136)
 ,('6009','1021',5,1923)
 ,('6010','1025',2,1200)
 ,('6010','1026',1,1200)
 ,('6010','1027',5,5000)
 ,('6011','1025',1,7500)
 ,('6011','1026',2,1650)
 ,('6011','1027',3,1550)
 ,('6012','1013',2,2300)
 ,('6012','1014',3,4350)
 ,('6012','1015',5,2900)
 ,('6013','1028',1,1400)
 ,('6013','1029',2,1000)
 ,('6013','1030',3,1100)
 ,('6014','1028',4,1400)
 ,('6014','1029',5,1000)
 ,('6014','1030',6,1100)
 ,('6015','1013',7,2300)
 ,('6015','1014',8,4350)
 ,('6015','1015',2,2900)
 ,('6016','1010',3,1650)
 ,('6016','1011',4,1250)
 ,('6016','1012',1,10000)
 ,('6017','1004',1,1000)
 ,('6017','1005',6,1400)
 ,('6017','1006',9,1650)
 ,('6018','1007',2,9000)
 ,('6018','1008',5,2150)
 ,('6018','1009',4,3350)
 ,('6019','1004',10,1000)
 ,('6019','1005',1,1400)
 ,('6019','1006',1,1650)
 ,('6020','1001',1,10250)
 ,('6020','1002',3,2400)
 ,('6020','1003',4,1200)
 ,('6021','1022',2,2600)
 ,('6021','1023',5,2136)
 ,('6021','1024',6,1923)
 ,('6022','1010',1,1650)
 ,('6022','1011',3,1250)
 ,('6022','1012',2,10000)
 ,('6023','1028',1,1400)
 ,('6023','1029',6,1000)
 ,('6023','1030',7,1100)
 ,('6024','1001',1,10250)
 ,('6024','1002',4,2400)
 ,('6024','1003',2,1200)
 ,('6025','1010',2,1650)
 ,('6025','1011',8,1250)
 ,('6025','1012',1,10000)
 ,('6026','1016',3,2750)
 ,('6026','1017',2,6500)
 ,('6026','1018',4,1650)
 ,('6027','1019',5,1200)
 ,('6027','1020',9,1200)
 ,('6027','1021',8,5000)
 ,('6028','1022',1,2600)
 ,('6028','1023',2,2136)
 ,('6028','1024',3,1923)
 ,('6029','1016',1,2750)
 ,('6029','1017',5,6500)
 ,('6029','1018',6,1650)
 ,('6030','1028',7,1400)
 ,('6030','1029',8,1000)
 ,('6030','1030',9,1100);

CREATE TABLE `phieunhap` (
  `MAPN` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MANCC` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MANV` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `NGAYNHAP` date NOT NULL,
  `TONGTIEN` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO phieunhap VALUES
('7001', '3001', '4001', '2020-06-13', '205000'),					
('7002', '3002', '4010', '2020-06-14', '48000'),
('7003', '3003', '4005', '2020-06-15', '24000'),				
('7004', '3001', '4006', '2020-06-16', '20000'),
('7005', '3002', '4007', '2020-06-17', '28000'),
('7006', '3003', '4002', '2020-06-18', '33000'),
('7007', '3001', '4010', '2020-06-19', '180000'),
('7008', '3002', '4003', '2020-06-20', '43000'),
('7009', '3003', '4002', '2020-06-21', '67000'),
('7010', '3001', '4006', '2020-06-22', '33000'),
('7011', '3002', '4009', '2020-06-23', '25000'),
('7012', '3003', '4010', '2020-06-24', '200000'),
('7013', '3001', '4004', '2020-06-25', '46000'),
('7014', '3002', '4003', '2020-06-26', '87000'),
('7015', '3003', '4005', '2020-06-27', '58000'),
('7016', '3001', '4008', '2020-06-28', '55000'),
('7017', '3002', '4002', '2020-06-29', '130000'),
('7018', '3003', '4003', '2020-06-30', '33000'),
('7019', '3001', '4004', '2020-07-01', '24000'),
('7020', '3002', '4001', '2020-07-02', '24000'),
('7021', '3003', '4006', '2020-07-03', '100000'),
('7022', '3001', '4008', '2020-07-04', '52000'),
('7023', '3002', '4002', '2020-07-05', '42720'),
('7024', '3003', '4001', '2020-07-06', '38460'),
('7025', '3001', '4009', '2020-07-07', '150000'),
('7026', '3002', '4010', '2020-07-08', '33000'),
('7027', '3003', '4003', '2020-07-09', '31000'),
('7028', '3001', '4004', '2020-07-10', '28000'),
('7029', '3002', '4005', '2020-07-11', '20000'),
('7030', '3003', '4007', '2020-07-12', '22000');

CREATE TABLE `chitietphieunhap` (
  `MAPN` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MALAPTOP` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `DONGIA` int(11) NOT NULL,
  `THANHTIEN` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO chitietphieunhap VALUES
('7001', '1001', '20', '10250', '205000'),
('7002', '1002', '20', '2400', '48000'),
('7003', '1003', '20', '1200', '24000'),
('7004', '1004', '20', '1000', '20000'),
('7005', '1005', '20', '1400', '28000'),
('7006', '1006', '20', '1650', '33000'),
('7007', '1007', '20', '9000', '180000'),
('7008', '1008', '20', '2150', '43000'),
('7009', '1009', '20', '3350', '67000'),
('7010', '1010', '20', '1650', '33000'),
('7011', '1011', '20', '1250', '25000'),
('7012', '1012', '20', '10000', '200000'),
('7013', '1013', '20', '2300', '46000'),
('7014', '1014', '20', '4350', '87000'),
('7015', '1015', '20', '2900', '58000'),
('7016', '1016', '20', '2750', '55000'),
('7017', '1017', '20', '6500', '130000'),
('7018', '1018', '20', '1650', '33000'),
('7019', '1019', '20', '1200', '24000'),
('7020', '1020', '20', '1200', '24000'),
('7021', '1021', '20', '5000', '100000'),
('7022', '1022', '20', '2600', '52000'),
('7023', '1023', '20', '2136', '42720'),
('7024', '1024', '20', '1923', '38460'),
('7025', '1025', '20', '7500', '150000'),
('7026', '1026', '20', '1650', '33000'),
('7027', '1027', '20', '1550', '31000'),
('7028', '1028', '20', '1400', '28000'),
('7029', '1029', '20', '1000', '20000'),
('7030', '1030', '20', '1100', '22000');



CREATE TABLE `user` (
  `USERID` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `USERNAME` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `PASS` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ROLE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
INSERT INTO `user` (`USERID`, `USERNAME`, `PASS`, `ROLE`, `enable`) VALUES
('4001', 'admin', '6bcbf7637fbf64a825165f588d9f0fb250e2a0cca105aa5af05fc7302f038328', 'Admin', 1),
('4002', 'sale', '96c9b9f54ed88e7f54028183ed73b45b23fa1ccba60878cef70672b8caea6017', 'Nhân Viên', 1),
('4003', '4003', 'a6124fbf413da8934929ed9972e2e55a8406202be86e711274fc5fc25ae06dd3', 'Nhân Viên', 1),
('4004', '4004', '2fb077740f63f4ee401d318a6b3ac631fb27d6f29fb3b5e26c1739497ea85fac', 'Nhân Viên', 1),
('4005', '4005', '93eb6442e74d9015918d7dbcbb18dd1d68de24054712f6cffc14b5e7dee08283', 'Nhân Viên', 1),
('4006', '4006', '5d6deeb70f1142bc79d97114a4dd19c8a367f3473945fd157a47425a81e88434', 'Nhân Viên', 1),
('4007', '4007', '3ea050432c1110e961900456741053319c272cf1925f78a946e04d98cf3a301f', 'Nhân Viên', 1),
('4008', '4008', '1677ec564a365a04449e0a3b787be243642c54c66b6f13c67e4fe5768e94e9ed', 'Nhân Viên', 1),
('4009', '4009', '2111e4501cb70dfdbe92337866659851e75b135d3e1f9d350682fc23ea0f44cf', 'Nhân Viên', 1),
('4010', '4010', 'de0e2e000f737a72c4ca943e8653dfb20bde4399fd76e422cb0301d7956b8b8a', 'Nhân Viên', 1),
('4011', 'test4011', '7476ac5227548892e26f4cffc0a7662c6bac11e39c93cc326a7b85a09cca0189', 'Nhân Viên', 1);


ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MAHD`,`MALAPTOP`),
  ADD KEY `fk_chitiethoadon_laptop_idx` (`MALAPTOP`),
  ADD KEY `fk_chitiethoadon_hoadon` (`MAHD`);

--
-- Indexes for table `chitietkm`
--
ALTER TABLE `chitietkm`
  ADD PRIMARY KEY (`MAKM`,`MALAPTOP`),
  ADD KEY `fk_chitietkm_laptop_idx` (`MALAPTOP`),
  ADD KEY `fk_chitietkm_ctkm_idx` (`MAKM`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`MAPN`,`MALAPTOP`),
  ADD KEY `fk_chitietphieunhap_laptop_idx` (`MALAPTOP`),
  ADD KEY `fk_chitietphieunhap_phieunhap_idx` (`MAPN`);

--
-- Indexes for table `chuongtrinhkhuyenmai`
--
ALTER TABLE `chuongtrinhkhuyenmai`
  ADD PRIMARY KEY (`MAKM`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MAHD`),
  ADD KEY `fk_hoadon_nhanvien_idx` (`MANV`),
  ADD KEY `fk_hoadon_khachhang_idx` (`MAKH`),
  ADD KEY `fk_hoadon_ctkm_idx` (`MAKM`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MAKH`),
  ADD UNIQUE KEY `khachhang_SDT_UNIQUE` (`SDT`);

--
-- Indexes for table `laptop`
--
ALTER TABLE `laptop`
  ADD PRIMARY KEY (`MALAPTOP`),
  ADD UNIQUE KEY `laptop_ten_UNIQUE` (`TEN`),
  ADD KEY `fk_laptop_nhasx_idx` (`MANSX`),
  ADD KEY `fk_laptop_nhacungcap_idx` (`MANCC`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MANCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MANV`);

--
-- Indexes for table `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  ADD PRIMARY KEY (`MANSX`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MAPN`),
  ADD KEY `fk_phieulap_nhacungcap_idx` (`MANCC`),
  ADD KEY `fk_phieulap_nhanvien_idx` (`MANV`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`USERID`),
  ADD UNIQUE KEY `user_username_unique` (`USERNAME`),
  ADD KEY `fk_user_userid` (`USERID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `fk_chitiethoadon_hoadon` FOREIGN KEY (`MAHD`) REFERENCES `hoadon` (`MAHD`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_chitiethoadon_laptop` FOREIGN KEY (`MALAPTOP`) REFERENCES `laptop` (`MALAPTOP`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `chitietkm`
--
ALTER TABLE `chitietkm`
  ADD CONSTRAINT `fk_chitietkm_ctkm` FOREIGN KEY (`MAKM`) REFERENCES `chuongtrinhkhuyenmai` (`MAKM`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_chitietkm_laptop` FOREIGN KEY (`MALAPTOP`) REFERENCES `laptop` (`MALAPTOP`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `fk_chitietphieunhap_laptop` FOREIGN KEY (`MALAPTOP`) REFERENCES `laptop` (`MALAPTOP`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_chitietphieunhap_phieunhap` FOREIGN KEY (`MAPN`) REFERENCES `phieunhap` (`MAPN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `fk_hoadon_ctkm` FOREIGN KEY (`MAKM`) REFERENCES `chuongtrinhkhuyenmai` (`MAKM`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_hoadon_khachhang` FOREIGN KEY (`MAKH`) REFERENCES `khachhang` (`MAKH`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_hoadon_nhanvien` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `laptop`
--
ALTER TABLE `laptop`
  ADD CONSTRAINT `fk_laptop_nhacungcap` FOREIGN KEY (`MANCC`) REFERENCES `nhacungcap` (`MANCC`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_laptop_nhasx` FOREIGN KEY (`MANSX`) REFERENCES `nhasanxuat` (`MANSX`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `fk_phieulap_nhacungcap` FOREIGN KEY (`MANCC`) REFERENCES `nhacungcap` (`MANCC`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_phieulap_nhanvien` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_userid` FOREIGN KEY (`USERID`) REFERENCES `nhanvien` (`MANV`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
