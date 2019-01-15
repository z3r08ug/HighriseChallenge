package chris.example.com.highrisechallenge.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import chris.example.com.highrisechallenge.R;
import chris.example.com.highrisechallenge.pojo.Actor;

public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ViewHolder>
{
    List<Actor> actors = new ArrayList<>();
    Context context;
    
    public ActorListAdapter(Actor[] a)
    {
        
        for(int i = 0; i < a.length; i++)
        {
            actors.add(a[i]);
        }
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actor_list_item, null);
        context = parent.getContext();
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(ActorListAdapter.ViewHolder holder, int position)
    {
        Actor actor = actors.get(position);
        if(actor != null)
        {
            if (!actor.getName().isEmpty())
            {
                holder.tvActorName.setText(actor.getName());
                holder.tvActorAge.setText(String.format("%d years old", actor.getAge()));
                
            }
            if (!actor.getPic().isEmpty())
            {
                Picasso.get()
                        .load(actor.getPic())
                        .placeholder(R.drawable.noimage)
                        .error(R.drawable.noimage)
                        .into(holder.ivActorPic);
            }
            else
            {
                Picasso.get()
                        .load("no pic use place holder")
                        .placeholder(R.drawable.noimage)
                        .error(R.drawable.noimage)
                        .into(holder.ivActorPic);
            }
        }
    }
    
    @Override
    public int getItemCount()
    {
        return actors.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvActorName;
        private final TextView tvActorAge;
        private final ImageView ivActorPic;
        
        public ViewHolder(final View itemView)
        {
            super(itemView);
            tvActorName = itemView.findViewById(R.id.tvActorName);
            tvActorAge = itemView.findViewById(R.id.tvActorAge);
            ivActorPic = itemView.findViewById(R.id.ivActorPic);
        }
    }
}

