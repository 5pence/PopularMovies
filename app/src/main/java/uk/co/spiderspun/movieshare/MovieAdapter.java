package uk.co.spiderspun.movieshare;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private static final String BASE_IMAGE_PATH = "https://image.tmdb.org/t/p/w780";
    private final MovieAdapterOnClickHandler movieAdapterOnClickHandler;
    private List<Movie> movies;

    MovieAdapter(MovieAdapterOnClickHandler movieAdapterOnClickHandler) {
        this.movieAdapterOnClickHandler = movieAdapterOnClickHandler;
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = View.inflate(viewGroup.getContext(), R.layout.movie, null);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieAdapterViewHolder holder, int position) {
        setMoviePoster(holder, position);
    }

    private void setMoviePoster(MovieAdapterViewHolder movieAdapterViewHolder, int position) {
        String mPosterPath = movies.get(position).getPosterPath();
        String posterPath = BASE_IMAGE_PATH.concat(mPosterPath);
        new Picasso.Builder(movieAdapterViewHolder
                .itemView.getContext())
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        exception.printStackTrace();
                    }
            })
             .build()
             .load(posterPath)
             .into(movieAdapterViewHolder.getBillboardImageView());
    }


    @Override
    public int getItemCount() {
        if (movies == null || movies.isEmpty()) {
            return 0;
        }
        return movies.size();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView mBillboardImageView;

        MovieAdapterViewHolder(View view) {
            super(view);
            mBillboardImageView = view.findViewById(R.id.movie_billboard_iv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            movieAdapterOnClickHandler.onClick(movies.get(adapterPosition));
        }

        ImageView getBillboardImageView() {
            return mBillboardImageView;
        }
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie movie);
    }
}
