package chris.example.com.highrisechallenge.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import chris.example.com.highrisechallenge.activities.DetailActivity;
import chris.example.com.highrisechallenge.R;
import chris.example.com.highrisechallenge.pojo.Movie;
import chris.example.com.highrisechallenge.pojo.MovieController;
import chris.example.com.highrisechallenge.pojo.MovieDetail;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder>
{
    MovieController controller;
    List<Movie> movies = new ArrayList<>();
    HashMap<String, MovieDetail> detailHashMap;
    Context context;
    
    public MovieListAdapter(MovieController controller)
    {
        this.controller = controller;
        detailHashMap = controller.getDetails();
        
        for(int i = 0; i < controller.getMovies().length; i++)
        {
            movies.add(controller.getMovies()[i]);
        }
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, null);
        context = parent.getContext();
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(MovieListAdapter.ViewHolder holder, int position)
    {
        Movie movie = movies.get(position);
        if(movie != null)
        {
            holder.tvMovieName.setText(movie.getName());
            holder.tvLastUpdated.setText(movie.getLastUpdated()+"");
//            Glide.with(context).load(book.getImageURL()).into(holder.ivBook);
        }
    }
    
    @Override
    public int getItemCount()
    {
        return movies.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvMovieName;
        private final TextView tvLastUpdated;
        public ViewHolder(final View itemView)
        {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.tvMovieName);
            tvLastUpdated = itemView.findViewById(R.id.tvLastUpdated);
            
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("detail", detailHashMap.get(movies.get(getAdapterPosition()).getName()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
