package edu.towson.cis.cosc455.ksubed1.project1.implementation;

public class Markdown {

	public static void main(String[] args) {
		/*
		 * create lex
		 * create syntax
		 * create sem
		 * 
		 * MySemanticAnalyzer sem = new MySemanticAnalyzer(filename.substring(0, filename.length() - 4))
		 * 
		 * myLexicalAnalyzer lex = new MyLexicalAnalyzer("file name")
		 * 
		 * mysyntax syn = new syntax(lex, sem)
		 */
		String fileName = "";
		MyLexicalAnalyzer lex = new MyLexicalAnalyzer ("file name");
		MySemanticAnalyzer sem = new MySemanticAnalyzer(fileName.substring(0, fileName.length() - 4));
		MySyntaxAnalyzer syn = new MySyntaxAnalyzer(lex,sem);
		
	}

}
