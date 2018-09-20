BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE words_in_blogs';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
CREATE TABLE  words_in_blogs (
  id number(10) NOT NULL,
  blog_id number(10) NOT NULL,
  word VARCHAR2(255) NOT NULL,
  PRIMARY KEY  (id),
	FOREIGN KEY (BLOG_ID) REFERENCES blog(ID)
    );

