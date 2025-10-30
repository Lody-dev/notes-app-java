package com.notes.notes_app.service;

import com.notes.notes_app.controller.NoteNotFoundException;
import com.notes.notes_app.model.NoteEntity;
import com.notes.notes_app.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class NoteService
{
    private final NoteRepository noteRepository;

    NoteService(NoteRepository noteRepository)
    {
        this.noteRepository = noteRepository;
    }

    public List<NoteEntity> findOrderedNotes()
    {
        int i;
        List<NoteEntity> notes= noteRepository.findAllByOrderByPinnedDescUpdatedAtDesc();

        i = -1;
        while(++i < notes.size())
        {
            if(notes.get(i).getTitle().isEmpty() && !notes.get(i).getContent().isEmpty())
                notes.get(i).setTitle("Empty title");
            if(notes.get(i).getContent().isEmpty() && notes.get(i).getTitle().isEmpty())
                notes.get(i).setTitle("Empty Note");
            if(notes.get(i).getContent().length() > 50)
                notes.get(i).setContent(notes.get(i).getContent().substring(0, 50) + "...");
        }
        return notes;
    }

    public String checkListStatus(List<NoteEntity> notes)
    {
        String status;

       if(notes.isEmpty())
           status="No notes here, add one :)";
       else
            status = "All notes";
        return status;
    }

    public boolean isTitleTooLong(String title)
    {
        if(title.length() > 255)
            return true;
        return false;
    }

    public void saveEditedNote(NoteEntity noteEntity) {
        if(noteEntity.getPinned() == null)
            noteEntity.setPinned(false);

        if(noteEntity.getPinned())
            noteEntity.setPinned(true);
        else
            noteEntity.setPinned(false);
        noteRepository.save(noteEntity);
    }

    public void pinNote(Long id) {
        NoteEntity note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note with id " + id + " could not be pinned\nTry edit less html"));
        if (note.getPinned())
            note.setPinned(false);
        else
            note.setPinned(true);
        noteRepository.save(note);
    }

    public void deleteNote(NoteEntity noteEntity) {
        noteRepository.findById(noteEntity.getId())
                .orElseThrow(() -> new NoteNotFoundException("Note with id " + noteEntity.getId() + " could not be deleted\nTry edit less html"));
        noteRepository.delete(noteEntity);
    }

    public NoteEntity updateNote(NoteEntity noteEntity) {
        NoteEntity note = noteRepository.findById(noteEntity.getId())
                .orElseThrow(() -> new NoteNotFoundException("Note with id " + noteEntity.getId() + " could not be found\nTry edit less html"));
        return note;
    }
}
