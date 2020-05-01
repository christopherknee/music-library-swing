package network;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class LastFMInfo {

    public static final String apiKey = "ce0ca5db4f0cd85a199340bf2e983ae7";
    public static final String apiRoot = "http://ws.audioscrobbler.com/2.0/";
    private static String getSimilarMethod = "?method=artist.getSimilar";
    private static String limitParameter = "&limit=";
    private static String artistParameter = "&artist=";

    public String getSimilarArtists(String artist, int limit) throws IOException {

        BufferedReader br = null;
        try {
            String theURL = apiRoot + getSimilarMethod + limitParameter + limit + artistParameter
                    + artist + "&api_key=" + apiKey + "&format=json";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            return sb.toString();

        } finally {
            if (br != null) {
                br.close();
            }
        }
    }


    public String parseSimilarArtists(String artist, int numResults) throws IOException {
        JsonParser jsonParser = new JsonParser();
        JsonElement artistTree = jsonParser.parse(getSimilarArtists(artist, numResults));
        JsonObject artistObject = artistTree.getAsJsonObject();
        artistObject = artistObject.getAsJsonObject("similarartists");
        JsonArray artistArray = artistObject.getAsJsonArray("artist");
        String result = "";
        for (int i = 0; i < numResults; i++) {
            artistObject = artistArray.get(i).getAsJsonObject();
            result = result + artistObject.get("name").getAsString() + "\r\n";

        }
        return result;
    }
}
