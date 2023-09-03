package com.vti.controller;

import com.vti.modal.dto.CreateSingerDto;
import com.vti.modal.dto.SearchSingerRequest;
import com.vti.modal.entity.Singer;
import com.vti.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/singer")
@CrossOrigin(value = "*")
public class SingerController {
    @Autowired
    private ISingerService singerService;

    @PostMapping("/search-singer")
    Page<Singer> searchSinger (@RequestBody SearchSingerRequest request){
        return singerService.searchSinger(request);
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')") // phân quyền admin đc truy cập
    Singer create(@RequestBody CreateSingerDto createSingerDto){
        return singerService.create(createSingerDto);
    }
}
