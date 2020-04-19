package uk.co.spiderspun.movieshare.utils;

import java.io.Serializable;

public class Movie implements Serializable {

    private final String title;
    private final String id;
    private final String posterPath;
    private final String backdropPath;
    private final String voteAverage;
    private final String releaseDate;
    private final String overview;

    Movie(String id, String title, String posterPath, String backdropPath,
          String voteAverage, String releaseDate, String overview) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate.substring(0,4);
    }

    public String getOverview() {
        return overview;
    }
}
