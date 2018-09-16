DROP TABLE IF EXISTS `comment`;
CREATE TABLE  `comment` (
  `id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `blog_id` int(11) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY  (`id`),
	FOREIGN KEY (BLOG_ID) REFERENCES blog(ID),
	FOREIGN KEY (AUTHOR_ID) REFERENCES user(ID)
);

