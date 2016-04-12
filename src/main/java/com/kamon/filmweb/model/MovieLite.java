package com.kamon.filmweb.model;

import java.util.List;

public class MovieLite {

    private String name;
    private String movieURL;
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

    @Override
    public String toString() {
        return "Name: " + getName();
    }
}
