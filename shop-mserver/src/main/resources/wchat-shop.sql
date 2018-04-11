/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.18 : Database - wchat_shop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wchat_shop` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `wchat_shop`;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userid` varchar(32) NOT NULL,
  `openid` varchar(32) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `nickname` varchar(25) DEFAULT NULL,
  `province` varchar(25) DEFAULT NULL,
  `headimgurl` text,
  `country` varchar(25) DEFAULT NULL,
  `city` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`userid`,`openid`,`sex`,`nickname`,`province`,`headimgurl`,`country`,`city`) values ('428d65cb08af4b08af6e0b2bdb8dc0df','owF-Kw_dNmnrDON7ZGz8VDP3p7k4','男','','四川','http://thirdwx.qlogo.cn/mmopen/vsKJUu5m5blP6jFGicS5nBz3ZJopEOypnCs8FiceXxbQrLjlf3mjUk9gWTHHYCDJRTicfsgHcyvlEoaoYiapiaiakhGbmveIenQ9ib0/132','中国','成都');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
