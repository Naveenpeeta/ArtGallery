package com.artgallery.demo.repository;

import java.util.ArrayList;

import com.artgallery.demo.Entity.Art;
import com.artgallery.demo.Entity.Artist;

public interface ArtRepository {
     ArrayList<Art> getArt();
     Art getArtById(int artId);
     Art addArt(Art art);
     Art updateArt(int artId, Art art);
     void deleteArt(int artId);
     Artist getArtArtist(int artId);
}
