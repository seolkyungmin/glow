/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 5.6.10 : Database - dev_mauritius
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dev_mauritius` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `dev_mauritius`;

/*Table structure for table `test_picture` */

DROP TABLE IF EXISTS `test_picture`;

CREATE TABLE `test_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `test_picture_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `test_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

/*Data for the table `test_picture` */

insert  into `test_picture`(`id`,`NAME`,`user_id`,`created_at`,`updated_at`) values 
(27,'1_folder1',1,'2020-08-26 16:08:01','2020-08-26 16:25:13'),
(28,'1_folder2',1,'2020-08-26 16:08:32','2020-08-26 16:25:18'),
(29,'1_folder!!!',1,'2020-08-26 16:42:11','2020-08-26 16:42:11'),
(30,'2_folder!!!',2,'2020-08-26 18:46:01','2020-08-26 18:46:01');

/*Table structure for table `test_picture_child` */

DROP TABLE IF EXISTS `test_picture_child`;

CREATE TABLE `test_picture_child` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `picture_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `picture_id` (`picture_id`),
  CONSTRAINT `test_picture_child_ibfk_1` FOREIGN KEY (`picture_id`) REFERENCES `test_picture` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;

/*Data for the table `test_picture_child` */

insert  into `test_picture_child`(`id`,`NAME`,`url`,`picture_id`,`created_at`,`updated_at`) values 
(36,'사진1002','www.test1002.com',28,'2020-08-26 16:09:10','2020-08-26 16:09:10'),
(37,'사진1002','www.test1002.com',28,'2020-08-26 16:09:10','2020-08-26 16:09:10'),
(38,'사진1004','www.test1004.com',28,'2020-08-26 16:41:16','2020-08-26 16:41:16'),
(39,'사진1005','www.test1005.com',28,'2020-08-26 16:41:16','2020-08-26 16:41:16'),
(40,'사진1004','www.test1004.com',28,'2020-08-26 18:46:03','2020-08-26 18:46:03'),
(41,'사진1005','www.test1005.com',28,'2020-08-26 18:46:03','2020-08-26 18:46:03');

/*Table structure for table `test_point_log` */

DROP TABLE IF EXISTS `test_point_log`;

CREATE TABLE `test_point_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `picture_id` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `log_data` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `test_point_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `test_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `test_point_log` */

insert  into `test_point_log`(`id`,`user_id`,`picture_id`,`points`,`log_data`,`created_at`,`updated_at`) values 
(1,1,27,1000,'PICTURE_CREATE_POINT','2020-08-26 16:08:01','2020-08-26 16:08:01'),
(2,1,28,1000,'PICTURE_CREATE_POINT','2020-08-26 16:08:32','2020-08-26 16:08:32'),
(3,1,28,100,'PICTURE_CHILD_CREATE_POINT','2020-08-26 16:09:10','2020-08-26 16:09:10'),
(4,1,28,100,'PICTURE_CHILD_CREATE_POINT','2020-08-26 16:09:10','2020-08-26 16:09:10'),
(5,1,28,100,'PICTURE_CHILD_CREATE_POINT','2020-08-26 16:41:16','2020-08-26 16:41:16'),
(6,1,28,100,'PICTURE_CHILD_CREATE_POINT','2020-08-26 16:41:16','2020-08-26 16:41:16'),
(7,1,29,1000,'PICTURE_CREATE_POINT','2020-08-26 16:42:11','2020-08-26 16:42:11'),
(8,2,30,1000,'PICTURE_CREATE_POINT','2020-08-26 18:46:01','2020-08-26 18:46:01'),
(9,1,28,100,'PICTURE_CHILD_CREATE_POINT','2020-08-26 18:46:03','2020-08-26 18:46:03'),
(10,1,28,100,'PICTURE_CHILD_CREATE_POINT','2020-08-26 18:46:03','2020-08-26 18:46:03');

/*Table structure for table `test_tag` */

DROP TABLE IF EXISTS `test_tag`;

CREATE TABLE `test_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) NOT NULL,
  `picture_child_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `picture_child_id` (`picture_child_id`),
  CONSTRAINT `test_tag_ibfk_1` FOREIGN KEY (`picture_child_id`) REFERENCES `test_picture_child` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4;

/*Data for the table `test_tag` */

insert  into `test_tag`(`id`,`tag_name`,`picture_child_id`,`created_at`,`updated_at`) values 
(82,'test@@',36,'2020-08-26 16:09:10','2020-08-26 16:09:10'),
(83,'test@@',37,'2020-08-26 16:09:10','2020-08-26 16:09:10'),
(84,'test@@',38,'2020-08-26 16:41:16','2020-08-26 16:41:16'),
(85,'test@@',39,'2020-08-26 16:41:16','2020-08-26 16:41:16'),
(86,'test@@',40,'2020-08-26 18:46:03','2020-08-26 18:46:03'),
(87,'test@@',41,'2020-08-26 18:46:03','2020-08-26 18:46:03');

/*Table structure for table `test_user` */

DROP TABLE IF EXISTS `test_user`;

CREATE TABLE `test_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `points` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `test_user` */

insert  into `test_user`(`id`,`name`,`points`,`created_at`,`updated_at`) values 
(1,'test1',2400,'2020-08-25 11:11:59','2020-08-26 18:46:03'),
(2,'test2',1000,'2020-08-26 14:22:39','2020-08-26 18:46:01');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
