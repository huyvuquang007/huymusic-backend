package com.vti.service;

import com.vti.modal.dto.FavouriteCreateDto;
import com.vti.modal.entity.Account;
import com.vti.modal.entity.Favourite;
import com.vti.modal.entity.Song;
import com.vti.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteService implements IFavouriteService{
    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private ISongService songService;

    @Autowired
    private IAccountService accountService;

    @Override
    public Favourite create(FavouriteCreateDto favouriteCreateDto) {
        Account account = accountService.getById(favouriteCreateDto.getAccountID());
        Song song = songService.getById(favouriteCreateDto.getSongId());
        Favourite favourite = new Favourite();
        favourite.setAccount(account);
        favourite.setSong(song);
        return favouriteRepository.save(favourite);
    }

    @Override
    public List<Favourite> getListFavouriteByID(int id) {
        return favouriteRepository.findByAccount_Id(id);
    }

    @Override
    public void deleteFavourite(int id) {
        if (favouriteRepository.existsById(id)){
        favouriteRepository.deleteById(id);
        }
    }
}
