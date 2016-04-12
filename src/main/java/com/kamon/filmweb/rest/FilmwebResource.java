package com.kamon.filmweb.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.kamon.filmweb.handlers.WatchlistHandler;
import com.kamon.filmweb.model.MovieLite;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("filmweb")
public class FilmwebResource {

    private WatchlistHandler watchlistHandler = new WatchlistHandler();
    private ObjectMapper mapper = new ObjectMapper();

    @POST
    @Produces("application/json")
    @Path("movie")
    public String getMovie() {
        return "Hello World!";
    }

    @GET
    @Produces("application/json")
    @Path("watchlist")
    public String getWatchlist() {
        System.out.println("HELLO!");
        List<String> userlist = Lists.newArrayList("kamon4", "mrukot");
        List<String> genres = Lists.newArrayList();
        List<MovieLite> movieLiteList = watchlistHandler.createSharedMovieList(userlist, genres);
        System.out.println(movieLiteList);
        String result = StringUtils.EMPTY;
        try {
            result = mapper.writeValueAsString(movieLiteList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    @GET
    @Produces("application/json")
    @Path("test")
    public String getDupa() {
        return "Hello World";
    }


}
