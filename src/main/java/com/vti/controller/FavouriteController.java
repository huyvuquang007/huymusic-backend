package com.vti.controller;

import com.vti.modal.dto.FavouriteCreateDto;
import com.vti.modal.entity.Favourite;
import com.vti.service.IFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/favourite")
@CrossOrigin(value = "*")
public class FavouriteController {
    @Autowired
    private IFavouriteService favouriteService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public Favourite create(@RequestBody FavouriteCreateDto favouriteCreateDto){
        return favouriteService.create(favouriteCreateDto);
    }

    @GetMapping("/getListFavouriteByID/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public List<Favourite> getListFavouriteByID(@PathVariable int id){
        return favouriteService.getListFavouriteByID(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFavourite(@PathVariable int id){
        favouriteService.deleteFavourite(id);
    }
}
