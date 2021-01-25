package simplewolapp.view;

import simplewolapp.controller.Controller;
import simplewolapp.model.Host;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private GridBagLayout layout = new GridBagLayout();
    public JFrame frame;
    public JList<Host> hostList = new JList<>();
    public JButton wakeButton = new JButton("Wake");
    public JButton addButton = new JButton("Add");
    public JTextField name = new JTextField();
    public JTextField mac = new JTextField();
    public JTextField ip = new JTextField();
    public JButton save = new JButton("Save");
    public JScrollPane listPane;
    public JButton removeButton = new JButton("Remove");
    public JPanel intPanel = new JPanel();
    public JLabel infoLabel = new JLabel(" ");
    public MainWindow(){
        frame = new JFrame("Simple Wol Application");
        frame.setSize(Controller.getScalingFactor(520), Controller.getScalingFactor(300));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buildUI();
        frame.setVisible(true);
    }
    
    public void buildUI(){
        GridBagConstraints constraints;
        
        frame.setLayout(layout);

        JPanel leftPane = new JPanel();
        leftPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "List of devices"));
        GridBagLayout leftLayout = new GridBagLayout();
        leftPane.setLayout(leftLayout);
               
        JPanel rightPane = new JPanel();
        rightPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Settings"));
        GridBagLayout rightLayout = new GridBagLayout();
        rightPane.setLayout(rightLayout);

        listPane = new JScrollPane(hostList);
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.weighty = 0.8;
        constraints.fill = GridBagConstraints.BOTH;
        leftLayout.setConstraints(listPane, constraints);
        leftPane.add(listPane);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.8;
        JPanel j = new JPanel();
        leftLayout.setConstraints(j, constraints);
        leftPane.add(j);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 0.1;
        leftLayout.setConstraints(addButton, constraints);
        leftPane.add(addButton);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.weightx = 0.1;
        constraints.anchor = GridBagConstraints.EAST;
        leftLayout.setConstraints(removeButton, constraints);
        leftPane.add(removeButton);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.3;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(leftPane, constraints);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.7;
        constraints.weighty = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(rightPane, constraints);
        
        JPanel rightSubPanel = new JPanel();
        GridBagLayout rightSubLayout = new GridBagLayout();
        rightSubPanel.setLayout(rightSubLayout);
        
        JLabel nameLabel = new JLabel("Name: ");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.1;
        constraints.anchor = GridBagConstraints.WEST;
        rightSubLayout.setConstraints(nameLabel, constraints);
        rightSubPanel.add(nameLabel);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.9;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        rightSubLayout.setConstraints(name, constraints);
        name.setEnabled(false);
        rightSubPanel.add(name);
        
        JLabel macLabel = new JLabel("MAC: ");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        rightSubLayout.setConstraints(macLabel, constraints);
        rightSubPanel.add(macLabel);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        rightSubLayout.setConstraints(mac, constraints);
        mac.setEnabled(false);
        rightSubPanel.add(mac);

        JLabel ipLabel = new JLabel("IP: ");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        rightSubLayout.setConstraints(ipLabel, constraints);
        rightSubPanel.add(ipLabel);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        rightSubLayout.setConstraints(ip, constraints);
        ip.setEnabled(false);
        rightSubPanel.add(ip);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        rightSubLayout.setConstraints(save, constraints);
        save.setEnabled(false);
        rightSubPanel.add(save);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;
        rightLayout.setConstraints(rightSubPanel, constraints);
        rightPane.add(rightSubPanel);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        rightLayout.setConstraints(intPanel, constraints);
        rightPane.add(intPanel);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        rightLayout.setConstraints(infoLabel, constraints);
        rightPane.add(infoLabel);
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        rightLayout.setConstraints(wakeButton, constraints);
        wakeButton.setEnabled(false);
        rightPane.add(wakeButton);
        
        frame.add(leftPane);
        frame.add(rightPane);
    }
}
