package com.vti.repository;

import com.vti.modal.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SongRepository extends JpaRepository<Song, Integer> , JpaSpecificationExecutor<Song> {
    boolean existsByName (String name);
    boolean existsById (int id);
    Song findByName (String name);
}
