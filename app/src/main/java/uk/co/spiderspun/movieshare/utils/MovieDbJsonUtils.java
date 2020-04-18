package uk.co.spiderspun.movieshare.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

final class MoviesDbJsonUtils {

    public static List<Movie> getMoviesStringsFromJson(String s) throws JSONException {
        final String MOVIE_LIST = "results";
        final String ID = "id";
        final String TITLE = "title";
        final String POSTER_PATH = "poster_path";
        final String BACKDROP_PATH = "backdrop_path";
        final String OVERVIEW = "overview";
        final String VOTE_AVERAGE = "vote_average";
        final String RELEASE_DATE = "release_date";

        List<Movie> movies = new ArrayList<>();
        JSONObject moviesJson = new JSONObject(s);
        JSONArray movieJsonArray = moviesJson.getJSONArray(MOVIE_LIST);

        for (int i = 0; i < movieJsonArray.length(); i++) {
            JSONObject movieJson = movieJsonArray.getJSONObject(i);
            String id = movieJson.getString(ID);
            String title = movieJson.getString(TITLE);
            String poster = movieJson.getString(POSTER_PATH);
            String backdrop = movieJson.getString(BACKDROP_PATH);
            String userRating = movieJson.getString(VOTE_AVERAGE);
            String releaseDate = movieJson.getString(RELEASE_DATE);
            String overview = movieJson.getString(OVERVIEW);

            Movie movie = new Movie(id, title, poster, backdrop, userRating, releaseDate, overview);
            movies.add(movie);
        }
        return movies;
    }
}

