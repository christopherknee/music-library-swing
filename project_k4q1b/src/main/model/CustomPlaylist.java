package model;


import java.util.ArrayList;
import java.util.Objects;

public class CustomPlaylist {


    private String name;
    public ArrayList<Song> playlist;

    public CustomPlaylist(String name) {
        this.name = name;
        playlist = new ArrayList<>();
    }


    public void addSongToPlaylist(Song s) {
        if (!playlist.contains(s)) {
            playlist.add(s);
            s.addPlaylist(this);
        }
    }

    public void removeSongFromPlaylist(Song s) {
        if (playlist.contains(s)) {
            playlist.remove(s);
            s.removePlaylist(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomPlaylist that = (CustomPlaylist) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
