package com.jacob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jacob.model.Blog;

@Repository("blogRepository")
public interface BlogRepository extends JpaRepository<Blog, Long> {
	
 Blog findById(int id);
 
 List<Blog> findAll(); 
 
 @Transactional
 void deleteById(int id);
 
}