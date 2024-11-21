package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Sunshine Farm Game user interface
public class FarmGameGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private CoreData coreData;

    // constructor of the FarmGameGUI, initialize the GUI
    public FarmGameGUI(CoreData coreData) {
        this.coreData = coreData;

        setTitle("SunShine Farm");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);

        JPanel mainMenu = createMainMenu();
        JPanel farmMenu = new FarmMenu(cardLayout, mainPanel, coreData);
        JPanel shopMenu = createShopMenu();

        mainPanel.add(mainMenu, "Main Menu");
        mainPanel.add(farmMenu, "Farm Menu");
        mainPanel.add(shopMenu, "Shop Menu");

        add(mainPanel);

        setVisible(true);
    }

    // EFFECTS: create Main Menu of the game
    private JPanel createMainMenu() {
        JPanel mainMenuPanel = new JPanel();

        mainMenuPanel.setLayout(new FlowLayout());

        JButton goToFarm = new JButton("Go to Farm");
        JButton goToShop = new JButton("Go to Shop");

        goToFarm.addActionListener(e -> cardLayout.show(mainPanel, "Farm Menu"));
        goToShop.addActionListener(e -> cardLayout.show(mainPanel, "Shop Menu"));

        JLabel mainMenuTextLabel = new JLabel("Hello,Sunshine Farm, what do you want to do today?");

        ImageIcon icon = new ImageIcon("./data/image/th.jpeg");

        Image image = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        JLabel imageLabel = new JLabel(scaledIcon);

        mainMenuPanel.add(mainMenuTextLabel);
        mainMenuPanel.add(imageLabel);
        mainMenuPanel.add(goToFarm);
        mainMenuPanel.add(goToShop);

        return mainMenuPanel;
    }

    // EFFECTS: create Shop Menu
    private JPanel createShopMenu() {
        return new JPanel();
    }
}
