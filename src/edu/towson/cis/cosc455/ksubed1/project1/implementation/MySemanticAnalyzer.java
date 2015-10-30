package edu.towson.cis.cosc455.ksubed1.project1.implementation;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class MySemanticAnalyzer {
	/*
	 * need queue or linked list or list
	 * and stack
	 */
	
	public void mardownB() {
		// add this to queue "<HTML>\n";
	}
	
	public void mardownE() {
		// add this to queue "<\HTML>\n";
		
		
	}
	
	public void title() {
		
	}
	
	public void head() {
		
	}
	
	public void openHTMLFileInBrowswer(String htmlFileStr){
		File file= new File(htmlFileStr.trim());
		if(!file.exists()){
			System.err.println("File "+ htmlFileStr +" does not exist.");
			return;
		}
		try{
			Desktop.getDesktop().browse(file.toURI());
		}
		catch(IOException ioe){
			System.err.println("Failed to open file");
			ioe.printStackTrace();
		}
		return ;
	}
	
	public void createFile() {
		// create html file
				/*
				 * have for loop for list or queue
				 * then ad everythign to file
				 * then open file in browser
				 * then quit program;
				 */
	}
}
