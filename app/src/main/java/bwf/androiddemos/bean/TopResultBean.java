package bwf.androiddemos.bean;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/10/25 0025.
 * Description:
 */

public class TopResultBean {

    public String count;
    public String start;
    public String total;
    public String title;
    public List<Subject> subjects;

    public class Subject {
        private Rating rating;

        private List<String> genres;

        private String title;

        private List<Cast> casts;

        private int collect_count;

        private String original_title;

        private String subtype;

        private List<Cast> directors;

        private String year;

        private Images images;

        private String alt;

        private String id;

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public Rating getRating() {
            return this.rating;
        }

        public void setString(List<String> genres) {
            this.genres = genres;
        }

        public List<String> getString() {
            return this.genres;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setCasts(List<Cast> casts) {
            this.casts = casts;
        }

        public List<Cast> getCasts() {
            return this.casts;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public int getCollect_count() {
            return this.collect_count;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getOriginal_title() {
            return this.original_title;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getSubtype() {
            return this.subtype;
        }

        public void setDirectors(List<Cast> directors) {
            this.directors = directors;
        }

        public List<Cast> getDirectors() {
            return this.directors;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getYear() {
            return this.year;
        }

        public void setImages(Images images) {
            this.images = images;
        }

        public Images getImages() {
            return this.images;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getAlt() {
            return this.alt;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        @Override
        public String toString() {
            return "Subject{" +
                    "rating=" + rating +
                    ", genres=" + genres +
                    ", title='" + title + '\'' +
                    ", casts=" + casts +
                    ", collect_count=" + collect_count +
                    ", original_title='" + original_title + '\'' +
                    ", subtype='" + subtype + '\'' +
                    ", directors=" + directors +
                    ", year='" + year + '\'' +
                    ", images=" + images +
                    ", alt='" + alt + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    public class Rating {
        private int max;

        private double average;

        private String stars;

        private int min;

        public void setMax(int max) {
            this.max = max;
        }

        public int getMax() {
            return this.max;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public double getAverage() {
            return this.average;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getStars() {
            return this.stars;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMin() {
            return this.min;
        }

        @Override
        public String toString() {
            return "Rating{" +
                    "max=" + max +
                    ", average=" + average +
                    ", stars='" + stars + '\'' +
                    ", min=" + min +
                    '}';
        }
    }

    public class Images {
        private String small;

        private String large;

        private String medium;

        public void setSmall(String small) {
            this.small = small;
        }

        public String getSmall() {
            return this.small;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getLarge() {
            return this.large;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getMedium() {
            return this.medium;
        }

        @Override
        public String toString() {
            return "Images{" +
                    "small='" + small + '\'' +
                    ", large='" + large + '\'' +
                    ", medium='" + medium + '\'' +
                    '}';
        }
    }

    public class Cast {
        private String alt;

        private Avatars avatars;

        private String name;

        private String id;

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getAlt() {
            return this.alt;
        }

        public void setAvatars(Avatars avatars) {
            this.avatars = avatars;
        }

        public Avatars getAvatars() {
            return this.avatars;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        @Override
        public String toString() {
            return "Cast{" +
                    "alt='" + alt + '\'' +
                    ", avatars=" + avatars +
                    ", name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    public class Avatars {
        private String small;

        private String large;

        private String medium;

        public void setSmall(String small) {
            this.small = small;
        }

        public String getSmall() {
            return this.small;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getLarge() {
            return this.large;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getMedium() {
            return this.medium;
        }

        @Override
        public String toString() {
            return "Avatars{" +
                    "small='" + small + '\'' +
                    ", large='" + large + '\'' +
                    ", medium='" + medium + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "TopResultBean{" +
                "count='" + count + '\'' +
                ", start='" + start + '\'' +
                ", total='" + total + '\'' +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
