package com.example.Challenge.Services;

import com.example.Challenge.Model.Notes;
import com.example.Challenge.Repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServices {
    @Autowired
    private NotesRepository notesRepo;

    public void createNote(Notes note) {
        notesRepo.save(note);
    }

    public void deleteNote(long id){
        notesRepo.deleteById(id);
    }

    public List<Notes> getAllNotes(){
        return notesRepo.findAll();
    }

    public Notes getNote(long id) {
        return notesRepo.findById(id).get();
    }

}
