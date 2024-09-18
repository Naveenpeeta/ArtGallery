package com.artgallery.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artgallery.demo.Entity.Art;
import com.artgallery.demo.Entity.Artist;

@Repository
public interface ArtistJpaRepository extends JpaRepository<Artist, Integer>{

	List<Art> findByArtist(Artist artist);

	

}
