package chris.example.com.highrisechallenge.pojo;

import java.io.Serializable;
import java.util.HashMap;

public class MovieController implements Serializable
{
    private Movie[] movies;
    private HashMap<String, MovieDetail> details;
    
    public MovieController()
    {
    }
    
    public MovieController(Movie[] movies, HashMap<String, MovieDetail> details)
    {
        this.movies = movies;
        this.details = details;
    }
    
    public Movie[] getMovies()
    {
        return movies;
    }
    
    public void setMovies(Movie[] movies)
    {
        this.movies = movies;
    }
    
    public HashMap<String, MovieDetail> getDetails()
    {
        return details;
    }
    
    public void setDetails(HashMap<String, MovieDetail> details)
    {
        this.details = details;
    }
    
    public MovieDetail getMovieDetail(String name)
    {
        return details.get(name);
    }
    
    
}
