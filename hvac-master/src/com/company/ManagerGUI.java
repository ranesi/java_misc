package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.InputMismatchException;

public class ManagerGUI extends JFrame {
    private JPanel rootPanel;
    private JRadioButton centralACRadioButton;
    private JRadioButton furnaceRadioButton;
    private JRadioButton waterHeaterRadioButton;
    private JTextField addressTextField;
    private JTextField problemTextField;
    private JList serviceCallList;
    private JButton addButton;
    private JButton resolveButton;
    private JButton quitButton;
    private JComboBox variableComboBox;
    private JTextField feeTextField;
    private JLabel variableLabel;
    private JTextField resolutionTextField;

    private ButtonGroup selectionGroup;
    private DefaultListModel<ServiceCall> callModel;

    public ManagerGUI() {
        initializeGUI();
        addListeneners();
    }

    private void addListeneners() {

        //BUTTONS/////////////////////////////////////////////////////////////
        addButton.addActionListener(e -> addButtonClicked());

        resolveButton.addActionListener(e -> resolveButtonClicked());

        quitButton.addActionListener(e -> quitButtonClicked());

        //RADIO BUTTONS//////////////////////////////////////////////////////

        centralACRadioButton.addActionListener(e -> centralACRadioClicked());

        furnaceRadioButton.addActionListener(e -> furnaceRadioClicked());

        waterHeaterRadioButton.addActionListener(e -> waterHeaterRadioClicked());
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ACTION METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void addButtonClicked() {
        // Add ServiceCall to model if valid
        if (validCall()) {
            if (centralACRadioButton.isSelected()) {
                callModel.add(0, new CentralAC(
                        addressTextField.getText(),
                        problemTextField.getText(),
                        new Date(),
                        variableComboBox.getSelectedItem().toString()
                ));
            } else if (furnaceRadioButton.isSelected()) {
                callModel.add(0, new Furnace(
                        addressTextField.getText(),
                        problemTextField.getText(),
                        new Date(),
                        variableComboBox.getSelectedItem().toString()
                ));
            } else if (waterHeaterRadioButton.isSelected()) {
                callModel.add(0, new WaterHeater(
                        addressTextField.getText(),
                        problemTextField.getText(),
                        new Date()
                ));
            }
        } else {
            JOptionPane.showMessageDialog(ManagerGUI.this, "Invalid service call entry!");
        }
        initControls();
    }

    private void resolveButtonClicked() {
        // Update ServiceCall to reflect resolution if valid
        if (validResolution()){
            int index = serviceCallList.getSelectedIndex();
            callModel.getElementAt(index).setFee(Double.parseDouble(feeTextField.getText()));
            callModel.getElementAt(index).setResolvedDate(new Date());
            callModel.getElementAt(index).setResolution(resolutionTextField.getText());
        } else {
            JOptionPane.showMessageDialog(ManagerGUI.this, "Invalid resolution entry!");
        }
        initControls();
    }

    private void quitButtonClicked() { this.dispose(); }

    private void centralACRadioClicked() {
        initControls();
        variableLabel.setText("Model");
        variableComboBox.removeAllItems();
        variableComboBox.setEnabled(true);
        String[] ACModels = {
                "CoolMaster 5000",
                "Fridge-O-Matic",
                "Whirligig"
        };
        for (String model : ACModels) {
            variableComboBox.addItem(model);
        }
    }

    private void furnaceRadioClicked() {
        initControls();
        variableLabel.setText("Type");
        variableComboBox.removeAllItems();
        variableComboBox.setEnabled(true);
        String[] furnaceTypes = { "Wood", "Gas", "Oil", "Kerosene", "Coke", "Coal", "Steam", "Electrical", "Fireplace" };
        for (String type : furnaceTypes)
            variableComboBox.addItem(type);
    }

    private void waterHeaterRadioClicked() {
        initControls();
        variableLabel.setText("N/A");
        variableComboBox.removeAllItems();
        variableComboBox.setEnabled(false);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // INITIALIZATION METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void initializeGUI() {
        setContentPane(rootPanel);
        pack();
        setSize(new Dimension(700, 500));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        callModel = new DefaultListModel<>();
        serviceCallList.setModel(callModel);
        selectionGroup = new ButtonGroup();
        addButtonToGroup();
        setVisible(true);
    }

    private void addButtonToGroup() {
        for(JRadioButton b : new JRadioButton[] {centralACRadioButton, furnaceRadioButton, waterHeaterRadioButton})
            selectionGroup.add(b);
    }

    private void initControls() {
        for (JTextField t : new JTextField[] {addressTextField, problemTextField, feeTextField, resolutionTextField})
            t.setText("");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VALIDATION METHOD(S)
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean validCall() {
        if (addressTextField.getText().length() > 0 && problemTextField.getText().length() > 0)
            return true;
         return false;
    }

    private boolean validResolution() {
        try {
            if (feeTextField.getText().length() > 0 && resolutionTextField.getText().length() > 0) {
                Double.parseDouble(feeTextField.getText());
                return true;
            }
        } catch (InputMismatchException ime) {
            JOptionPane.showMessageDialog(ManagerGUI.this, "Fee must be a valid number!");
        }
        return false;
    }
}
