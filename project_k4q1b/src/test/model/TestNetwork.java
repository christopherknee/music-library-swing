package model;

import network.LastFMInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class TestNetwork {

    LastFMInfo lastFMInfo;

    @BeforeEach
    public void setup() {
        lastFMInfo = new LastFMInfo();
    }

    @Test
    void testGetSimilarArtist() {
        try {
            lastFMInfo.getSimilarArtists("Dio", 3);
        } catch (Exception e) {
            fail();
        }
        try {
            lastFMInfo.parseSimilarArtists("Pink Floyd", 10);
        } catch (Exception e) {
            fail();
        }
    }
}
