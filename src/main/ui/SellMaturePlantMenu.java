package ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import exceptions.NegativeGrowthTimeException;
import exceptions.NotMatureException;
import model.AgriculturalEntity;
import model.CoreData;

// JPanel for user to sell the Plant in the Slot
public class SellMaturePlantMenu extends HelperPanel {

    // EFFECTS: initialize the Panel
    public SellMaturePlantMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        super(cardLayout, mainPanel, coreData);
        setLayout(new FlowLayout());
        initialValue(coreData.getPlantsSlot(),"Time to growth");
        initButtons();
    }

    @Override
    // EFFETCS: try to sell the indicated plant and earn money
    // if the plant is mature, earn double price of the plant
    // otherwise show user it is not valid to sell them
    public void buttonMethod(AgriculturalEntity agriculturalEntity,JButton tempButton) {
        try {
            coreData.sellMaturePlants(agriculturalEntity);
        } catch (NotMatureException e) {
            headerLabel.setText("The plant is Not Mature");
        } catch (NegativeGrowthTimeException e) {
            headerLabel.setText("Error time to grow up, can't be negative");
        }
    }

    @Override
    // EFFECTS: set the default Laber text
    protected String getHeaderLabelText() {
        return "select one Plant in the slot you want to sell";
    }

    @Override
    // EFFECTS: set the text on the panel change button
    protected String getBackButtonText() {
        return "Back to Farm";
    }

    @Override
    // EFFECTS: set the constrains String of the panel in the mainPanel
    protected String getBackPanelName() {
        return "Farm Menu";
    }

}
