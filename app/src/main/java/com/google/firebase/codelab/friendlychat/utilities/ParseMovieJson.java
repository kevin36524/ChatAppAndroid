package com.google.firebase.codelab.friendlychat.utilities;

/**
 * Created by Disha on 10/20/2016.
 */
public class ParseMovieJson {
    private static final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private static final String TRAILER_BASE_URL="https://www.youtube.com/watch?v=";
    //URL to get movie JSON
    private static String movieApi_url = "https://api.themoviedb.org/3/movie/209112/videos?api_key="
            +API_KEY;

    //JSON Node name
    private static final String MOVIE_ID = "id";
    private static final String MOVIE_NAME = "name";
    private static final String MOVIE_URL = "KEY";
    //Trailer Url
    private static String MOVIE_TRAILER_URL = TRAILER_BASE_URL+MOVIE_URL;
    
}
