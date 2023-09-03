package com.vti.service;

import com.vti.exception.CustomException;
import com.vti.exception.ErrorResponseEnum;
import com.vti.modal.dto.BaseRequest;
import com.vti.modal.dto.CreateSingerDto;
import com.vti.modal.dto.SearchSingerRequest;
import com.vti.modal.entity.Singer;
import com.vti.repository.SingerRepository;
import com.vti.repository.Specification.SingerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SingerService implements ISingerService{
    @Autowired
    private SingerRepository singerRepository;

    @Override
    public Singer findByName(String name) {
        if (singerRepository.existsByName(name)){
            return singerRepository.findByName(name);
        } else throw new CustomException(ErrorResponseEnum.NOT_FOUND_SINGER_NAME);
    }

    @Override
    public Page<Singer> searchSinger(SearchSingerRequest request) {
        BaseRequest.verify(request);

        Specification<Singer> condition = SingerSpecification.singerBuildCondition(request);

        PageRequest pageRequest = BaseRequest.buildPageRequest(request);
        return singerRepository.findAll(condition,pageRequest);
    }

    @Override
    public Singer create(CreateSingerDto createSingerDto) {
        if (singerRepository.existsByName(createSingerDto.getName())){
            throw new CustomException(ErrorResponseEnum.NAME_SINGER_EXISTED);
        }
        Singer singer = new Singer(createSingerDto);
        return singerRepository.save(singer);
    }


}
