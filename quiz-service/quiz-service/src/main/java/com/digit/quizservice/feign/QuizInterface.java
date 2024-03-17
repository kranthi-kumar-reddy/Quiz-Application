package com.digit.quizservice.feign;

import com.digit.quizservice.model.QuestionWrapper;
import com.digit.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    // generate
    @GetMapping("question/generateQues")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQ);


    // getQuestions (questionId)
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> ids);

    // getScore
    @PostMapping("question/calculateScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
