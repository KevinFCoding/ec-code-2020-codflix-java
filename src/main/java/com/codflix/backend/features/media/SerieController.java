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

        public String list(Request request, Response response) {
            List<Episode_Media> episodes;

            int id_media_serie = Integer.parseInt(request.params(":id"));
            episodes = serieDao.getEpisodeByMediaID(id_media_serie);
            Map<String, Object> model = new HashMap<>();
            model.put("episodes", episodes);
            return Template.render("episode_list.html", model);
        }

        public String detail(Request request, Response res) {
            int id = Integer.parseInt(request.params(":id"));
            Episode_Media episodes = serieDao.getEpisodeById(id);

            Map<String, Object> model = new HashMap<>();
            model.put("media", episodes);
            return Template.render("media_detail.html", model);
        }
    }