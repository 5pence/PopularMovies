package uk.co.spiderspun.movieshare.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import uk.co.spiderspun.movieshare.BuildConfig;

class NetworkUtils {
    private static final String THE_MOVIE_DB_URL = "https://api.themoviedb.org";
    private static final String API_VERSION = "3";
    private static final String MEDIA_TYPE = "movie";
    private static final String API_KEY_PARAM = "api_key";
    private static final String API_KEY = BuildConfig.ApiKey;

    static URL buildUrl(String filter) {
        Uri builtUri = Uri.parse(THE_MOVIE_DB_URL).buildUpon()
                .appendPath(API_VERSION)
                .appendPath(MEDIA_TYPE)
                .appendPath(filter)
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return  null;
    }
}
