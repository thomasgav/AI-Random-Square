/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package random_square;

import java.util.Comparator;

/**
 *This is the comparator for the Tree Nodes, when using the A Star Algorithm
 * @author Thomas
 */
public class AStarScoreComparator implements Comparator<Node>{
    /**
     * A* score comparator
     */
    public int compare(Node n1, Node n2) { 
                if (n1.a_star_score > n2.a_star_score){ 
                    return 1; 
                }
                else if (n1.a_star_score < n2.a_star_score){
                     return -1; 
                } 
                   
                 return 0; 
    }
}
