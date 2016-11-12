package com.google.firebase.codelab.friendlychat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.codelab.friendlychat.adapters.LaunchChatsAdapter;
import com.google.firebase.codelab.friendlychat.models.FriendlyChats;
import com.google.firebase.codelab.friendlychat.models.FriendlyMessage;
import com.google.firebase.codelab.friendlychat.models.Group;
import com.google.firebase.codelab.friendlychat.models.User;
import com.google.firebase.codelab.friendlychat.utilities.ChatApplication;
import com.google.firebase.codelab.friendlychat.utilities.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aditi on 11/10/2016.
 */

public class LaunchChatsActivity extends AppCompatActivity {

    static final String TAG = LaunchChatsActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_chats);

        List<FriendlyChats> myChats = new ArrayList<FriendlyChats>();

        FriendlyChats chat1 = new FriendlyChats(new User("1", "Aditi Lonhari", "aditi@gmail.com", null));
        FriendlyMessage friendlyMessage1 = new FriendlyMessage();
        friendlyMessage1.setText("Hello there..");
        chat1.setChatToMessages(friendlyMessage1);
        myChats.add(chat1);

        FriendlyChats chat2 = new FriendlyChats(new User("2", "Disha Satija", "disha@gmail.com", null));
        FriendlyMessage friendlyMessage2 = new FriendlyMessage();
        friendlyMessage2.setText("Yes, I saw that last night!");
        chat2.setChatToMessages(friendlyMessage2);
        myChats.add(chat2);

        FriendlyChats chat3 = new FriendlyChats(new User("3", "Kevin Patel", "kevin@gmail.com", null));
        FriendlyMessage friendlyMessage3 = new FriendlyMessage();
        friendlyMessage3.setText("OMG! Seriously???");
        chat3.setChatToMessages(friendlyMessage3);
        myChats.add(chat3);

        FriendlyChats chat4 = new FriendlyChats(new User("4", "Harshit", "harshit@gmail.com", null));
        FriendlyMessage friendlyMessage4 = new FriendlyMessage();
        friendlyMessage4.setText("Let's meet at Codepath class at FB Bldg 20 Lobby2, 1 Facebook way.");
        chat4.setChatToMessages(friendlyMessage4);
        myChats.add(chat4);

        LaunchChatsAdapter adapter = new LaunchChatsAdapter(LaunchChatsActivity.this, myChats);
        RecyclerView rvChatList = (RecyclerView) findViewById(R.id.rvChats);
        rvChatList.setAdapter(adapter);
        rvChatList.setLayoutManager(new LinearLayoutManager(this));

        setFAB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ChatApplication.getFirebaseClient().getGroupsForCurrentUserIfSetupDone(new FirebaseUtils.FetchedMultiChildListener() {
            @Override
            public void fetchedMultiListener(ArrayList<DataSnapshot> groupsSnapShot) {
                ArrayList<Group> groups = new ArrayList<>();
                for(int i = 0; i < groupsSnapShot.size(); i++) {
                    groups.add(groupsSnapShot.get(i).getValue(Group.class));
                }
                Log.d(TAG, "Got groups");
            }
        });
    }

    private void setFAB(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Glide.with(this).load(R.drawable.ic_action_addchat)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LaunchChatsActivity.this, ContactsListActivity.class);
                startActivity(i);
            }
        });
    }
}
