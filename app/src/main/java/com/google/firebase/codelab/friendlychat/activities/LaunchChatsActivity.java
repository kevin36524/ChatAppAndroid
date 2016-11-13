package com.google.firebase.codelab.friendlychat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.codelab.friendlychat.adapters.LaunchChatsAdapter;
import com.google.firebase.codelab.friendlychat.models.Group;
import com.google.firebase.codelab.friendlychat.utilities.ChatApplication;
import com.google.firebase.codelab.friendlychat.utilities.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import static com.google.firebase.codelab.friendlychat.activities.IndividualChatActivity.INTENT_GROUP_KEY;

/**
 * Created by aditi on 11/10/2016.
 */

public class LaunchChatsActivity extends AppCompatActivity {

    static final String TAG = LaunchChatsActivity.class.getSimpleName();
    ArrayList<Group> myGroups;
    LaunchChatsAdapter mGroupsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final Context context = LaunchChatsActivity.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_chats);

        myGroups = new ArrayList<>();
        mGroupsAdapter = new LaunchChatsAdapter(this, myGroups, new LaunchChatsAdapter.ClickDelegate() {
            @Override
            public void onConversationClicked(Group selectedGroup) {
                Intent i = new Intent(context , IndividualChatActivity.class);
                i.putExtra(INTENT_GROUP_KEY, selectedGroup.getId());
                context.startActivity(i);
            }
        });
        RecyclerView rvChatList = (RecyclerView) findViewById(R.id.rvChats);
        rvChatList.setAdapter(mGroupsAdapter);
        rvChatList.setLayoutManager(new LinearLayoutManager(this));

        setFAB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGroupsAdapter.updateGroups(new ArrayList<Group>());
        mGroupsAdapter.notifyDataSetChanged();

        ChatApplication.getFirebaseClient().getGroupsForCurrentUserIfSetupDone(new FirebaseUtils.FetchedMultiChildListener() {
            @Override
            public void fetchedMultiListener(ArrayList<DataSnapshot> groupsSnapShot) {
                ArrayList<Group> groups = new ArrayList<>();
                for(int i = 0; i < groupsSnapShot.size(); i++) {
                    groups.add(groupsSnapShot.get(i).getValue(Group.class));
                }
                mGroupsAdapter.updateGroups(groups);
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
