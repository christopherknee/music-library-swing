package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.CustomPlaylist;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomPlaylist {

    private CustomPlaylist testPlaylist;

    @BeforeEach
    public void setup() {
        testPlaylist = new CustomPlaylist("Workout");
    }

    @Test
    public void testAddSongEmpty() {
        Song testSong = new Song("A", "B", "C");
        testPlaylist.addSongToPlaylist(testSong);
        assertTrue(testPlaylist.playlist.contains(testSong));
        assertTrue(testSong.playlists.contains(testPlaylist));
    }

    @Test
    public void testEquals() {
        CustomPlaylist testPlaylist2 = new CustomPlaylist("Workout");
        assertTrue(testPlaylist.equals(testPlaylist2));
    }

    @Test
    public void testHashCode() {
        assertEquals(Objects.hash("Workout"), testPlaylist.hashCode());
    }

    @Test
    public void testRemoveSong() {
        Song testSong = new Song( "A", "B", "C");
        testPlaylist.addSongToPlaylist(testSong);
        assertTrue(testPlaylist.playlist.contains(testSong));
        assertTrue(testSong.playlists.contains(testPlaylist));
        testPlaylist.removeSongFromPlaylist(testSong);
        assertFalse(testPlaylist.playlist.contains(testSong));
        assertFalse(testSong.playlists.contains(testPlaylist));
    }
}
