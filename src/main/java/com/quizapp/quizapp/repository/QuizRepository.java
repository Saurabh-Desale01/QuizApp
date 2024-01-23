package com.quizapp.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizapp.quizapp.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
