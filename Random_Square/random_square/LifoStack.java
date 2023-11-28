/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package random_square;

/**
 *This class implements a LIFO stack with the nodes of the Tree that are being searched
 * @author Thomas
 */

import java.util.LinkedList;

public class LifoStack {
    private LinkedList<Node> list = new LinkedList<Node>();
 
    public void push(Node n) {
        list.addFirst(n);
    }
 
    public Node pop() {
        return list.removeFirst();
    }
 
    public Node peek() {
        return list.getFirst();
    }
 
    public int size() {
        return list.size();
    }
 
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
