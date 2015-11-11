package edu.towson.cis.cosc455.ksubed1.project1.implementation;

public class Stack {
	private int count = 0;
	private Node top;
	private Node node;
	
	
	
	public String search(String x) {
		String value = "";
		Node n = node;
		
		if(n.getInfo()[0].equals(x)) {
			value = n.getInfo()[1];
		}
		else {
			while(n.getNext() != null) {
				n = n.getNext();
				if(n.getInfo()[0].equals(x)) {
					value = n.getInfo()[1];
				}
			}
		}
		
		return value;
	}
	public void add(String[] add) {
       
        if(count == 0) {
            node = new Node(add, null);
            top = node;
            
        }
        else {
            top.setNext(new Node(add, null));
            top = top.getNext();
            
        }
        
        count++;
        
    }
	public void push(String[] x) {
		
		if(count == 0) {
			node = new Node(x, null);
			top = node;
		}
		
		else {
			top.setNext(new Node(x, null));
		}
		
		count++;
	}
	
	public void pop() {
		
		if(count == 0) {
			node = null;
			top = null;
			return;
			
			}
		Node n = node;
		int x = 0;
		
		while(x < count - 2) {
			n = n.getNext();
			x++;
		}
		
		Node t = node;
		int z = 0;
		
		while(z < count - 3) {
			t = t.getNext();
			z++;
		}
		
		t.setNext(new Node(n.getInfo(), null));
		
		
		
		count--;
	}
		
	public boolean contains(String x) {
		Node z = node;
		boolean r = false;
		if(z == null) {
			return false;
		}
		else {
			while(z.getNext() != null) {
				z = z.getNext();
				
				if(z.getInfo()[0].equals(x)) {
					r  =  true;
				}
			}
		}
		
		return r;
		
		
		
	}
	
}