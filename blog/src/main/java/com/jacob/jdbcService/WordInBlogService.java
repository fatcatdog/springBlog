package com.jacob.jdbcService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.WordInBlogDAO;
import com.jacob.model.Blog;
import com.jacob.model.WordInBlog;

@Service("wordInBlogService")
public class WordInBlogService implements WordInBlogServiceInterface {

	@Autowired
	private WordInBlogDAO wordInBlogDAO;

	@Override
	public List<WordInBlog> getAllWordsInAllBlogs() {
		return wordInBlogDAO.getAllWordsInAllBlogs();
	}

	@Override
	public List<WordInBlog> getAllWordsInAllBlogsByWordSearched(String wordsSearched) {
		String[] searchedString = wordsSearched.trim().split(" ");
		List<WordInBlog> results = new ArrayList<WordInBlog>();

		for(int i = 0; i < searchedString.length; i++) {
			List<WordInBlog> individualWordResults = wordInBlogDAO.getAllWordsInAllBlogsByWordSearched(searchedString[i].toLowerCase());

			for(int j=0; j < individualWordResults.size(); j++) {
				results.add(individualWordResults.get(j));
			}
		}

		return results;
	}

	@Override
    public List<WordInBlog> getAllWordsInAllBlogsByBlogId(int blog_id){
		return wordInBlogDAO.getAllWordsInAllBlogsByBlogId(blog_id);
	}


	@Override
	public WordInBlog getWordInBlogById(int id) {
		return wordInBlogDAO.getWordInBlogById(id);
	}

	@Override
	public void saveWordInBlogInDb(WordInBlog wordInBlog) {
		wordInBlogDAO.saveWordInBlogInDb(wordInBlog);
	}

	@Override
	public void deleteWordInBlogFromDb(int id) {
		wordInBlogDAO.deleteWordInBlogFromDb(id);
	}

	@Override
	public int getANewId() {
		return wordInBlogDAO.getANewId();
	}

	@Override
	public int getBlogIdFromWordInBlog(int id) {
		WordInBlog wordInBlog = getWordInBlogById(id);

		return wordInBlog.getBlog_id();
	}

	@Override
	 public void saveWordsFromBlog(int blog_id, Blog blog_about_to_be_saved) {
		 String title = blog_about_to_be_saved.getTitle();
		 String content = blog_about_to_be_saved.getContent();

		 String allOfOurTextFromBlog = title + " " + content;
		 String[] words = allOfOurTextFromBlog.trim().split("\\P{L}+");

		 List<String> uniqueWords = new ArrayList<String>();

		 for(int i = 0; i < words.length; i++) {
			 if(!uniqueWords.contains(words[i].toLowerCase())) {
				 uniqueWords.add(words[i].toLowerCase());
			 }
		 }

		 for(int i = 0; i < uniqueWords.size(); i++) {
			 WordInBlog wordInBlog = new WordInBlog(getANewId(), blog_id, uniqueWords.get(i));
			 saveWordInBlogInDb(wordInBlog);
		 }

	 }

}
