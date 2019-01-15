package chris.example.com.highrisechallenge.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import chris.example.com.highrisechallenge.R;
import chris.example.com.highrisechallenge.pojo.MovieDetail;
import chris.example.com.highrisechallenge.util.ActorListAdapter;

public class DetailActivity extends AppCompatActivity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    
        TextView tvMovieName = findViewById(R.id.tvMovieName);
        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvDescription = findViewById(R.id.tvDescription);
        RecyclerView rvActors = findViewById(R.id.rvActors);
        
        ActorListAdapter actorListAdapter;
        
        MovieDetail detail = (MovieDetail) getIntent().getSerializableExtra("detail");
        
        if (detail != null)
        {
            for (int i = 0; i < detail.getActors().length; i++)
            {
                System.out.println(detail.getActors()[i].getPic().isEmpty());
            }
            tvMovieName.setText(detail.getName());
            tvScore.setText("Score: "+detail.getScore());
            tvDescription.setText(detail.getDescription());
    
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
    
            rvActors.setLayoutManager(layoutManager);
            rvActors.setItemAnimator(itemAnimator);
    
            actorListAdapter = new ActorListAdapter(detail.getActors());
            rvActors.setAdapter(actorListAdapter);
        }
    }
}
