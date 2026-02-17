package com.example.onlinemovieticket.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinemovieticket.R;
import com.example.onlinemovieticket.models.Movie;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList;
    private OnMovieClickListener listener;

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    public MovieAdapter(List<Movie> movieList, OnMovieClickListener listener) {
        this.movieList = movieList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivMoviePoster;
        private TextView tvMovieTitle, tvMovieGenre, tvMovieRating, tvMoviePrice;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMoviePoster = itemView.findViewById(R.id.ivMoviePoster);
            tvMovieTitle = itemView.findViewById(R.id.tvMovieTitle);
            tvMovieGenre = itemView.findViewById(R.id.tvMovieGenre);
            tvMovieRating = itemView.findViewById(R.id.tvMovieRating);
            tvMoviePrice = itemView.findViewById(R.id.tvMoviePrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onMovieClick(movieList.get(position));
                    }
                }
            });
        }

        public void bind(Movie movie) {
            tvMovieTitle.setText(movie.getTitle());
            tvMovieGenre.setText(movie.getGenre());
            tvMovieRating.setText("★ " + movie.getRating());
            tvMoviePrice.setText("₹" + movie.getPrice());
            
            // Set movie poster image
            String imageName = movie.getImageUrl();
            int resourceId = itemView.getContext().getResources().getIdentifier(
                imageName, "drawable", itemView.getContext().getPackageName());
            
            if (resourceId != 0) {
                ivMoviePoster.setImageResource(resourceId);
            } else {
                ivMoviePoster.setBackgroundColor(android.graphics.Color.GRAY);
            }
        }
    }
}