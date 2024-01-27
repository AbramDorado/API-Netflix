package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Movies;
import com.example.demo.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository movierepo;
	

    public List<Movies> getAllMovies() {
        return movierepo.findAll();
    }
    
    public Movies getMovieById(Integer movieId) {
        return movierepo.findById(movieId).orElse(null);
    }

    public void saveMovie(Movies movie) {
        movierepo.save(movie);
    }
	
}
