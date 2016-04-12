package com.kamon.filmweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieLite {

    @JsonProperty
    private String name;

    @JsonProperty
    private String movieURL;

    @JsonProperty
    private List<String> genres;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieURL() {
        return movieURL;
    }

    public void setMovieURL(String movieURL) {
        this.movieURL = movieURL;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (!(object instanceof MovieLite)) {
            result = false;
        } else {
            MovieLite movieLiteObject = (MovieLite) object;
            if (movieLiteObject.getMovieURL().equals(this.getMovieURL())
                    && movieLiteObject.getName().equals(this.getName())) {
                result = true;
            }
        }
        return result;
    }
}
