package com.kamon.filmweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieFull {

    @JsonProperty
    private String name;
    @JsonProperty
    private String year;
    @JsonProperty
    private String director;
    @JsonProperty
    private String genre;
    @JsonProperty
    private String production;
    @JsonProperty
    private String description;
    @JsonProperty
    private String posterURL;
    @JsonProperty
    private String movieURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getMovieURL() {
        return movieURL;
    }

    public void setMovieURL(String movieURL) {
        this.movieURL = movieURL;
    }

}
