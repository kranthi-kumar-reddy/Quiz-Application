package com.digit.quizApp.service;

import com.digit.quizApp.model.Question;
import com.digit.quizApp.repository.QuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionsRepo questionsRepo;

    public List<Question> getAllQuestions() {
        return questionsRepo.findAll();
    }

    public List<Question> getJavaQuestions(String category) {
        return questionsRepo.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionsRepo.save(question);
        return "data inserted successfully";
    }

    public List<Question> getQuestionByCategoryAndDifficultyLevel(String category, String difficultyLevel) {
        return questionsRepo.findByCategoryAndDifficultyLevel(category,difficultyLevel);
    }

    public List<Question> getPythonQuestions() {
        return questionsRepo.getPythonQuestions();
    }
}
