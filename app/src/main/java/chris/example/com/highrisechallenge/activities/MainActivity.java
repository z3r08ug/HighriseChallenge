package chris.example.com.highrisechallenge.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;

import chris.example.com.highrisechallenge.R;
import chris.example.com.highrisechallenge.pojo.MovieController;
import chris.example.com.highrisechallenge.pojo.MovieDetail;
import chris.example.com.highrisechallenge.util.MovieListAdapter;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private MovieListAdapter movieListAdapter;
    
    // Used to load the 'native-lib' library on application startup.
    static
    {
        System.loadLibrary("native-lib");
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        recyclerView = findViewById(R.id.rvMovies);
        
        MovieController controller = loadMovieData();
    
        HashMap<String, MovieDetail> detailHashMap = controller.getDetails();
        for (String key : detailHashMap.keySet())
        {
            System.out.println("------------------------------------------------");
            System.out.println("Iterating or looping map using java5 foreach loop");
            System.out.println("key: " + key + " value: " + detailHashMap.get(key));
        }
    
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
    
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
    
        movieListAdapter = new MovieListAdapter(controller);
        recyclerView.setAdapter(movieListAdapter);
    }
    
    
    public native MovieController loadMovieData();
}
