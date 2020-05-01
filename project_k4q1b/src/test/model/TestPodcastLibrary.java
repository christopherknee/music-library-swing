package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestPodcastLibrary {

    PodcastLibrary podcastLibrary;
    PodcastSeries podcastSeries;

    @BeforeEach
    void setup() {
        podcastLibrary = new PodcastLibrary();
        podcastSeries = new PodcastSeries("Science Friday", "Science", "Artist");
    }

    @Test
    void testAddMedia() {
        podcastLibrary.addMedia("Science Friday","Science", "Artist" );
    }

    @Test
    void testListenMedia() {
        podcastLibrary.listenMedia(podcastSeries);
    }
}
