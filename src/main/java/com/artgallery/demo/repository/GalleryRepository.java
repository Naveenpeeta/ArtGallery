package com.artgallery.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.artgallery.demo.Entity.Artist;
import com.artgallery.demo.Entity.Gallery;

public interface GalleryRepository {
   ArrayList<Gallery> getGalleries();
   Gallery getGalleryById(int galleryId);
   Gallery addGallery(Gallery gallery);
   Gallery updateGallery(int galleryId,Gallery gallery);
   void deleteGallery(int galleryId);
   List <Artist> getArtistGalleries(int galleryId);
}
