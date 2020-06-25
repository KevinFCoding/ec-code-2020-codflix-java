package com.codflix.backend.models;

import java.util.Date;

public class Episode_Media {

        private int id;
        private int id_media_serie;
        private String episode_title;
        private String summary;
        private Date releaseDate;
        private int episode_number;
        private int season_number;
        private String episode_url;

        public Episode_Media(int id, int id_media_serie, String episode_title, String summary, Date releaseDate,int episode_number, int season_number, String episode_url) {
            this.id = id;
            this.id_media_serie = id_media_serie;
            this.episode_title = episode_title;
            this.summary = summary;
            this.releaseDate = releaseDate;
            this.episode_number = episode_number;
            this.season_number = season_number;
            this.episode_url = episode_url;
        }

        @Override
        public String toString() {
            return "Episode_Media{" +
                    "id=" + id +
                    ", id_media_serie=" + id_media_serie +
                    ", episode_title='" + episode_title + '\'' +
                    ", summary='" + summary + '\'' +
                    ", releaseDate=" + releaseDate +
                    ", episode_number='" + episode_number + '\'' +
                    ", season_number='" + season_number + '\'' +
                    ", episode_url='" + episode_url + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSerieId() {
            return id_media_serie;
        }

        public void setSerieId(int id_media_serie) {
            this.id_media_serie = id_media_serie;
        }

        public String getEpisodetitle() {
            return episode_title;
        }

        public void setTitle(String episode_title) {
            this.episode_title = episode_title;
        }

        public String getSummary() {
            return summary;
        }

    public void setSummary(String summary) {
        this.summary = summary;
    }

        public Date getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(Date releaseDate) {
            this.releaseDate = releaseDate;
        }

        public int getEpisodeNumber(){
            return episode_number;
        }

        public void setEpisodeNumber(int episode_number) {
            this.episode_number = episode_number;
        }

    public int getSeasonNumber(){
        return season_number;
    }

    public void setSeasonNumber(int season_number) {
        this.season_number = season_number;
    }

        public String getEpisodeUrl() {
            return episode_url;
        }

        public void setEpisodeUrl(String episode_url) {
            this.episode_url = episode_url;
        }
    }

