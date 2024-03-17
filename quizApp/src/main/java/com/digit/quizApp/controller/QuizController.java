package com.digit.quizApp.controller;

import com.digit.quizApp.model.Response;
import com.digit.quizApp.model.ResponseData;
import com.digit.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private RestTemplate restTemplate;

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<?> createQuiz(@RequestParam String category,@RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getQuizQuestions(@PathVariable int id) throws Throwable {
        return quizService.getQuizById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<?> fetchResult(@PathVariable("id") int quizId,@RequestBody ResponseData response)
    {
        return quizService.fetchResult(quizId,response);
    }

}
