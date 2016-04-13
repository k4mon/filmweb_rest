package com.kamon.filmweb.handlers;

import com.kamon.filmweb.model.MovieFull;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import java.io.IOException;

public class MovieHandler {


    private static final String MOVIE_TEMPLATE = "http://www.filmweb.pl%s";
    private static final String MOVIE_DESCRIPTION_TEMPLATE = "http://www.filmweb.pl%s/descs";

    private SiteParser parser = new SiteParser();

    public MovieFull getMovie(String moviePostfix) {
        String movieSource = tryToGetMovie(moviePostfix);
        String descriptionSource = tryToGetMovieDescription(moviePostfix);
        MovieFull movie = parser.parseMovieSourceForMovie(movieSource, descriptionSource);
        movie.setMovieURL(getMovieLink(moviePostfix));
        return movie;
    }

    private String tryToGetMovie(String titleURLPostfix) {
        String movieSource = StringUtils.EMPTY;
        try {
            movieSource = Jsoup.connect(getMovieLink(titleURLPostfix)).get().html();
        } catch (IOException e) {
            System.out.println(String.format("Failed to fetch movie source for movie postfix: %s, with error: %s", titleURLPostfix, e.getMessage()));
            e.printStackTrace();
        }
        return movieSource;
    }

    private String tryToGetMovieDescription(String titleURLPostfix) {
        String movieDescriptionSource = StringUtils.EMPTY;
        try {
            movieDescriptionSource = Jsoup.connect(String.format(MOVIE_DESCRIPTION_TEMPLATE, titleURLPostfix)).get().html();
        } catch (IOException e) {
            System.out.println(String.format("Failed to fetch movie description source for movie postfix: %s, with error: %s", titleURLPostfix, e.getMessage()));
            e.printStackTrace();
        }
        return movieDescriptionSource;
    }

    private String getMovieLink(String titleURLPostfix) {
        return String.format(MOVIE_TEMPLATE, titleURLPostfix);
    }
}
