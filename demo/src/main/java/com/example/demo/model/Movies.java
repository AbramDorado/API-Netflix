package com.example.demo.model;

import java.time.Duration;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="movies")
public class Movies {

	@Id
	@Column(name="movie_id")
	Integer movie_id;
	
	@Column(name="title")
	String title;
	
	 @Column(name="duration")
	    String duration;
	 
	 public Movies() {
		 
	 }

	public Movies(Integer movie_id, String title, String duration) {
		this.movie_id = movie_id;
		this.title = title;
		this.duration = duration;
	}

	public Integer getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
}

