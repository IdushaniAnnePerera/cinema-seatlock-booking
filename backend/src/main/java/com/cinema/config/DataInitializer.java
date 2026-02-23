package com.cinema.config;

import com.cinema.domain.*;
import com.cinema.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration @RequiredArgsConstructor
public class DataInitializer {
  private final UserRepository users; private final MovieRepository movies; private final CinemaRepository cinemas; private final ScreenRepository screens; private final SeatLayoutRepository layouts; private final ShowtimeRepository showtimes; private final PasswordEncoder encoder;

  @Bean CommandLineRunner seed() { return args -> {
    if (users.count()==0) {
      users.save(User.builder().email("admin@cinema.io").fullName("Admin").role(Enums.Role.ADMIN).passwordHash(encoder.encode("Admin@123")).build());
      users.save(User.builder().email("staff@cinema.io").fullName("Staff").role(Enums.Role.STAFF).passwordHash(encoder.encode("Staff@123")).build());
      users.save(User.builder().email("customer@cinema.io").fullName("Customer").role(Enums.Role.CUSTOMER).passwordHash(encoder.encode("Customer@123")).build());
    }
    if (layouts.count()==0) {
      var layout = layouts.save(SeatLayout.builder().rows(8).cols(10).blockedSeats(Set.of("A1")).seatTypes(Map.of("A2", Enums.SeatType.PREMIUM)).build());
      var cinema = cinemas.save(Cinema.builder().name("Grand Cinema").location("Downtown").build());
      var screen = screens.save(Screen.builder().cinemaId(cinema.getId()).name("Screen 1").seatLayoutId(layout.getId()).build());
      var mv = movies.save(Movie.builder().title("The Quantum Heist").language("English").genres(List.of("Sci-Fi", "Thriller")).nowShowing(true).releaseDate(Instant.now()).build());
      showtimes.save(Showtime.builder().movieId(mv.getId()).screenId(screen.getId()).startTime(Instant.now().plusSeconds(7200)).pricingRules(Map.of(Enums.SeatType.REGULAR, 12.5, Enums.SeatType.PREMIUM, 18.0)).build());
    }
  }; }
}
