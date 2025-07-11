package com.example.Challenge.Repository;

import com.example.Challenge.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByOrderByUploadedAtDesc();
}
