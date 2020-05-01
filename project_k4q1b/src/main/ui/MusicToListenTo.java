package ui;

/*
public class MusicToListenTo {

    private Scanner scanner;
    private String emptyMusicLibraryMessage = "Your music library is empty! Add a song before continuing";
    private String instructions = "Enter one of the following commands:" + "\r\n"
            + "[1] Add a song to your music!" + "\r\n"
            + "[2] Listen to your music!" + "\r\n"
            + "[3] Rate a song." + "\r\n"
            + "[4] Go back to Main Menu.";

    //Constructs ToListenTo app with 2 lists, songsToListen and songsListened
    //EFFECTS: Both lists begin empty. Calls actionHub
    public MusicToListenTo() throws IOException, ClassNotFoundException {
        scanner = new Scanner(System.in);
        musicActionHub();
    }

    public void musicOrPodcasts() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("----------------------------" + "\r\n" + "\r\n"
                    + "Welcome to NameInProgress" + "\r\n" + "Enter one of the following" + "\r\n"
                    + "[1] Music     [2] Podcasts     [3] Quit" + "\r\n"
                    + "----------------------------");
            String action = scanner.nextLine();
            if (action.equals("1")) {
                musicActionHub();

            }
            if (action.equals("3")) {
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: Create infinite loop that allows user to pick a desired action
    public void musicActionHub() throws IOException, ClassNotFoundException {
        MusicLibrary musicLibrary = new MusicLibrary();
        musicLibrary.load();
        actionHubLoopAddQuit(musicLibrary);
    }

    private void actionHubLoopAddQuit(MusicLibrary musicLibrary) throws IOException {
        while (true) {
            System.out.println(instructions);
            String action = scanner.nextLine();
            if (action.equals("1")) {
                scannerAdd(musicLibrary);
            }
            actionHubLoopListenRate(musicLibrary, action);
            if (action.equals("4")) {
                musicLibrary.save();
                break;
            }
        }
    }

    private void actionHubLoopListenRate(MusicLibrary musicLibrary, String action) {
        if (action.equals("2")) {
            try {
                scannerListen(musicLibrary);
            } catch (EmptyMusicLibrary e) {
                System.out.println(emptyMusicLibraryMessage);
            } catch (InvalidNumberEntry e) {
                System.out.println("Invalid number entry!");
            }
        }
        if (action.equals("3")) {
            try {
                scannerRate(musicLibrary);
            } catch (EmptyMusicLibrary emptyMusicLibrary) {
                System.out.println(emptyMusicLibraryMessage);
            } catch (InvalidNumberEntry invalidNumberEntry) {
                System.out.println("Invalid number entry!");
            }
        }
    }


    private void scannerRate(MusicLibrary musicLibrary) throws EmptyMusicLibrary, InvalidNumberEntry {
        if (musicLibrary.yourMusic.size() == 0) {
            throw new EmptyMusicLibrary();
        }
        System.out.println("What Song would you like to rate? [Enter entry number]");
        int entryNumber = printAndPickSong(musicLibrary);
        System.out.println("How many stars? (1-5)");
        int rating = scanner.nextInt();
        String e = scanner.nextLine();
        if (entryNumber > musicLibrary.yourMusic.size() || entryNumber <= 0) {
            throw new InvalidNumberEntry();
        }
        try {
            musicLibrary.rateMedia(musicLibrary.yourMusic.get(entryNumber - 1), rating);
        } catch (SongNotListenedTo songNotListenedTo) {
            System.out.println("You must listen to a song before you can rate it");
        }
    }

    private void scannerAdd(MusicLibrary musicLibrary) {
        System.out.println("Enter Song name");
        String newSongName = scanner.nextLine();
        System.out.println("Enter Song genre");
        String newGenre = scanner.nextLine();
        System.out.println("Enter Artist name");
        String artist = scanner.nextLine();
        musicLibrary.addMedia(newSongName, newGenre, artist);
    }

    private void scannerListen(MusicLibrary musicLibrary) throws EmptyMusicLibrary, InvalidNumberEntry {
        if (musicLibrary.yourMusic.size() == 0) {
            throw new EmptyMusicLibrary();
        }
        System.out.println("What song would you like to listen to? [Enter entry number]");
        int entryNumber = printAndPickSong(musicLibrary);
        if (entryNumber > musicLibrary.yourMusic.size() || entryNumber <= 0) {
            throw new InvalidNumberEntry();
        }
        musicLibrary.listenMedia(musicLibrary.yourMusic.get(entryNumber - 1));
    }

    private int printAndPickSong(MusicLibrary musicLibrary) {
        printOutMusic(musicLibrary);
        String entryString = scanner.nextLine();
        int entryNumber = 0;
        try {
            entryNumber = Integer.parseInt(entryString);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid number entry");
        }
        return entryNumber;
    }

    private void printOutMusic(MusicLibrary musicLibrary) {
        for (int i = 0; i < musicLibrary.yourMusic.size(); i++) {
            System.out.println((i + 1) + ". " + musicLibrary.yourMusic.get(i).getName() + ", by "
                    + musicLibrary.yourMusic.get(i).getArtist() + "");
        }
    }
}*/
