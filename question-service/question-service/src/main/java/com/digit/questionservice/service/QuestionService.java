package com.digit.questionservice.service;


import com.digit.questionservice.model.Question;
import com.digit.questionservice.model.QuestionWrapper;
import com.digit.questionservice.model.Response;
import com.digit.questionservice.repository.QuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int numQ) {
        List<Question> questions =  questionsRepo.findRandomQuestionsByCategory(numQ,category);
        List<Integer> questionIds = questions.stream().map(Question::getId).collect(Collectors.toList());
        return new ResponseEntity<>(questionIds, HttpStatus.OK);
    }



    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> ids) {
        List<QuestionWrapper> questions = ids.stream().map(this::mapToQuestionWrapper).collect(Collectors.toList());
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    private QuestionWrapper mapToQuestionWrapper(Integer id) {
        Question question =  questionsRepo.findById(id).get();
        return QuestionWrapper.builder()
                .id(question.getId())
                .questionTitle(question.getQuestionTitle())
                .difficultyLevel(question.getDifficultyLevel())
                .option1(question.getOption1())
                .option2(question.getOption2())
                .option3(question.getOption3())
                .option4(question.getOption4())
                .build();
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;
        for(Response response : responses)
        {
            Question question = questionsRepo.findById(response.getQuestionId()).get();
            if(question.getRightAnswer().equals(response.getSelectedAnswer()))
            {
                score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
