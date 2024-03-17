package com.digit.quizservice.controller;


import com.digit.quizservice.model.QuizDto;
import com.digit.quizservice.model.ResponseData;
import com.digit.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("quiz")
public class QuizController {


    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<?> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategory(),quizDto.getNumQ(),quizDto.getTitle());
    }

//    @GetMapping("get/{id}")
//    public ResponseEntity<?> getQuizQuestions(@PathVariable int id) throws Throwable {
//        return quizService.getQuizById(id);
//    }

    @PostMapping("submit/{id}")
    public ResponseEntity<?> fetchResult(@PathVariable("id") int quizId,@RequestBody ResponseData response)
    {
        return quizService.fetchResult(quizId,response);
    }

}
