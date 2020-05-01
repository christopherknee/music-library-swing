package model;

import model.exceptions.EmptyFieldException;
import model.exceptions.SongNotListenedTo;

import java.io.IOException;
import java.util.Observable;

public abstract class MediaLibrary extends Observable implements Saveable, Loadable {
    //MODIFIES: this
    //EFFECTS: Add new Song with specific name and genre to songsToListen
    public abstract void addMedia(String newMediaName, String newGenre, String artist) throws EmptyFieldException;

    //MODIFIES: this
    //EFFECTS: Change status of song in yourMusic to true. Add 1 to times played.
    public void listenMedia(Media m) {
        m.setListenedTo(true);
        m.listenTo();
        if (m instanceof Song) {
            System.out.println("You listened to " + m.getName());
        } else if (m instanceof PodcastEpisode) {
            System.out.println("You listened to " + m.getName() + ((PodcastEpisode) m).getEpisodeNumber());
        }

    }

    //MODIFIES: Song in yourMusic
    //EFFECTS: Sets rating from 1-5 stars for specific song
    public abstract void rateMedia(Media m, int rating) throws SongNotListenedTo;

    @Override
    public abstract void save() throws IOException;

    @Override
    public abstract void load() throws IOException, ClassNotFoundException;
}
