package model;

import model.exceptions.InvalidNumberEntry;

import java.io.Serializable;

public abstract class Media implements Serializable {
    protected String genre;
    protected String name;
    protected String artist;
    protected boolean listenedTo;
    protected int rating;
    protected int timesPlayed;
    protected boolean rated;

    public Media(String n, String g, String a) {
        genre = g;
        name = n;
        artist = a;
        listenedTo = false;
        timesPlayed = 0;
        rated = false;
    }

    //MODIFIES: this
    //EFFECTS: set the name of a Song
    public void setName(String songName) {
        this.name = songName;
    }

    //REQUIRES: songName is not empty
    //EFFECTS: Returns name of a Song
    public String getName() {
        return this.name;
    }

    //MODIFIES: this
    //EFFECTS: set the genre of a Song
    public void setGenre(String genre) {
        this.genre = genre;
    }

    //REQUIRES: Genre is not empty
    //EFFECTS: Returns genre of a Song
    public String getGenre() {
        return this.genre;
    }

    //MODIFIES: this
    //EFFECTS: set the rating of a Song
    public void setRating(int rating) throws InvalidNumberEntry {
        if (rating > 5 | rating < 0) {
            throw new InvalidNumberEntry();
        }
        this.rating = rating;
    }

    //REQUIRES: Rating has been set
    //EFFECTS: Returns rating of a Song
    public int getRating() {
        return this.rating;
    }

    public void setListenedTo(boolean b) {
        this.listenedTo = b;
    }

    public boolean getListenedTo() {
        return this.listenedTo;
    }

    public void listenTo() {
        timesPlayed = timesPlayed + 1;
    }

    public int getTimesPlayed() {
        return this.timesPlayed;
    }

    public String getArtist() {
        return this.artist;
    }

    public boolean getRated() {
        return this.rated;
    }


    @Override
    public String toString() {
        return getName() + " " + getGenre();

    }
}
