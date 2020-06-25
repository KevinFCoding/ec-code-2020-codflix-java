package com.codflix.backend.features.other;

import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Template;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public class HomeController {

    /**
     * Access home logging page, verify if user already connected and rooting him to home / medias.
     * @param request
     * @param response
     * @return
     */
    public String home(Request request, Response response) {

        // If there is an active session, redirect to the logged root route
        Session session = request.session(false);
        if (session != null) {
            response.redirect(Conf.ROUTE_LOGGED_ROOT);
            return "";
        }

        Map<String, Object> model = new HashMap<>();
        return Template.render("home.html", model);
    }

}
