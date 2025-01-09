package karim.debza.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import karim.debza.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    
}
