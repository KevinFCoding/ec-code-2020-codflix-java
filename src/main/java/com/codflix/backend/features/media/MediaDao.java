package com.codflix.backend.features.media;

import com.codflix.backend.core.Database;
import com.codflix.backend.models.Media;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MediaDao {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Directly select all Datas in Media for display, the more recent film are displayed first
     * @return all Datas about the Media, id, time, type, genre ...
     */
    public List<Media> getAllMedias() {
        List<Media> medias = new ArrayList<>();

        Connection connection = Database.get().getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM media ORDER BY release_date DESC");
            while (rs.next()) {
                medias.add(mapToMedia(rs));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return medias;
    }

    /**
     * Used by the search function to target specified medias.
     * @param title full or part of the title searched.
     *              No ajax.
     * @return
     */
    public List<Media> filterMedias(String title) {
        List<Media> medias = new ArrayList<>();
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM media WHERE title LIKE CONCAT('%', ?, '%') ORDER BY release_date DESC");
            st.setString(1, title);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                medias.add(mapToMedia(rs));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return medias;
    }

    /**
     * Directly get the specified Media by its ID, used for Details
     * @param id Target the media_id for details.
     * @return
     */
    public Media getMediaById(int id) {

        Media media = null;
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM media WHERE id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                media = mapToMedia(rs);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return media;
    }

    /**
     * Map for remote use.
     * @param rs
     * @return
     * @throws SQLException
     * @throws ParseException
     */
    private Media mapToMedia(ResultSet rs) throws SQLException, ParseException {
        return new Media(
                rs.getInt(1), // id
                rs.getInt(2), // genre_id
                rs.getString(3), // title
                rs.getString(4), // type
                rs.getString(5), // status
                DATE_FORMAT.parse(rs.getString(6)),
                rs.getInt(7),// release_date
                rs.getString(8), // summary
                rs.getString(9) // trailer_url
        );
    }

}
