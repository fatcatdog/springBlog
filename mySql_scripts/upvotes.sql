create table Upvotes
(
ID bigint not null,
AUTHOR_ID bigint not null,
BLOG_ID bigint not null,
 PRIMARY KEY(ID),
 FOREIGN KEY (AUTHOR_ID) REFERENCES User(ID),
 FOREIGN KEY (BLOG_ID) REFERENCES Blog(ID)
);
