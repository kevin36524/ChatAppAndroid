package com.google.firebase.codelab.friendlychat.models;

/**
 * Created by aditi on 11/10/2016.
 */

public class FriendlyChats {

    public Contact getChatToContact() {
        return chatToContact;
    }

    private Contact chatToContact; // change to arraylist for groupchat
    private FriendlyMessage chatToMessages;

    public FriendlyChats(){

    }

    public FriendlyChats(Contact contact){
        this.chatToContact = contact;
    }

    public FriendlyMessage getChatToMessages() {
        return chatToMessages;
    }

    public void setChatToMessages(FriendlyMessage chatToMessages) {
        this.chatToMessages = chatToMessages;
    }

}
