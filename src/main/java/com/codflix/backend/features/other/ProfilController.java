package com.codflix.backend.features.other;

import com.codflix.backend.core.Template;
import com.codflix.backend.features.user.UserDao;
import com.codflix.backend.models.User;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public class ProfilController {

    /**
     * get Data from the userDao to display and interact in the profil
     */
    private final UserDao userDao = new UserDao();

    public String detail(Request request, Response res) {
        Session session = request.session(true);
        int userId = session.attribute("user_id");

        User user = userDao.getUserById(userId);
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        return Template.render("profil.html", model);
    }

    //TODO update and suppress profil, need to verify if user is secured (verification from password)
    public String updateProfil(Request request, Response res) {
        return "KO";
    }


}
