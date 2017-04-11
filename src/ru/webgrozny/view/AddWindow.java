package ru.webgrozny.view;

import javax.swing.*;
import java.awt.*;

public class AddWindow {
    public JTextField nameField = new JTextField();
    public JTextField macField = new JTextField();
    public JButton addButton = new JButton("Add");
    public JLabel msg;
    public JFrame frame;
    public GridBagLayout layout;
    public AddWindow(){
        frame = new JFrame("Add host");
        frame.setSize(200, 150);
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JLabel labelName = new JLabel("Name: ");
        JLabel labelMac = new JLabel("Mac");
        msg = new JLabel("");
        
        layout = new GridBagLayout();
        GridBagConstraints constraints;
        frame.setLayout(layout);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.1;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(labelName, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.9;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(nameField, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(labelMac, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(macField, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        layout.setConstraints(addButton, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        layout.setConstraints(msg, constraints);
        
        frame.add(labelName);
        frame.add(nameField);
        frame.add(labelMac);
        frame.add(macField);
        frame.add(addButton);
        frame.add(msg);
        
        frame.setVisible(true);
    }
}
