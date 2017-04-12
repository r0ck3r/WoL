package simplewolapp.controller;

import simplewolapp.model.Host;
import simplewolapp.model.MacException;
import simplewolapp.model.Storage;
import simplewolapp.model.WakeOnLan;
import simplewolapp.view.MainWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Set;

public class Controller {
    MainWindow mainWindow;
    WakeOnLan wakeOnLan = new WakeOnLan();
    JCheckBox[] checkBoxes;
    NetworkInterface[] interfaces;
    public Storage storage = new Storage();
    Host selected = null;
    int selectedIndex = 0;
    ListSelectionListener listSelectionListener;
    
    public Controller(){
        SwingUtilities.invokeLater(
                () -> {
                    setUIFont(new FontUIResource("tahoma",Font.PLAIN, 13));
                    mainWindow = new MainWindow();
                    reloadStorageHosts();
                    addInterfaces();
                    addListeners();
                });
        
    }
    
    private void addInterfaces(){
        interfaces = wakeOnLan.getInterfaces();
        checkBoxes = new JCheckBox[interfaces.length];
        mainWindow.intPanel.setLayout(new GridLayout(interfaces.length + 1, 1));
        mainWindow.intPanel.add(new JLabel("Send from: "));
        int i = 0;
        
        for(NetworkInterface networkInterface : interfaces){
            JCheckBox jCheckBox = new JCheckBox(networkInterface.getDisplayName(), true);
            checkBoxes[i++] = jCheckBox;
            mainWindow.intPanel.add(jCheckBox);
        }
        mainWindow.frame.repaint();
        mainWindow.frame.revalidate();
    }
    
    public void reloadStorageHosts(){
        mainWindow.hostList.removeListSelectionListener(listSelectionListener);
        if(storage.hosts != null && storage.hosts.length > 0) {
            mainWindow.hostList.setListData(storage.hosts);
        }
        mainWindow.hostList.addListSelectionListener(listSelectionListener);
    }
    
    private void addListeners(){
        listSelectionListener = listSelectionEvent -> {
            Host gotHost = mainWindow.hostList.getSelectedValue();
            if(gotHost != null) {
                selectedIndex = mainWindow.hostList.getSelectedIndex();
                if (gotHost.mac.length != 0) {
                    selected = gotHost;
                } else {
                    selected = null;
                }
            }
            updateHostComponents();
        };
        mainWindow.hostList.addListSelectionListener(listSelectionListener);
        mainWindow.hostList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    wake();
                }
            }
        });
        mainWindow.wakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                wake();
            }
        });
        Controller controller = this;
        mainWindow.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddController(controller);
            }
        });
        
        mainWindow.save.addActionListener( (actionEvent) -> {
            try {
                Host host = new Host(mainWindow.name.getText(), mainWindow.mac.getText());
                storage.editHost(host, selectedIndex);
                selected = null;
                selectedIndex = 0;
                updateHostComponents();
                reloadStorageHosts();
            } catch (MacException e) {
                
            }
        } );
        
        mainWindow.removeButton.addActionListener( (actionEvent -> {
            if(selectedIndex != 0) {
                storage.remove(selectedIndex);
                selected = null;
                selectedIndex = 0;
                updateHostComponents();
                reloadStorageHosts();
            }
        }) );
    }
    
    private void updateHostComponents(){
        boolean act = selected != null;
        mainWindow.wakeButton.setEnabled(act);
        mainWindow.save.setEnabled(act);
        if(act){
            mainWindow.name.setText(selected.name);
            mainWindow.mac.setText(selected.getHumanMac());
            mainWindow.name.setEnabled(true);
            mainWindow.mac.setEnabled(true);
        }else{
            mainWindow.name.setText("");
            mainWindow.mac.setText("");
            mainWindow.name.setEnabled(false);
            mainWindow.mac.setEnabled(false);
        }
    }
    
    private void wake(){
        if(selected != null) {
            ArrayList<NetworkInterface> interfaceArrayList = new ArrayList<>();

            for (int i = 0; i < interfaces.length; i++) {
                if (checkBoxes[i].isSelected()) {
                    interfaceArrayList.add(interfaces[i]);
                }
            }
            NetworkInterface[] selectedInterfaces = new NetworkInterface[interfaceArrayList.size()];
            interfaceArrayList.toArray(selectedInterfaces);
            wakeOnLan.wake(selected, selectedInterfaces);
            mainWindow.infoLabel.setText("Host " + selected.name + " started");
        
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    mainWindow.infoLabel.setText(" ");
                } catch (InterruptedException e) { }
            }).start();
        }
    }
    
    public static double scaleFactor;
    
    public static int getScalingFactor(int e){
        return (int) ((double) e * scaleFactor);
    }
    
    public static void setUIFont(javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while(keys.hasMoreElements()){
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof javax.swing.plaf.FontUIResource) UIManager.put(key, f);
        }
        int pixelPerInch=java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        int origSize = f.getSize();
        scaleFactor = (double) (pixelPerInch) / 96.;
        int newSize = (int) (scaleFactor * (double) origSize);
        setDefaultSize(newSize);
    }
    public static void setDefaultSize(int size) {
        Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
        Object[] keys = keySet.toArray(new Object[keySet.size()]);
        for (Object key : keys) {
            if (key != null && key.toString().toLowerCase().contains("font")) {
                Font font = UIManager.getDefaults().getFont(key);
                if (font != null) {
                    font = font.deriveFont((float)size);
                    UIManager.put(key, font);
                }
            }
        }
    }
}
