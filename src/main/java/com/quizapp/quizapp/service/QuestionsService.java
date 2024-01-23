package com.quizapp.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapp.quizapp.entity.Questions;
import com.quizapp.quizapp.repository.QuestionsRepository;

@Service
public class QuestionsService {
	
	@Autowired
	public QuestionsRepository questionsRepository;
	
	public ResponseEntity<List<Questions>> getAllQuestions(){
		return new ResponseEntity(questionsRepository.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<Optional<Questions>> getQuestionsById(Integer questionId) {
		return new ResponseEntity(questionsRepository.findById(questionId), HttpStatus.OK);
	}
	
	public ResponseEntity<List<Questions>> getQuestionByCategory(String category){
		return new ResponseEntity(questionsRepository.findByCategory(category), HttpStatus.OK);
	}
	
	public ResponseEntity<String> addQuestion(Questions question) {
		questionsRepository.save(question);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}

}
