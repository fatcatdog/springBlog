BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE comments';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
CREATE TABLE  comments (
  id number(10) NOT NULL,
  blog_id number(10) NOT NULL,
    author_id number(10) NOT NULL,
  content clob NOT NULL,
  PRIMARY KEY  (id),
	FOREIGN KEY (BLOG_ID) REFERENCES blog(ID),
    FOREIGN KEY (author_id) REFERENCES bloguser(ID)
);
