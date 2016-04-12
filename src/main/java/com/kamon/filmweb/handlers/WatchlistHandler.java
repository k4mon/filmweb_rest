package com.kamon.filmweb.handlers;

import com.google.common.collect.Lists;
import com.kamon.filmweb.model.MovieLite;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WatchlistHandler {

    private final Random GENERATOR = new Random();
    private SiteDownloader connector = new SiteDownloader();
    private SiteParser parser = new SiteParser();

    public List<MovieLite> createSharedMovieList(List<String> userlist, List<String> genres) {
        boolean first = true;
        List<MovieLite> list = Lists.newArrayList();
        for (String user : userlist) {
            if (first) {
                list.addAll(createMovieMap(user));
                first = false;
            } else {
                list.retainAll(createMovieMap(user));
            }
        }

        if (!genres.isEmpty()) {
            filterMovieList(list, genres);
        }
        return list;
    }

    public List<MovieLite> createMovieMap(String username) {
        return getMovieMapFromWeb(username);
    }

    private List<MovieLite> getMovieMapFromWeb(String username) {
        return parser.parseWatchlistSourceForMovieList(connector.getWatchlistForUser(username));
    }

    private void filterMovieList(List<MovieLite> movieList, List<String> genres) {
        for (Iterator<MovieLite> iterator = movieList.iterator(); iterator.hasNext();) {
            MovieLite movieLite = iterator.next();
            if (!movieLite.getGenres().containsAll(genres)) {
                iterator.remove();
            }
        }
    }
}
