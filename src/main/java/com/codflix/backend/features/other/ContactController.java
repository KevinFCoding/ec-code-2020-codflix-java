package com.codflix.backend.features.other;

import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Template;
import spark.Request;
import spark.Response;
import spark.Session;

import java.net.Authenticator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ContactController {

    public String accessContact(Request request, Response response) {

        // If there is an active session, redirect to the logged root route

        Session session = request.session(false);

        Map<String, Object> model = new HashMap<>();
        return Template.render("contact.html", model);
    }

    public static void contactUs(){

    }



}
