package com.ranesi;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by RH Anesi on 3/23/2017.
 */
public class CCGUI extends JFrame{
    private JPanel rootPanel;
    private JTextPane txtInput;
    private JButton btnSubmit;
    private JLabel lblValid;
    private JComboBox ccTypeComboBox;
    private boolean resetMessageOnKeyPress = false;

    private final String VISA = "Visa";
    private final String MASTERCARD = "Mastercard";
    private final String AMEX = "American Express";
    private final String DISCOVER = "Discover";

    public CCGUI(){
        setContentPane(rootPanel);
        pack();
        setTitle("Visa Validator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(new Dimension(400,200));

        populateComboBox();

        btnSubmit.setText("Click to validate " + (String)ccTypeComboBox.getSelectedItem());

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cc = txtInput.getText();
                String cardType = ccTypeComboBox.getSelectedItem().toString();
                boolean valid = false;

                if (cardType.equals(VISA)) {
                    valid = isValidVisa(cc);
                } else if (cardType.equals(MASTERCARD)) {
                    // Mastercard
                } else if (cardType.equals(AMEX)) {
                    // American Express
                } else if (cardType.equals(DISCOVER)) {
                    valid = false; //haaa discover cards are bad
                }

                if (valid) {
                    lblValid.setText("Card is VALID");
                } else {
                    lblValid.setText("Card not VALID");
                }

                resetMessageOnKeyPress = true;

            }
        });
        txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (resetMessageOnKeyPress) {
                    lblValid.setText("Valid or NOT??");
                    resetMessageOnKeyPress = false;
                }
            }
        });
        ccTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardType = (String)ccTypeComboBox.getSelectedItem();
                btnSubmit.setText("Click to validate " + cardType);
            }
        });
    }

    private boolean isValidVisa(String cc){
        if (cc.length() >= 13 && cc.length() <= 16 ) {
            int[] a = new int[cc.length()];
            for (int x = 0; x < cc.length(); x++) {
                a[x] = Integer.parseInt(Character.toString(cc.charAt(x)));
            }
            for (int x = 0; x < a.length; x++)
                if (x % 2 == 0) a[x] *= 2;
            String temp = "";
            for (int x = 0; x < a.length; x++)
                temp += Integer.toString(a[x]);
            int sum = 0;
            for (int x = 0; x < temp.length(); x++)
                sum += Integer.parseInt(Character.toString(temp.charAt(x)));
            if (sum % 10 == 0)
                return true;
            else
                return false;
        }
        return false;
    }

    private void populateComboBox(){
        ccTypeComboBox.addItem(VISA);
        ccTypeComboBox.addItem(MASTERCARD);
        ccTypeComboBox.addItem(AMEX);
        ccTypeComboBox.addItem(DISCOVER);
    }
}
