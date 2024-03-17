package com.digit.quizApp.repository;

import com.digit.quizApp.model.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepo extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    List<Question> findByCategoryAndDifficultyLevel(String category, String difficultyLevel);

    @Query(value = "select q from Question q where q.category like 'P%'")
    List<Question> getPythonQuestions();

    @Query(value = "select q from Question q where q.category = :category order by RANDOM() LIMIT :numQ")
    List<Question> findRandomQuestionsByCategory(int numQ, String category);
}
