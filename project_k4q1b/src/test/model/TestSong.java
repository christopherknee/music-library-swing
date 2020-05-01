package model;

import model.Song;
import model.exceptions.InvalidNumberEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;

import static org.junit.jupiter.api.Assertions.*;

class TestSong {
    private Song testSong;

    @BeforeEach
    void setup(){
        testSong = new Song("", "", "");
    }

    @Test
    void testSetSongName() {
        assertEquals(testSong.getName(), "");
        testSong.setName("Testing 1 2 3");
        assertEquals(testSong.getName(), "Testing 1 2 3");
    }

    @Test
    void testGetSongNameEmpty() {
        assertEquals(testSong.getName(),"");
    }

    @Test
    void testGetSongNameNotEmpty() {
        testSong.setName("Spain");
        assertEquals(testSong.getName(), "Spain");
    }

    @Test
    void testGetGenreEmpty(){
        assertEquals(testSong.getGenre(),"");
    }

    @Test
    void testGetGenreNotEmpty() {
        testSong.setGenre("Jazz");
        assertEquals(testSong.getGenre(), "Jazz");
    }

    @Test
    void testSetGenre() {
        testSong.setGenre("Rock");
        assertEquals(testSong.getGenre(), "Rock");
    }

    @Test
    void testSetRating() {
        try {
            testSong.setRating(5);
        } catch (InvalidNumberEntry invalidNumberEntry) {
            fail();
        }
        assertEquals(testSong.getRating(), 5);
    }

    @Test
    void testGetRating() {
        try {
            testSong.setRating(2);
        } catch (Exception e) {
            fail();
        }
        assertEquals(testSong.getRating(), 2);
    }

    @Test
    void testToString() {
        testSong.setName("Test");
        testSong.setGenre("Song");
        assertEquals(testSong.toString(), "Test Song");
    }

    @Test
    void testAddPlaylist() {
        CustomPlaylist testPlaylist = new CustomPlaylist("Workout");
        testSong.addPlaylist(testPlaylist);
        assertTrue(testSong.playlists.contains(testPlaylist));
        assertTrue(testPlaylist.playlist.contains(testSong));
    }

}