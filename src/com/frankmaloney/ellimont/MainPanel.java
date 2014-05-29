package com.frankmaloney.ellimont;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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
		openButton = new JButton("Select a file");
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
				try {
					readAndReplace(f);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else{
				System.out.println("File selection cancelled");
			}
		}
		
	}
	
public void readAndReplace(File f) throws IOException{
	PrintWriter writer = new PrintWriter("newSynonymFile.txt", "UTF-8");


	BufferedReader br = null;
	try {
		br = new BufferedReader(new FileReader(f));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	while (br.ready()) {
	  String s = br.readLine();
	  String words[] = s.split(" ");
	  String master = "";
	  
	  	for(int i = 0; i < words.length; i++){
	  		if(words[i].contains("*")){
	  			words[i] = words[i].replace("*", "");
	  			words[i] = words[i].replace(".", "");
	  			System.out.println(words[i]);
	  			words[i] = Synonym.getSynonym(words[i]);
	  			master += words[i] + " ";
	  		}else{
	  			master += words[i] + " ";
	  		}
	  	}
	  	
	  	writer.println(master);
	  
	}
	br.close();
	writer.close();
}
	
}
