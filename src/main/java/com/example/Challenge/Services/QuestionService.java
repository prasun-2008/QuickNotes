package com.example.Challenge.Services;

import com.example.Challenge.Model.Question;
import com.example.Challenge.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository repo;

    public List<Question> getAllQuestions() {
        return repo.findAllByOrderByUploadedAtDesc();
    }

    public void save(Question q) {
        q.setUploadedAt(LocalDateTime.now());
        repo.save(q);
    }

    public Question getById(Long id) {
        return repo.findById(id).orElse(null);
    }
}

