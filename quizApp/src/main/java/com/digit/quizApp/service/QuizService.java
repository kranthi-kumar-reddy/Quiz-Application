package com.digit.quizApp.service;

import com.digit.quizApp.model.*;
import com.digit.quizApp.repository.QuestionsRepo;
import com.digit.quizApp.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuestionsRepo questionsRepo;
    public ResponseEntity<?> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionsRepo.findRandomQuestionsByCategory(numQ,category);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        return new ResponseEntity<>(quizRepo.save(quiz), HttpStatus.CREATED);

    }

    public ResponseEntity<?> getQuizById(int id) throws Throwable {
        Quiz quiz = quizRepo.findById(id).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new NullPointerException();
            }
        });

        List<Question> questions = quiz.getQuestions();


        return new ResponseEntity<>(questions.stream().map(this::mapToQuestionWrapper).collect(Collectors.toList()),HttpStatus.OK);
    }

    private QuestionWrapper mapToQuestionWrapper(Question question) {
        System.out.println(question.getId());
        return QuestionWrapper.builder()
                .id(question.getId())
                        .category(question.getCategory())
                                .difficultyLevel(question.getDifficultyLevel())
                                        .questionTitle(question.getQuestionTitle())
                                                .option1(question.getOption1())
                                                        .option2(question.getOption2())
                                                                .option3(question.getOption3())
                                                                        .option4(question.getOption4())
                                                                                .build();

    }

    public ResponseEntity<?> fetchResult(int quizId, ResponseData response) {
        int score = 0;

        Optional<Quiz> quiz = quizRepo.findById(quizId);
        List<Question> questions = quiz.get().getQuestions();
//        ResponseEntity<List<QuestionWrapper>> responses = restTemplate.exchange("http://localhost:8080/quiz/get/" + quizId, HttpMethod.GET, null, new ParameterizedTypeReference<List<QuestionWrapper>>() {});
//        List<QuestionWrapper> questions = responses.getBody();
        for(Response responseOfUser : response.getResponses())
        {
            int questionId = responseOfUser.getQuestionId();
            Question dataOfQuestion = findTheQuestionById(questionId,questions);
            if(dataOfQuestion==null){
                throw new NullPointerException("Question data not found for question id "+questionId);
            }

            if(dataOfQuestion.getRightAnswer().equals(responseOfUser.getSelectedAnswer())){
                score++;
            }

        }


     return new ResponseEntity<>(score,HttpStatus.OK);
    }

    private Question findTheQuestionById(int questionId,List<Question> questions) {
        for(Question question : questions){
            if(question.getId()==questionId){
                return question;
            }
        }
        return null;
    }
}
