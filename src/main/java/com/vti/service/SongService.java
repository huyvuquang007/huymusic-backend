package com.vti.service;

import com.vti.exception.CustomException;
import com.vti.exception.ErrorResponseEnum;
import com.vti.modal.dto.BaseRequest;
import com.vti.modal.dto.SearchSongRequest;
import com.vti.modal.dto.SongCreatDto;
import com.vti.modal.dto.SongUpdateDto;
import com.vti.modal.entity.Singer;
import com.vti.modal.entity.Song;
import com.vti.repository.SingerRepository;
import com.vti.repository.SongRepository;
import com.vti.repository.Specification.SongSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService implements ISongService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ISingerService singerService;

    @Override
    public List<Song> getAllSong() {
        return songRepository.findAll();
    }

    @Override
    public Song getById(int id) {
        Optional<Song> songOptional = songRepository.findById(id);
        if (songOptional.isEmpty()){
            throw new CustomException(ErrorResponseEnum.NOT_FOUND_SONG);
        }else return songOptional.get();
    }

    @Override
    public Song getByName(String name) {
        return songRepository.findByName(name);
    }

    @Override
    public Song createSong(SongCreatDto songCreatDto) {
        if (songRepository.existsByName(songCreatDto.getName())){
            throw new CustomException(ErrorResponseEnum.NAME_SONG_EXISTED);
        }
        Song song = new Song(songCreatDto);
        Singer singer = singerService.findByName(songCreatDto.getSinger());
        song.setSinger(singer);
        return songRepository.save(song);
    }

    @Override
    public Song updateSong(SongUpdateDto songUpdateDto) {
        if (songRepository.existsById(songUpdateDto.getId())){
            Song song = new Song(songUpdateDto);
            Singer singer = singerService.findByName(songUpdateDto.getSinger());
            song.setSinger(singer);
            return songRepository.save(song);
        } else throw new CustomException(ErrorResponseEnum.NOT_FOUND_SONG);


    }

    @Override
    public String deleteSong(int id) {
        if (songRepository.existsById(id)){
            songRepository.deleteById(id);
            return  "Xóa thành công";
        } else return "xóa thất bại";
    }

    @Override
    public Page<Song> search(SearchSongRequest request) {
        BaseRequest.verify(request);

        Specification<Song> condition = SongSpecification.songBuildCondition(request);

        PageRequest pageRequest = BaseRequest.buildPageRequest(request);
        return songRepository.findAll(condition,pageRequest);
    }
}
