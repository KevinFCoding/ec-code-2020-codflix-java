package com.codflix.backend.features.history;

import com.codflix.backend.core.Template;
import com.codflix.backend.features.user.UserDao;
import com.codflix.backend.models.History;
import com.codflix.backend.models.User;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * History not finished
 * Data are getting in place, receive when someone is consulting a Movie / Serie
 * Add itself to the page historic (only datas, no url).
 *
 */

public class HistoryController {
    private final HistoryDao historyDao = new HistoryDao();
    private final UserDao userDao = new UserDao();


    public String list(Request request, Response res) {
        List<History> histories;

        Session session = request.session(true);
        int userId = session.attribute("user_id");

        System.out.println(userId);
        User user = userDao.getUserById(userId);
        histories = historyDao.getStreamsHistoryForUser(userId);

        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("histories", histories);
        return Template.render("history_list.html", model);
    }

}
