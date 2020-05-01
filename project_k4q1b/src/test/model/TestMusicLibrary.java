package model;

import model.exceptions.EmptyFieldException;
import model.exceptions.SongNotListenedTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestMusicLibrary {

    private MusicLibrary testMusicLibrary;

    @BeforeEach
    void setup() throws IOException, ClassNotFoundException {

        testMusicLibrary = new MusicLibrary();
    }

    @Test
    void testAddSongEmpty() {

        String testName = "Everlong";
        String testGenre = "Rock";
        String testArtist = "Foo Fighters";
        try {
            testMusicLibrary.addMedia(testName, testGenre, testArtist);
        } catch (EmptyFieldException e) {
            fail();
        }
        assertEquals(testMusicLibrary.yourMusic.get(testMusicLibrary.yourMusic.size()-1).getGenre(), testGenre);
        assertEquals(testMusicLibrary.yourMusic.get(testMusicLibrary.yourMusic.size()-1).getName(), testName);
        assertEquals(testMusicLibrary.yourMusic.get(testMusicLibrary.yourMusic.size()-1).getArtist(), testArtist);
    }

    @Test
    void testListenSongNotEmpty() {
        Song testSong = new Song("September", "Funk", "Earth, Wind & Fire");
        Song testSong2 = new Song("Can't Stop", "Rock", "Red Hot Chili Peppers");
        testMusicLibrary.yourMusic.add(testSong);
        testMusicLibrary.yourMusic.add(testSong2);
        testMusicLibrary.listenMedia(testMusicLibrary.yourMusic.get(0));
        assertTrue(testSong.getListenedTo());
        assertFalse(testSong2.getListenedTo());
        testMusicLibrary.listenMedia(testMusicLibrary.yourMusic.get(1));
        assertTrue(testSong.getListenedTo());
        assertTrue(testSong2.getListenedTo());
    }

    @Test
    void testListenSongEmpty() {
        Song testSong = new Song("Even Flow", "Rock", "Pearl Jam");
        testMusicLibrary.yourMusic.add(testSong);
        testMusicLibrary.listenMedia(testMusicLibrary.yourMusic.get(0));
        assertTrue(testMusicLibrary.yourMusic.contains(testSong));
        assertTrue(testSong.getListenedTo());
        assertEquals(testSong.getTimesPlayed(),1);
    }
    

    @Test
    void testSave() {
        String testName = "Autumn Leaves";
        String testGenre = "Jazz";
        String testArtist = "Joseph Kosma";
        try {
            testMusicLibrary.addMedia(testName, testGenre, testArtist);
        } catch (EmptyFieldException e) {
            fail();
        }
        try {
            testMusicLibrary.save();
        } catch (IOException e) {
            fail();
        }
        try {
            testMusicLibrary.addMedia("The Trooper", "Heavy Metal", "Iron Maiden");
        } catch (EmptyFieldException e) {
            fail();
        }
        try {
            testMusicLibrary.load();
        } catch (IOException e) {
            fail();
        } catch (ClassNotFoundException e) {
            fail();
        }
        assertEquals(testMusicLibrary.yourMusic.get(testMusicLibrary.yourMusic.size()-1).getGenre(), testGenre);

    }

    @Test
    void testRateSongNotRate() {
        Song testSong = new Song("Pent Up House", "Jazz", "Sonny Rollins");
        testMusicLibrary.yourMusic.add(testSong);
        testMusicLibrary.listenMedia(testMusicLibrary.yourMusic.get(0));
        try {
            testMusicLibrary.rateMedia(testSong, 5);
        } catch (SongNotListenedTo songNotListenedTo) {
            fail("Not listened to");
        }
        assertEquals(testSong.getRating(),5);

    }

    @Test
    void testRateSongAlreadyRated() {
        Song testSong = new Song("Pent Up House", "Jazz", "Sonny Rollins");
        testMusicLibrary.yourMusic.add(testSong);

        testMusicLibrary.listenMedia(testMusicLibrary.yourMusic.get(0));

        try {
            testMusicLibrary.rateMedia(testSong, 5);
            assertEquals(testSong.getRating(),5);
            testMusicLibrary.rateMedia(testSong, 4);
            assertEquals(testSong.getRating(),4);
        } catch (SongNotListenedTo songNotListenedTo) {
            fail("Not listened to");
        }

    }

    @Test
    void testRateSongNotListened() {
        Song testSong = new Song("Eruption", "Rock", "Van Halen");
        testMusicLibrary.yourMusic.add(testSong);
        try {
            testMusicLibrary.rateMedia(testSong, 5);
        } catch (SongNotListenedTo songNotListenedTo) {}
        assertFalse(testSong.getRating() == 5);

        testMusicLibrary.listenMedia(testMusicLibrary.yourMusic.get(0));

        try {
            testMusicLibrary.rateMedia(testSong, 5);
        } catch (SongNotListenedTo songNotListenedTo) {
            fail("Not listened to");
        }
        assertTrue(testSong.getRating() == 5);

    }

    @Test
    void testSongsPerGenre() {
        Song testSong = new Song("Africa", "Pop", "Toto");
        assertFalse(testMusicLibrary.songsPerGenre.containsKey("Pop"));
        try {
            testMusicLibrary.addMedia("Africa", "Pop", "Toto");
        } catch (EmptyFieldException e) {
            fail();
        }
        assertTrue(testMusicLibrary.songsPerGenre.containsKey("Pop"));
    }

    @Test
    void testPrintMusicToString() {
        assertEquals("Add music to view your list", testMusicLibrary.printOutMusicToString());
        Song testSong = new Song("Africa", "Pop", "Toto");
        try {
            testMusicLibrary.addMedia("Africa", "Pop", "Toto");
        } catch (EmptyFieldException e) {
            fail();
        }
        assertEquals("1. Africa - Toto" + "\r\n", testMusicLibrary.printOutMusicToString());
        Song testSong1 = new Song("Eruption", "Rock", "Van Halen");
        testMusicLibrary.yourMusic.add(testSong1);
        assertEquals("1. Africa - Toto" + "\r\n" + "2. Eruption - Van Halen" + "\r\n", testMusicLibrary.printOutMusicToString());
    }

    @Test
    void testPrintOutMusic() {
        try {
            testMusicLibrary.addMedia("Test", "Song","Test");
        } catch (EmptyFieldException e) {
            fail();
        }
        testMusicLibrary.printOutMusic();
    }

    @Test
    void testPrintOutMusicException() {
        try {
            testMusicLibrary.addMedia("", "Song","Test");
        } catch (EmptyFieldException e) {
            System.out.println("Expected Exception");
        }
    }
}
