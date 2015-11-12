package edu.towson.cis.cosc455.ksubed1.project1.implementation;

import edu.towson.cis.cosc455.ksubed1.project1.interfaces.CompilerException;

public class Markdown {
	/**
	 * 
	 * @param name of file to be analyzed
	 * @throws CompilerException
	 */
	public static void main(String[] args) throws CompilerException {
	
		String fileName = "/Users/swami/Desktop/COSC 455/Project 1 Test Cases/Test2.mkd";
		
		if(!fileName.substring(fileName.length() - 4).equals(".mkd")) {
			System.out.println("This is not a .mkd file, please try again");
		}
		
		else {
			MyLexicalAnalyzer lex = new MyLexicalAnalyzer (fileName);
			MySemanticAnalyzer sem = new MySemanticAnalyzer(fileName.substring(0, fileName.length() - 4));
			MySyntaxAnalyzer syn = new MySyntaxAnalyzer(lex,sem);
			
			syn.start();
		}
		
		
		
		
		
		
		
	}

}
