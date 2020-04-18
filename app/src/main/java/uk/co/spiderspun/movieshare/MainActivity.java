package uk.co.spiderspun.movieshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import uk.co.spiderspun.movieshare.adapter.MovieAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mMovieAdapter = new MovieAdapter((MovieAdapter.MovieAdapterOnClickHandler) this);
        mRecyclerView.setAdapter(mMovieAdapter);


    }
}
