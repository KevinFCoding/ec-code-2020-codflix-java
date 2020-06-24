package com.codflix.backend.features.other;

import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Template;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public class ContactController {

    public String contact(Request request, Response response) {


        System.out.println("test");
        // If there is an active session, redirect to the logged root route

        Session session = request.session(false);
        System.out.println(session);

        Map<String, Object> model = new HashMap<>();
        return Template.render("contact.html", model);
    }



}
