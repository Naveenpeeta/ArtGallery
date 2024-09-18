package com.artgallery.demo.controller;

import java.util.ArrayList;

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
import com.artgallery.demo.service.ArtJpaService;


@RestController
public class ArtController {
  @Autowired
  ArtJpaService artJpaService;
  
  @GetMapping("/galleries/artists/arts")
  public  ArrayList<Art> getArt(){
	  return artJpaService.getArt();
  }
  @GetMapping("/galleries/artists/arts/{artId}")
  public Art getArtById(@PathVariable("artId") int artId) {
	  return artJpaService.getArtById(artId);
  }

  @PostMapping("/galleries/artists/arts")
  public Art addArt(@RequestBody Art art) {
      return artJpaService.addArt(art);
  }
  @PutMapping("/galleries/artists/arts/{artId}")
  public Art updateArt(@PathVariable("artId") int artId, @RequestBody Art art) {
      return artJpaService.updateArt(artId, art);
  }
  @DeleteMapping("/galleries/artists/arts/{artId}")
  public void deleteArt(@PathVariable("artId") int artId) {
      artJpaService.deleteArt(artId);
  }

  @GetMapping("/arts/{artId}/artist")
  public Artist getArtArtist(@PathVariable("artId") int artId) {
      return artJpaService.getArtArtist(artId);
  }
}
