package com.digit.quizApp.controller;

import com.digit.quizApp.model.Question;
import com.digit.quizApp.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Question> getQuestionByCategoryAndDifficultyLevel(@RequestParam String category,@RequestParam String difficultyLevel)
    {
        return questionService.getQuestionByCategoryAndDifficultyLevel(category,difficultyLevel);
    }

    @GetMapping("/getPython")
    public List<Question> getPythonQuestions()
    {
        return questionService.getPythonQuestions();
    }
}
