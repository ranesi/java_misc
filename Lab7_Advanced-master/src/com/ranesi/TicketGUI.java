package com.ranesi;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.*;

public class TicketGUI extends JFrame {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // FIELDS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private JTextField descriptionTextBox;
    private JTextField reporterTextBox;
    private JPanel rootPanel;
    private JList ticketList;
    private JComboBox priorityComboBox;
    private JButton addButton;
    private JButton resolveButton;
    private JButton quitButton;
    private JButton descriptionButton;
    private JTextArea resolutionTextArea;

    private DefaultListModel<Ticket> ticketModel;
    private LinkedList<Ticket> ticketQueue;
    private LinkedList<ResolvedTicket> resolvedTickets;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // EVENTS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void addButtonClicked() {
        // Adds ticket object to ticket model
        if (validOpenTicket()) {
            Ticket ticket = new Ticket(
                    Integer.parseInt(priorityComboBox.getSelectedItem().toString()),
                    descriptionTextBox.getText(),
                    reporterTextBox.getText(),
                    new Date() // current date/time
            );
            ticketQueue.add(ticket);
            ticketModel.add(0, ticket);
        } else {
            JOptionPane.showMessageDialog(TicketGUI.this, "Invalid entry!");
        }
        initializeTextFields();
    }

    private void resolvedButtonClicked() {
        // Deletes ticket from ticket model; adds to resolved tickets
        if (validResolvedTicket()) {
            int index = ticketList.getSelectedIndex();
            Ticket resolved = ticketModel.getElementAt(index);
            ticketModel.remove(index);
            resolvedTickets.add(new ResolvedTicket(
                    resolved.getTicketID(),
                    resolved.getPriority(),
                    resolved.getDescription(),
                    resolved.getReporter(),
                    resolved.getDateReported(),
                    new Date(), //Set to current time
                    resolutionTextArea.getText()
            ));
            deleteTicketFromQueue(resolved.getTicketID());
        } else {
            JOptionPane.showMessageDialog(TicketGUI.this, "Invalid resolution!");
        }
        initializeTextFields();
    }

    private void quitButtonClicked() {
        // Perform cleanup, dispose of GUI
        FileIO.writeOpenTickets(ticketQueue);
        FileIO.writeResolvedTickets(resolvedTickets);
        this.dispose();
    }

    private void descriptionButtonClicked() {
        // Create message dialog including information not in GUI list
        Ticket t = (Ticket)ticketList.getSelectedValue();
        JOptionPane.showMessageDialog(TicketGUI.this,
                "DESCRIPTION: " + t.getDescription() + ", REPORTER: " + t.getReporter());
        initializeTextFields();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONTROL CONFIGURATION METHODS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public TicketGUI() { initializeGUI(); }

    private void initializeGUI() {
        // Initial GUI configuration
        setContentPane(rootPanel);
        pack();
        setTitle("Ticket Manager");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 400));
        populatePriorityComboBox();

        ///////////////////////////////////////////////////////////

        ticketQueue = readTicketsFromFile();
        resolvedTickets = new LinkedList<>();
        ticketModel = new DefaultListModel<Ticket>();
        addToTicketModel(ticketQueue);
        ticketList.setModel(ticketModel);

        ///////////////////////////////////////////////////////////

        addListeners();
        setVisible(true);
    }

    private void initializeTextFields() {
        // Remove all input from text boxes
        for (JTextField t : new JTextField[] {descriptionTextBox, reporterTextBox}) t.setText("");
        resolutionTextArea.setText("");
    }

    private void addListeners() {
        addButton.addActionListener(e -> addButtonClicked());
        resolveButton.addActionListener(e -> resolvedButtonClicked());
        descriptionButton.addActionListener(e -> descriptionButtonClicked());
        quitButton.addActionListener(e -> quitButtonClicked());
    }

    private void populatePriorityComboBox() {
        // Add entries to combo box; 5 .. 1
        for (int x = 5; x > 0; x--)
            priorityComboBox.addItem("" + x);
    }

    private void addToTicketModel(LinkedList<Ticket> tickets) { for (Ticket t : tickets) ticketModel.addElement(t); }

    private LinkedList<Ticket> readTicketsFromFile() {
        // retrieve tickets from open_tickets.txt
        try {
            LinkedList<Ticket> ret = FileIO.readOpenTickets();
            return ret;
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(TicketGUI.this, "Unable to parse ticket file!");
        }
        return new LinkedList<>();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VALIDATION METHODS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean validOpenTicket() {
        // Validate entry for new ticket
        if (descriptionTextBox.getText().length() > 0 &&
                reporterTextBox.getText().length() > 0 &&
                priorityComboBox.getSelectedIndex() != -1) {
            return true;
        }
        return false;
    }

    private boolean validResolvedTicket() {
        // Validate entry for resolved ticket
        if (resolutionTextArea.getText().length() > 0) {
            return true;
        }
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MISCELLANEOUS METHODS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void deleteTicketFromQueue(int id) {
        for (Ticket t : ticketQueue) {
            if (t.getTicketID() == id) {
                ticketQueue.remove(t);
                break;
            }
        }
    }
}
