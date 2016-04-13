package com.kamon.filmweb.handlers;

import com.google.common.collect.Lists;
import com.kamon.filmweb.model.MovieLite;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class WatchlistHandler {

    private static final String WATCHLIST_TEMPLATE = "http://www.filmweb.pl/user/%s/films/wanna-see";
    private SiteParser parser = new SiteParser();

    public List<MovieLite> createSharedMovieList(List<String> userlist, List<String> genres) {
        boolean first = true;
        List<MovieLite> list = Lists.newArrayList();
        for (String user : userlist) {
            if (first) {
                list.addAll(getMovieListFromWeb(user));
                first = false;
            } else {
                list.retainAll(getMovieListFromWeb(user));
            }
        }

        if (!genres.isEmpty()) {
            filterMovieList(list, genres);
        }
        return list;
    }

    private List<MovieLite> getMovieListFromWeb(String username) {
        return parser.parseWatchlistSourceForMovieList(tryToGetWatchlistForUser(username));
    }

    private void filterMovieList(List<MovieLite> movieList, List<String> genres) {
        for (Iterator<MovieLite> iterator = movieList.iterator(); iterator.hasNext(); ) {
            MovieLite movieLite = iterator.next();
            if (!movieLite.getGenres().containsAll(genres)) {
                iterator.remove();
            }
        }
    }

    private String tryToGetWatchlistForUser(String username) {
        String watchlistSource = StringUtils.EMPTY;
        try {
            watchlistSource = Jsoup.connect(String.format(WATCHLIST_TEMPLATE, username)).get().html();
        } catch (IOException e) {
            System.out.println(String.format("Failed to fetch watchlist source for user: %s, with error: %s", username, e.getMessage()));
            e.printStackTrace();
        }
        return watchlistSource;
    }
}
