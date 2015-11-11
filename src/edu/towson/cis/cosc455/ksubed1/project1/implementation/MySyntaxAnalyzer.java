package edu.towson.cis.cosc455.ksubed1.project1.implementation;

import java.util.*;

import edu.towson.cis.cosc455.ksubed1.project1.interfaces.CompilerException;
import edu.towson.cis.cosc455.ksubed1.project1.interfaces.SyntaxAnalyzer;

public class MySyntaxAnalyzer implements SyntaxAnalyzer {
// get token from lexical
	// check which token it is
	// and then call the right method
	// markdown head= > title body while loop markdown
	
	String fileName = "";
	MyLexicalAnalyzer lex = new MyLexicalAnalyzer ("file name");
	MySemanticAnalyzer sem = new MySemanticAnalyzer(fileName.substring(0, fileName.length() - 4));
	MySyntaxAnalyzer syn = new MySyntaxAnalyzer(lex,sem);

	public Stack myStack = new Stack();// Stack for variables
	
	
	
	public MySemanticAnalyzer semantics;
	public MySyntaxAnalyzer(MyLexicalAnalyzer lex, MySemanticAnalyzer sem) {
		// TODO Auto-generated construct
		
		semantics = sem;
	}
	
	
	
	/**
	 * 
	 * exits the program
	 * @param void
	 * @return void
	 * @throws void
	 * 
	 */
	public void exit() {
		System.exit(0);
	}

	@Override
	public void markdown() throws CompilerException {
		
		/*
		 * if linenum == 1
		 * #begin
		 * semantics.mardownBegin();
		 * else
		 * #END
		 * semantics.mardownEnd();
		 */
		
		if(lex.currentToken.length() < 1 || lex.currentToken.isEmpty() || lex.currentToken.equals("")) {
			return;
		}
			char c = lex.currentToken.charAt(0);
			if (c == Tokens.ADDE || c == Tokens.LINKE || c == Tokens.PARAE || c == Tokens.TITLEE) {
				throw new CompilerException("Syntax error at line num- " + lex.lineN + " and character num- " + lex.currentPosition);
			
			}	
	}

	@Override
	public void head() throws CompilerException {
		boolean x = false;
		String h = "";
		String t = "";
		lex.getNextToken();
		
		while(lex.currentToken.charAt(0) != Tokens.HEAD) {
			
			if(lex.currentToken.charAt(0) == Tokens.TITLEB) {
				t = title();
			}
			
			else {
				h += lex.currentToken + " ";
			}
		}
		
		if(x) {
			sem.head(t, true);
		}
		else {
			sem.head(h, false);
		}
		
		
		
	}

	@Override
	public String title() throws CompilerException {
		
		String t = "";
		
		lex.getNextToken();
		while(lex.currentToken.charAt(0) != (Tokens.TITLEE)) {
			t += lex.currentToken;
		}
		
		return t;
	}

	@Override
	public void body() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paragraph() throws CompilerException {
		int count = 0;
		
		
		sem.addParaB();
		
		lex.getNextToken();
		
		
		while (!lex.currentToken.equals(Tokens.PARAE)) {
			if(lex.currentToken.equals(Tokens.PARAB)) {
				throw new CompilerException("Syntax error expected " + Tokens.PARAE + " got " +
						lex.currentToken + " instead at line " +
						lex.lineN);
			}
			
			else if(lex.currentToken.length() < 1 || lex.currentToken.isEmpty() || lex.currentToken.equals("")) {
				lex.getNextToken();
				continue;
			}
			
			
			else if(lex.currentToken.charAt(0) == Tokens.VAR) {
				if(lex.currentToken.equals(Tokens.VARU)) {
					variableUse();
				}
				
				if(lex.currentToken.equals(Tokens.VARB)) {
					variableDefine();
					
					count++;
				}
				
				
			}
			
			else if (lex.currentToken.equals(Tokens.NEWLINE)) {
				newline();
			}
			
			else if(lex.currentToken.charAt(0) == Tokens.ITALIC) {
				if(lex.currentToken.equals(Tokens.BOLD)) {
					bold();
				}
				else {
					italics();
				}
			}
			
			else if(lex.currentToken.charAt(0) == Tokens.LISTB) {
				listitem();
			}
			
			else if(lex.currentToken.charAt(0) == Tokens.AUDIO) {
				audio();
			}
			
			else if(lex.currentToken.charAt(0) == Tokens.VIDEO) {
				video();
			}
			
			else if(lex.currentToken.charAt(0) == Tokens.LINKB) {
				link();
			}
		
			else if(lex.currentToken.equals("\n")) {
				sem.newline();
			}
			
			else {
				innerText();
			}
			
			lex.getNextToken();
		}
		
		for(int i = 0; i < count; i++) {
			myStack.pop();
		}
		
		sem.addParaE();

	}

	@Override
	public void innerText() throws CompilerException {

		
	}

	@Override
	public void variableDefine() throws CompilerException {
		String name;
		String value;
		
		lex.getNextToken();
		
		name = lex.currentToken;
		
		lex.getNextToken(); 
		
		if(lex.currentToken.charAt(0) != Tokens.EQSIGN) {
			throw new CompilerException("Syntax error expected " + Tokens.EQSIGN + " got " +
					lex.currentToken + " instead at line " +
					lex.lineN);
		}
		else {
			lex.getNextToken(); 
			
			
			value = lex.currentToken;
			String[] s = {name, value};
			myStack.add(s);
		}
		
		lex.getNextToken();
		
		if(!lex.currentToken.equalsIgnoreCase(Tokens.VARE)) {
			throw new CompilerException("Expected " + Tokens.VARE + " got " +
					lex.currentToken + " instead at line " +
					lex.lineN);
		}
		
	}

	@Override
	public void variableUse() throws CompilerException {
		lex.getNextToken(); // should be the variable name
		
		if(myStack.contains(lex.currentToken)) {
			String value = myStack.search(lex.currentToken);
			
			lex.getNextToken(); // should be the variable end token $end
			
			if(!(lex.currentToken.equals(Tokens.VARE))) {
				throw new CompilerException("Syntax error, expected " + Tokens.VARE + " got " +
						lex.currentToken + " instead at line " +
						lex.lineN);
			}
			
			else {
				sem.innerText(lex.currentToken);
			}
			
			
		}
		else {
			throw new CompilerException("Semantic error variable '" + lex.currentToken + "' is not defined at" +
							" line # " + lex.lineN);
		}
		


		
	}

	@Override
	public void bold() throws CompilerException {
		String bld = "";
		
		lex.getNextToken();
		
		bld.concat(lex.currentToken).concat(" ");
		
		while(!(lex.currentToken.equals(Tokens.BOLD))){
			lex.getNextToken();
		}
		
	sem.bold(bld);	
	}

	@Override
	public void italics() throws CompilerException {
		String itl = "";
        
        lex.getNextToken();
        
        itl.concat(lex.currentToken).concat(" ");
        
        while(!(lex.currentToken.equals(Tokens.ITALIC))) {
        	lex.getNextToken();
        	
        	if(!(lex.currentToken.equals(Tokens.ITALIC))) {
        		itl.concat(lex.currentToken).concat(" ");
        	}
        }
        
     sem.italics(itl);
     
	}

	@Override
	public void listitem() throws CompilerException {
		lex.getNextToken();
		
		String b = lex.currentToken.concat(" ");

		while (!lex.currentToken.equals(Tokens.LISTE)) {
			
			
			lex.getNextToken(); 
			if(lex.currentToken.length() < 1 || lex.currentToken.isEmpty() || lex.currentToken.equals("")) {
				lex.getNextToken();
				continue;
			}
			else if(lex.currentToken.charAt(0) == Tokens.VAR) {
				
				if(lex.currentToken.equalsIgnoreCase(Tokens.VARB)) {
					throw new CompilerException("Syntax error " + lex.currentToken + 
							" at line " +
							lex.lineN);
				}
				else {
					if(lex.currentToken.equalsIgnoreCase(Tokens.VARU)) {
						lex.getNextToken();
						if(myStack.contains(lex.currentToken)) {
							b.concat(myStack.search(lex.currentToken)).concat(" ");
						}
						else {
							throw new CompilerException("Semantic error variable '" + lex.currentToken + "' is not defined at" +
									" line num- " + lex.lineN);
						}
					}
					
					if(lex.currentToken.equalsIgnoreCase(Tokens.VARE)) {
						continue;
					}
				}
			lex.getNextToken();
			}
			else if(lex.currentToken.charAt(0) == Tokens.ADDE || 
					lex.currentToken.charAt(0) == Tokens.LINKE || 
					lex.currentToken.charAt(0) == Tokens.PARAE || 
					lex.currentToken.charAt(0) == Tokens.TITLEE ||
					lex.currentToken.charAt(0) == Tokens.LINKB || 
					lex.currentToken.charAt(0) == Tokens.PARAB ||
					lex.currentToken.charAt(0) == Tokens.ADDB || 
					lex.currentToken.charAt(0) == Tokens.TITLEB  )
				
				throw new CompilerException("Syntax error '" + 
						lex.currentToken + "' at line " +
						lex.lineN);
			else if(lex.currentToken.charAt(0) != Tokens.LISTE && lex.currentToken.charAt(0) != Tokens.VAR) {
				b.concat(lex.currentToken).concat(" ");
			}
			
			
		}
		
		sem.listItem(b);


		
	}

	@Override
	public void innerItem() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void link() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void audio() throws CompilerException {

		String curA = lex.currentToken;
		
		//to check there is ( and ends with ) after @
		if(curA.charAt(1) != Tokens.ADDB) { 
			throw new CompilerException("Expected token was " + Tokens.ADDB + " however, got " +
					lex.currentToken + ". -line " +
					lex.lineN);
		}
		
		else if(curA.charAt(curA.length() - 1 ) != Tokens.ADDE) { 
			throw new CompilerException("Expected token was " + Tokens.ADDE + " however, got " +
					lex.currentToken + ". -line " +
					lex.lineN);
		}
		
		else  {
			String audio = curA.substring(2, curA.length() - 1 );
			sem.audio(audio);
		}
		
	}

	@Override
	public void video() throws CompilerException {
String curV = lex.currentToken;

		//to check there is  ( and ends with ) after %
		if(curV.charAt(1) != Tokens.ADDB) { 
			throw new CompilerException("Expected token was " + Tokens.ADDB + " however, got " +
					lex.currentToken + ". -line " +
					lex.lineN);
		}
		
		else if(curV.charAt(curV.length() - 1 ) != Tokens.ADDE) { 
			throw new CompilerException("Expected token was " + Tokens.ADDE + " however, got " +
					lex.currentToken + ". -line " +
					lex.lineN);
		}
		
		else  {
			String video = curV.substring(2, curV.length() - 1 );
			sem.audio(video);
		}


		
	}

	@Override
	public void newline() throws CompilerException {

		/*
		 * ~
		 * <br>
		 */
		
		sem.newline();
		
	}

	

}
