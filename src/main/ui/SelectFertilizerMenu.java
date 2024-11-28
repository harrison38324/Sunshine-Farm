package ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.AgriculturalEntity;
import model.CoreData;

// JPanel for user to select the fertilizer they have to use
public class SelectFertilizerMenu extends HelperPanel {

    // EFFECTS: initialize the Panel
    public SelectFertilizerMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        super(cardLayout, mainPanel, coreData);
        setLayout(new FlowLayout());
        initialValue(coreData.getFertilizerStorage(),"Time to reduce");
        initButtons();
    }

    @Override
    // MODIFIES: coreData
    // EFFECTS: select the fertilizer and show the ApplyFertilizerMenu
    public void buttonMethod(AgriculturalEntity agriculturalEntity,JButton tempButton) {
        coreData.selectFertilizer(agriculturalEntity);
        
        cardLayout.show(mainPanel,"Apply Fertilizer Menu");
    }

    // EFFECTS: below, 3 methods set values for the PlantPlantsMenu in HelperPanel
    @Override
    // EFFECTS: set the default Laber text
    protected String getHeaderLabelText() {
        return "You have thses Fertilizers,choose one you want to use";
    }

    @Override
    // EFFECTS: set the text on the panel change button
    protected String getBackButtonText() {
        return "Go back to Farm";
    }

    @Override
    // EFFECTS: set the constrains String of the panel in the mainPanel
    protected String getBackPanelName() {
        return "Farm Menu";
    }

}
