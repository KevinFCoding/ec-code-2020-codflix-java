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

import java.util.HashMap;
import java.util.Map;

public class ProfilController {

    private final UserDao userDao = new UserDao();

    public String detail(Request request, Response res) {

        int id = Integer.parseInt(request.params("id"));
        User user = userDao.getUserById(id);

        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        return Template.render("profil.html", model);
    }
}
