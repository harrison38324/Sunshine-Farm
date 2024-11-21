package ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.AgriculturalEntity;

// Plant plants Menu for planting actions
public class PlantPlantsMenu extends HelperPanel {
    private CoreData coreData;
    private JLabel plantPlantsMenuText;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public PlantPlantsMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        super(cardLayout, mainPanel, coreData);
        setLayout(new FlowLayout());

        initButtons(coreData.plantsStorage, "Time to grow up");
    }

    @Override
    // MODIFIES: coreData
    // EFFECTS: Plant plants - remove plant from storage and plant to the slots
    public void buttonMethod(AgriculturalEntity plant) {
        cardLayout.show(mainPanel, "Farm Menu");
        coreData.plantPlants(plant);
    }

    // EFFECTS: below, 3 methods set values for the PlantPlantsMenu in HelperPanel
    @Override
    protected String getHeaderLabelText() {
        return "You have these Plants, choose the one you want to plant";
    }

    @Override
    protected String getBackButtonText() {
        return "return to the Farm";
    }

    @Override
    protected String getBackPanelName() {
        return "Farm Menu";
    }

}
