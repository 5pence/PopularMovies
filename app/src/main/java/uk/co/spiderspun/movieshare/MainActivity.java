package uk.co.spiderspun.movieshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;
import java.util.Objects;

import uk.co.spiderspun.movieshare.adapter.MovieAdapter;
import uk.co.spiderspun.movieshare.utils.Movie;
import uk.co.spiderspun.movieshare.utils.MovieLoad;


public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler, LoaderManager.LoaderCallbacks<List<Movie>> {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private MovieAdapter mMovieAdapter;
    private View mLoadingSpinner;
    private Parcelable mRecyclerLocation;

    private static final int MOVIE_LOADER = 14;
    private static final int TOP_RATED_LOADER = 22;
    private static final String FILTER_STATE = "filter";
    private static final String RECYCLER_POSITION = "RecyclerViewLocation";
    private String mFilterState = "popular";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        // wire up recycle view to resource
        mRecyclerView = findViewById(R.id.list_of_movies_rv);
        // create grid layout manager
        mGridLayoutManager = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        // wire up the grid layout manager to recycler view
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        // wire the movie adapter to the recycler
        mMovieAdapter = new MovieAdapter( this);
        mRecyclerView.setAdapter(mMovieAdapter);
        mLoadingSpinner = findViewById(R.id.loading_spinner_pb);
        // start the load
        LoaderManager.getInstance(this).initLoader(MOVIE_LOADER, null, this);
    }


    @NonNull
    @Override
    public Loader<List<Movie>> onCreateLoader(int id, @Nullable Bundle args) {
        mLoadingSpinner.setVisibility(View.VISIBLE);
        if (id == MOVIE_LOADER) {
            return new MovieLoad(this, "popular");
        } else {
            return new MovieLoad(this, "top_rated");
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Movie>> loader, List<Movie> data) {
        renderMovies(data);
        mLoadingSpinner.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_action, m);
        return super.onCreateOptionsMenu(m);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.set_popular:
                getMovieInfo("popular");
                mFilterState = "popular";
                setActionBarTitle(mFilterState);
                return true;
            case R.id.set_trending:
                getMovieInfo("top_rated");
                mFilterState = "top_rated";
                setActionBarTitle(mFilterState);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(String title) {
        if (title.equals("popular")) { setTitle("Most Popular"); }
        else setTitle("Highest Rated");
    }

    private void getMovieInfo(String filter) {
        if (filter.equals("top_rated")) {
            LoaderManager.getInstance(this).initLoader(TOP_RATED_LOADER, null, this);
        } else {
            LoaderManager.getInstance(this).initLoader(MOVIE_LOADER, null, this);
        }
    }

    private void renderMovies(List<Movie> movieList) {
        mMovieAdapter.buildMovies(movieList);
        mMovieAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
        if (mRecyclerLocation != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Objects.requireNonNull(mRecyclerView.getLayoutManager()).onRestoreInstanceState(mRecyclerLocation);
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Movie>> loader) {

    }

    @Override
    public void onClick(Movie movie) {
        Intent movieDetailIntent = new Intent(MainActivity.this, DetailActivity.class);
        movieDetailIntent.putExtra("movie", movie);
        startActivity(movieDetailIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(FILTER_STATE, mFilterState);
        outState.putParcelable(RECYCLER_POSITION,
                Objects.requireNonNull(mRecyclerView.getLayoutManager()).onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mFilterState = savedInstanceState.getString(FILTER_STATE);
        mRecyclerLocation = savedInstanceState.getParcelable(RECYCLER_POSITION);

        if (mFilterState.equals("top_rated")) {
            getMovieInfo("top_rated");
        } else {
            getMovieInfo("popular");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        if (mRecyclerLocation != null) {
            Objects.requireNonNull(mRecyclerView.getLayoutManager()).onRestoreInstanceState(mRecyclerLocation);
        }
    }

}
