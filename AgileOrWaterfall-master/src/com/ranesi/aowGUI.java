package com.ranesi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

/**
 * Created by RH Anesi on 3/23/2017.
 */
public class aowGUI extends JFrame {
    private JPanel rootPanel;
    private JCheckBox yeaCheckBox5;
    private JCheckBox yeaCheckBox4;
    private JCheckBox yeaCheckBox3;
    private JCheckBox yeaCheckBox2;
    private JCheckBox yeaCheckBox1;
    private JTextPane txtInput;
    private JButton btnSubmit;
    private JLabel lblResult;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JLabel l6;
    private JCheckBox yeaCheckBox6;

    aowGUI(){
        setContentPane(rootPanel);
        pack();
        setTitle("Agile or CASCADA");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(500,300));
        setVisible(true);
        setLabelText();


        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int score = 0;

                for (JCheckBox cb : new JCheckBox[]
                        { yeaCheckBox1, yeaCheckBox2, yeaCheckBox3, yeaCheckBox4, yeaCheckBox5, yeaCheckBox6 }){
                    if (cb.isSelected())
                        score--;
                    else
                        score++;
                }
                setLblResult(score);
            }
        });
    }

    private void setLblResult(int score){
        String output = "THE BEST CHOICE FOR "
                + txtInput.getText() + " IS";
        if (score < 0)
            output += " WATERFALL";
        else if (score > 0)
            output += " AGILE";
        else
            output += " EITHER";
        lblResult.setText(output);
    }

    private void setLabelText(){
        String[] questions = {
                "Lots of programmers??",
                "Are firm deadlines required?",
                "Are the programmers experienced?",
                "Are there stringent QC requirements?",
                "Is early integration a biggy?",
                "Need early versions available?"
        };
        int index = 0;
        for (JLabel l : new JLabel[] { l1, l2, l3, l4, l5, l6 }){
            l.setText(questions[index]);
            index++;
        }
    }

}
