use mybatis;
DROP TABLE IF EXISTS `man`;
create table `man`(
    `id` int(11) not null AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `sex` varchar(10) DEFAULT NULL,
    `address` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
insert into `man`(`id`,`name`,`sex`,`address`)
values (1,'张三','男','北京'),(2,'李四','女','北京'),(3,'王五','男','上海');