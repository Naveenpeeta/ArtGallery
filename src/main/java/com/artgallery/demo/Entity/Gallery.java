package com.artgallery.demo.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Gallery")
public class Gallery {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer galleryId;
	@Column(name="getGalleryName")
	private String galleryName;
	@Column(name="location")
	private String location;
	@ManyToMany(mappedBy = "galleries")
	@JsonIgnoreProperties("galleries")
	private List<Artist> artists;
	
	public int getGalleryId() {
		return galleryId;
	}
	public void setGalleryId(Integer galleryId) {
		this.galleryId = galleryId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<Artist> getArtists() {
		return artists;
	}
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	public String getGalleryName() {
		return galleryName;
	}
	public void setGalleryName(String galleryName) {
		this.galleryName = galleryName;
	}
	
	
	
	
}
