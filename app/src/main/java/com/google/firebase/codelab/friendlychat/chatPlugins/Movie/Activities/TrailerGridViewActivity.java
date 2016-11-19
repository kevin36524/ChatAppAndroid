package com.google.firebase.codelab.friendlychat.chatPlugins.Movie.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.codelab.friendlychat.chatPlugins.Movie.adapters.TrailerGridAdapter;
import com.google.firebase.codelab.friendlychat.chatPlugins.Movie.model.Movie;
import com.google.firebase.codelab.friendlychat.chatPlugins.Movie.utils.MovieNetworkClient;
import com.google.firebase.codelab.friendlychat.utilities.ChatApplication;

import java.util.ArrayList;
import java.util.Arrays;

public class TrailerGridViewActivity extends AppCompatActivity {
    private GridView mGridView;
    private TrailerGridAdapter mTrailerGridAdapter;
    private ArrayList<Movie> mGridData;

    //private ProgressBar mTrailerProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_grid_view);
        mGridView = (GridView) findViewById(R.id.gvTrailor);

        if (ChatApplication.isNetworkReachable()) {
            Toast.makeText(this, "You are connected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You are not connected", Toast.LENGTH_SHORT).show();
        }
        mGridData = new ArrayList<>();
        mTrailerGridAdapter = new TrailerGridAdapter(this, R.layout.trailer_poster_item, mGridData);
        mGridView.setAdapter(mTrailerGridAdapter);

        getMovieDetails();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Movie movie = (Movie) parent.getItemAtPosition(position);
                movie.getJSONString();
            }
        });
        getMovieDetails();
    }

    private void getMovieDetails() {
        MovieNetworkClient.getInstance().getMovies(new MovieNetworkClient.MovieResponseHandler() {
            @Override
            public void fetchedMovies(Movie[] movies) {
                mGridData = (ArrayList<Movie>) Arrays.asList(movies);
                mTrailerGridAdapter.setmGridData(mGridData);
            }
        });
    }
}





