package com.vti.service;

import com.vti.modal.dto.CreateSingerDto;
import com.vti.modal.dto.SearchSingerRequest;
import com.vti.modal.entity.Singer;
import org.springframework.data.domain.Page;

public interface ISingerService {
    Singer findByName(String name);
//    boolean existsByName(String name);
    Page<Singer> searchSinger(SearchSingerRequest request);
    Singer create(CreateSingerDto createSingerDto);
}
