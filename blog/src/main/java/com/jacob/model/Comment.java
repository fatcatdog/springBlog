//@Author: Jacob Duchen

package com.jacob.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
 
 @Id
 private int id;
 
 @Column(name = "author_id")
 private int author_id;
 
 @Column(name = "blog_id")
 private int blog_id; 
 
 @Column(name = "content")
 private String content;
 
 

public Comment(int id, int author_id, int blog_id, String content) {
	super();
	this.id = id;
	this.author_id = author_id;
	this.blog_id = blog_id;
	this.content = content;
}

public Comment() {}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getAuthor_id() {
	return author_id;
}

public void setAuthor_id(int author_id) {
	this.author_id = author_id;
}

public int getBlog_id() {
	return blog_id;
}

public void setBlog_id(int blog_id) {
	this.blog_id = blog_id;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}
 
 
}
