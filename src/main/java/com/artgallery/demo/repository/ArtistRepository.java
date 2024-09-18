package com.artgallery.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.artgallery.demo.Entity.Art;
import com.artgallery.demo.Entity.Artist;
import com.artgallery.demo.Entity.Gallery;

public interface ArtistRepository {
    ArrayList<Artist> getArtists();
    Artist getArtistById(int artistId);
    Artist addArtist(Artist artist);
    Artist updaArtist(int artistId, Artist artist);
    void deleteArtist(int artistId);
    
    List <Art> getArtistArts(int artistId);
    List <Gallery> getArtistGalleries(int artistId);
}