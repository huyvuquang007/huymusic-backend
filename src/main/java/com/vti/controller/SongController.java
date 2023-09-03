package com.vti.controller;

import com.vti.modal.dto.SearchSongRequest;
import com.vti.modal.dto.SongCreatDto;
import com.vti.modal.dto.SongUpdateDto;
import com.vti.modal.entity.Song;
import com.vti.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/song")
@CrossOrigin(value = "*")
@Validated
public class SongController {
    @Autowired
    ISongService songService;

    @GetMapping("/get-all-song")
    List<Song> getAllSong(){
        return songService.getAllSong();
    }

    @GetMapping("/{id}")
    Song getById(@PathVariable int id){
        return songService.getById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')") // phân quyền admin đc truy cập
    @PostMapping("/create-song")
    Song createSong(@RequestBody @Valid SongCreatDto songCreatDto){
        return songService.createSong(songCreatDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')") // phân quyền admin đc truy cập
    @PutMapping("/update-song")
    Song updateSong(@RequestBody SongUpdateDto songUpdateDto){
        return songService.updateSong(songUpdateDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')") // phân quyền admin đc truy cập
    @DeleteMapping("/{id}")
    public String deleteSong(@PathVariable int id){
        return songService.deleteSong(id);
    }

    @PostMapping("/search")
    Page<Song> search (@RequestBody SearchSongRequest request){
        return songService.search(request);
    }
}
