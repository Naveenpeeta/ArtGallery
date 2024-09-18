package com.artgallery.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.artgallery.demo.Entity.Artist;
import com.artgallery.demo.Entity.Gallery;
import com.artgallery.demo.repository.ArtistJpaRepository;
import com.artgallery.demo.repository.GalleryJpaRepository;
import com.artgallery.demo.repository.GalleryRepository;

@Service
public class GalleryJpaService implements GalleryRepository{
   @Autowired
   private GalleryJpaRepository galleryJpaRepository;
   @Autowired
   private ArtistJpaRepository artistJpaRepository;
@Override
public ArrayList<Gallery> getGalleries() {
	List<Gallery> galleryList =  galleryJpaRepository.findAll();
	ArrayList<Gallery> galleries = new ArrayList<>(galleryList);
	return galleries;
}
@Override
public Gallery getGalleryById(int galleryId) {
	try {
		Gallery gallery =  galleryJpaRepository.findById(galleryId).get();
	
		return gallery;
	}catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
	
}
@Override
public Gallery addGallery(Gallery gallery) {
     List<Integer> artistIds = new ArrayList<>();
     for(Artist artist:gallery.getArtists()) {
    	 artistIds.add(artist.getArtistId());
     }
     List<Artist> artists = artistJpaRepository.findAllById(artistIds);
     gallery.setArtists(artists);
     
     for(Artist artist:artists) {
    	 artist.getGalleries().add(gallery);
     }
     Gallery savedGAllery = galleryJpaRepository.save(gallery);
     artistJpaRepository.saveAll(artists);
	return savedGAllery;
}
@Override
public Gallery updateGallery(int galleryId, Gallery gallery) {
	try{
        Gallery newGallery = galleryJpaRepository.findById(galleryId).get();
        if(gallery.getGalleryName()!=null) {
            newGallery.setGalleryName(gallery.getGalleryName());
        }
        if(gallery.getLocation()!=null) {
            newGallery.setLocation(gallery.getLocation());
        }
        if (gallery.getArtists() != null) {
            List<Artist> artists = newGallery.getArtists();
            for (Artist artist : artists) {
                artist.getGalleries().remove(newGallery);
            }
            artistJpaRepository.saveAll(artists);
            List<Integer> newArtistIds = new ArrayList<>();
            for (Artist artist : gallery.getArtists()) {
                newArtistIds.add(artist.getArtistId());
            }
            List<Artist> newArtists = artistJpaRepository.findAllById(newArtistIds);
            for (Artist artist : newArtists) {
                artist.getGalleries().add(newGallery);
            }
            artistJpaRepository.saveAll(newArtists);
            newGallery.setArtists(newArtists);
        }
        return galleryJpaRepository.save(newGallery);
    }catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
@Override
public void deleteGallery(int galleryId) {
	 try {
         Gallery gallery = galleryJpaRepository.findById(galleryId).get();

         List<Artist> artists = gallery.getArtists();
         for (Artist artist : artists) {
             artist.getGalleries().remove(gallery);
         }

         artistJpaRepository.saveAll(artists);

         galleryJpaRepository.deleteById(galleryId);

     } catch(Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
     }
     throw new ResponseStatusException(HttpStatus.NO_CONTENT);
 }
	

@Override
public List<Artist> getArtistGalleries(int galleryId) {
	 try {
         Gallery gallery = galleryJpaRepository.findById(galleryId).get();
         return gallery.getArtists();
     } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
     }
}
}
