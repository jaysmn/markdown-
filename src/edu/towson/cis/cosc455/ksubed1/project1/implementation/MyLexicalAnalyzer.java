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
	
	public String currentToken;
	
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
	
	@Override
	public void getNextToken() {
		if(endOfLine) {
			nextLine();
		}
		
		if(isSpace(lineS.charAt(charNum))) {
			skipSpace();
			
		}
		
		while(!isSpace(lineS.charAt(charNum))) {
			getCharacter();
			addCharacter();
			
		}
		
		if(!lookupToken()) {
			System.out.println("Error at " + lineN);
			System.exit(0);
		}
		

	}

	@Override
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

	@Override
	public void addCharacter() {
		currentToken += nextChar;

	}

	@Override
	public boolean isSpace(char c) {
		
		return c == ' ';
	}
	
	public void skipSpace() {
		while(isSpace(lineS.charAt(charNum))) {
			charNum++;
		}
	}

	@Override
	public boolean lookupToken() {
		boolean b = false;
		
		char c = currentToken.charAt(0);
		
		if(c == Tokens.HASH || c == Tokens.VAR) {
			b = Tokens.search(currentToken);
		}
		
		return b;
		
	
	}

}
