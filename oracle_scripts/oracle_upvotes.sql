BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE upvote';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
CREATE TABLE  upvote (
  id number(10) NOT NULL,
  blog_id number(10) NOT NULL,
    author_id number(10) NOT NULL,
  PRIMARY KEY  (id),
	FOREIGN KEY (BLOG_ID) REFERENCES blog(ID),
    	FOREIGN KEY (author_id) REFERENCES bloguser(ID)

    );
