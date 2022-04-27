/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package random_square;

/**
 *The purpose of this class is to save all the data for each junction in the square.
 * @author Thomas
 */
public class Junction {
    private int number; // The number of each junction. Starts with 1, all the way to junction number n*n. Junction number 1 is the upper left junction of the square.
    private int x;
    private int y; //The (x,y) coordinates of the junction. They will be used as an alternative way of keeping track of the junctions. The upper left junction is the (1,1).
    private int heuristic; //The heuristic value of the current junction.


    /**
     * Initiallization with 1 paramater
     * @param n (int) number: The number of the junction.
     */
    public Junction(int n){
        number=n;
    }

    /**
     * Setting the x coordinate.
     */
    public void setx(int xx){
        x=xx;
    }

    /**
     * Setting the y coordinate.
     */
    public void sety(int yy){
        y=yy;
    }


    /**
     * Setting the heuristic value.
     */
    public boolean setheuristic(int i, int j, int h){
        if (x==i && y==j){ // This check is happening in case the coordinates inserted were wrong.
            heuristic=h;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Return the x coordinate.
     */
    public int returnx(){
        return x;
    }

    /**
     * Return the y coordinate.
     */
    public int returny(){
        return y;
    }

    /**
     * Return the heuristic value.
     */
    public int returnheuristic(){
        return heuristic;
    }

    /**
     * Return the heuristic value of specific junction.
     * @param num (int) number: The number of the junction.
     */
    public int getheuristic(int num){
        if (number==num){
            return heuristic;
        }else{
            return -1;
        }
    }

    /**
     * Return the number of the junction
     */
    public int returnNumber(){
        return number;
    }

    /**
     * Return the number of the Junction, using the (x,y) coordinates.
     * @param i (int) The x coordinate
     * @param j (int) The y coordinate
     */
    public int returnNumberwithaxis(int i,int j){
        if (x==i && y==j){
            return number;
        }else{
            return 0;
        }
    }

    /**
     * Printing information for the current junction.
     */
    public String printJunctionInfo(){
        return "Junction: "+number+", Coordinates: ("+x+","+y+"), Distance from target (heuristic value): "+heuristic+".\n";
    }
    
}
