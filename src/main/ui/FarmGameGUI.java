package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Sunshine Farm Game user interface
public class FarmGameGUI extends JFrame {

    // constructor of the FarmGameGUI, initialize the GUI
    public FarmGameGUI(){
        setTitle("SunShine Farm");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JLabel statusLabel = new JLabel("Welcome to the Game");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        createJbuttons();

        addButtonListeners();

    }

    // EFFECTS: creates Jbuttons needed for the Game
    private void createJbuttons(){
        JButton viewPlotButton = new JButton("View Plot Status");//stub
    }

    // EFFECTS: creates correct button listeners for the given Jbuttions
    private void addButtonListeners(){
        //stub
    }
}
