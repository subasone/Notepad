/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.notepad.appgui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author onesoft
 */
public class AppGui extends JFrame implements ActionListener{

    private TextArea textArea = new TextArea("",0,0, TextArea.SCROLLBARS_VERTICAL_ONLY);
    private MenuBar menuBar = new MenuBar();
    private Menu file = new Menu();
    
    
    private MenuItem openFile = new MenuItem();
    private MenuItem saveFile = new MenuItem();
    private MenuItem close = new MenuItem();
    
    public AppGui(){
        this.setSize(500, 300);
        this.setTitle("NotePad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.textArea.setFont(new Font("Century Gothic", Font.BOLD, 12));
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(textArea);
        
        this.setMenuBar(this.menuBar);
        this.menuBar.add(this.file);
        
        this.file.setLabel("File");
        
        this.openFile.setLabel("Open");
        this.openFile.addActionListener(this);
        this.openFile.setShortcut(new MenuShortcut(KeyEvent.VK_0, false));
        this.file.add(this.openFile);
        
        this.saveFile.setLabel("Save");
        this.saveFile.addActionListener(this);
        this.saveFile.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
        this.file.add(this.saveFile);
        
        this.close.setLabel("Close");
        this.close.addActionListener(this);
        this.close.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
        this.file.add(this.close);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource() == this.close)
            this.dispose();
        
        else if(e.getSource() == this.openFile){
            JFileChooser open = new JFileChooser();
            int option = open.showOpenDialog(this);
            if(option == JFileChooser.APPROVE_OPTION ){
                String line ="";
                StringBuilder content = new StringBuilder();
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(open.getSelectedFile().getPath()));
                    while(( line= reader.readLine()) != null)
                        content.append(line + "\r\n");
                } catch(IOException ioe){
                    System.out.println(ioe.getMessage());
                }
                    
            }
        } else if(e.getSource() == this.saveFile){
            JFileChooser save = new JFileChooser();
            int option = save.showSaveDialog(this);
            if(option == JFileChooser.APPROVE_OPTION){
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
                    writer.write(this.textArea.getText());
                    writer.close();
                } catch(IOException ioe){
                    System.out.println(ioe.getMessage());
                }
            }
        }
    }
    
}
