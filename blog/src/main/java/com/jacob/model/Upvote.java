//@Author: Jacob Duchen

package com.jacob.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "upvote")
public class Upvote {
 
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private int id;
 
 @Column(name = "author_id")
 private int author_id;
 
 @Column(name = "blog_id")
 private int blog_id;

public Upvote(int id, int author_id, int blog_id) {
	super();
	this.id = id;
	this.author_id = author_id;
	this.blog_id = blog_id;
}

public Upvote() {
	super();
}

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
 
 
}
