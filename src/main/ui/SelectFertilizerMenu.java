package ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import model.AgriculturalEntity;


public class SelectFertilizerMenu extends HelperPanel {
    private CoreData coreData;

    public SelectFertilizerMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        super(cardLayout, mainPanel, coreData);
        setLayout(new FlowLayout());
        initButtons(coreData.fertilizerStorage, "Time to reduce");
    }

    @Override
    // MODIFIES: coreData
    // EFFECTS: select the fertilizer and show the ApplyFertilizerMenu
    public void buttonMethod(AgriculturalEntity agriculturalEntity) {
        coreData.selectedFertilizer = agriculturalEntity;
        cardLayout.show(mainPanel,"Apply Fertilizer Menu");
    }

    // EFFECTS: below, 3 methods set values for the PlantPlantsMenu in HelperPanel
    @Override
    protected String getHeaderLabelText() {
        return "You have thses Fertilizers,choose one you want to use";
    }

    @Override
    protected String getBackButtonText() {
        return "Go back to Farm";
    }

    @Override
    protected String getBackPanelName() {
        return "Farm Menu";
    }

}
