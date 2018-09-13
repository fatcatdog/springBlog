DROP TABLE IF EXISTS `upvote`;
CREATE TABLE  `upvote` (
  `id` int(11) NOT NULL auto_increment,
  `author_id` int(11) NOT NULL,
  `blog_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
   FOREIGN KEY (AUTHOR_ID) REFERENCES user(ID),
    FOREIGN KEY (BLOG_ID) REFERENCES blog(ID)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
