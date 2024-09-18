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

import com.artgallery.demo.Entity.Artist;
import com.artgallery.demo.Entity.Gallery;
import com.artgallery.demo.service.GalleryJpaService;

@RestController
public class GallleryController {
  @Autowired
  GalleryJpaService galleryJpaService;
  @GetMapping("/galleries")
  public ArrayList<Gallery> getGalleries() {
      return galleryJpaService.getGalleries();
  }

  @GetMapping("/galleries/{galleryId}")
  public Gallery getGalleryById(@PathVariable("galleryId") int galleryId) {
      return galleryJpaService.getGalleryById(galleryId);
  }

  @PostMapping("/galleries")
  public Gallery addGallery(@RequestBody Gallery gallery) {
      return galleryJpaService.addGallery(gallery);
  }

  @PutMapping("/galleries/{galleryId}")
  public Gallery updateGallery(@PathVariable("galleryId") int galleryId, @RequestBody Gallery gallery) {
      return galleryJpaService.updateGallery(galleryId, gallery);
  }

  @DeleteMapping("/galleries/{galleryId}")
  public void deleteGallery(@PathVariable("galleryId") int galleryId) {
      galleryJpaService.deleteGallery(galleryId);
  }

  @GetMapping("/galleries/{galleryId}/artists")
  public List<Artist> getGalleryArtists(@PathVariable("galleryId") int galleryId) {
      return galleryJpaService.getArtistGalleries(galleryId);
  }
}
