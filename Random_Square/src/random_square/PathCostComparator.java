/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package random_square;

import java.util.Comparator;

/**
 * *This is the comparator for the Tree Nodes, when running the Algorithms
 * @author Thomas
 */
public class PathCostComparator implements Comparator<Node> {
    /**
     * Path cost comparator
     */
    public int compare(Node n1, Node n2) {
                if (n1.path_cost > n2.path_cost){ 
                    return 1; 
                }
                else if (n1.path_cost < n2.path_cost){
                     return -1; 
                } 
                   
                 return 0; 
    } 
}
