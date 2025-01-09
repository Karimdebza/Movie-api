package karim.debza.model;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Movie")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "genre", nullable = false)
    private String  genre;

        
    @Column(name = "releaseDate", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "status", nullable = false)
    private String status;

    
    public Long getId(){
        return this.idMovie;
    }

    public void setId(Long id){
         this.idMovie = id;
    }

    public String getTitle(){
       return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDirector(){
        return this.director;
    }

    public void setDirector(String dir){
        this.director = dir;
    }


    public String getGenre(){
        return this.genre;
    }

    public void setGenre(String gnr){
        this.genre = gnr;
    }

    public LocalDate  getReleaseDate(){
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate date){
        this.releaseDate = date;
    }


    public String  getStatus(){
        return this.status;
    }

    public void setStatus(String status ){
        this.status = status;
    }
    



}
