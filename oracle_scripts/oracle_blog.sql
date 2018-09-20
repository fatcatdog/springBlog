BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE blog';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
create table Blog 
(
ID number(19) not null,
AUTHOR_ID number(19) not null,
TITLE clob not null,
CONTENT clob not null,
 PRIMARY KEY(ID),
 FOREIGN KEY (AUTHOR_ID) REFERENCES Bloguser(ID)
);



-- Generate ID using sequence and trigger
CREATE SEQUENCE blog_seq START WITH 2 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER blog_seq_tr
 BEFORE INSERT ON blog FOR EACH ROW
 WHEN (NEW.id IS NULL)
BEGIN
 SELECT blog_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

