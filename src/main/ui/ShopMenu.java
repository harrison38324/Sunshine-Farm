package ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.AgriculturalEntity;
import model.MoneyNotEnoughException.MoneyNotEnoughException;

public class ShopMenu extends HelperPanel {

    public ShopMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        super(cardLayout, mainPanel, coreData);
        setLayout(new FlowLayout());
        initialValue(coreData.plantsCommodity, "Time to grow up");
        initButtons();
        initialValue(coreData.fertilizerCommodity, "Time to reduce");
        initButtons();
    }

    @Override
    public void buttonMethod(AgriculturalEntity agriculturalEntity, JButton tempButton) {
        try {
            coreData.tryToBuyCommodity(agriculturalEntity);
        } catch (MoneyNotEnoughException e) {
            headerLabel.setText("Not enough Balance");
        }
    }

    // MODIFIES: this
    // EFFECTS: regenerate the buttons based on the latest core data
    @Override
    public void refreshStatus() {
        headerLabel
                .setText("These items are on sale, what do you want \n Your Balance: " + coreData.wallet.getBalance());
        revalidate();
        repaint();
    }

    @Override
    protected String getHeaderLabelText() {
        return "These items are on sale, what do you want \n Your Balance: " + coreData.wallet.getBalance();
    }

    @Override
    protected String getBackButtonText() {
        return "Back to Main Menu";
    }

    @Override
    protected String getBackPanelName() {
        return "Main Menu";
    }
}
