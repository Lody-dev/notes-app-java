package com.notes.notes_app;

import com.notes.notes_app.model.NoteEntity;
import com.notes.notes_app.repository.NoteRepository;
import com.notes.notes_app.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestNoteService {
    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    private List<NoteEntity> simulatedNoteList;

    @BeforeEach
    void seatup()
    {
        //Arrange
        NoteEntity noteEntity0 = new NoteEntity();
        NoteEntity noteEntity1 = new NoteEntity();

        noteEntity0.setTitle("Title");
        noteEntity0.setContent("Content");
        noteEntity1.setTitle("Title");
        noteEntity1.setContent("Content");

        simulatedNoteList = new ArrayList<>(Arrays.asList(noteEntity0, noteEntity1));

    }
    @Test
    void checkListStatusIfNotEmpty(){
        //Act
        String status = noteService.checkListStatus(simulatedNoteList);
        //Assert
        assertEquals("All notes", status);
    }

    @Test
    void checkListStatusIfEmpty(){
        //Arrange
        simulatedNoteList.clear();
        //Act
        String status = noteService.checkListStatus(simulatedNoteList);
        //Assert
        assertEquals("No notes here, add one :)", status);
    }

//    @Test
//    void checkOrderedNotes()
//    {
//        when()
//    }

}
