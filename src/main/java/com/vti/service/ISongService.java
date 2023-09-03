package com.vti.service;

import com.vti.modal.dto.SearchSongRequest;
import com.vti.modal.dto.SongCreatDto;
import com.vti.modal.dto.SongUpdateDto;
import com.vti.modal.entity.Song;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISongService {
    List<Song> getAllSong();
    Song getById(int id);
    Song getByName(String name);
    Song createSong (SongCreatDto songCreatDto);
    Song updateSong (SongUpdateDto songUpdateDto);
    String deleteSong (int id);
    Page<Song> search (SearchSongRequest request);
}
