package com.cinema.controller;

import com.cinema.domain.Movie;
import com.cinema.domain.Showtime;
import com.cinema.repo.MovieRepository;
import com.cinema.repo.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/public") @RequiredArgsConstructor
public class PublicController {
  private final MovieRepository movieRepository;
  private final ShowtimeRepository showtimeRepository;
  @GetMapping("/movies") public List<Movie> movies(@RequestParam(defaultValue = "true") boolean nowShowing){ return movieRepository.findByNowShowing(nowShowing); }
  @GetMapping("/movies/{id}/showtimes") public List<Showtime> showtimes(@PathVariable String id){ return showtimeRepository.findByMovieId(id); }
}
