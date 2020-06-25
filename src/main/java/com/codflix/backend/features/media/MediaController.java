package com.codflix.backend.features.media;

import com.codflix.backend.core.Template;
import com.codflix.backend.features.genre.GenreDao;
import com.codflix.backend.features.history.HistoryDao;
import com.codflix.backend.models.Genre;
import com.codflix.backend.models.Media;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Main function at the moment, get Media Data, History & Genre, display them for the user
 *
 */

public class MediaController {
    private final MediaDao mediaDao = new MediaDao();
    private final GenreDao genreDao = new GenreDao();
    private final HistoryDao historyDao = new HistoryDao();

    /**
     * Get all media to display on media lists
     * @param request Send directly when going to Media
     * @param response
     * @return
     */
    public String list(Request request, Response response) {
        List<Media> medias;
        String title = request.queryParams("title");

        if (title != null && !title.isEmpty()) {
            medias = mediaDao.filterMedias(title);
        } else {
            medias = mediaDao.getAllMedias();
        }
        Map<String, Object> model = new HashMap<>();
        model.put("medias", medias);
        return Template.render("media_list.html", model);
    }

    public String detail(Request request, Response res) {

        Session session = request.session(true);
        int userId = session.attribute("user_id");

        int id = Integer.parseInt(request.params(":id"));
        Media media = mediaDao.getMediaById(id);
        Genre genre = genreDao.getGenreById(media.getGenreId());

        mediaDao.getMediaById(id).getTrailerUrl();

        Map<String, Object> model = new HashMap<>();
        model.put("media", media);
        model.put("genre", genre);

        historyDao.addHistoryForUser(userId, media.getId());

        return Template.render("media_detail.html", model);
    }

    /**
     * Use the same filterMedias than list to get all filtered Medias
     * @param request From the search on dashboard, use a LIKE %% to get all Datas
     * @param res
     * @return
     */
    public String search(Request request, Response res) {
        List<Media> medias;
        System.out.println("got here");
        String title = request.queryParams("title");
        medias = mediaDao.filterMedias(title);
        Map<String, Object> model = new HashMap<>();
        model.put("medias", medias);
        return Template.render("media_list.html", model);
    }

    /**
     * Unused, to convert time in hours, minuts, seconds
     * @param millie
     * @return
     */
    public static String convertMillieToHMmSs(long millie) {
        long seconds = (millie / 1000);
        long second = seconds % 60;
        long minute = (seconds / 60) % 60;
        long hour = (seconds / (60 * 60)) % 24;

        String result = "";
        if (hour > 0) {
            return String.format("%02d:%02d:%02d", hour, minute, second);
        } else {
            return String.format("%02d:%02d", minute, second);
        }
    }
}
