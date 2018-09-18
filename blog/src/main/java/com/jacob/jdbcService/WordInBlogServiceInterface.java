package com.jacob.jdbcService;

import java.util.List;

import com.jacob.model.Blog;
import com.jacob.model.WordInBlog;

public interface WordInBlogServiceInterface {
	public List<WordInBlog> getAllWordsInAllBlogs();
	
    public List<WordInBlog> getAllWordsInAllBlogsByBlogId(int blog_id);

	public List<WordInBlog> getAllWordsInAllBlogsByWordSearched(String wordsSearched);
	
	public WordInBlog getWordInBlogById(int id);
	
	public void saveWordInBlogInDb(WordInBlog wordInBlog);
	
	public void deleteWordInBlogFromDb(int id);
	
	public int getANewId();
	
	public int getBlogIdFromWordInBlog(int id);
	
	public void saveWordsFromBlog(int blog_id, Blog blog_about_to_be_saved);
	 
}
