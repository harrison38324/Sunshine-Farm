package ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import model.AgriculturalEntity;

public class SellMaturePlantMenu extends HelperPanel {

    public SellMaturePlantMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        super(cardLayout, mainPanel, coreData);
        setLayout(new FlowLayout());

    }

    @Override
    public void buttonMethod(AgriculturalEntity agriculturalEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buttonMethod'");
    }

    @Override
    protected String getHeaderLabelText() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeaderLabelText'");
    }

    @Override
    protected String getBackButtonText() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBackButtonText'");
    }

    @Override
    protected String getBackPanelName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBackPanelName'");
    }
}
