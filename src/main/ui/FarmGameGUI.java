package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Sunshine Farm Game user interface
public class FarmGameGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // constructor of the FarmGameGUI, initialize the GUI
    public FarmGameGUI(){
        setTitle("SunShine Farm");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);

        JPanel mainMenu = createMainMenu();
        JPanel farmMenu = createFarmMenu();
        JPanel shopMenu = createShopMenu();

    }

    // EFFECTS: create Main Menu of the game
    private JPanel createMainMenu(){
        return new JPanel();
    }

    // EFFECTS: create Farm Menu
    private JPanel createFarmMenu(){
        return new JPanel();
    }

    // EFFECTS: create Shop Menu
    private JPanel createShopMenu(){
        return new JPanel();
    }
}
