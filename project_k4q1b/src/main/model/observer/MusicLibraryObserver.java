package model.observer;

import java.util.Observable;
import java.util.Observer;

public class MusicLibraryObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("You added a " + arg + " song to your library for the first time!");

    }
}
