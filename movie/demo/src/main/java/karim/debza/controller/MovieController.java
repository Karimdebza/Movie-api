package karim.debza.controller;
import karim.debza.service.MovieService;
import karim.debza.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/movie")
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){

        try{
            Movie saveMovie = movieService.saveMovie(movie);
            return new ResponseEntity<>(saveMovie,HttpStatus.CREATED);
        }catch( Exception e ){
            System.err.println("Error creating movie: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovie(){
        try{
            List<Movie> movies = movieService.getAllMovies();
            return ResponseEntity.ok(movies);
        }catch(Exception e){
            System.err.println("Error de recuperer movie: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id_movie}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id_movie) {
         try {
        Optional<Movie> movie = movieService.getMovieById(id_movie);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }catch (Exception e) {
            // Log the exception
            System.err.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/update/{id_movie}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id_movie, @RequestBody Movie movie) {
       try {
         Movie movieUpdate = movieService.updateMovie(id_movie, movie);
         return movieUpdate != null ? ResponseEntity.ok(movieUpdate) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       } catch (Exception e) {
        System.err.println("Error updating movie: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
    
    @DeleteMapping("/{id_movie}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id_movie) {
        try {
            if(movieService.getMovieById(id_movie).isPresent()){
                movieService.deleteMovie(id_movie);
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            System.err.println("Error deleting movie: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

}

}