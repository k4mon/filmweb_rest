package com.kamon.filmweb.handlers;

import com.google.common.collect.Lists;
import com.kamon.filmweb.model.MovieFull;
import com.kamon.filmweb.model.MovieLite;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SiteParser {

    private boolean isFirst = false;

    public ArrayList<MovieLite> parseWatchlistSourceForMovieList(String site) {
        ArrayList<MovieLite> list = new ArrayList<MovieLite>();
        Document doc = Jsoup.parse(site);
        getMovieTitlesFromContent(doc, list);
        return list;
    }

    public MovieFull parseMovieSourceForMovie(String movieSource, String descriptionSource) {
        MovieFull movie = new MovieFull();
        movie.setName(getMovieNameFromSource(movieSource));
        movie.setYear(getMovieYearFromSource(movieSource));
        movie.setDirector(getMovieDirectorFromSource(movieSource));
        movie.setGenre(getMovieGenreFromSource(movieSource));
        movie.setProduction(getMovieProductionFromSource(movieSource));
        movie.setDescription(getMovieDescriptionFromSource(descriptionSource));
        movie.setPosterURL(getPosterURLFromSource(movieSource));

        return movie;
    }

    private void getMovieTitlesFromContent(Document doc, ArrayList<MovieLite> list) {

        String currentMovie = StringUtils.EMPTY;
        String currentTitle = StringUtils.EMPTY;
        List<String> currentGenres = Lists.newArrayList();
        Elements content1 = doc.getElementsByAttribute("href");

        for (org.jsoup.nodes.Element element : content1) {
            if (element.hasClass("fNoImg0")) {
                if (isFirst) {
                    list.add(createMovieLite(currentMovie, currentTitle, currentGenres));
                    currentGenres = Lists.newArrayList();
                }
                currentMovie = element.attr("href");
                isFirst = true;
            } else if (currentMovie.equals(element.attr("href"))) {
                currentTitle = element.text();
            } else if (element.attr("href").contains("genreIds")) {
                currentGenres.add(element.text());
            }
        }

        isFirst = false;
    }

    private MovieLite createMovieLite(String currentMovie, String currentTitle, List<String> currentGenres) {
        MovieLite result = new MovieLite();
        result.setName(currentTitle);
        result.setMovieURL(currentMovie);
        result.setGenres(currentGenres);
        return result;
    }

    private String getMovieNameFromSource(String movieSource) {
        Document doc = Jsoup.parse(movieSource);
        Elements content = doc.getElementsByAttributeValue("property", "og:title");
        return content.attr("content");
    }

    private String getMovieYearFromSource(String movieSource) {
        Document doc = Jsoup.parse(movieSource);
        Elements content = doc.getElementsByClass("halfsize");
        String result = content.text();
        return result.substring(1, result.length() - 1);
    }

    private String getMovieDirectorFromSource(String movieSource) {
        Document doc = Jsoup.parse(movieSource);
        Elements content = doc.getElementsByAttributeValue("rel", "v:directedBy");
        return content.text();
    }

    private String getMovieGenreFromSource(String movieSource) {
        String genre = StringUtils.EMPTY;
        try {
            Document doc = Jsoup.parse(movieSource);
            Elements content = doc.getElementsByAttributeValueContaining("href", "/search/film?genreIds=");
            for (Element element : content) {
                genre += (element.text() + ", ");
            }
            genre = genre.substring(0, genre.length() - 2);
        } catch (Exception e) {
            genre = "brak";
        }
        return genre;
    }

    private String getMovieProductionFromSource(String movieSource) {
        String production = "";
        try {
            Document doc = Jsoup.parse(movieSource);
            Elements content = doc.getElementsByAttributeValueContaining("href", "/search/film?countryIds=");
            for (Element element : content) {
                production += (element.text() + ", ");
            }
            production = production.substring(0, production.length() - 2);
        } catch (Exception e) {
            production = "brak";
        }

        return production;
    }

    private String getMovieDescriptionFromSource(String descriptionSource) {
        String description;
        try {
            Document doc = Jsoup.parse(descriptionSource);
            Element content = doc.getElementsByClass("def").get(0).getElementsByAttributeValueMatching("class", "text")
                    .get(0);
            description = content.text();
        } catch (Exception e) {
            description = "brak";
        }
        return description;
    }

    private String getPosterURLFromSource(String movieSource) {
        Document doc = Jsoup.parse(movieSource);
        Elements content = doc.getElementsByAttributeValue("rel", "v:image");
        return content.attr("href");
    }

}
