package ru.webgrozny.view;

import ru.webgrozny.model.Host;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    public GridBagLayout layout = new GridBagLayout();
    public JFrame frame;
    public JList<Host> hostList = new JList<>();
    public JButton wakeButton = new JButton("Wake");
    public JButton addButton = new JButton("Add");
    public JTextField name = new JTextField();
    public JTextField mac = new JTextField();
    public JButton save = new JButton("Save");
    public JScrollPane listPane;
    public JButton removeButton = new JButton("Remove");
    public JPanel intPanel = new JPanel();
    public JLabel infoLabel = new JLabel("");
    public MainWindow(){
        frame = new JFrame("Simple Wol Application");
        frame.setSize(470, 300);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLayout(layout);
        name.setEnabled(false);
        mac.setEnabled(false);
        save.setEnabled(false);
        wakeButton.setEnabled(false);
        
        JLabel listLabel = new JLabel("List");
        JLabel nameLabel = new JLabel("Name:");
        JLabel macLabel = new JLabel("MAC: ");

        listPane = new JScrollPane(hostList);
        listPane.setPreferredSize(new Dimension(200, 200));
        
        GridBagConstraints constraints;
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        layout.setConstraints(listLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(nameLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(name, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(macLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(mac, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(save, constraints);

        constraints = new GridBagConstraints();
        constraints.gridy = 4;
        constraints.gridx = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        layout.setConstraints(intPanel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 4;
        layout.setConstraints(listPane, constraints);

        constraints = new GridBagConstraints();
        constraints.gridy = 5;
        constraints.gridx = 0;
        constraints.weightx = 0.1;
        layout.setConstraints(addButton, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridy = 5;
        constraints.gridx = 1;
        constraints.weightx = 0.1;
        layout.setConstraints(removeButton, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridy = 5;
        constraints.gridx = 2;
        constraints.gridwidth = 2;
        constraints.weightx = 0.8;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(wakeButton, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        layout.setConstraints(infoLabel, constraints);
        
        frame.add(listLabel);
        frame.add(nameLabel);
        frame.add(name);
        frame.add(macLabel);
        frame.add(mac);
        frame.add(save);
        frame.add(intPanel);
        frame.add(listPane);
        frame.add(addButton);
        frame.add(removeButton);
        frame.add(wakeButton);
        frame.add(infoLabel);
        
        frame.setVisible(true);
    }
    public static void setUIFont(javax.swing.plaf.FontUIResource f)
    {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while(keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof javax.swing.plaf.FontUIResource) UIManager.put(key, f);
        }
    }
}
