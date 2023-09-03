package com.vti.service;

import com.vti.modal.dto.PlayListCreateDto;
import com.vti.modal.entity.Account;
import com.vti.modal.entity.PlayList;
import com.vti.modal.entity.Song;
import com.vti.repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService implements IPlayListService{
    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ISongService songService;


    public PlayList create(PlayListCreateDto playListCreateDto){
        Account account = accountService.getById(playListCreateDto.getAccountId());
        Song song = songService.getById(playListCreateDto.getSongId());
        PlayList playList = new PlayList();
        playList.setAccount(account);
        playList.setName(playListCreateDto.getName());
        playList.setSong(song);
        return playListRepository.save(playList);
    }

    @Override
    public List<PlayList> getAllPlayList() {
        return playListRepository.findAll();
    }
}
