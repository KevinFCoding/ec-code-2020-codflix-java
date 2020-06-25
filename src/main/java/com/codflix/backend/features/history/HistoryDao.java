package com.codflix.backend.features.history;

import com.codflix.backend.core.Database;
import com.codflix.backend.models.History;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryDao {

    /**
     * Get stream History for the specified user, tracking its userId
     * @param userId personnal ID for the user.
     * @return Every movie / serie consulted on the website
     */

    public List<History> getStreamsHistoryForUser(int userId) {
        List<History> histories = new ArrayList<>();
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM history WHERE user_id=?");
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                History history = mapToStreamHistory(rs);
                histories.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histories;
    }

    /**
     * Add to history table all information about the media watched by the user
     * @param userId We target the specified user
     * @param mediaId Add the media to the historic page and database.
     */
    public void addHistoryForUser(int userId, int mediaId) {
        Connection connection = Database.get().getConnection();

        try {
            String query = " INSERT INTO history (user_id, media_id, start_date, finish_date, watch_duration)"
                    + " VALUES (?, ?, ?, ?, ?)";


            // create the mysql insert preparedstatement

            DateFormat dateFormatMDY = new SimpleDateFormat("yyyy/MM/dd");
            Date now = new Date();
            String vDateMDY = dateFormatMDY.format(now);


            System.out.println(vDateMDY);
            System.out.println("Did we get here");
            // TODO verification if mediaId already exist for user, set true data, refresh
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, userId);
            preparedStmt.setInt(2, mediaId);
            preparedStmt.setString(3, vDateMDY);
            preparedStmt.setString(4, vDateMDY);
            preparedStmt.setInt(5, 0);

            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("Did we get here too");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param rs
     * @return mapping of historyDao for remote use
     * @throws SQLException
     */
    private History mapToStreamHistory(ResultSet rs) throws SQLException {
        return new History(
                rs.getInt(1), // id
                rs.getInt(2), // user_id
                rs.getInt(3), // stream_id
                rs.getString(4), // startDate
                rs.getString(5), // endDate
                rs.getInt(6) // watchDuration
        );
    }
}
