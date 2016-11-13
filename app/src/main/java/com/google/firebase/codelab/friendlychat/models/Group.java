package com.google.firebase.codelab.friendlychat.models;

import android.text.format.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    public String getImageUrl(String currentUserID) {
        Iterator it = usersImgs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (pair.getKey() != currentUserID) {
                return (String) pair.getValue();
            }
            it.remove();
        }
        return "";
    }

    public String getRelativeTimeStamp() {
        return DateUtils.getRelativeTimeSpanString(ts,
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
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
