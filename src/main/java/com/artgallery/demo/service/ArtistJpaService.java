package com.artgallery.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.artgallery.demo.Entity.Art;
import com.artgallery.demo.Entity.Artist;
import com.artgallery.demo.Entity.Gallery;
import com.artgallery.demo.repository.ArtistJpaRepository;
import com.artgallery.demo.repository.ArtistRepository;
import com.artgallery.demo.repository.GalleryJpaRepository;

@Service
public class ArtistJpaService implements ArtistRepository {
   @Autowired
   private GalleryJpaRepository galleryJpaRepository;
   @Autowired
   private ArtistJpaRepository artistJpaRepository;
   @Autowired
   private ArtistJpaRepository artJpaRepository;
@Override
public ArrayList<Artist> getArtists() {
	List<Artist> artistList = artistJpaRepository.findAll();
	ArrayList<Artist> artist = new ArrayList<>(artistList);
	return artist;
}
@Override
public Artist getArtistById(int artistId) {
	try {
		Artist artist = artistJpaRepository.findById(artistId).get();
		
		return artist;	
	}catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
	
}
@Override
public Artist addArtist(Artist artist) {
	try {
		List<Integer> galleryIds = new ArrayList<>();
		for(Gallery gallery:artist.getGalleries()) {
			galleryIds.add(gallery.getGalleryId());
			}
		List<Gallery> galleries = galleryJpaRepository.findAllById(galleryIds);
		if(galleries.size()!=galleryIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		}
		artist.setGalleries(galleries);
		return artist;
	}catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
	
}
@Override
public Artist updaArtist(int artistId, Artist artist) {
	try {
		Artist newArtist = artistJpaRepository.findById(artistId).get();
		if(artist.getArtistName()!=null) {
			newArtist.setArtistName(artist.getArtistName());
		}
		if(artist.getGenre()!=null) {
			newArtist.setArtistName(artist.getGenre());
		}
		if(artist.getGalleries()!=null) {
			List<Integer> galleryIds = new ArrayList<>();
			for(Gallery gallery:artist.getGalleries()) {
				galleryIds.add(gallery.getGalleryId());
			}
			List<Gallery> galleries = galleryJpaRepository.findAllById(galleryIds);
			if(galleries.size()!=galleryIds.size()) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
            newArtist.setGalleries(galleries);

		}
		
	return artistJpaRepository.save(newArtist);
	}
	catch (NoSuchElementException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
@Override
public void deleteArtist(int artistId) {
	try {
		artistJpaRepository.deleteById(artistId);
	}catch (NoSuchElementException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    throw new ResponseStatusException(HttpStatus.NO_CONTENT);

	
}
@Override
public List<Art> getArtistArts(int artistId) {
	try {
		Artist artist = artistJpaRepository.findById(artistId).get();
	
		return artJpaRepository.findByArtist(artist);	
	}catch (NoSuchElementException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
	
}
@Override
public List<Gallery> getArtistGalleries(int artistId) {
	try {
		Artist artist = artistJpaRepository.findById(artistId).get();
		return artist.getGalleries();
	}catch (NoSuchElementException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
}
