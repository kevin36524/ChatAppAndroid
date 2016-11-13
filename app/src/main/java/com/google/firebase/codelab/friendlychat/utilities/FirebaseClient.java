package com.google.firebase.codelab.friendlychat.utilities;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.codelab.friendlychat.models.FriendlyMessage;
import com.google.firebase.codelab.friendlychat.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.firebase.codelab.friendlychat.activities.IndividualChatActivity.MESSAGES_FOR_GROUP_NODE;

/**
 * Created by patelkev on 11/10/16.
 */

public class FirebaseClient {

    private interface PostSetupInterface {
        public void  postSetupInterface();
    }

    public ArrayList<PostSetupInterface> postSetupInterfaces;
    private static final String TAG = FirebaseClient.class.getSimpleName();
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final String USERS_NODE = "users";
    public static final String GROUPS_FOR_USER_NODE = "groupsForUser";
    public static final String GROUPS_NODE= "groups";
    public String[] groupIdsForCurrentUser;
    public Boolean setupDone = false;

    private FirebaseClient() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        postSetupInterfaces = new ArrayList<>();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser == null) {
                    // Not signed in, launch the Sign In activity
                    //startActivity(new Intent(IndividualChatActivity.this, SignInActivity.class));
                    //finish();
                    setupDone = false;
                    return;
                } else {
                    addFireBaseUserIfNeeded(mFirebaseUser);
                    fetchGroupIdsForCurrentUser();
                }
            }
        };
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    private void executePostSetupInterfaces() {
        for (int i = 0; i < postSetupInterfaces.size(); i++ ) {
            postSetupInterfaces.get(i).postSetupInterface();
        }
        postSetupInterfaces = new ArrayList<>();
        setupDone = true;
    }

    private void fetchGroupIdsForCurrentUser() {
        DatabaseReference groupReference = mFirebaseDatabaseReference.child(GROUPS_FOR_USER_NODE).child(mFirebaseUser.getUid());
        groupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "got the Groups data");
                HashMap valueMap = (HashMap) dataSnapshot.getValue();
                groupIdsForCurrentUser = (String[]) valueMap.keySet().toArray(new String[valueMap.size()]);
                executePostSetupInterfaces();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage());
            }
        });
    }

    private void addFireBaseUserIfNeeded(FirebaseUser mFirebaseUser) {
        DatabaseReference usersNodeRef = mFirebaseDatabaseReference.child(USERS_NODE);
        DatabaseReference currentUserRef = usersNodeRef.child(mFirebaseUser.getUid());

        User newUser = new User(mFirebaseUser);
        currentUserRef.setValue(newUser);
    }

    private void getGroupsForCurrentUser (FirebaseUtils.FetchedMultiChildListener mGroupsListener) {
        ArrayList<DatabaseReference> references = new ArrayList<>();
        for (int i = 0; i < this.groupIdsForCurrentUser.length; i++) {
            references.add(mFirebaseDatabaseReference.child(GROUPS_NODE).child(groupIdsForCurrentUser[i]));
        }

        FirebaseUtils.MultiChildFetcher fetcher = new FirebaseUtils.MultiChildFetcher(references, mGroupsListener);
        fetcher.fetchDataForAllReferences();
    }

    public void getGroupsForCurrentUserIfSetupDone (final FirebaseUtils.FetchedMultiChildListener mGroupsListener) {
        if (!setupDone) {
            postSetupInterfaces.add(new PostSetupInterface() {
                @Override
                public void postSetupInterface() {
                    getGroupsForCurrentUser(mGroupsListener);
                }
            });
            return;
        }
        getGroupsForCurrentUser(mGroupsListener);
    }

    public void sendMessageForGroup(String groupID, FriendlyMessage messageToSend) {
        mFirebaseDatabaseReference.child(MESSAGES_FOR_GROUP_NODE).child(groupID)
                .push().setValue(messageToSend);
    }

    public FirebaseAuth getmFirebaseAuth() {
        return mFirebaseAuth;
    }

    public FirebaseUser getmFirebaseUser() {
        return mFirebaseUser;
    }

    private static class LazyHolder {
        private static final FirebaseClient INSTANCE = new FirebaseClient();
    }

    public static FirebaseClient getInstance() {
        return LazyHolder.INSTANCE;
    }

}