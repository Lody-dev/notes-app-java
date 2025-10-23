package com.notes.notes_app.service;

import com.notes.notes_app.model.NoteEntity;
import com.notes.notes_app.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CRUDService
{
    private final NoteRepository noteRepository;

    @Autowired
    CRUDService(NoteRepository noteRepository)
    {
        this.noteRepository = noteRepository;
    }

    public NoteEntity save(NoteEntity noteEntity)
    {
        return noteRepository.save(noteEntity);
    }

    public List<NoteEntity> findAll()
    {
        return noteRepository.findAll();
    }

    public NoteEntity findById(Long id)
    {
        return noteRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id)
    {
        noteRepository.deleteById(id);
    }
}
