package com.kamon.filmweb.rest;

import com.google.common.collect.Lists;
import com.kamon.filmweb.handlers.MovieHandler;
import com.kamon.filmweb.handlers.WatchlistHandler;
import com.kamon.filmweb.model.MovieFull;
import com.kamon.filmweb.model.MovieLite;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("filmweb")
public class FilmwebResource {

    private WatchlistHandler watchlistHandler = new WatchlistHandler();
    private MovieHandler movieHandler = new MovieHandler();

    @GET
    @Produces("application/json;charset=UTF-8")
    @Path("movie")
    public MovieFull getMovie() {
        String moviePostfix = "/film/Mustang-2015-742573";
        MovieFull movieFull = movieHandler.getMovie(moviePostfix);
        System.out.println(movieFull);
        return movieFull;

    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Path("watchlist")
    public List<MovieLite> getWatchlist() {
        List<String> userlist = Lists.newArrayList("kamon4", "mrukot");
        List<String> genres = Lists.newArrayList();
        List<MovieLite> movieLiteList = watchlistHandler.createSharedMovieList(userlist, genres);
        System.out.println(movieLiteList);
        return movieLiteList;
    }

}
