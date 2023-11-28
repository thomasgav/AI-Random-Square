/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package random_square;

/**
 *  The purpose of this class is to save all the data for each node. All the nodes will create the search tree.
 *
 * @author Thomas
 */

public class Node {
    private int state;
    private Node parentNode;
    //private action????
    int path_cost;
    private int depth;
    int a_star_score;

    /**
     * Initiallization with 4 paramaters
     * @param s (int) State: The number of the node that is checked
     * @param pn (Node) Parent Node: The link between the current node and its parent
     * @param pc (int) Path Cost: The cost of the path from the root node up to the current one
     * @param d (int) Depth: The depth of the tree up until the current node
     */
    public Node(int s,Node pn,int pc, int d){
        state=s;
        pn=parentNode;
        path_cost=pc;
        depth=d;
    }

    /**
     * Initiallization with no paramaters
     */
    public Node(){
    }

    /**
     * Initiallization with 3 paramaters
     * @param s (int) State: The number of the node that is checked
     * @param pc (int) Path Cost: The cost of the path from the root node up to the current one
     * @param d (int) Depth: The depth of the tree up until the current node
     */
    public Node(int s,int pc, int d){
        state=s;
        path_cost=pc;
        depth=d;
    }

    /**
     * Initiallization with 1 paramater
     * @param s (int) State: The number of the node that is checked
     */
    public Node(int s){
        state=s;
    }

    /**
     * Set the parent node of the current one.
     * @param p (Node) Parent Node: The link between the current node and its parent
     */
    public void setparent(Node p){
        parentNode=p;
    }

    /**
     * Set the path cost up until the current node
     * @param pc (int) Path Cost: The cost of the path from the root node up to the current one
     */
    public void setpathcost(int pc){
        path_cost=pc;
    }

    /**
     * Set the depth of the tree up until the current node
     * @param d (int) Depth: The depth of the tree up until the current node
     */
    public void setdepth(int d){
        depth=d;
    }

    /**
     * Set the A* score
     * @param a (int) setAstarscore: A* score of the node
     */
    public void setAstarscore(int a){
        a_star_score=a;
    }

    /**
     * Returns parent node
     * @return The parent node of the current one
     */
    public Node getparent(){
        return parentNode;
    }

    /**
     * Returns the state of the node/the number of the junction
     * @return The state of the node
     */
    public int getState(){
        return state;
    }

    /**
     * Returns path cost
     * @return The path cost of the node
     */
    public int getpathcost(){
        return path_cost;
    }

    /**
     * Returns depth of tree
     * @return The depth of tree up to the current node
     */
    public int getdepth(){
        return depth;
    }

    /**
     * Returns the A* score
     * @return The A* score of the node
     */
    public int getAstarscore(){
        return a_star_score;
    }
}
