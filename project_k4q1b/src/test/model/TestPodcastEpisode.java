package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPodcastEpisode {
    PodcastEpisode testPodcastEpisode;

    @BeforeEach
    public void setup() {
        testPodcastEpisode = new PodcastEpisode("", "", "", 1);
    }

    @Test
    public void testSetGetPodcast() {
        testPodcastEpisode.setName("Science Friday");
        assertEquals(testPodcastEpisode.getName(), "Science Friday");
    }

}
