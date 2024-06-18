package com.example.projectsoftware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder> {

    private List<Destination> destinations;
    private List<Destination> destinationsFull;
    private Context context;

    public DestinationAdapter(List<Destination> destinations, Context context) {
        this.destinations = destinations;
        this.destinationsFull = new ArrayList<>(destinations);
        this.context = context;
    }

    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.destinationlist, parent, false);
        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder holder, int position) {
        Destination destination = destinations.get(position);
        holder.imageView.setImageResource(destination.getImageResId());
        holder.nameTextView.setText(destination.getName());
        holder.locationTextView.setText(destination.getLocation());

        if (destination.isFavorite()) {
            holder.favoriteButton.setImageResource(R.drawable.heartlikedd);
        } else {
            holder.favoriteButton.setImageResource(R.drawable.heartlike);
        }

        holder.favoriteButton.setOnClickListener(v -> {
            toggleFavorite(destination);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    public void setFilteredList(List<Destination> filteredList) {
        this.destinations = filteredList;
        notifyDataSetChanged();
    }

    private void toggleFavorite(Destination destination) {
        destination.setFavorite(!destination.isFavorite());
    }

    static class DestinationViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, locationTextView;
        ImageView favoriteButton;

        public DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.destinationImage);
            nameTextView = itemView.findViewById(R.id.destinationName);
            locationTextView = itemView.findViewById(R.id.location);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
        }
    }
}
