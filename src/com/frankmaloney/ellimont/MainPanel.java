package com.frankmaloney.ellimont;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class MainPanel extends JPanel implements ActionListener{
	JButton openButton;
	JFileChooser fileChooser;
	public MainPanel(){
		this.setLayout(new BoxLayout(this, 3));
		this.add(new JLabel("Ellimont v. 0.001"));
		openButton = new JButton("Select a folder");
		openButton.addActionListener(this);
		this.add(openButton);
		fileChooser = new JFileChooser();	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == openButton){
			int returnVal = fileChooser.showOpenDialog(MainPanel.this);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File f = fileChooser.getSelectedFile();
				//mainFunctionality(f);
			}else{
				System.out.println("File selection cancelled");
			}
		}
		
	}
	
	
}
