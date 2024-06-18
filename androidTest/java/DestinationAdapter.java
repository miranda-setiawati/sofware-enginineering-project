package com.example.projectsoftware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder> {

    private List<Destination> destinations;
    private Context context;

    public DestinationAdapter(List<Destination> destinations, Context context) {
        this.destinations = destinations;
        this.context = context;
    }

    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destinationlist, parent, false);
        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder holder, int position) {
        Destination destination = destinations.get(position);
        holder.destinationImage.setImageResource(destination.getImageResId());
        holder.destinationName.setText(destination.getName());
        holder.location.setText(destination.getLocation());
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    public void filterList(List<Destination> filteredList) {
        this.destinations = filteredList;
        notifyDataSetChanged();
    }

    public static class DestinationViewHolder extends RecyclerView.ViewHolder {
        ImageView destinationImage;
        TextView destinationName;
        TextView location;

        public DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
            destinationImage = itemView.findViewById(R.id.destinationImage);
            destinationName = itemView.findViewById(R.id.destinationName);
            location = itemView.findViewById(R.id.location);
        }
    }
}