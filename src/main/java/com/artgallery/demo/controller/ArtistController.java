package com.artgallery.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.demo.Entity.Art;
import com.artgallery.demo.Entity.Artist;
import com.artgallery.demo.Entity.Gallery;
import com.artgallery.demo.service.ArtistJpaService;

@RestController
public class ArtistController {
  @Autowired
  ArtistJpaService artistJpaService;
  @GetMapping("/galleries/artists")
  public ArrayList<Artist> getArtists() {
      return artistJpaService.getArtists();
  }
  @GetMapping("/galleries/artists/{artistId}")
  public Artist getArtistById(@PathVariable("artistId") int artistId) {
      return artistJpaService.getArtistById(artistId);
  }
  @PostMapping("/galleries/artists")
  public Artist addArtist1(@RequestBody Artist artist) {
      return artistJpaService.addArtist(artist);
  }
  @PostMapping("/galleries/artists")
  public Artist addArtist(@RequestBody Artist artist) {
      return artistJpaService.addArtist(artist);
  }

  @PutMapping("/galleries/artists/{artistId}")
  public Artist updateArtist(@PathVariable("artistId") int artistId, @RequestBody Artist artist) {
      return artistJpaService.updaArtist(artistId, artist);
  }

  @DeleteMapping("/galleries/artists/{artistId}")
  public void deleteArtist(@PathVariable("artistId") int artistId) {
      artistJpaService.deleteArtist(artistId);
  }

  @GetMapping("/artists/{artistId}/arts")
  public List<Art> getArtistArts(@PathVariable("artistId") int artistId) {
      return artistJpaService.getArtistArts(artistId);
  }

  @GetMapping("/artists/{artistId}/galleries")
  public List<Gallery> getArtistGalleries(@PathVariable("artistId") int artistId) {
      return artistJpaService.getArtistGalleries(artistId);
  }
}
