package ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.AgriculturalEntity;
import model.exceptions.NegativeGrowthTimeException;
import model.exceptions.NotMatureException;

public class SellMaturePlantMenu extends HelperPanel {
    public SellMaturePlantMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        super(cardLayout, mainPanel, coreData);
        setLayout(new FlowLayout());
        initialValue(coreData.plantsSlot,"Time to growth");
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
    protected String getHeaderLabelText() {
        return "select one Plant in the slot you want to sell";
    }

    @Override
    protected String getBackButtonText() {
        return "Back to Farm";
    }

    @Override
    protected String getBackPanelName() {
        return "Farm Menu";
    }

}
