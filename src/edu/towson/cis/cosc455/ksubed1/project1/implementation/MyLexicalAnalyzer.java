package edu.towson.cis.cosc455.ksubed1.project1.implementation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.towson.cis.cosc455.ksubed1.project1.interfaces.LexicalAnalyzer;

public class MyLexicalAnalyzer implements LexicalAnalyzer {
	public BufferedReader br;
	public int lineN = 1;
	public String lineS;
	
	public int charNum = 0;
	
	public String currentToken = "";
	
	public boolean endOfFile = false;
	public boolean endOfLine = true;
	
	public char nextChar = 'a';
	
	public MyLexicalAnalyzer(String fileName){
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error wrong file ");
			
			
		}
		
		
		
	}
	/**
	 * next line
	 * @param void
	 * @return void
	 */
	
	public void nextLine() {
		if(endOfFile) {
			return;
		}
		
		try {
			lineS = br.readLine();
			lineN ++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			endOfFile = true;
		}
	}
	
	/**
	 * get next token ignoring spaces and lines until end
	 * @param void
	 * @return void
	 */
	public void getNextToken() {
		currentToken = " ";
		if(endOfLine) {
			nextLine();
		}
		
		if(charNum < lineS.length()) {
			if(isSpace(lineS.charAt(charNum))) {
				skipSpace();
				
			}
			
			while(!(isSpace(lineS.charAt(charNum)))) {
				getCharacter();
				addCharacter();
				
			}
			
			if(!lookupToken()) {
				System.out.println("Lexical Error at " + lineN);
				System.exit(0);
			}
			
			System.out.println(currentToken + " current token");
		
		}
		
		else {
			endOfLine = true;
			getNextToken();
		}
		
		

	}

	 /**
     * Gets the character
     *@param void
	 *@return void
     * 
     */
	public void getCharacter() {
		if(charNum < lineS.length()) {
			nextChar = lineS.charAt(charNum);
			charNum += 1;
		}
		else {
			nextChar = '\n';
			endOfLine = true;
		}

	}

	/**
	 * adds character
	 * @param void
	 * @return void
	 */
	public void addCharacter() {
		currentToken += nextChar;

	}

	/**
	 * adds html begin tag to the program queue
	 * @param c character
	 * @return true if space
	 */
	public boolean isSpace(char c) {
		
		return c == ' ';
	}
	/**
     * skip space
     *@param void
     * @return void
     */
	public void skipSpace() {
		while(isSpace(lineS.charAt(charNum))) {
			charNum++;
		}
	}

	 /**
     * looks up token
     *@param void
     * @return true 
     */
	public boolean lookupToken() {
		boolean b = true;
		
		char c = currentToken.charAt(0);
		
		if(c == Tokens.HASH || c == Tokens.VAR) {
			b = Tokens.search(currentToken);
		}
		
		return b;
		
	
	}

}
