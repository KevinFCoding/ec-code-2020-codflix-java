package com.codflix.backend.features.user;

import com.codflix.backend.core.Database;
import com.codflix.backend.models.User;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;

public class UserDao {

    /**
     * Get user directly by its email and password, not its personnal ID
     * @param email email user in DB
     * @param password password user
     * @return all data from user with the right email AND password
     */
    public User getUserByCredentials(String email, String password) {
        User user = null;

        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE email=? AND password=?");

            st.setString(1, email);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = mapToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    private User mapToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt(1), // id
                rs.getString(2), // email
                rs.getString(3), // password
                rs.getBoolean(4) // verified
        );
    }

    /**
     * Target sepcified user with its ID
     * @param userId
     * @return all data in DB from the user specified.
     */
    public User getUserById(int userId) {
        User user = null;
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE id=?");

            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = mapToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * TODO the set-up to check if user is verified : limited access and asking for verification on email
     * @param email
     * @return
     */
    public boolean isUserVerified(String email) {
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT verified FROM user WHERE email=?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            return rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Add user to DB with the unverified status
     * @param email email user from form
     * @param password password user
     */
    public void addUnverifiedUser(String email, String password) {

        System.out.println(password);

        Connection connection = Database.get().getConnection();
        try {
            String query = " INSERT INTO user (email, password, verified)"
                    + " VALUES (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, password);
            preparedStmt.setBoolean(3, false);

            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
