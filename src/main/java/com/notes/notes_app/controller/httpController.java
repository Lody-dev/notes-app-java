package com.notes.notes_app.controller;

import com.notes.notes_app.model.NoteEntity;
import com.notes.notes_app.repository.NoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class httpController {

    private final NoteRepository noteRepository;

    public httpController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    public String noteList() {
        return "index"; // corresponds to index.html in templates folder
    }

    @PostMapping("/note")
    public String newNote()
    {
        noteEntity = new NoteEntity();
    }
}