package uk.co.spiderspun.movieshare.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import static uk.co.spiderspun.movieshare.utils.NetworkUtils.*;

public class MovieLoad extends AsyncTaskLoader<List<Movie>> {
    private final String mFilter;
    private List<Movie> mJson;

    public MovieLoad(@NonNull Context context, String filter) {
        super(context);
        mFilter = filter;
    }

    @Override
    protected void onStartLoading() {
        if (mJson != null) { deliverResult(mJson); }
        else { forceLoad(); }
    }

    @Override
    public List<Movie> loadInBackground() {
        if (mFilter == null) {
            return null;
        }
        URL moviesRequestUrl = NetworkUtils.buildUrl(mFilter);
        try {
            String jsonMovieResponse = getResponseFromHttpUrl(moviesRequestUrl);
            return MoviesDbJsonUtils.getMoviesStringsFromJson(jsonMovieResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void deliverResult(List<Movie> githubJson) {
        mJson = githubJson;
        super.deliverResult(githubJson);
    }
}
