package com.codflix.backend.features.media;

import com.codflix.backend.core.Database;
import com.codflix.backend.models.Episode_Media;
import com.codflix.backend.models.Media;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SerieDao {


    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public List<Episode_Media> getEpisodeByMediaID(int media_id) {
        List<Episode_Media> episodes = new ArrayList<>();
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM media_episodes WHERE id_media_serie=? ORDER BY release_date ASC");
            st.setInt(1, media_id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                episodes.add(mapToMedia(rs));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return episodes;
    }

    public Episode_Media getEpisodeById(int id) {

        System.out.println(id);
        Episode_Media episode = null;
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM media_episodes WHERE id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                episode = mapToMedia(rs);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return episode;
    }

    private Episode_Media mapToMedia(ResultSet rs) throws SQLException, ParseException {
        return new Episode_Media(
                rs.getInt(1), // id
                rs.getInt(2), // id_media_serie
                rs.getString(3), // episode_title
                rs.getString(4), //summary
                DATE_FORMAT.parse(rs.getString(5)),
                rs.getInt(6),// release_date
                rs.getInt(7), // summary
                rs.getInt(8), // trailer_url
                rs.getString(9)
        );
    }

}
