package com.vti.service;

import com.vti.modal.dto.FavouriteCreateDto;
import com.vti.modal.entity.Favourite;

import java.util.List;

public interface IFavouriteService {
    Favourite create(FavouriteCreateDto favouriteCreateDto);
    List<Favourite> getListFavouriteByID(int id);
    void deleteFavourite(int id);
}
