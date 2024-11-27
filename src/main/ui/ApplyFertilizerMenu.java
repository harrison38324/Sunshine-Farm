package ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.AgriculturalEntity;
import model.CoreData;

// Menu for user to aplly the selected fertilizer
public class ApplyFertilizerMenu extends HelperPanel {

    // EFFECTS: initialize the JPanel and buttons
    public ApplyFertilizerMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        super(cardLayout, mainPanel, coreData);
        setLayout(new FlowLayout());
        initialValue(coreData.plantsSlot, "time to grow up");
        initButtons();
    }

    @Override
    // MODIFIES: coreData
    // EFFECTS: apply the fertilizer and remove it from the storage
    public void buttonMethod(AgriculturalEntity agriculturalEntity,JButton tempButton) {
        coreData.applyFertilizer(coreData.selectedFertilizer,agriculturalEntity);
        cardLayout.show(mainPanel,"Farm Menu");
        coreData.selectedFertilizer = null;
    }

    // EFFECTS: below, 3 methods set values for the ApplyFertilizerMenu in
    // HelperPanel
    @Override
    protected String getHeaderLabelText() {
        return "You have thses Plants in slot,choose one you want to apply the fertilizer";
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
