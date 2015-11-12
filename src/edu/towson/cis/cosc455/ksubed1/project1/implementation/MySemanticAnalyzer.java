package edu.towson.cis.cosc455.ksubed1.project1.implementation;

import java.awt.Desktop;
import java.io.*;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Queue;

public class MySemanticAnalyzer {
	/*
	 * need queue or linked list or list
	 * and stack
	 */
	
	public Queue<String> myQueue = new LinkedList<String>(); // new queue to store the translated input
	
	
	/*
	 * to add myQueue.add(Stuff to add);
	 * 
	 * when reading the queue
	 * while(myQueue.peek() != null) {
	 *  
	 * 		String s = myQueue.remove();
	 * }
	 */
	
	public String fileName;
	
	public MySemanticAnalyzer(String fileName) {
		this.fileName = fileName;
	}
	
	public void markdownB() {
		// add this to queue "<HTML>\n";
		myQueue.add("<html>\n");
		
		
	}
	
	public void markdownE() {
		// add this to queue "<\HTML>\n";
		myQueue.add("</HTML>");
		createFile();
	}
		
	public void createFile() {
		
		fileName.concat(".html");
		
		Writer writer = null;
		
		

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(fileName), "utf-8"));
		    while(myQueue.peek() != null) {
		   	  
		   	  		String s = myQueue.remove();
		   	  		writer.write(s);
		   	  }
		    
		    openHTMLFileInBrowswer(fileName);
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
	
	public void title() {
		
		
	}
	
	public void head(String s, boolean b) {
		//String head = "<" + text + ">";
		
		//myQueue.add(head);
		
		String h;
		if(b) {
			h = "<head> <title> " + s + " </title> </head>";
		}
		else {
			h = "<head> " + s + " </head>";
		}
		
		myQueue.add(h);
		
		
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
	
	/**
	 * simply adds the audio tag to the program queue
	 * @param String-link for the audio file
	 * @return void
	 */
	public void audio(String text) {
		String audio = "<audio controls> \n" +
                       "<source src=" + text +">\n" +
                       "</audio>\n";
		
		myQueue.add(audio);
	}
	
	/**
	 * simply adds the video tag to the program queue
	 * @param String text -> the link for the video file
	 * @return void
	 */
	public void video(String text) {
		String video = "<iframe src=" + text + "/>";
		
		myQueue.add(video);
	}
	
	
	/**
	 * simply adds the <br>  tag to the program queue
	 * @param void
	 * @return void
	 */
	public void newline() {
		String n = "<br> ";
		myQueue.add(n);
				
	}
	
	/**
	 * creates the link and adds it to the queue
	 * @param String l => name of the link
	 * @param String value => actual link it self 
	 * @return void
	 */
	public void link(String l, String value) {
		String link = "<a href=" + value + ">" + l + " </a>";
		myQueue.add(link);
	}
	
	
	/**
	 * adds bolded text
	 * @param String s => text to be bolded
	 * @return void
	 */
	public void bold(String s) {
		String b = "<b> " + s + "</b>";
		myQueue.add(b);
	}
	
	/**
	 * adds italics text
	 * @param String s => text to be in italics
	 * @return void
	 */
	public void italics(String s) {
		String b = "<i> " + s + "</i>";
		myQueue.add(b);
	}
	
	/**
	 * adds a paragraph
	 * @param String s => what will be in the paragraph
	 * @return void
	 */
	public void paragraph(String s) {
		String para = "<p>" + s + "</p>";
		myQueue.add(para);
	}
	
	/**
	 * adds list items
	 * @param String s => list item
	 * @return void
	 */
	public void listItem(String s) {
		String l = "<li>" + s + "</li>";
		myQueue.add(l);
	}
	
	public void innerText(String s) {
		myQueue.add(s);
	}
	
	public void addParaB() {
		myQueue.add("<p>");
	}
	
	public void addParaE() {
		myQueue.add("</p>");
	}
	
	
}


