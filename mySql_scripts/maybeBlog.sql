DROP TABLE IF EXISTS `blog`;
CREATE TABLE  `blog` (
  `id` int(11) NOT NULL auto_increment,
  `author_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

