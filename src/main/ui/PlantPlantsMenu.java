package ui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.AgriculturalEntity;
import model.Plants;
import model.PlantsStorage;

public class PlantPlantsMenu extends JPanel{
    private CoreData coreData;
    private JLabel PlantPlantsMenuText;

    public PlantPlantsMenu(CoreData coreData){
        this.coreData = coreData;
        setLayout(new FlowLayout());

        PlantPlantsMenuText = new JLabel("You have these Plants, select the one you want to plant");

        createButtons();
        addActionListeners();

        add(PlantPlantsMenuText);
        addButtons();
    }

    // EFFECTS: create buttons of user owned Plants for user to plant
    private void createButtons(){
        PlantsStorage plantsStorage = coreData.plantsStorage;
        for (AgriculturalEntity plant: plantsStorage.getStorage()){
            JButton tempButton = new JButton(plant.getName()+" Time to grow up:"+plant.getTime());
            tempButton.addActionListener(e-> );
            add(tempButton);
        }
    }
}
