package model;

import model.exceptions.EmptyFieldException;
import model.exceptions.InvalidNumberEntry;
import model.exceptions.SongNotListenedTo;
import model.observer.MusicLibraryObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MusicLibrary extends MediaLibrary {

    public ArrayList<Song> yourMusic;
    Map<String, ArrayList<Song>> songsPerGenre;


    public MusicLibrary() {
        addObserver(new MusicLibraryObserver());
        yourMusic = new ArrayList<>();
        songsPerGenre = new HashMap<>();
    }

    //MODIFIES: this
    //EFFECTS: Add new Song with specific name and genre to songsToListen
    @Override
    public void addMedia(String newSongName, String newGenre, String artist) throws EmptyFieldException {
        if (newSongName.equals("") | newGenre.equals("") | artist.equals("")) {
            throw new EmptyFieldException();
        }
        Song song = new Song(newSongName, newGenre, artist);
        yourMusic.add(song);
        if (!songsPerGenre.containsKey(newGenre)) {
            setChanged();
            notifyObservers(newGenre);
            addGenre(newGenre);
        }
        addSongToGenre(song);
        System.out.println("You added: " + song.getName() + " by " + song.getArtist());
    }

    //MODIFIES: this
    //EFFECTS: Change status of song in yourMusic to true. Add 1 to times played.
    @Override
    public void listenMedia(Media song) {
        song.setListenedTo(true);
        song.listenTo();
        System.out.println("You listened to: " + song.getName());
    }

    //MODIFIES: Song in yourMusic
    //EFFECTS: Sets rating from 1-5 stars for specific song
    @Override
    public void rateMedia(Media song, int rating) throws SongNotListenedTo {
        if (!song.getListenedTo()) {
            throw new SongNotListenedTo();
        } else {
            try {
                song.setRating(rating);
            } catch (InvalidNumberEntry invalidNumberEntry) {
                System.out.println("Error");
            }
            song.rated = true;
            System.out.println("You rated " + song.getName() + " " + song.getRating() + " Stars");
        }
    }



    private void addGenre(String genre) {
        songsPerGenre.put(genre, new ArrayList<>());
    }

    private void addSongToGenre(Song song) {
        ArrayList<Song> songs = songsPerGenre.get(song.getGenre());
        songs.add(song);
    }

    public void printOutMusic() {
        for (int i = 0; i < yourMusic.size(); i++) {
            System.out.println((i + 1) + ". " + yourMusic.get(i).getName() + ", by "
                    + yourMusic.get(i).getArtist() + "\r\n");
        }
    }

    String printOutMusicToString() {
        String list = "";
        for (int i = 0; i < yourMusic.size(); i++) {
            list = list + (i + 1) + ". " + yourMusic.get(i).getName() + " - "
                    + yourMusic.get(i).getArtist() + "\r\n";
        }
        if (list.equals("")) {
            return "Add music to view your list";
        }
        return list;
    }


    @Override
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("./data/TLTdata.txt");
        ObjectOutputStream save = new ObjectOutputStream(fos);
        save.writeObject(yourMusic);
        save.writeObject(songsPerGenre);
        save.close();
    }

    @Override
    public void load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("./data/TLTdata.txt");
        ObjectInputStream load = new ObjectInputStream(fis);
        yourMusic = (ArrayList<Song>) load.readObject();
        songsPerGenre = (Map<String, ArrayList<Song>>) load.readObject();
        load.close();
    }
}
