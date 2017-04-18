package simplewolapp.controller;

import sun.awt.datatransfer.ClipboardTransferable;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by r0ck3r on 18.04.17.
 */
public class ContextMenu {
    JPopupMenu popupMenu = new JPopupMenu(){
        @Override
        public void show(Component invoker, int x, int y) {
            super.show(invoker, x, y);
            try{
                textField = (JTextField) invoker;
                selectedText = textField.getSelectedText();
                clipboardText = getClipBoardText();
                if( selectedText == null || selectedText.length() == 0 ){
                    copy.setEnabled(false);
                    cut.setEnabled(false);
                }else{
                    copy.setEnabled(true);
                    cut.setEnabled(true);
                }
                paste.setEnabled(clipboardText.length() != 0);
            }catch (ClassCastException e){

            }
        }
    };
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem clear = new JMenuItem("Clear");
    String selectedText;
    String clipboardText;
    JTextField textField;
    
    ContextMenu(){
        init();
    }
    
    private void init(){
        popupMenu.add(copy);
        popupMenu.add(cut);
        popupMenu.add(paste);
        popupMenu.add(clear);
        
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                copy();
            }
        });
        
        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                copy();
                cut();
            }
        });
        
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clear();
            }
        });
        
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textField.setText(new StringBuilder(textField.getText()).insert(textField.getCaretPosition(), clipboardText).toString());
            }
        });
    }
    
    private void copy(){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(selectedText), null);
    }
    
    private void cut(){
        String str = textField.getText();
        int start = textField.getSelectionStart();
        int end = textField.getSelectionEnd();
        textField.setText(str.substring(0, start) + str.substring(end));
    }
    
    private void clear(){
        textField.setText("");
    }
    
    private String getClipBoardText(){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String line = null;
        try {
            String str = (String) clipboard.getData(DataFlavor.stringFlavor);
            line = str;
        } catch (UnsupportedFlavorException e) {
            
        } catch (IOException e) {
            
        }
        return line == null ? new String() : line;
    }
    
    public static void setToTextField(ContextMenu menu, JTextField t){
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.isPopupTrigger()){
                    menu.popupMenu.show(t, e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.isPopupTrigger()){
                    menu.popupMenu.show(t, e.getX(), e.getY());
                }
            }
        });
    }
}
