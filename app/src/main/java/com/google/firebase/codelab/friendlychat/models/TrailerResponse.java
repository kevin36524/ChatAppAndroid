package com.google.firebase.codelab.friendlychat.models;

import java.util.Collection;

/**
 * Created by Disha on 10/20/2016.
 */
public class TrailerResponse {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<Trailer> getResults() {
        return results;
    }

    public void setResults(Collection<Trailer> results) {
        this.results = results;
    }

    private Integer id;
    private Collection<Trailer> results;

}
