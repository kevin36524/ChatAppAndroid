package com.google.firebase.codelab.friendlychat.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.codelab.friendlychat.activities.IndividualChatActivity;
import com.google.firebase.codelab.friendlychat.models.User;
import com.google.firebase.codelab.friendlychat.models.FriendlyChats;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by aditi on 11/9/2016.
 */

public class LaunchChatsAdapter extends RecyclerView.Adapter<LaunchChatsAdapter.ViewHolder> {

    private List<FriendlyChats> myChats;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private User mUser;// change to arraylist for groupchat
        private FriendlyChats mFriendlyChats;

        private TextView tvChatContactName;
        private TextView tvChatTimestamp;
        private TextView tvChatLastComment;
        private CircleImageView civProfileImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvChatContactName = (TextView) itemView.findViewById(R.id.tvNameChat);
            tvChatTimestamp = (TextView) itemView.findViewById(R.id.tvTimestampChat);
            tvChatLastComment = (TextView) itemView.findViewById(R.id.tvLastCommentChat);
            civProfileImage = (CircleImageView) itemView.findViewById(R.id.ivProfileImageChat);
        }
    }

    @Override
    public LaunchChatsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LaunchChatsAdapter.ViewHolder viewholder, int position) {
        Glide.with(mContext).load(R.drawable.ic_account_circle_black_36dp).into(viewholder.civProfileImage);
        viewholder.tvChatContactName.setText(myChats.get(position).getChatToUser().getName());
        viewholder.tvChatTimestamp.setText("1-1-2017");
        viewholder.tvChatLastComment.setText(myChats.get(position).getChatToMessages().getText().toString());

        viewholder.tvChatLastComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Clicked from adap", Snackbar.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), IndividualChatActivity.class);
                getContext().startActivity(i);
            }
        });
    }

    public LaunchChatsAdapter(Context context, List<FriendlyChats> chats){
        mContext = context;
        myChats = chats;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public int getItemCount() {
        return myChats.size();
    }

}
