package com.artgallery.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.artgallery.demo.Entity.Art;
import com.artgallery.demo.Entity.Artist;
import com.artgallery.demo.repository.ArtJpaRepository;
import com.artgallery.demo.repository.ArtRepository;
import com.artgallery.demo.repository.ArtistJpaRepository;

@Service
public class ArtJpaService implements  ArtRepository{
    @Autowired
    private ArtJpaRepository artJpaRepository;
    @Autowired
    private ArtistJpaRepository artistJpaRepository;
	@Override
	public ArrayList<Art> getArt() {
		List <Art> art =artJpaRepository.findAll();
		ArrayList<Art> arts = new ArrayList<>(art);
		return arts;
	}
	@Override
	public Art getArtById(int artId) {
		try {
			Art art = artJpaRepository.findById(artId).get();
			return art;

		}catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
	}
	@Override
	public Art addArt(Art art) {
		try {
			int artistId =art.getArtist().getArtistId();
			Artist artist = artistJpaRepository.findById(artistId).get();
			art.setArtist(artist);
			
			return artJpaRepository.save(art);
		}catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
		
	}
	@Override
	public Art updateArt(int artId, Art art) {
		try {
			Art newArt = artJpaRepository.findById(artId).get();
			if(art.getArtTitle() !=null) {
				newArt.setArtTitle(art.getArtTitle());
			}
			if(art.getTheme()!=null) {
				newArt.setArtId(art.getArtId());
			}
			if(art.getArtist()!=null) {
				int artistId =art.getArtist().getArtistId();
				Artist artist = artistJpaRepository.findById(artistId).get();
				art.setArtist(artist);
			}
		return artJpaRepository.save(newArt);
		}catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
	}
	@Override
	public void deleteArt(int artId) {
		try {
			artJpaRepository.deleteById(artId);
		}catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);

	}
	@Override
	public Artist getArtArtist(int artId) {
		try {
			Art art = artJpaRepository.findById(artId).get();
			
		return art.getArtist();
		}catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
	}
}
