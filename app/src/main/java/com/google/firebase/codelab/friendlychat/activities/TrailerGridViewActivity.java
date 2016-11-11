package com.google.firebase.codelab.friendlychat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.codelab.friendlychat.adapters.TrailerGridAdapter;

public class TrailerGridViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_grid_view);
        GridView gridview = (GridView) findViewById(R.id.gvTrailerList);
        final TrailerGridAdapter gridadapter = new TrailerGridAdapter(this);
        gridview.setAdapter(gridadapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v,
                                    int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("response","response");
        setResult(RESULT_OK, intent);
        finish();
    }
});
    }
}