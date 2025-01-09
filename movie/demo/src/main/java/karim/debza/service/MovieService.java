package karim.debza.service;
import karim.debza.repositories.MovieRepository;
import karim.debza.model.Movie;
import org.springframework.stereotype.Service;
import io.micrometer.common.util.StringUtils;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MovieService  {
    @Autowired

    private MovieRepository movieRepository;

   

    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){

        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(long movie_id){
        return movieRepository.findById(movie_id);
    }

    public Movie updateMovie(long movie_id, Movie movie){
        
       Movie movieExist =  movieRepository.findById(movie_id).orElseThrow(() -> new RuntimeException("movie not found"));
       Optional.ofNullable(movie.getTitle()).filter(StringUtils::isBlank).ifPresent(movieExist::setTitle);
       Optional.ofNullable(movie.getDirector()).filter(StringUtils::isBlank).ifPresent(movieExist::setDirector);
       Optional.ofNullable(movie.getStatus()).filter(StringUtils::isNotBlank).ifPresent(movieExist::setStatus);


       return movieRepository.save(movie);

    }

    public void deleteMovie(long movie_id){
          movieRepository.deleteById(movie_id);
    }


    
}
