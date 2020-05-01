package model;

import model.exceptions.SongNotListenedTo;

import java.io.IOException;
import java.util.ArrayList;

public class PodcastLibrary extends MediaLibrary {

    public ArrayList<PodcastSeries> yourPodcastSeries;

    public PodcastLibrary() {
        yourPodcastSeries = new ArrayList<>();
    }

    @Override
    public void addMedia(String newPodcastName, String newGenre, String artist) {
        PodcastSeries podcastSerie = new PodcastSeries(newPodcastName, newGenre, artist);
        yourPodcastSeries.add(podcastSerie);
        System.out.println("You added " + podcastSerie.getName() + ", " + podcastSerie.getGenre());
    }

    @Override
    public void listenMedia(Media podcastEpisode) {
        podcastEpisode.setListenedTo(true);
        podcastEpisode.listenTo();
    }


    @Override
    public void rateMedia(Media m, int rating) throws SongNotListenedTo {

    }



    @Override
    public void save() throws IOException {

    }

    @Override
    public void load() throws IOException, ClassNotFoundException {

    }
}
