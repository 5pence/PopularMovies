package uk.co.spiderspun.movieshare;

import java.io.Serializable;

public class Movie implements Serializable {

    private String title;
    private String id;
    private String posterPath;
    private String backdropPath;
    private String voteAverage;
    private String releaseDate;
    private String overview;

    public Movie(String id, String title, String posterPath, String backdropPath,
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

    String getTitle() {
        return title;
    }

    String getPosterPath() {
        return posterPath;
    }

    String getBackdropPath() {
        return backdropPath;
    }

    String getVoteAverage() {
        return voteAverage;
    }

    String getReleaseDate() {
        return releaseDate;
    }

    String getOverview() {
        return overview;
    }
}
