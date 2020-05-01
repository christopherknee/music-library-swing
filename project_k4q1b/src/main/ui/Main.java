package ui;



import javax.swing.*;
import java.io.IOException;

public class Main  {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GUI gui = new GUI();
                    gui.frame.add(gui.createPanels());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
