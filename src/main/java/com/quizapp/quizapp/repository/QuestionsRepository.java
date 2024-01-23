package com.quizapp.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizapp.quizapp.entity.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer>{

	public List<Questions> findByCategory(String category);

	@Query(value = "SELECT * FROM questions q Where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	public List<Questions> findRandomQuestionsByCategory(String category, int numQ);
}
