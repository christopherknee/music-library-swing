package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import model.MusicLibrary;
import model.exceptions.EmptyFieldException;
import model.exceptions.SongNotListenedTo;
import network.LastFMInfo;

public class GUI extends JFrame implements ActionListener, MouseListener {

    private JTextField nameField;
    private JTextField genreField;
    private JTextField artistField;
    public MusicLibrary musicLibrary;
    private JList musicTextArea;
    private JScrollPane scroll;
    JFrame frame;
    private DefaultListModel listModel;
    private JEditorPane similarArtistPane;
    private JTextField similarField;
    private JLabel similarLabel;
    private MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1) {
                musicLibrary.listenMedia(musicLibrary.yourMusic.get(musicTextArea.getSelectedIndex()));
                updateList();
            } else if (e.getButton() == 3) {
                String selectedItem = (String) musicTextArea.getSelectedValue();
                String s = (String)JOptionPane.showInputDialog(frame, "Rate this Song out of 5 stars",
                        "Rate Song", JOptionPane.PLAIN_MESSAGE, null, null,
                        "");
                if ((s != null) && (s.length() > 0)) {
                    doubleClickRate(s);
                    return;
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    private void doubleClickRate(String s) {
        try {
            musicLibrary.rateMedia(musicLibrary.yourMusic.get(musicTextArea.getSelectedIndex()),
                    Integer.parseInt(s));
            updateList();
        } catch (SongNotListenedTo songNotListenedTo) {
            System.out.println("Song not listened to!");
        }
    }


    private void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Music Library App");
        frame.setSize(1000, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);


        //Display the window.
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                try {
                    musicLibrary.save();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        );
    }

    public GUI() throws IOException, ClassNotFoundException {
        musicLibrary = new MusicLibrary();
        createAndShowGUI();



    }

    JComponent createPanels() throws IOException, ClassNotFoundException {
        JPanel panel = new JPanel();

        JPanel addSongPanel = new JPanel();
        addSongPanel.setLayout(new BoxLayout(addSongPanel, BoxLayout.PAGE_AXIS));
        addSongPanel.add(createEntryFields());
        addSongPanel.add(createButton());

        JPanel musicLibraryPanel = new JPanel();
        musicLibraryPanel.setLayout(new BoxLayout(musicLibraryPanel, BoxLayout.PAGE_AXIS));
        musicLibraryPanel.add(createYourMusicPanel());

        JPanel similarArtistPanel = new JPanel();
        similarArtistPanel.setLayout(new BoxLayout(similarArtistPanel, BoxLayout.PAGE_AXIS));
        similarArtistPanel.add(createSimilarArtistPanel());


        panel.add(addSongPanel);
        panel.add(musicLibraryPanel);
        panel.add(similarArtistPanel);

        panel.setLayout(new FlowLayout());

        return panel;
    }

    private Component createSimilarArtistPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));

        panel.add(createSimilarArtistField());
        panel.add(createSimilarArtistButton());

        similarArtistPane = new JEditorPane();
        similarArtistPane.setEditable(false);
        similarArtistPane.setMinimumSize(new Dimension(300,300));
        similarArtistPane.setMaximumSize(new Dimension(300,300));

        panel.add(similarArtistPane);



        return panel;
    }

    private Component createSimilarArtistField() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));

        similarField = new JTextField();
        similarLabel = new JLabel("Enter Artist Name");
        similarField.setColumns(20);

        panel.add(similarLabel);
        panel.add(similarField);


        return panel;
    }

    private Component createSimilarArtistButton() {
        JPanel panel = new JPanel();

        JButton button = new JButton("Find Similar Artists");
        button.addActionListener(this);
        button.setActionCommand("Similar");
        panel.add(button);

        return panel;
    }

    private Component createYourMusicPanel() throws IOException, ClassNotFoundException {
        JPanel panel = new JPanel();

        listModel = new DefaultListModel();
        musicTextArea = new JList<String>(listModel);

        musicTextArea.addMouseListener(mouseListener);
        musicTextArea.setLayout(new BoxLayout(musicTextArea, BoxLayout.PAGE_AXIS));
        musicLibrary.load();
        updateList();

        scroll = new JScrollPane(musicTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setWheelScrollingEnabled(true);


        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("Left click to mark as listened, right click to rate");
        panel.add(label);
        panel.add(scroll);

        return panel;
    }


    private JComponent createButton() {
        JPanel panel = new JPanel();

        JButton button = new JButton("Add Song");
        button.addActionListener(this);
        button.setActionCommand("Add");
        panel.add(button);

        return panel;
    }

    private JComponent createEntryFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        nameField = new JTextField();
        genreField = new JTextField();
        artistField = new JTextField();
        JLabel nameLabel = new JLabel("Song Title");
        JLabel genreLabel = new JLabel("Genre");
        JLabel artistLabel = new JLabel("Artist");
        nameLabel.setLabelFor(nameField);
        genreLabel.setLabelFor(genreField);
        artistLabel.setLabelFor(artistField);
        nameField.setColumns(10);
        genreField.setColumns(10);
        artistField.setColumns(10);
        addFieldsAndLabels(panel, nameField, genreField, artistField, nameLabel, genreLabel, artistLabel);
        return panel;
    }

    private void addFieldsAndLabels(JPanel panel, JTextField nameField, JTextField genreField,
                                    JTextField artistField, JLabel nameLabel, JLabel genreLabel,
                                    JLabel artistLabel) {
        panel.add(nameLabel);

        panel.add(nameField);

        panel.add(genreLabel);

        panel.add(genreField);

        panel.add(artistLabel);

        panel.add(artistField);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            try {
                musicLibrary.addMedia(nameField.getText(),genreField.getText(),artistField.getText());
                updateList();
            } catch (EmptyFieldException ex) {
                System.out.println("Error");
            }
            musicLibrary.printOutMusic();


        } else if (e.getActionCommand().equals("Similar")) {
            lastFmAction();

        }

    }

    private void lastFmAction() {
        LastFMInfo lastFMInfo = new LastFMInfo();
        try {
            if (similarField.getText().equals("")) {
                similarArtistPane.setText("");
            } else {
                String result = lastFMInfo.parseSimilarArtists(similarField.getText(), 5);
                similarArtistPane.setText(result);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateList() {
        listModel.clear();
        for (int i = 0; i < musicLibrary.yourMusic.size(); i++) {
            if (musicLibrary.yourMusic.get(i).getRated()) {
                listModel.addElement((i + 1) + ". " + musicLibrary.yourMusic.get(i).getName() + " - "
                        + musicLibrary.yourMusic.get(i).getArtist() + "     "
                        + musicLibrary.yourMusic.get(i).getRating() + "/5 Stars" + "     ✔");
            } else if (musicLibrary.yourMusic.get(i).getListenedTo()) {
                listModel.addElement((i + 1) + ". " + musicLibrary.yourMusic.get(i).getName() + " - "
                        + musicLibrary.yourMusic.get(i).getArtist() + "     ✔");
            } else {
                listModel.addElement((i + 1) + ". " + musicLibrary.yourMusic.get(i).getName() + " - "
                        + musicLibrary.yourMusic.get(i).getArtist());
            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
