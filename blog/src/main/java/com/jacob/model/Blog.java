package com.jacob.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
public class Blog {
 
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private int id;
 
 @Column(name = "author_id")
 private int author_id;
 
 @Column(name = "title")
 private String title; 
 
 @Column(name = "content")
 private String content;
 
 public Blog() {
 }
 
//public Blog(int author_id, String title, String content) {
//	super();
//	this.author_id = author_id;
//	this.title = title;
//	this.content = content;
//}
//
//public Blog(int id, int author_id, String title, String content) {
//	super();
//	this.id = id;
//	this.author_id = author_id;
//	this.title = title;
//	this.content = content;
//}

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

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}
 
}
 

