package edu.towson.cis.cosc455.ksubed1.project1.implementation;

import edu.towson.cis.cosc455.ksubed1.project1.interfaces.CompilerException;
import edu.towson.cis.cosc455.ksubed1.project1.interfaces.SyntaxAnalyzer;

public class MySyntaxAnalyzer implements SyntaxAnalyzer {
// get token from lexical
	// check which token it is
	// and then call the right method
	// markdown head= > title body while loop markdown
	
	
	MySemanticAnalyzer semantics = new MySemanticAnalyzer();
	@Override
	public void markdown() throws CompilerException {
		// TODO Auto-generated method stub
		/*
		 * if linenum == 1
		 * #begin
		 * semantics.mardownBegin();
		 * else
		 * #END
		 * semantics.mardownEnd();
		 */
		
	}

	@Override
	public void head() throws CompilerException {
		// TODO Auto-generated method stub
		// to check the chars  token.charAt(0) == Tokens.HEAD;
		/* ^    < Ttile!!!>   ^
		 * ^ text ^
		 * if the first charcter is ^
		 * then the next symbol should <
		 * some text
		 * next symbol >
		 * 
		 * next symbol is ^
		 * 
		 * 
		 * emantics.head() 
		 */
		
	}

	@Override
	public void title() throws CompilerException {
		
		/*
		 * check for < some text >
		 * s
		 */
		// TODO Auto-generated method stub
		
	}

	@Override
	public void body() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paragraph() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void innerText() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void variableDefine() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void variableUse() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bold() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void italics() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listitem() throws CompilerException {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void video() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newline() throws CompilerException {
		// TODO Auto-generated method stub
		
	}

	

}
