package com.google.firebase.codelab.friendlychat.utilities;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.codelab.friendlychat.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by patelkev on 11/10/16.
 */

public class FirebaseClient {

    private static final String TAG = FirebaseClient.class.getSimpleName();
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final String USERS_NODE = "users";

    private FirebaseClient() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser == null) {
                    // Not signed in, launch the Sign In activity
                    //startActivity(new Intent(IndividualChatActivity.this, SignInActivity.class));
                    //finish();
                    return;
                } else {
                    addFireBaseUserIfNeeded(mFirebaseUser);
                }
            }
        };
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    private void addFireBaseUserIfNeeded(FirebaseUser mFirebaseUser) {
        DatabaseReference usersNodeRef = mFirebaseDatabaseReference.child(USERS_NODE);
        DatabaseReference currentUserRef = usersNodeRef.child(mFirebaseUser.getUid());

        User newUser = new User(mFirebaseUser);
        currentUserRef.setValue(newUser);
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