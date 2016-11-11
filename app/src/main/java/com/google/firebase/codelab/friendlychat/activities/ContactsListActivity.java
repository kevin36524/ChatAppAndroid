package com.google.firebase.codelab.friendlychat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.codelab.friendlychat.adapters.ContactListAdapter;
import com.google.firebase.codelab.friendlychat.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aditi on 11/9/2016.
 */

public class ContactsListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        List<User> myUsers = new ArrayList<User>();

        myUsers.add(new User("1", "Aditi Lonhari", "aditi@gmail.com", null));
        myUsers.add(new User("2", "Disha Satija", "disha@gmail.com", null));
        myUsers.add(new User("3", "Kevin Patel", "kevin@gmail.com", null));
        myUsers.add(new User("4", "Harshit", "harshit@gmail.com", null));



        Log.d("DEBUG contacts", myUsers.toString());
        ContactListAdapter adapter = new ContactListAdapter(ContactsListActivity.this, myUsers);
        RecyclerView rvContactList = (RecyclerView) findViewById(R.id.rvContacts);
        rvContactList.setAdapter(adapter);
        rvContactList.setLayoutManager(new LinearLayoutManager(this));
    }

}
