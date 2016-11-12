package com.google.firebase.codelab.friendlychat.models;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by patelkev on 11/12/16.
 */

public class Group {
    String lmSnippet;
    String title;
    Long ts;
    String id;
    HashMap<String, String> usersImgs;

    public Group() {
    }

    public Date getLastUpdatedDate() {
        return new Date(ts);
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.ts = lastUpdatedDate.getTime();
    }

    public String getId() {
        return id;
    }

    public HashMap<String, String> getUsersImgs() {
        return usersImgs;
    }

    public String getLastMessageSnippet() {
        return lmSnippet;
    }

    public void setLastMessageSnippet(String lmSnippet) {
        this.lmSnippet = lmSnippet;
    }

    public String getTitle() {
        return title;
    }
}
