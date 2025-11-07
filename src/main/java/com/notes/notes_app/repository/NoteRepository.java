package com.notes.notes_app.repository;

import com.notes.notes_app.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    List<NoteEntity> findByOwnerUsernameOrderByPinnedDescUpdatedAtDesc(String username);
    Optional<NoteEntity> findByIdAndOwnerUsername(long id, String username);
}