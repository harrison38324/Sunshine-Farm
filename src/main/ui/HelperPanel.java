package ui;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.AgriculturalEntity;
import model.AgriculturalEntityStorage;

public abstract class HelperPanel extends JPanel implements ButtonActionForButtonInitialize {
    protected CoreData coreData;
    protected JLabel headerLabel;
    protected JButton backButton;
    protected CardLayout cardLayout;
    protected JPanel mainPanel;

    public HelperPanel(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        this.coreData = coreData;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        initializeCommonComponents();
    }

    // MODIFIES: this
    // EFFECTS: sets up the header label and back button
    private void initializeCommonComponents() {
        headerLabel = new JLabel(getHeaderLabelText());
        backButton = new JButton(getBackButtonText());
        backButton.addActionListener(e -> cardLayout.show(mainPanel, getBackPanelName()));

        add(headerLabel);
        add(backButton);
    }

    // MODIFIES: this JPanel
    // EFFECTS: create a button to every item in the list
    public void initButtons(AgriculturalEntityStorage agriculturalEntityStorage,
            String timePropertyText) {
        for (AgriculturalEntity agriculturalEntity : agriculturalEntityStorage.getStorage()) {
            JButton tempButton = new JButton(
                    agriculturalEntity.getName() + " " + timePropertyText + ": " + agriculturalEntity.getTime());
            tempButton.addActionListener(e -> {
                buttonMethod(agriculturalEntity);
            });
            this.add(tempButton);
        }
    }

    // Abstract methods to be defined by subclasses for customization
    protected abstract String getHeaderLabelText();

    protected abstract String getBackButtonText();

    protected abstract String getBackPanelName();

    // protected abstract void onEntityButtonClick(AgriculturalEntity entity);
}
