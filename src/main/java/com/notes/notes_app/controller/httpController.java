package com.notes.notes_app.controller;
import com.notes.notes_app.service.NoteService;
import org.springframework.ui.Model;
import com.notes.notes_app.model.NoteEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class httpController {

    private final NoteService noteService;

    public httpController(NoteService noteService)
    {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String loadMainPage(Model model) {
        List<NoteEntity> notes = noteService.findOrderedNotes();
        model.addAttribute("notes", notes);
        model.addAttribute("status", noteService.checkListStatus(notes));
        return "index";
    }

    @GetMapping("/new-note")
    public String newNote() {
        return "new-note";
    }

    @PostMapping("/submit")
    public String postNote(@ModelAttribute NoteEntity noteEntity)
    {
        if(noteService.isTitleTooLong(noteEntity.getTitle())) {
            throw new TitleTooLongException("Title too long");
        }
        noteService.saveEditedNote(noteEntity);
        return "redirect:/";
    }

    @PostMapping("/note")
    public String updateNote(@ModelAttribute NoteEntity noteEntity, Model model) {
        model.addAttribute("note", noteService.updateNote(noteEntity));
        return "edit-note";
    }

    @PostMapping("/pin")
    public String pinNote(@RequestParam Long id) {
        noteService.pinNote(id);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteNote(@ModelAttribute NoteEntity noteEntity)
    {
        noteService.deleteNote(noteEntity);
        return "redirect:/";
    }
}
