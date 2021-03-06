package uk.co.spiderspun.movieshare;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.spiderspun.movieshare.utils.Movie;
import com.squareup.picasso.Picasso;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    public static final String BACKDROP_BASE_PATH = "https://image.tmdb.org/t/p/w1280";
    public static final String POSTER_BASE_PATH = "https://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        ImageView mBackdropPath = findViewById(R.id.movieBackdrop);
        ImageView mPosterPath = findViewById(R.id.moviePosterThumbnail);
        TextView mName = findViewById(R.id.movieName);
        TextView mRating = findViewById(R.id.rating);
        TextView mReleaseDate = findViewById(R.id.releaseDate);
        TextView mOverview = findViewById(R.id.overview);

        Movie mMovie = (Movie) getIntent().getExtras().getSerializable("movie");

        Picasso.get().load(BACKDROP_BASE_PATH.concat(mMovie.getBackdropPath())).into(mBackdropPath);
        Picasso.get().load(POSTER_BASE_PATH.concat(mMovie.getPosterPath())).into(mPosterPath);

        setTitle("Movie Detail");
        String userRating = mMovie.getVoteAverage();
        String newUserRating = userRating + "/10";

        mName.setText(mMovie.getTitle());
        mReleaseDate.setText(mMovie.getReleaseDate());
        mRating.setText(newUserRating);
        mOverview.setText(mMovie.getOverview());
    }
}
