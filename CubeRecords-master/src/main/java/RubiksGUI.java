import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import static javax.swing.JOptionPane.showMessageDialog;

public class RubiksGUI extends JFrame implements WindowListener {
    private JPanel rootPanel;
    private JTable rubiksTable;
    private JButton quitButton;
    private JTextField timeTextField;
    private JTextField nameTextField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    RubiksGUI(RubiksModel rm) {
        initGUI(rm);
        addActionListeners();
    }

    private void addActionListeners() {

        //Add button clicked; creates database entry with information in text fields
        addButton.addActionListener(e -> {
            if (    Validator.validString(nameTextField.getText()) &&
                    Validator.validString(timeTextField.getText()) &&
                    Validator.validDouble(timeTextField.getText())) {
                Database.insertInto(nameTextField.getText(), Double.parseDouble(timeTextField.getText()));
                updateTable();
                clearTextFields();
            } else {
                showMessageDialog(RubiksGUI.this, "Input invalid!");
            }
        });

        updateButton.addActionListener(e -> {
            //Updates records
            if (
                    Validator.validString(nameTextField.getText()) ||
                    Validator.validString(timeTextField.getText())
                    ){
                //Something entered into either (or all) textbox(es)
                if (
                        Validator.validString(nameTextField.getText())
                        && !Validator.validString(timeTextField.getText())
                        && !Validator.validDouble(timeTextField.getText())
                        ){
                    // Only name field is valid
                    Database.updateName(
                            nameTextField.getText(),
                            rubiksTable.getSelectedRow() + 1
                    );
                } else if (
                        Validator.validString(timeTextField.getText())
                        && Validator.validDouble(timeTextField.getText())
                        && !Validator.validString(nameTextField.getText())
                        ){
                    // Only time field is valid
                    Database.updateTime(
                            Double.parseDouble(timeTextField.getText()),
                            rubiksTable.getSelectedRow() + 1
                    );
                } else if (
                        Validator.validString(nameTextField.getText())
                        && Validator.validString(timeTextField.getText())
                        && Validator.validDouble(timeTextField.getText())
                        ){
                    // Both time and text fields are valid
                    Database.updateEntry(
                            nameTextField.getText(),
                            Double.parseDouble(timeTextField.getText()),
                            rubiksTable.getSelectedRow() + 1
                    );
                } else {
                    // Nothing is valid
                    showMessageDialog(RubiksGUI.this, "Invalid input!");
                }
                updateTable();
                clearTextFields();
            }
            // no else necessary; will only trigger when update button is clicked with nothing entered
        });

        //Allows deleting of selected item from table
        deleteButton.addActionListener(e -> {
            Database.deleteFrom(rubiksTable.getSelectedRow() + 1);
            updateTable();
            clearTextFields();
        });

        //Quit program
        quitButton.addActionListener(e -> Main.shutdown());
    }

    private void updateTable() {
        //Updates JTable to reflect new model
        RubiksModel updateTable = new RubiksModel(Database.selectAll());
        rubiksTable.setModel(updateTable);
        rubiksTable.repaint();
    }

    private void clearTextFields() {
        //Replace text in textfields with empty strings
        for (JTextField t:new JTextField[] {
                nameTextField, timeTextField
        })
            t.setText("");
    }

    private void initGUI(RubiksModel rm) {
        //Initialize GUI
        setContentPane(rootPanel);
        pack();
        addWindowListener(this);
        setVisible(true);
        setTitle("Rubik's Cube Record Manager");
        setSize(new Dimension(600, 400));
        rubiksTable.setModel(rm);
        rubiksTable.setGridColor(Color.BLACK);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    //Window events
    public void windowClosing(WindowEvent e) {
        Main.shutdown();
    }
    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
