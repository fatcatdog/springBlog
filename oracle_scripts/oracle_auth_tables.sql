BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE role';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
CREATE TABLE  role (
  role_id number(10) NOT NULL,
  role varchar2(255) default NULL,
  PRIMARY KEY (role_id)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE role_seq START WITH 2 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER role_seq_tr
 BEFORE INSERT ON role FOR EACH ROW
 WHEN (NEW.role_id IS NULL)
BEGIN
 SELECT role_seq.NEXTVAL INTO :NEW.role_id FROM DUAL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE bloguser';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
CREATE TABLE bloguser (
  id number(10) NOT NULL,
  firstname varchar2(255) NOT NULL,
  lastname varchar2(255) NOT NULL,
  email varchar2(255) NOT NULL,
  password varchar2(255) NOT NULL,
  active number(10) default NULL,
  PRIMARY KEY (id)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER user_seq_tr
 BEFORE INSERT ON bloguser FOR EACH ROW
 WHEN (NEW.id IS NULL)
BEGIN
 SELECT user_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE user_role';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
CREATE TABLE  user_role (
  user_id number(10) NOT NULL,
  role_id number(10) NOT NULL,
  PRIMARY KEY  (user_id,role_id)
 ,
  CONSTRAINT user_userrole FOREIGN KEY (user_id) REFERENCES bloguser (id),
  CONSTRAINT role_userrole FOREIGN KEY (role_id) REFERENCES role (role_id)
) ;

CREATE INDEX user_role_key ON user_role (role_id);

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE persistent_logins';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
CREATE TABLE  persistent_logins (
  username varchar2(64) NOT NULL,
  series varchar2(64) NOT NULL,
  token varchar2(64) NOT NULL,
  last_used timestamp(0) default SYSTIMESTAMP NOT NULL,
  PRIMARY KEY  (series)
) ;

INSERT INTO role VALUES (1,'ADMIN');