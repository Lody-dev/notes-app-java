package com.notes.notes_app.controller;
import org.springframework.ui.Model;
import com.notes.notes_app.model.NoteEntity;
import com.notes.notes_app.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class httpController {

    @Autowired
    private final NoteRepository noteRepository;


    public httpController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    public String noteList(Model model) {
        int i = -1;
        String var;
        List<NoteEntity> notes= noteRepository.findAll();
        while(++i < notes.size())
        {
            if(notes.get(i).getTitle().equals("") && !notes.get(i).getContent().equals(""))
                notes.get(i).setTitle("Empty title");
            if(notes.get(i).getContent().equals("") && notes.get(i).getTitle().equals(""))
                notes.get(i).setTitle("Empty Note");
            if(notes.get(i).getContent().length() > 50)
                notes.get(i).setContent(notes.get(i).getContent().substring(0, 50) + "...");
        }
        model.addAttribute("notes", notes);
        return "index"; // corresponds to index.html in templates folder
    }

    @GetMapping("/new-note")
    public String newNote() {
        return "new-note";
    }

    @PostMapping("/note")
    public String updateNote(@ModelAttribute NoteEntity noteEntity, Model model) {
        NoteEntity note = noteRepository.findById(noteEntity.getId()).get();
        model.addAttribute("note", note);
        return "redirect:/new-note";
    }

    @PostMapping("/submit")
    public String postNote(@ModelAttribute NoteEntity noteEntity) {
        noteEntity.setPinned(false);
        noteRepository.save(noteEntity);
        return "redirect:/";
    }
}