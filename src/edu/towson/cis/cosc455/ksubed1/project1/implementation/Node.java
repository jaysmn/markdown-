package edu.towson.cis.cosc455.ksubed1.project1.implementation;

public class Node {
	
	
	public String[] info;
	public Node next;
	
	public Node(String[] info, Node next) {
		this.info = info;
		this.next = next;
	}
	
	public String[] getInfo() {
		return info;
	}
	
	public void setNext(Node n) {
		next = n;
	}
	
	public Node getNext() {
		return next;
	}
}
