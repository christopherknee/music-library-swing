package model;

public class PodcastSeries extends Media {

    private String name;
    private String genre;

    public PodcastSeries(String name, String genre, String artist) {
        super(name, genre, artist);
        this.name = name;
        this.genre = genre;
    }

    public String getName() {
        return this.name;
    }

    public String getGenre() {
        return this.genre;
    }
}
