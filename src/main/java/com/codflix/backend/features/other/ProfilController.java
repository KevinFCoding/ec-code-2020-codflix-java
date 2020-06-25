package com.codflix.backend.features.other;

import com.codflix.backend.core.Template;
import com.codflix.backend.features.user.AuthController;
import com.codflix.backend.features.user.UserDao;
import com.codflix.backend.models.Episode_Media;
import com.codflix.backend.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public class ProfilController {

    private final UserDao userDao = new UserDao();

    public String detail(Request request, Response res) {

        Session session = request.session(false);

        Map<String, Object> model = new HashMap<>();
        return Template.render("profil.html", model);
    }

    public String updateDb(Request request, Response res) {
        return "KO";
    }


    }
