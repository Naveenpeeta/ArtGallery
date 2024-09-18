package com.artgallery.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artgallery.demo.Entity.Gallery;

@Repository
public interface GalleryJpaRepository extends JpaRepository<Gallery, Integer> {

}
