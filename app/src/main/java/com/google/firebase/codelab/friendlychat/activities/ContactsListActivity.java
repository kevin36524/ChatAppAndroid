package com.google.firebase.codelab.friendlychat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.codelab.friendlychat.adapters.ContactListAdapter;
import com.google.firebase.codelab.friendlychat.models.Contact;

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
        List<Contact> myContacts = new ArrayList<Contact>();

        myContacts.add(new Contact(1, "Aditi Lonhari", "aditi@gmail.com"));
        myContacts.add(new Contact(2, "Disha Satija", "disha@gmail.com"));
        myContacts.add(new Contact(3, "Kevin Patel", "kevin@gmail.com"));
        myContacts.add(new Contact(4, "Harshit", "harshit@gmail.com"));



        Log.d("DEBUG contacts", myContacts.toString());
        ContactListAdapter adapter = new ContactListAdapter(ContactsListActivity.this, myContacts);
        RecyclerView rvContactList = (RecyclerView) findViewById(R.id.rvContacts);
        rvContactList.setAdapter(adapter);
        rvContactList.setLayoutManager(new LinearLayoutManager(this));
    }

}
