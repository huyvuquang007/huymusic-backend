package com.vti.controller;

import com.vti.modal.dto.PlayListCreateDto;
import com.vti.modal.entity.PlayList;
import com.vti.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/playlist")
@CrossOrigin(value = "*")
public class PlayListController {
    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public PlayList create(@RequestBody PlayListCreateDto playListCreateDto){
        return playlistService.create(playListCreateDto);
    }

    @GetMapping("/get-all")
    public List<PlayList> getAllPlayList(){
        return playlistService.getAllPlayList();
    }
}
