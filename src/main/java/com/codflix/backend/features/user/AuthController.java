package com.codflix.backend.features.user;
import com.codflix.backend.core.Conf;
import com.codflix.backend.core.Template;
import com.codflix.backend.models.User;
import com.codflix.backend.utils.URLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Session;

import javax.swing.*;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserDao userDao = new UserDao();

    public String login(Request request, Response response) {
        if (request.requestMethod().equals("GET")) {
            Map<String, Object> model = new HashMap<>();
            return Template.render("auth_login.html", model);
        }

        // Get parameters
        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String email = query.get("email");
        String password = query.get("password");

        password = sha256(password);

        // Authenticate user
        User user = userDao.getUserByCredentials(email, password);
        if (user == null) {

            logger.info("User not found. Redirect to login");
            response.removeCookie("session");
            response.redirect("/login");
            return "KO";
        }
        //boolean isBoolean = userDao.isUserVerified(email);

        // Create session
        Session session = request.session(true);
        session.attribute("user_id", user.getId());
        response.cookie("/", "user_id", "" + user.getId(), 3600, true);

        // Redirect to medias page
        response.redirect(Conf.ROUTE_LOGGED_ROOT);
        return "OK";
    }
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public String signUp(Request request, Response response) {

        Map<String, Object> model = new HashMap<>();
        if (request.requestMethod().equals("GET")) {
            return Template.render("auth_signup.html", model);
        }
        else if (request.requestMethod().equals("POST")) {
            Map<String, String> query = URLUtils.decodeQuery(request.body());
            String email = query.get("email");
            String password = query.get("password");
            String password_confirm = query.get("password_confirm");

            if (password.equals(password_confirm)) {
                System.out.println("wut");
                password = sha256(password);
                userDao.addUnverifiedUser(email, password);
                response.redirect(Conf.ROUTE_LOGGED_ROOT);

                return "OK";
            }
            else {

                System.out.println("wtf");
                return "KO : " + password + " " + password_confirm;
            }
        }
        response.redirect(Conf.ROUTE_NOTLOGGED_ROOT);
        return "OK";
    }

    public String logout(Request request, Response response) {
        Session session = request.session(false);
        if (session != null) {
            session.invalidate();
        }
        response.removeCookie("session");
        response.removeCookie("JSESSIONID");
        response.redirect(Conf.ROUTE_NOTLOGGED_ROOT);

        return "";
    }

}
