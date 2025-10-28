package com.notes.notes_app.controller;
import org.springframework.ui.Model;
import com.notes.notes_app.model.NoteEntity;
import com.notes.notes_app.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class httpController {

    @Autowired
    private final NoteRepository noteRepository;


    public httpController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    public String noteList(Model model) {
        String status;

        int i = -1;
        List<NoteEntity> notes= noteRepository.findAllByOrderByPinnedDescUpdatedAtDesc();
        if(notes.size()>0)
            status = "All notes";
        else
            status = "No notes here, add one :)";
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
        model.addAttribute("status", status);
        return "index"; // corresponds to index.html in templates folder
    }

    @GetMapping("/new-note")
    public String newNote() {
        return "new-note";
    }

    @PostMapping("/submit")
    public String postNote(@ModelAttribute NoteEntity noteEntity) {
        if(noteEntity.getPinned() == null)
            noteEntity.setPinned(false);
        if(noteEntity.getPinned())
            noteEntity.setPinned(true);
        else
            noteEntity.setPinned(false);
        noteRepository.save(noteEntity);
        return "redirect:/";
    }

    @PostMapping("/note")
    public String updateNote(@ModelAttribute NoteEntity noteEntity, Model model) {
        Optional<NoteEntity> note = noteRepository.findById(noteEntity.getId());
        model.addAttribute("note", note);
        return "edit-note";
    }

    @PostMapping("/pin")
    public String pinNote(@RequestParam Long id) {
        NoteEntity note = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid note ID: " + id));
        if (note.getPinned())
            note.setPinned(false);
        else
            note.setPinned(true);
        noteRepository.save(note);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteNote(@ModelAttribute NoteEntity noteEntity)
    {
        noteRepository.delete(noteEntity);
        return "redirect:/";
    }
}
