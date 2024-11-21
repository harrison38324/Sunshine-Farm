package ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.AgriculturalEntity;

// Plant plants Menu for planting actions
public class PlantPlantsMenu extends HelperPanel {

    public PlantPlantsMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        super(cardLayout, mainPanel, coreData);
        setLayout(new FlowLayout());
        initialValue(coreData.plantsStorage,"Time to grow up");
        initButtons();
    }

    @Override
    // MODIFIES: coreData
    // EFFECTS: Plant plants - remove plant from storage and plant to the slots
    public void buttonMethod(AgriculturalEntity plant,JButton tempButton) {
        cardLayout.show(mainPanel, "Farm Menu");
        coreData.plantPlants(plant);
        remove(tempButton);
        revalidate();
        repaint();
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
