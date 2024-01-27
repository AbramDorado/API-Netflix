package com.example.demo.controller;
import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Movies;
import com.example.demo.model.UserTable;
import com.example.demo.service.MovieService;
import com.example.demo.service.UserTableService;

import jakarta.annotation.PreDestroy;

@RestController
public class MovieController {

	@Autowired
	MovieService movieservice;
	
	@Autowired
	UserTableService usertableservice;
	
	//Get all movies
	 private ExecutorService executorService = Executors.newFixedThreadPool(10); // Use a thread pool for managing concurrent movie watches
	 @GetMapping("/movies")
	    public List<Movies> getAllMovies() {
	        return movieservice.getAllMovies();
	    }
	 
	 //Watch a movie
	 @PostMapping("/{userId}/watch/{movieId}")
	 public ResponseEntity<String> watchMovie(@PathVariable Integer userId, @PathVariable Integer movieId) {
	     UserTable user = usertableservice.getUserById(userId);
	     Movies movie = movieservice.getMovieById(movieId);

	     if (user != null && movie != null) {
	         int availableScreens = user.getAvailable_screen();
	         if (availableScreens > 0) {
	             user.setAvailable_screen(availableScreens - 1);

	             List<Integer> watchingMovies = user.getWatchingMovies();
	             if (watchingMovies == null) {
	                 watchingMovies = new ArrayList<>();
	                 user.setWatchingMovies(watchingMovies);
	             }

	             watchingMovies.add(movieId);
	             usertableservice.saveUser(user);

	             final List<Integer> finalWatchingMovies = watchingMovies;
	             
	             // Get the duration of the movie
	             Duration movieDuration;
	             try {
	                 movieDuration = parseDurationString(movie.getDuration());
	             } catch (IllegalArgumentException e) {
	                 watchingMovies.remove(movieId);
	                 user.setAvailable_screen(availableScreens);
	                 usertableservice.saveUser(user);
	                 return ResponseEntity.badRequest().body("Invalid duration format");
	             }
	             
	             // Submit a task to the executor service to simulate movie watching
	             executorService.submit(() -> {
	                 try {
	                     Thread.sleep(movieDuration.toMillis());
	                 } catch (InterruptedException e) {
	                     e.printStackTrace();
	                 } finally {
	                     // Free up the screen after the movie ends
	                     finalWatchingMovies.remove(movieId);
	                     user.setAvailable_screen(availableScreens);
	                     usertableservice.saveUser(user);
	                     System.out.println("Movie ended: " + movie.getTitle()); // Print movie ended message
	                 }
	             });

	             return ResponseEntity.ok().body("The movie started");
	         } else {
	             return ResponseEntity.badRequest().body("User has no available screens");
	         }
	     } else {
	         return ResponseEntity.badRequest().body("No user or movie found");
	     }
	 }

	 //stop a movie
	 @PostMapping("/{userId}/stop/{movieId}")
	 public ResponseEntity<String> stopMovie(@PathVariable Integer userId, @PathVariable Integer movieId) {
	     UserTable user = usertableservice.getUserById(userId);
	     Movies movie = movieservice.getMovieById(movieId);

	     if (user != null && movie != null) {
	         // Check if the user is currently watching the movie
	         List<Integer> watchingMovies = user.getWatchingMovies();
	         if (watchingMovies != null && watchingMovies.contains(movieId)) {
	             // Free up the screen for the user
	             int availableScreens = user.getAvailable_screen();
	             user.setAvailable_screen(availableScreens + 1);
	             watchingMovies.remove(movieId);
	             usertableservice.saveUser(user);

	             return ResponseEntity.ok().body("The movie stopped");
	         } else {
	             return ResponseEntity.badRequest().body("No movie is being played");
	         }
	     } else {
	         return ResponseEntity.badRequest().body("No User or Movie found");
	     }
	 }

	 	// Helper method to parse duration string to Duration object
	    private Duration parseDurationString(String durationString) {
	        try {
	            String[] parts = durationString.split("\\s+");
	            int hours = 0;
	            int minutes = 0;
	            int seconds = 0;

	            for (int i = 0; i < parts.length; i += 2) {
	                int value = Integer.parseInt(parts[i]);
	                String unit = parts[i + 1].toLowerCase();

	                if (unit.contains("hour")) {
	                    hours = value;
	                } else if (unit.contains("minute")) {
	                    minutes = value;
	                } else if (unit.contains("second")) {
	                    seconds = value;
	                }
	            }

	            return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
	        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
	            throw new IllegalArgumentException("Invalid format", e);
	        }
	    }

	    // Shutdown the executor service when the application is shut down
	    @PreDestroy
	    public void shutdownExecutorService() {
	        executorService.shutdown();
	    }
	    
	    @PostMapping("/addMovie")
	    public ResponseEntity<String> addMovie(@RequestBody Movies newMovie) {
	        movieservice.saveMovie(newMovie);
	        return ResponseEntity.ok().body("Movie added successfully");
	    }
	    
	    @PostMapping("/{userId}/pause/{movieId}")
	    public ResponseEntity<String> pauseMovie(@PathVariable Integer userId, @PathVariable Integer movieId) {
	        UserTable user = usertableservice.getUserById(userId);
	        Movies movie = movieservice.getMovieById(movieId);

	        if (user != null && movie != null) {
	            // Check if the user is currently watching the movie
	            List<Integer> watchingMovies = user.getWatchingMovies();
	            if (watchingMovies != null && watchingMovies.contains(movieId)) {
	                // Movie is being played by the user, do the pause logic here

	                return ResponseEntity.ok().body("The movie was paused");
	            } else {
	                return ResponseEntity.badRequest().body("No movie is being played");
	            }
	        } else {
	            return ResponseEntity.badRequest().body("No User or movie found");
	        }
	    }
	    

}
