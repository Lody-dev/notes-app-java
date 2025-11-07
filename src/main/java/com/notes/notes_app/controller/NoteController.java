package com.notes.notes_app.controller;
import com.notes.notes_app.service.NoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import com.notes.notes_app.model.NoteEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService)
    {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String loadMainPage(Model model, @AuthenticationPrincipal User authUser) {
        List<NoteEntity> notes = noteService.findOrderedNotes(authUser.getUsername());
        model.addAttribute("notes", notes);
        model.addAttribute("status", noteService.checkListStatus(notes));
        return "index";
    }

    @GetMapping("/new-note")
    public String newNote() {
        return "new-note";
    }

    @PostMapping("/submit")
    public String postNote(@ModelAttribute NoteEntity noteEntity, @AuthenticationPrincipal User authUser)
    {
        if(noteService.isTitleTooLong(noteEntity.getTitle())) {
            throw new TitleTooLongException("Title too long");
        }
        noteService.saveEditedNote(noteEntity,  authUser.getUsername());
        return "redirect:/";
    }

    @PostMapping("/note")
    public String getNote(@ModelAttribute NoteEntity noteEntity, Model model) {
        model.addAttribute("note", noteService.updateNote(noteEntity));
        return "edit-note";
    }

    @PostMapping("/pin")
    public String pinNote(@RequestParam Long id) {
        noteService.pinNote(id);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteNote(@ModelAttribute NoteEntity noteEntity, @AuthenticationPrincipal User authUser)
    {
        Long id = noteEntity.getId();
        String username = authUser.getUsername();
        noteService.deleteNote(noteEntity);
        return "redirect:/";
    }
}