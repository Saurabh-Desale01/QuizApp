package com.quizapp.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapp.quizapp.entity.QuestionWrapper;
import com.quizapp.quizapp.entity.Questions;
import com.quizapp.quizapp.entity.Quiz;
import com.quizapp.quizapp.entity.Response;
import com.quizapp.quizapp.repository.QuestionsRepository;
import com.quizapp.quizapp.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	public QuizRepository quizRepository;
	@Autowired
	public QuestionsRepository questionsRepository;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Questions> questions = questionsRepository.findRandomQuestionsByCategory(category, numQ);

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizRepository.save(quiz);

		return new ResponseEntity<>("Success", HttpStatus.CREATED);

	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizRepository.findById(id);
		List<Questions> questionsFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		for (Questions q : questionsFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getQuestionId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
					q.getOption3(), q.getOption4());
			questionsForUser.add(qw);
		}

		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizRepository.findById(id).get();
		List<Questions> questions = quiz.getQuestions();
		int right = 0;
		int i = 0;
		for (Response response : responses) {
			if (response.getResponse().equals(questions.get(i).getRightAnswer()))
				right++;

			i++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
