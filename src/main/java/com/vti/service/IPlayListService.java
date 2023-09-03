package com.vti.service;

import com.vti.modal.dto.PlayListCreateDto;
import com.vti.modal.entity.PlayList;

import java.util.List;

public interface IPlayListService {
    PlayList create(PlayListCreateDto playListCreateDto);
    List<PlayList> getAllPlayList();
}
