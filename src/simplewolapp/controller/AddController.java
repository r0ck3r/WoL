package simplewolapp.controller;

import simplewolapp.model.Host;
import simplewolapp.model.MacException;
import simplewolapp.view.AddWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by r0ck3r on 10.04.17.
 */
public class AddController {
    AddWindow addWindow;
    Controller mainController;
    public AddController(Controller mainController){
        this.mainController = mainController; 
        SwingUtilities.invokeLater( () -> {
            addWindow = new AddWindow();
            addListeners();
        });
    }
    
    private void addListeners(){
        addWindow.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Host host = new Host(addWindow.nameField.getText(), addWindow.macField.getText());
                    mainController.storage.addHost(host);
                    mainController.reloadStorageHosts();
                    addWindow.frame.setVisible(false);
                } catch (MacException e) {
                    addWindow.msg.setText("Mac format error!");
                }
            }
        });
    }
}