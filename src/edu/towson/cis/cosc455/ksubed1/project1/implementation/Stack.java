package edu.towson.cis.cosc455.ksubed1.project1.implementation;

public class Stack<T> implements Stack<T> {

    private int total;

    private Node first;

    private class Node {
        private T ele;
        private Node next;
    }

    public Stack() { }

    public Stack<T> push(T ele)
    {
        Node current = first;
        first = new Node();
        first.ele = ele;
        first.next = current;
        total++;
        return this;
    }

    public T pop()
    {
        if (first == null) new java.util.NoSuchElementException();
        T ele = first.ele;
        first = first.next;
        total--;
        return ele;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        Node tmp = first;
        while (tmp != null) {
            sb.append(tmp.ele).append(", ");
            tmp = tmp.next;
        }
        return sb.toString();
    }

}