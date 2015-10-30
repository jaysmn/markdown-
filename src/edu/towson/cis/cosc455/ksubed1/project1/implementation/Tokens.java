package edu.towson.cis.cosc455.ksubed1.project1.implementation;

public class Tokens {
	public static final String DOCB = "#BEGIN";
	public static final String DOCE = "#END";
	public static final String VARB = "$DEF";
	public static final String VARE = "$END";
	public static final String VARU = "$USE";
	
	
	public static final String BOLD = "**";
	
	public static final char HASH = '#';
	public static final char HEAD = '^';
	public static final char TITLEB = '<';
	public static final char TITLEE = '>';
	public static final char PARAB = '{';
	public static final char PARAE = '}';
	public static final char EQSIGN = '=';
	public static final char ITALIC = '*';
	public static final char NEWLINE = '~';
	public static final char VAR = '$';
	public static final char LINKB = '[';
	public static final char LINKE = ']';
	public static final char AUDIO = '@';
	public static final char VIDEO = '%';
	public static final char ADDB = '(';
	public static final char ADDE = ')';
	public static final char LISTB = '+';
	public static final char LISTE = ';';
	
	
	public static final char[] symbols = {HASH, HEAD, TITLEB, TITLEE, PARAB, PARAE, EQSIGN, 
			ITALIC, NEWLINE, VAR, LINKB, LINKE, AUDIO, VIDEO, ADDB, ADDE, LISTB, LISTE};
	
	public static final String[] tokens = {DOCB, DOCE, VARB, VARE, VARU};
	
	public static boolean search(String s) {
		boolean b = false;
		for(int i = 0; i < tokens.length; i++) {
			if(s.equalsIgnoreCase(tokens[i])) {
				b = true;
				break;
			}
		}
		return b;
	}
	
	public static boolean search(char c) {
		boolean b = false;
		for(int i = 0; i < symbols.length; i++) {
			if(c == symbols[i]) {
				b = true;
				break;
			}
		}
		return b;
	}

}
