package com.codflix.backend.features.media;

import com.codflix.backend.core.Template;
import com.codflix.backend.models.Episode_Media;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class SerieController {

    private final SerieDao serieDao = new SerieDao();

    /**
     * Target specificly the series, get all the episodes list and setup the rooting for it.
     * @param request The series looked for the user.
     * @param response
     * @return The full list of episode from the serie.
     */
    public String list(Request request, Response response) {
        List<Episode_Media> episodes;

        int id_media_serie = Integer.parseInt(request.params(":id"));
        episodes = serieDao.getEpisodeByMediaID(id_media_serie);
        Map<String, Object> model = new HashMap<>();
        model.put("episodes", episodes);
        return Template.render("episode_list.html", model);
    }

    /**
     * Target a specified episode to watch, get the detail, like a movie or serie.
     * @param request The specific episode details.
     * @param res
     * @return
     */
    public String detail(Request request, Response res) {
        int id = Integer.parseInt(request.params(":id"));
        System.out.println(id);
        Episode_Media episode = serieDao.getEpisodeById(id);

        Map<String, Object> model = new HashMap<>();
        model.put("episode", episode);
        return Template.render("episode_detail.html", model);
    }
}