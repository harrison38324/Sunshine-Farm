package ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import exceptions.MoneyNotEnoughException;
import model.AgriculturalEntity;

// Shop Menu for user to but items (plants / fertilizer) they want
public class ShopMenu extends HelperPanel {

    // EFFECTS: initialize the Panel and buttons
    public ShopMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        super(cardLayout, mainPanel, coreData);
        setLayout(new FlowLayout());
        initialValue(coreData.plantsCommodity, "Time to grow up");
        initButtons();
        initialValue(coreData.fertilizerCommodity, "Time to reduce");
        initButtons();
    }

    @Override
    // EFFECTS: try to buy the indicated commodity
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
    // EFFECTS: set the default Laber text
    protected String getHeaderLabelText() {
        return "These items are on sale, what do you want \n Your Balance: " + coreData.wallet.getBalance();
    }

    @Override
    // EFFECTS: set the text on the panel change button
    protected String getBackButtonText() {
        return "Back to Main Menu";
    }

    @Override
    // EFFECTS: set the constrains String of the panel in the mainPanel
    protected String getBackPanelName() {
        return "Main Menu";
    }
}
