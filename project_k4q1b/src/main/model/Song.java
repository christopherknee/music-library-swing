package model;


import network.LastFMInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class Song extends Media implements Serializable {

    public ArrayList<CustomPlaylist> playlists;

    //Constructs a Song
    //EFFECTS: Song has a name, n and a genre, g
    public Song(String n, String g, String a) {
        super(n, g, a);
        playlists = new ArrayList<>();

    }

    public void addPlaylist(CustomPlaylist c) {
        if (!playlists.contains(c)) {
            playlists.add(c);
            c.addSongToPlaylist(this);
        }
    }

    public void removePlaylist(CustomPlaylist c) {
        if (playlists.contains(c)) {
            playlists.remove(c);
            c.removeSongFromPlaylist(this);
        }
    }
}
