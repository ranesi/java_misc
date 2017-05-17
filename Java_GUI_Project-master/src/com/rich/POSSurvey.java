package com.rich;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by richa on 3/22/2017.
 */
public class POSSurvey extends JFrame {
    private JPanel rootPanel;
    private JCheckBox cbCat;
    private JCheckBox bowBowCheckBox;
    private JCheckBox blubBlubCheckBox;
    private JLabel lblExplanation;
    private JLabel lblResults;
    private JButton btnQuit;

    private boolean meow;
    private boolean bow;
    private boolean blub;

    public POSSurvey(){
        setContentPane(rootPanel);
        pack();
        setSize(new Dimension(350,200));
        setTitle("Pet Survy");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quit = JOptionPane.showConfirmDialog(POSSurvey.this, "LEAVE?",
                        "Qiut", JOptionPane.OK_CANCEL_OPTION);
                if (quit == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

        cbCat.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                meow = cbCat.isSelected();
                updateResults();
            }
        });

        bowBowCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                bow = bowBowCheckBox.isSelected();
                updateResults();
            }
        });

        blubBlubCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                blub = blubBlubCheckBox.isSelected();
                updateResults();
            }
        });
    }

    private void updateResults(){
        String havesBow = bow ? "one bow" : "no bow";
        String havesMeow = meow ? "one meow" : "no meow";
        String havesBlub = blub ? "one blub" : "no blub";

        String results = "You have " + havesMeow + " and " + havesBow + " and " + havesBlub;
        lblResults.setText(results);

    }
}
