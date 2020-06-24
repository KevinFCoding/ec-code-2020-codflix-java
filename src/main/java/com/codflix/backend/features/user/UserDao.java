package com.codflix.backend.features.user;

import com.codflix.backend.core.Database;
import com.codflix.backend.models.User;

import javax.swing.*;
import java.sql.*;

public class UserDao {
    public User getUserByCredentials(String email, String password, String verified) {
        User user = null;

        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE email=? AND password=? AND verified=?");

            st.setString(1, email);
            st.setString(2, password);
            st.setString(3, verified);

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

    public void addUnverifiedUser(String email, String password){

        String newUserEmail = email;
        String newUserPassword = password;

        Connection connection = Database.get().getConnection();
        try {
            String query = " INSERT INTO user (email, password, verified)"
                    + " VALUES (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newUserEmail);
            preparedStmt.setString (2, newUserPassword);
            preparedStmt.setBoolean(3, false);

            preparedStmt.execute();
            connection.close();

            JOptionPane.showInputDialog("Utilisateur ajouté");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
