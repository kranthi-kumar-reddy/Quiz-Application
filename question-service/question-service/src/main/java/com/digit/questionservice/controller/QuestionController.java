package com.digit.questionservice.controller;


import com.digit.questionservice.model.Question;
import com.digit.questionservice.model.QuestionWrapper;
import com.digit.questionservice.model.Response;
import com.digit.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("{category}")
    public List<Question> getJavaQuestions(@PathVariable("category") String category){
//        StringBuffer sb = request.getRequestURL();
//        String category = sb.substring(sb.lastIndexOf("/")+1,sb.length());
        return questionService.getJavaQuestions(category);
    }

    @PostMapping("addQuestion")
    public String addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }


    @GetMapping("/getQuestionByCategoryAndDifficultyLevel")
    public List<Question> getQuestionByCategoryAndDifficultyLevel(@RequestParam String category, @RequestParam String difficultyLevel)
    {
        return questionService.getQuestionByCategoryAndDifficultyLevel(category,difficultyLevel);
    }

    @GetMapping("/getPython")
    public List<Question> getPythonQuestions()
    {
        return questionService.getPythonQuestions();
    }

    // generate
    @GetMapping("generateQues")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam int numQ)
    {
        return questionService.getQuestionsForQuiz(category,numQ);
    }


    // getQuestions (questionId)
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> ids)
    {
        return questionService.getQuestionsById(ids);
    }


    // getScore
    @PostMapping("calculateScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }

}
