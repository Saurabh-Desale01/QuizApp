package com.quizapp.quizapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.quizapp.entity.Questions;
import com.quizapp.quizapp.service.QuestionsService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("questions")
public class QuestionsController {
	
	@Autowired
	public QuestionsService questionsService;
	
	@GetMapping("allquestions")
	public ResponseEntity<List<Questions>> getData() {
		return questionsService.getAllQuestions();
	}
	
	@GetMapping("{questionId}")
	public ResponseEntity<Optional<Questions>> getQuestionsById(@PathVariable("questionId") Integer Id){
		return questionsService.getQuestionsById(Id);
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable("category") String category){
		return questionsService.getQuestionByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Questions question) {
		return questionsService.addQuestion(question);
	}

}
