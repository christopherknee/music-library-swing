package model;

public class PodcastEpisode extends Media {

    private int episodeNumber;

    //Constructs a Podcast
    //EFFECTS: Song has a name, n and a genre, g

    public PodcastEpisode(String n, String g, String a, int e) {
        super(n, g, a);
        episodeNumber = e;

    }

    public int getEpisodeNumber() {
        return this.episodeNumber;
    }

    public String getNextEpisode() {
        return getName() + " " + "Ep. " + getEpisodeNumber() + 1;
    }
}
