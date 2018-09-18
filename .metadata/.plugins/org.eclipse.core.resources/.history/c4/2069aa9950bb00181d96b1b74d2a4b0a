package com.jacob.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "words_in_blog")	 
public class WordInBlog {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 
	 @Column(name = "blog_id")
	 private int blog_id;
	 
	 @Column(name = "title")
	 private String word;

	public WordInBlog(int id, int blog_id, String word) {
		super();
		this.id = id;
		this.blog_id = blog_id;
		this.word = word;
	}
	
	public WordInBlog() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	} 

	 
}
