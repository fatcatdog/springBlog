DROP TABLE IF EXISTS `words_in_blogs`;
CREATE TABLE  `words_in_blogs` (
  `id` int(11) NOT NULL,
  `blog_id` int(11) NOT NULL,
  `word` VARCHAR(255) NOT NULL,
  PRIMARY KEY  (`id`),
	FOREIGN KEY (BLOG_ID) REFERENCES blog(ID)
    );

