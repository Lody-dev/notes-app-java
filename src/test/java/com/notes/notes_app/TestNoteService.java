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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestNoteService {
    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    private List<NoteEntity> simulatedNoteList;

    @BeforeEach
    void setup()
    {
        //Arrange
        NoteEntity noteEntity0 = new NoteEntity();
        NoteEntity noteEntity1 = new NoteEntity();
        NoteEntity noteEntity2 = new NoteEntity();
        NoteEntity noteEntity3 = new NoteEntity();
        NoteEntity noteEntity4 = new NoteEntity();
        //note    0 setup
        noteEntity0.setTitle("I am a note");
        noteEntity0.setContent("My id is 0");
        noteEntity0.setPinned(false);
        noteEntity0.setId(0L);

        //note    1 setup
        noteEntity1.setTitle("I am other note");
        noteEntity1.setContent("And my id is 1");
        noteEntity1.setPinned(true);
        noteEntity1.setId(1L);

        //note    2 setup
        noteEntity2.setTitle("");
        noteEntity2.setContent("");
        noteEntity2.setPinned(true);
        noteEntity2.setId(2L);

        //note    3 setup
        noteEntity3.setTitle("");
        noteEntity3.setContent("Some content");
        noteEntity3.setPinned(true);
        noteEntity3.setId(3L);

        //note    4 setup
        noteEntity4.setTitle("");
        noteEntity4.setContent("A content with more than 50 characters P{}<>?:P{:<>?><???<><><>?@#%%#@$##%@#@%%##@%$%@%**%%^^$%&$&%$&^$&#fasdfasdhfasdhfasdkahflkjsdsdfaskhdljfasjhdkllfahjskdfasdhjkllfasdhkjfajjfasdhkjfalsdsd");
        noteEntity4.setPinned(true);
        noteEntity4.setId(4L);

        simulatedNoteList = new ArrayList<>(Arrays.asList(noteEntity0, noteEntity1, noteEntity2, noteEntity3,noteEntity4));
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
//        when(noteRepository.findAllByOwnerOrderByPinnedDescUpdatedAtDesc()).thenReturn(simulatedNoteList);
//
//        List<NoteEntity> result = noteService.findOrderedNotes();
//        assertEquals(5,result.size());
//        assertEquals(simulatedNoteList.get(0), result.get(0));
//        assertEquals("Empty Note", result.get(2).getTitle());
//        assertEquals("Empty title", result.get(3).getTitle());
//        assertEquals("Some content", result.get(3).getContent());
//        assertEquals(53, result.get(4).getContent().length());
//    }
//
//    @Test
//    void checkPinNote()
//    {
//        when(noteRepository.findAllByOwnerOrderByPinnedDescUpdatedAtDesc()).thenReturn(simulatedNoteList);
//        when(noteRepository.findById(1L)).thenReturn(Optional.ofNullable(simulatedNoteList.get(1)));
//
//        boolean passed = false;
//        List<NoteEntity> noteList = noteService.findOrderedNotes();
//        boolean statePinned = noteList.get(1).getPinned();
//        noteService.pinNote(noteList.get(1).getId());
//        if(statePinned != noteList.get(1).getPinned())
//            passed = true;
//        assertTrue(passed);
//    }

}
