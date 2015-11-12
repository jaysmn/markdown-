package edu.towson.cis.cosc455.ksubed1.project1.implementation;

import java.util.*;

import edu.towson.cis.cosc455.ksubed1.project1.interfaces.CompilerException;
import edu.towson.cis.cosc455.ksubed1.project1.interfaces.SyntaxAnalyzer;

public class MySyntaxAnalyzer implements SyntaxAnalyzer {
// get token from lexerical
	// check which token it is
	// and then call the right method
	// markdown head= > title body while loop markdown
	
	String fileName;
	
	MySemanticAnalyzer sem;
	

	public Stack myStack = new Stack();// Stack for variables
	public MyLexicalAnalyzer lexer;
	
	
	public MySemanticAnalyzer semantics;
	public MySyntaxAnalyzer(MyLexicalAnalyzer lexer, MySemanticAnalyzer sem) {
		// TODO Auto-generated construct
		this.lexer = lexer;
		semantics = sem;
	}
	/**
     * start
     *
     *@throws CompilerException
     * @return void
     */
	public void start() throws CompilerException {
		lexer.getNextToken();
		while(lexer.endOfFile == false) {
			//System.out.println(lexer.currentToken + "&&&");

			// this turned in to an infinite loop
			
			if(lexer.currentToken.length() < 1 || lexer.currentToken.isEmpty() || lexer.currentToken.equals(" ")) {
				
				continue;
			}
			else if (lexer.currentToken.charAt(0) == Tokens.ADDE || 
					lexer.currentToken.charAt(0) == Tokens.LINKE ||
							lexer.currentToken.charAt(0) == Tokens.PARAE || 
									lexer.currentToken.charAt(0) == Tokens.TITLEE) {
				throw new CompilerException("Syntax error at line # " + lexer.lineN + " & character # " + lexer.currentPosition);
			}
			
			
			else if(lexer.currentToken.charAt(0) == Tokens.VAR) {
				variables();
			}
			
			else if(lexer.currentToken.charAt(0) == Tokens.HASH) {
				markdown();
			}
			
			
			
			
			else if(lexer.currentToken.charAt(0) == Tokens.HEAD) {
				head();
			}
			
			
			
			else if(lexer.currentToken.charAt(0) == Tokens.ITALIC) {
				if(lexer.currentToken.equals(Tokens.BOLD)) {
					bold();
				}
				else {
					italics();
				}
			}
			
			
			else if(lexer.currentToken.charAt(0) == Tokens.LISTB) {
				listitem();
			}
			
			else if(lexer.currentToken.charAt(0) == Tokens.AUDIO) {
				audio();
			}
			
			else if(lexer.currentToken.charAt(0) == Tokens.VIDEO) {
				video();
			}
			
			else if(lexer.currentToken.charAt(0) == Tokens.PARAB) {
				paragraph();
			}
			
			else if(lexer.currentToken.charAt(0) == Tokens.NEWLINE) {
				newline();
			}
			
			else if(lexer.currentToken.charAt(0) == Tokens.LINKB) {
				link();
			}
			
			else if(lexer.currentToken.equals("\n")) {
				sem.newline();
			}
			
			else {
		
			}
			
		lexer.getNextToken();
			
		}
		//lexer.getNextToken();
	}
	

	/**
     * checks to see if define or use variable
     *@param void
     * @return true 
     */
	public void variables() throws CompilerException {
		if(lexer.currentToken.equalsIgnoreCase(Tokens.VARB)) {
			variableDefine();
		}
		else if (lexer.currentToken.equalsIgnoreCase(Tokens.VARU)) {
			variableUse();
		}
		else {
			throw new CompilerException("Syntax error at line #: " + lexer.lineN);
		}
	}
	
	
	public void markdown() throws CompilerException {
		
		/*
		  if linenum == 1
		 #begin
		  semantics.mardownBegin();
		  else
		  #END
		  semantics.mardownEnd();
		 */
		
		if(lexer.currentToken.equalsIgnoreCase(Tokens.DOCB)) {
			mkdBegin();
		}
		else {
			mkdEnd();
		}
		
		
	}
	public void mkdBegin() throws CompilerException {
		
		
		if(lexer.currentToken.equalsIgnoreCase(Tokens.DOCB)) {
			System.out.println("markdown begin");
			semantics.markdownB();
		}
		
		else {
			throw new CompilerException("error" + Tokens.DOCB + " got " +
										lexer.currentToken + " instead at line  " +
										lexer.lineN);
		}
		
		

	}
	
	public void mkdEnd() throws CompilerException {
		if(lexer.currentToken.equalsIgnoreCase(Tokens.DOCE)) {
			semantics.markdownE();
		}
		
		else {
			throw new CompilerException("error expected " + Tokens.DOCE + " got " +
										lexer.currentToken + " instead of a line- " +
										lexer.lineN);
		}

	}

	@Override
	public void head() throws CompilerException {
		boolean x = false;
		String h = "";
		String t = "";
		lexer.getNextToken();
		
		while(lexer.currentToken.charAt(0) != Tokens.HEAD) {
			
			if(lexer.currentToken.charAt(0) == Tokens.TITLEB) {
				t = title();
			}
			
			else {
				h += lexer.currentToken + " ";
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
		
		lexer.getNextToken();
		while(lexer.currentToken.charAt(0) != (Tokens.TITLEE)) {
			t += lexer.currentToken;
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
		
		lexer.getNextToken();
		
		
		while (!lexer.currentToken.equals(Tokens.PARAE)) {
			if(lexer.currentToken.equals(Tokens.PARAB)) {
				throw new CompilerException("Syntax error expected " + Tokens.PARAE + " got " +
						lexer.currentToken + " instead at line " +
						lexer.lineN);
			}
			
			else if(lexer.currentToken.length() < 1 || lexer.currentToken.isEmpty() || lexer.currentToken.equals("")) {
				lexer.getNextToken();
				continue;
			}
			
			
			else if(lexer.currentToken.charAt(0) == Tokens.VAR) {
				if(lexer.currentToken.equals(Tokens.VARU)) {
					variableUse();
				}
				
				if(lexer.currentToken.equals(Tokens.VARB)) {
					variableDefine();
					
					count++;
				}
				
				
			}
			
			else if (lexer.currentToken.equals(Tokens.NEWLINE)) {
				newline();
			}
			
			else if(lexer.currentToken.charAt(0) == Tokens.ITALIC) {
				if(lexer.currentToken.equals(Tokens.BOLD)) {
					bold();
				}
				else {
					italics();
				}
			}
			
			else if(lexer.currentToken.charAt(0) == Tokens.LISTB) {
				listitem();
			}
			
			else if(lexer.currentToken.charAt(0) == Tokens.AUDIO) {
				audio();
			}
			
			else if(lexer.currentToken.charAt(0) == Tokens.VIDEO) {
				video();
			}
			
			else if(lexer.currentToken.charAt(0) == Tokens.LINKB) {
				link();
			}
		
			else if(lexer.currentToken.equals("\n")) {
				sem.newline();
			}
			
			else {
				innerText();
			}
			
			lexer.getNextToken();
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
		
		lexer.getNextToken();
		
		name = lexer.currentToken;
		
		lexer.getNextToken(); 
		
		if(lexer.currentToken.charAt(0) != Tokens.EQSIGN) {
			throw new CompilerException("Syntax error expected " + Tokens.EQSIGN + " got " +
					lexer.currentToken + " instead at line " +
					lexer.lineN);
		}
		else {
			lexer.getNextToken(); 
			
			
			value = lexer.currentToken;
			String[] s = {name, value};
			myStack.add(s);
		}
		
		lexer.getNextToken();
		
		if(!lexer.currentToken.equalsIgnoreCase(Tokens.VARE)) {
			throw new CompilerException("Expected " + Tokens.VARE + " got " +
					lexer.currentToken + " instead at line " +
					lexer.lineN);
		}
		
	}

	@Override
	public void variableUse() throws CompilerException {
		lexer.getNextToken(); // should be the variable name
		
		if(myStack.contains(lexer.currentToken)) {
			String value = myStack.search(lexer.currentToken);
			
			lexer.getNextToken(); // should be the variable end token $end
			
			if(!(lexer.currentToken.equals(Tokens.VARE))) {
				throw new CompilerException("Syntax error, expected " + Tokens.VARE + " got " +
						lexer.currentToken + " instead at line " +
						lexer.lineN);
			}
			
			else {
				sem.innerText(lexer.currentToken);
			}
			
			
		}
		else {
			throw new CompilerException("Semantic error variable '" + lexer.currentToken + "' is not defined at" +
							" line # " + lexer.lineN);
		}
		


		
	}

	@Override
	public void bold() throws CompilerException {
		String bld = "";
		
		lexer.getNextToken();
		
		bld.concat(lexer.currentToken).concat(" ");
		
		while(!(lexer.currentToken.equals(Tokens.BOLD))){
			lexer.getNextToken();
		}
		
	sem.bold(bld);	
	}

	@Override
	public void italics() throws CompilerException {
		String itl = "";
        
        lexer.getNextToken();
        
        itl.concat(lexer.currentToken).concat(" ");
        
        while(!(lexer.currentToken.equals(Tokens.ITALIC))) {
        	lexer.getNextToken();
        	
        	if(!(lexer.currentToken.equals(Tokens.ITALIC))) {
        		itl.concat(lexer.currentToken).concat(" ");
        	}
        }
        
     sem.italics(itl);
     
	}

	@Override
	public void listitem() throws CompilerException {
		lexer.getNextToken();
		
		String b = lexer.currentToken.concat(" ");

		while (!lexer.currentToken.equals(Tokens.LISTE)) {
			
			
			lexer.getNextToken(); 
			if(lexer.currentToken.length() < 1 || lexer.currentToken.isEmpty() || lexer.currentToken.equals("")) {
				lexer.getNextToken();
				continue;
			}
			else if(lexer.currentToken.charAt(0) == Tokens.VAR) {
				
				if(lexer.currentToken.equalsIgnoreCase(Tokens.VARB)) {
					throw new CompilerException("Syntax error " + lexer.currentToken + 
							" at line " +
							lexer.lineN);
				}
				else {
					if(lexer.currentToken.equalsIgnoreCase(Tokens.VARU)) {
						lexer.getNextToken();
						if(myStack.contains(lexer.currentToken)) {
							b.concat(myStack.search(lexer.currentToken)).concat(" ");
						}
						else {
							throw new CompilerException("Semantic error variable '" + lexer.currentToken + "' is not defined at" +
									" line num- " + lexer.lineN);
						}
					}
					
					if(lexer.currentToken.equalsIgnoreCase(Tokens.VARE)) {
						continue;
					}
				}
			lexer.getNextToken();
			}
			else if(lexer.currentToken.charAt(0) == Tokens.ADDE || 
					lexer.currentToken.charAt(0) == Tokens.LINKE || 
					lexer.currentToken.charAt(0) == Tokens.PARAE || 
					lexer.currentToken.charAt(0) == Tokens.TITLEE ||
					lexer.currentToken.charAt(0) == Tokens.LINKB || 
					lexer.currentToken.charAt(0) == Tokens.PARAB ||
					lexer.currentToken.charAt(0) == Tokens.ADDB || 
					lexer.currentToken.charAt(0) == Tokens.TITLEB  )
				
				throw new CompilerException("Syntax error '" + 
						lexer.currentToken + "' at line " +
						lexer.lineN);
			else if(lexer.currentToken.charAt(0) != Tokens.LISTE && lexer.currentToken.charAt(0) != Tokens.VAR) {
				b.concat(lexer.currentToken).concat(" ");
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

		String curA = lexer.currentToken;
		
		//to check there is ( and ends with ) after @
		if(curA.charAt(1) != Tokens.ADDB) { 
			throw new CompilerException("Expected token was " + Tokens.ADDB + " however, got " +
					lexer.currentToken + ". -line " +
					lexer.lineN);
		}
		
		else if(curA.charAt(curA.length() - 1 ) != Tokens.ADDE) { 
			throw new CompilerException("Expected token was " + Tokens.ADDE + " however, got " +
					lexer.currentToken + ". -line " +
					lexer.lineN);
		}
		
		else  {
			String audio = curA.substring(2, curA.length() - 1 );
			sem.audio(audio);
		}
		
	}

	@Override
	public void video() throws CompilerException {
String curV = lexer.currentToken;

		//to check there is  ( and ends with ) after %
		if(curV.charAt(1) != Tokens.ADDB) { 
			throw new CompilerException("Expected token was " + Tokens.ADDB + " however, got " +
					lexer.currentToken + ". -line " +
					lexer.lineN);
		}
		
		else if(curV.charAt(curV.length() - 1 ) != Tokens.ADDE) { 
			throw new CompilerException("Expected token was " + Tokens.ADDE + " however, got " +
					lexer.currentToken + ". -line " +
					lexer.lineN);
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
